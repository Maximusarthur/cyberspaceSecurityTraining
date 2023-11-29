package Elm;

import java.sql.*;

public class CusUserDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            " DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;

    public boolean addUser(Customer user) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Customer(Cname, Cpwd, Ctel, Caddress) VALUES (?, ?, ?,?)"
            );
            statement.setString(1, user.getCname());
            statement.setString(2, user.getCpwd());
            statement.setString(3, user.getCtel());
            statement.setString(4, user.getCaddress());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int Cid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Customer WHERE Cid = ?"
            );
            statement.setInt(1, Cid);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateUser(Customer user) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Customer SET Cname = ?, Cpwd = ?, Ctel = ? WHERE Cid = ?"
            );
            statement.setString(1, user.getCname());
            statement.setString(2, user.getCpwd());
            statement.setString(3, user.getCtel());
            statement.setInt(4, user.getCid());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer loginUser(String Cname, String Cpwd) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Customer WHERE Cname = ? AND Cpwd = ?"
            );
            statement.setString(1, Cname);
            statement.setString(2, Cpwd);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCid(resultSet.getInt("Cid"));
                customer.setCname(resultSet.getString("Cname"));
                customer.setCaddress(resultSet.getString("Caddress"));
                customer.setCtel(resultSet.getString("Ctel"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


