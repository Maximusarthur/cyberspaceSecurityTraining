package Dao;

import Elm.Goods;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            "DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;

    public boolean addGoods(Goods goods, int Bid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Goods(Bid, Gname, GPrice, GDetail) VALUES (?, ?, ?,?)"
            );
            statement.setInt(1, Bid);
            statement.setString(2, goods.getGName());
            statement.setFloat(3, goods.getGPrice());
            statement.setString(4, goods.getGDetail());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteGoods(int Gid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Goods WHERE Gid = ?"
            );
            statement.setInt(1, Gid);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateGoods(Goods goods) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Goods SET Gname = ?, GPrice = ?, GDetail = ? WHERE Gid = ?"
            );
            statement.setString(1, goods.getGName());
            statement.setFloat(2, goods.getGPrice());
            statement.setString(3, goods.getGDetail());
            statement.setInt(4, goods.getGId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Goods> checkGoods(int Bid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Goods WHERE Bid = ? "
            );
            statement.setInt(1, Bid);
            ResultSet resultSet = statement.executeQuery();
            List<Goods> goodsList = new ArrayList<>();
            while (resultSet.next()) {
                Goods goods = new Goods();
                goods.setGId(resultSet.getInt("GId"));
                goods.setGName(resultSet.getString("GName"));
                goods.setGPrice(resultSet.getFloat("GPrice"));
                goods.setGDetail(resultSet.getString("GDetail"));
                goodsList.add(goods);
            }
            return goodsList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


