package Dao;

import Elm.BusiUser;

import java.sql.*;

public class BusinessUserDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            " DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;

    public boolean addUser(BusiUser user) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO BusiUser(Bname, Bpwd, Btel, Baddress) VALUES (?, ?, ?,?)"
            );
            statement.setString(1, user.getBname());
            statement.setString(2, user.getBpwd());
            statement.setString(3, user.getBtel());
            statement.setString(4, user.getBaddress());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int Bid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM BusiUser WHERE Bid = ?"
            );
            statement.setInt(1, Bid);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateUser(BusiUser user) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE BusiUser SET Bname = ?, Bpwd = ?, Btel = ? WHERE Bid = ?"
            );
            statement.setString(1, user.getBname());
            statement.setString(2, user.getBpwd());
            statement.setString(3, user.getBtel());
            statement.setInt(4, user.getBid());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BusiUser loginUser(String Bname, String Bpwd) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM BusiUser WHERE Bname = ? AND Bpwd = ?"
            );
            statement.setString(1, Bname);
            statement.setString(2, Bpwd);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BusiUser Buser = new BusiUser();
                Buser.setBid(resultSet.getInt("Bid"));
                Buser.setBname(resultSet.getString("Bname"));
                Buser.setBaddress(resultSet.getString("Baddress"));
                Buser.setBtel(resultSet.getString("Btel"));
                return Buser;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


