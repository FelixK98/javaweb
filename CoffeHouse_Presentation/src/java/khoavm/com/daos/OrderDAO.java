/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import khoavm.com.db.MyConnection;
import khoavm.com.dto.OrderDTO;


/**
 *
 * @author MinhKhoa
 */
public class OrderDAO implements Serializable{
    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;
    
    private void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(pre != null){
            pre.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    public void addCustomerOrder(String user, Integer totalPayment) throws SQLException, ClassNotFoundException {
        try {
           conn = MyConnection.openConnection();
           String sql = "Insert into CustomerOrders(Customer,OrderDate,Total_Payment) values(?,?,?)";
           pre = conn.prepareStatement(sql);
           pre.setString(1, user);
           pre.setString(2, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));
           pre.setInt(3, totalPayment);
           pre.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public String getOrderID() throws ClassNotFoundException, SQLException {
        String id = null;
        try {
            conn = MyConnection.openConnection();
            String sql = "Select MAX(OrderID) as OrderID from CustomerOrders";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if(rs.next()){
                id = rs.getString(1);
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public void addOderedProduct(String name, String quantity, String price,String orderID) throws ClassNotFoundException, SQLException {
        try {
            conn = MyConnection.openConnection();
            String sql = "Insert into OrderedProducts(ProName, Quantity, Price,OrderID) values(?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, quantity);
            pre.setString(3, price);
            pre.setString(4, orderID);
            pre.executeUpdate();
        } finally{
            closeConnection();
        }
    }

    public List<OrderDTO> getHistoryOrderList(String user) throws ClassNotFoundException, SQLException {
        List<OrderDTO> historyList = null;
        try {
            conn = MyConnection.openConnection();
            String sql = " Select ProName,Quantity,Price,OrderDate"
                    + " from OrderedProducts p,CustomerOrders s"
                    + " where  p.OrderID = s.OrderID and s.Customer = ? ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            rs = pre.executeQuery();
            historyList = new ArrayList<>();
            while(rs.next()){    
                historyList.add(new OrderDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } finally {
            closeConnection();
        }
        return historyList;
    }
    
}
