package Dao;

import Elm.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao {
    String dbURL = "jdbc:sqlserver://SD-20220201KPRR:1433;"+
            " DatabaseName=ElmDatabase;"+
            "integratedSecurity=false;"+
            "encrypt=true;"+
            "trustServerCertificate=true;";
    private Connection connection;
    public boolean addOrder(Orders orders) {//添加订单
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Orders(OrderDate, OrderSum, Cname, Ctel, Caddress, Bid, Baddress) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, orders.getOrderDate());
            statement.setFloat(2, orders.getOrderSum());
            statement.setString(3, orders.getCname());
            statement.setString(4, orders.getCtel());
            statement.setString(5, orders.getCaddress());
            statement.setInt(6, orders.getBid());
            statement.setString(7, orders.getBaddress());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteOrder(int OrderId) {//删除订单
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Orders WHERE OrderId = ?"
            );
            statement.setInt(1, OrderId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateOrder(Orders orders) {//更新订单
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Orders SET OrderSum = ?, Cname = ?, Ctel = ?, Caddress = ?, Bid = ?, Baddress = ? WHERE orders = ?"
            );
            statement.setFloat(2, orders.getOrderSum());
            statement.setString(3, orders.getCname());
            statement.setString(4, orders.getCtel());
            statement.setString(4, orders.getCaddress());
            statement.setInt(4, orders.getBid());
            statement.setString(4, orders.getBaddress());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Orders> checkOrder(Customer customer) {//查询用户的订单
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Orders WHERE Cname = ? AND Ctel = ?"
            );
            statement.setString(1, customer.getCname());
            statement.setString(2, customer.getCtel());
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
                order.setBid(resultSet.getInt("Bid"));
                order.setBaddress(resultSet.getString("Baddress"));
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
    public Orders checkOrderForItem(String dateString) {//查询用户的订单
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Orders WHERE OrderDate = ?"
            );
            statement.setString(1, dateString);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("OrderId"));
                order.setOrderDate(resultSet.getString("OrderDate"));
                order.setCname(resultSet.getString("Cname"));
                order.setCtel(resultSet.getString("Ctel"));
                order.setCaddress(resultSet.getString("Caddress"));
                order.setOrderSum(resultSet.getFloat("OrderSum"));
                order.setBid(resultSet.getInt("Bid"));
                order.setBaddress(resultSet.getString("Baddress"));
                order.setRid(resultSet.getInt("Rid"));
                return order;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderItems> checkOrderItem (int OrderId){//查询订单包含的单品
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
    public List<BusiUser> checkBusiness (){//查询所有商家
        try{
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM BusiUser"
            );
            ResultSet resultSet = selectStatement.executeQuery();
            List<BusiUser> BusiList = new ArrayList<>();
            while (resultSet.next()) {
                BusiUser Buser = new BusiUser();
                Buser.setBid(resultSet.getInt("Bid"));
                Buser.setBname(resultSet.getString("Bname"));
                Buser.setBtel(resultSet.getString("Btel"));
                Buser.setBaddress(resultSet.getString("Baddress"));
                BusiList.add(Buser);
            }
            return BusiList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Goods> checkBusinessItem (int Bid){//查询某一商家的商品
        try{
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT GId, GName, GPrice, GDetail FROM Goods WHERE Bid = ?"
            );
            selectStatement.setInt(1, Bid);
            ResultSet resultSet = selectStatement.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Customer checkCustomer(int Cid){
        try {
            connection = DriverManager.getConnection(dbURL, "Elm", "");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT Cname, Ctel, Caddress FROM Customer WHERE Cid = ?"
            );
            selectStatement.setInt(1, Cid);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCname(resultSet.getString("Cname"));
                customer.setCtel(resultSet.getString("Ctel"));
                customer.setCaddress(resultSet.getString("Caddress"));
                return customer;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BusiUser checkBusinessAddress(int Bid){
        try {
            connection = DriverManager.getConnection(dbURL, "Elm", "");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT Baddress FROM BusiUser WHERE Bid = ?"
            );
            selectStatement.setInt(1, Bid);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                BusiUser Buser = new BusiUser();
                Buser.setBaddress(resultSet.getString("Baddress"));
                return Buser;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Goods getGoods(int Gid){
        try {
            connection = DriverManager.getConnection(dbURL, "Elm", "");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT GName, GPrice FROM Goods WHERE Gid = ?"
            );
            selectStatement.setInt(1, Gid);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                Goods goods = new Goods();
                goods.setGName(resultSet.getString("GName"));
                goods.setGPrice(resultSet.getFloat("GPrice"));

                return goods;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public OrderItems addItem(OrderItems items){
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO OrderItems(ItemName, ItemPrice, ItemCount, OrderId) " +
                            "VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, items.getItemName());
            statement.setFloat(2, items.getItemPrice());
            statement.setInt(3, items.getItemCount());
            statement.setInt(4, items.getOrderId());
            int rowsInserted = statement.executeUpdate();
            return items;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<OrderItems> getItemPrice(int OrderId){
        try{
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT ItemPrice, ItemCount FROM OrderItems WHERE OrderId = ?"
            );
            selectStatement.setInt(1, OrderId);
            ResultSet resultSet = selectStatement.executeQuery();
            List<OrderItems> ItemList = new ArrayList<>();
            while (resultSet.next()) {
                OrderItems items = new OrderItems();
                items.setItemPrice(resultSet.getFloat("ItemPrice"));
                items.setItemCount(resultSet.getInt("ItemCount"));

                ItemList.add(items);
            }
            return ItemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertOrderSum(int OrderId, float OrderSum){
        try {
            connection = DriverManager.getConnection(dbURL,"Elm","");
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Orders SET OrderSum = ? WHERE OrderId = ?"
            );
            statement.setFloat(1, OrderSum);
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