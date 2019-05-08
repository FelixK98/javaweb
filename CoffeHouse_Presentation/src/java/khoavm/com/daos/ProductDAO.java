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
import java.util.ArrayList;
import java.util.List;
import khoavm.com.db.MyConnection;
import khoavm.com.dto.ProductDTO;

/**
 *
 * @author MinhKhoa
 */
public class ProductDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;
    
    private void closeConnection() throws SQLException{
        if(conn != null){
            conn.close();
        }
        if(preSt != null){
            preSt.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    public List<ProductDTO> getProductList() throws ClassNotFoundException, SQLException {
        List<ProductDTO> dto = null;
        try {
            conn = MyConnection.openConnection();
            String sql = "Select ProName,Description,Price,Image,Type,Quantity,Supplier  from Products where isAvailable='True'";
            preSt = conn.prepareStatement(sql);
            rs = preSt.executeQuery();
            dto = new ArrayList<>();
            while(rs.next()){
                dto.add(new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)));                
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean deleteProduct(String name) throws SQLException, ClassNotFoundException {
        boolean rs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Update Products set isAvailable='FALSE' where ProName = ?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, name);
            rs = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return rs;
    }

    public boolean updateProduct(String name, String des, String price, String quantity, String supplier) throws ClassNotFoundException, SQLException {
        boolean rs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Update Products set Description = ?, Price = ?, Quantity = ? , Supplier = ? where ProName = ? ";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, des);
            preSt.setString(2, price);
            preSt.setString(3, quantity);
            preSt.setString(4, supplier);
            preSt.setString(5, name);
            rs = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return rs;
    }

   

    public boolean addProduct(String name, String des, String price, String img, String type, String quantity, String supplier) throws SQLException, ClassNotFoundException {
        boolean rs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Insert into Products(Proname, Description, Price, Image, Type, Quantity,Supplier,isAvailable) values(?,?,?,?,?,?,?,'True')";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, name.toUpperCase());
            preSt.setString(2, des);
            preSt.setString(3, price);
            preSt.setString(4, img);
            preSt.setString(5, type);
            preSt.setString(6, quantity);
            preSt.setString(7, supplier);
            rs = preSt.executeUpdate() > 0;
        } finally{
        closeConnection();
        }
        return rs;
    }

    public void updateQuantity(String name, Integer remainQuantity) throws SQLException, ClassNotFoundException {
        try {
            conn = MyConnection.openConnection();
            String sql = "Update Products set Quantity = ? where ProName = ?";
            preSt = conn.prepareStatement(sql);
            preSt.setInt(1, remainQuantity);
            preSt.setString(2, name);
            preSt.executeUpdate();
        } finally {
            closeConnection();
        }
    }
    
   
    
}
