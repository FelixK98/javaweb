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
import khoavm.com.dto.SupplierDTO;


/**
 *
 * @author MinhKhoa
 */
public class SupplierDAO implements Serializable{
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
    public List<SupplierDTO> getSupplier() throws SQLException, ClassNotFoundException{
        List<SupplierDTO> supplierList = null;
        try {
            conn = MyConnection.openConnection();
            String sql = "Select * from Suppliers";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            supplierList = new ArrayList<>();
            while(rs.next()){
                supplierList.add(new SupplierDTO(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } finally {
            closeConnection();
        }
        return supplierList;
    }
}
