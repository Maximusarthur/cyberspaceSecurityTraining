package Dao;

import Elm.Rider;

import java.sql.*;

public class RiderUserDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            " DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;

    public boolean addUser(Rider user) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Rider(Rname, Rpwd, Rtel) VALUES (?, ?, ?)"
            );
            statement.setString(1, user.getRname());
            statement.setString(2, user.getRpwd());
            statement.setString(3, user.getRtel());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int Rid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Rider WHERE Rid = ?"
            );
            statement.setInt(1, Rid);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateUser(Rider user) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Rider SET Rname = ?, Rpwd = ?, Rtel = ? WHERE Rid = ?"
            );
            statement.setString(1, user.getRname());
            statement.setString(2, user.getRpwd());
            statement.setString(3, user.getRtel());
            statement.setInt(4, user.getRid());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Rider loginUser(String Rname, String Rpwd) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Rider WHERE Rname = ? AND Rpwd = ?"
            );
            statement.setString(1, Rname);
            statement.setString(2, Rpwd);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Rider Ruser = new Rider();
                Ruser.setRid(resultSet.getInt("Rid"));
                Ruser.setRname(resultSet.getString("Rname"));
                Ruser.setRtel(resultSet.getString("Rtel"));
                return Ruser;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


