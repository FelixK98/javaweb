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
import khoavm.com.db.MyConnection;
import khoavm.com.dto.DiscountCodeDTO;

/**
 *
 * @author MinhKhoa
 */
public class DisCountCodeDAO implements Serializable{
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

    public DiscountCodeDTO checkDiscountCode(String code) throws ClassNotFoundException, SQLException {
        DiscountCodeDTO codeDTO = null;
        try {
            conn = MyConnection.openConnection();
            String sql = "Select CodeValue,DiscountValue,Quantity from DiscountCodes where CodeValue = ?";            
            pre = conn.prepareStatement(sql);
            pre.setString(1, code);
            rs = pre.executeQuery();
            if(rs.next()){
                codeDTO = new DiscountCodeDTO(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } finally {
        closeConnection();
        }
        return codeDTO;
    }

    public boolean checkUsedCode(String code, String user) throws SQLException, ClassNotFoundException {
        boolean usedRs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Select Customer from UsedCodes where CodeValue = ? and Customer = ?";            
            pre = conn.prepareStatement(sql);
            pre.setString(1, code);
            pre.setString(2, user);
            rs = pre.executeQuery();
            if(rs.next()){
                usedRs = true;
            }
        } finally {
            closeConnection();
        }
        return usedRs;
    }

    public void addUsedCode(String code, String user) throws ClassNotFoundException, SQLException {
        try {
            conn = MyConnection.openConnection();
            String sql = "Insert into UsedCodes(CodeValue,Customer,UsedDate) values(?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, code);
            pre.setString(2, user);
            pre.setString(3, DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss").format(LocalDateTime.now()));
            pre.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public void updateCodeQuantity(String code) throws SQLException, ClassNotFoundException {
        try {
            conn = MyConnection.openConnection();
            String sql = "Update DiscountCodes set Quantity = Quantity - 1 where CodeValue = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, code);
            pre.executeUpdate();
        } finally {
            closeConnection();
        }
    }
    
    
}
