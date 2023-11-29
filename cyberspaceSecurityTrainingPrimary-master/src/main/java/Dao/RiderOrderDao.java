package Dao;

import Elm.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RiderOrderDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            " DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;

    public List<Orders> checkOrder(Rider rider) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Orders WHERE Rid = ? "
            );
            statement.setInt(1, rider.getRid());
            ResultSet resultSet = statement.executeQuery();
            List<Orders> orderList = new ArrayList<>();
            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("OrderId"));
                order.setOrderDate(resultSet.getString("OrderDate"));
                order.setCname(resultSet.getString("Cname"));
                order.setCtel(resultSet.getString("Ctel"));
                order.setCaddress(resultSet.getString("Caddress"));
                order.setBid(resultSet.getInt("Bid"));
                order.setBaddress(resultSet.getString("Baddress"));
                order.setOrderSum(resultSet.getFloat("OrderSum"));
                order.setRid(resultSet.getInt("Rid"));

                orderList.add(order);
            }
            return orderList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Orders> checkOrderNullRid() {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Orders WHERE Rid IS null "
            );
            ResultSet resultSet = statement.executeQuery();
            List<Orders> orderList = new ArrayList<>();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("OrderId"));
                order.setOrderDate(resultSet.getString("OrderDate"));
                order.setCname(resultSet.getString("Cname"));
                order.setCtel(resultSet.getString("Ctel"));
                order.setCaddress(resultSet.getString("Caddress"));
                order.setBid(resultSet.getInt("Bid"));
                order.setBaddress(resultSet.getString("Baddress"));
                order.setOrderSum(resultSet.getFloat("OrderSum"));
                order.setRid(resultSet.getInt("Rid"));

                orderList.add(order);
            }
            return orderList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean getOrderForRider(int OrderId, int Rid){
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Orders SET Rid = ? WHERE OrderId = ?"
            );
            statement.setInt(1, Rid);
            statement.setInt(2, OrderId);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
