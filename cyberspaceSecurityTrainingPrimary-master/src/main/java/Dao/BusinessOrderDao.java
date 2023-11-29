package Dao;

import Elm.OrderItems;
import Elm.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessOrderDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            " DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;
    public List<Orders> checkOrder(int Bid) {
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Orders WHERE Bid = ? "
            );
            statement.setInt(1, Bid);
            ResultSet resultSet = statement.executeQuery();
            List<Orders> orderList = new ArrayList<>();
            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("OrderId"));
                order.setOrderDate(resultSet.getString("OrderDate"));
                order.setCname(resultSet.getString("Cname"));
                order.setCtel(resultSet.getString("Ctel"));
                order.setCaddress(resultSet.getString("Caddress"));
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
    public List<OrderItems> checkOrderItem (int OrderId){
        try{
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT ItemName, ItemCount, ItemPrice FROM OrderItems WHERE OrderId = ?"
            );
            selectStatement.setInt(1, OrderId);
            ResultSet resultSet = selectStatement.executeQuery();
            List<OrderItems> itemList = new ArrayList<>();
            while (resultSet.next()) {
                OrderItems item = new OrderItems();
                item.setItemName(resultSet.getString("ItemName"));
                item.setItemCount(resultSet.getInt("ItemCount"));
                item.setItemPrice(resultSet.getFloat("ItemPrice"));

                itemList.add(item);
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
