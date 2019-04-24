/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import khoavm.db.MyConnection;
import khoavm.dtos.AccountDTO;

/**
 *
 * @author MinhKhoa
 */
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;

    public AccountDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preSt != null) {
            preSt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "Select Role from Account where Username = ? and Password = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, username);
            preSt.setString(2, password);
            rs = preSt.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public List<AccountDTO> searchByFullName(String searchValue) throws Exception {
        List<AccountDTO> list;
        try {
            String sql = "Select Username, Fullname, Email, Phone, Role from Account where Fullname like ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, "%" + searchValue + "%");
            rs = preSt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                AccountDTO dto = new AccountDTO(rs.getString("Username"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("Role"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteAccount(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete from Account where Username = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, id);
            check = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public AccountDTO searchByUsername(String id) throws Exception {
        AccountDTO dto = null;
        try {
            String sql = "Select Username, Fullname, Email, Phone, Role from Account where Username = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, id);
            rs = preSt.executeQuery();
            if (rs.next()) {
                dto = new AccountDTO(rs.getString("Username"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("Role"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateAccount(AccountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Account set Fullname= ?, Email = ?, Phone = ? where Username = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, dto.getFullname());
            preSt.setString(2, dto.getEmail());
            preSt.setString(3, dto.getPhone());
            preSt.setString(4, dto.getUsername());
            check = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean isDuplicate(String acccountAttr, String key, String username) throws Exception {
        boolean check = false;
        try {
            String sql = "Select Username from Account Where " + acccountAttr + " = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, key);

            rs = preSt.executeQuery();

            if (rs.next()) {
                if (username.equals(rs.getString("Username"))) {
                    if (rs.next()) {
                        check = true;
                    }
                } else {
                    check = true;
                }

            }
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean registerUser(AccountDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into Account(Username,Password,Fullname,Email,Phone,Role) Values(?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, dto.getUsername());
            preSt.setString(2, dto.getPassword());
            preSt.setString(3, dto.getFullname());
            preSt.setString(4, dto.getEmail());
            preSt.setString(5, dto.getPhone());
            preSt.setString(6, "User");
            check = preSt.executeUpdate() > 0;
           
        } finally{
            closeConnection();
        }
        return check;
    }
    public boolean updatePassword(String username, String password, String newPassword) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Account set Password = ? where Username = ? and Password = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, newPassword);
            preSt.setString(2, username);
            preSt.setString(3, password);
            check = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
 
}
