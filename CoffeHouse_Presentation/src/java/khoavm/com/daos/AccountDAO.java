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
import khoavm.com.dto.AccountDTO;

/**
 *
 * @author MinhKhoa
 */
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        } else if (preSt != null) {
            preSt.close();
        } else if (conn != null) {
            conn.close();
        }
    }

    public List<AccountDTO> getAccountList() throws ClassNotFoundException, SQLException {
        List<AccountDTO> ls = null;
        try {
            conn = MyConnection.openConnection();
            String sql = "Select * from Accounts";
            preSt = conn.prepareStatement(sql);
            rs = preSt.executeQuery();
            ls = new ArrayList<>();
            while(rs.next()){
                ls.add(new AccountDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public boolean createAccount(String username, String password, String fullname, String email, String phone) throws ClassNotFoundException, SQLException {
        boolean rs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Insert into Accounts(Username,Password,Fullname,Email,Phone,Role,isActive) VALUES(?,?,?,?,?,'user','True')";            
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setString(3, fullname);
            preSt.setString(4, email);
            preSt.setString(5, phone);
            rs = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return rs;
    }

    public boolean deleteAccount(String name) throws SQLException, ClassNotFoundException {
        boolean rs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Update Accounts set isActive='False' where Username = ?";
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, name);
            rs = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return rs;
    }

    public boolean updateAccount(String username, String password, String fullname, String email, String phone) throws SQLException, ClassNotFoundException {
        boolean rs = false;
        try {
            conn = MyConnection.openConnection();
            String sql = "Update Accounts set Password = ?, Fullname = ?, Email = ?, Phone = ? where Username = ?";            
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, password);
            preSt.setString(2, fullname);
            preSt.setString(3, email);
            preSt.setString(4, phone);
            preSt.setString(5, username);
            rs = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return rs;
    }
    
    
    

}
