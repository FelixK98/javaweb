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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import khoavm.db.MyConnection;
import khoavm.dtos.ProductDTO;

/**
 *
 * @author MinhKhoa
 */
public class ProductDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;

    public ProductDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preSt != null) {
            preSt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<ProductDTO> searchByName(String name) throws Exception {
      List<ProductDTO> searchList = null;
        try {
            String sql = "Select ProID,ProName,Price,Description, Type,Quantity, supID from Product where ProName = ? and isAvailable = 'True'";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, name);
            rs = preSt.executeQuery();
            searchList = new ArrayList<>();
            while(rs.next()){
                ProductDTO dto = new ProductDTO(rs.getString("ProID"), rs.getString("ProName"), rs.getString("Price"), rs.getString("Description"), rs.getString("Type"), rs.getString("Quantity"), rs.getString("supID"));
                searchList.add(dto);
            }
        } finally {
            closeConnection();
        }
      return searchList;
    }

    public List<ProductDTO> searchByNameAndType(String name, String type) throws Exception {
        List<ProductDTO> searchList;
        try {
            String sql = "Select ProID, ProName, Price, Description, Quantity, SupID From Product where ProName like ? and Type = ? and isAvailable='True'";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, "%" + name + "%");
            preSt.setString(2, type);
            rs = preSt.executeQuery();
            searchList = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO(rs.getString("ProID"), rs.getString("ProName"), rs.getString("Price"), rs.getString("Description"), rs.getString("Quantity"), rs.getString("SupID"));
                searchList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return searchList;
    }

    public boolean deleteProduct(String proId) throws Exception {
        boolean check;
        try {
            String sql = "Update Product set isAvailable = 'False' where ProID = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, proId);
            check = preSt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return check;
    }

    public ProductDTO searchByProductId(String name) throws Exception {
        ProductDTO dto = null;
        try {
            String sql = "Select ProID, ProName, Price, Description,Type,Quantity, SupID from Product where ProID = ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, name);
            rs = preSt.executeQuery();
            if (rs.next()) {
                dto = new ProductDTO(rs.getString("ProID"), rs.getString("ProName"), rs.getString("Price"), rs.getString("Description"), rs.getString("Type"), rs.getString("Quantity"), rs.getString("SupID"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<String> searchSupID(String supId) throws Exception {
        List<String> supList = null;
        try {
            String sql = "Select SupID from Supplier where SupID != ?";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, supId);
            rs = preSt.executeQuery();
            supList = new ArrayList<>();
            while (rs.next()) {
                supList.add(rs.getString("SupID"));
            }
        } finally {
            closeConnection();
        }
        return supList;
    }

    public List<String> searchSupID() throws Exception {
        List<String> supList = null;
        try {
            String sql = "Select SupID from Supplier";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);

            rs = preSt.executeQuery();
            supList = new ArrayList<>();
            while (rs.next()) {
                supList.add(rs.getString("SupID"));
            }
        } finally {
            closeConnection();
        }
        return supList;
    }

    public boolean updateProduct(ProductDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Product set ProName = ?, Price = ?, Description = ?, Type = ?,Quantity = ?, SupID= ? Where ProID = ? ";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, dto.getProName());
            preSt.setString(2, dto.getPrice());
            preSt.setString(3, dto.getDescription());
            preSt.setString(4, dto.getType());
            preSt.setString(5, dto.getQuantity());
            preSt.setString(6, dto.getSupID());
            preSt.setString(7, dto.getProID());

            check = preSt.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertProduct(ProductDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into Product(ProID, ProName, Price, Description, Type, Quantity, isAvailable, SupID) Values(?,?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, dto.getProID());
            preSt.setString(2, dto.getProName());
            preSt.setString(3, dto.getPrice());
            preSt.setString(4, dto.getDescription());
            preSt.setString(5, dto.getType());
            preSt.setString(6, dto.getQuantity());
            preSt.setString(7, "True");
            preSt.setString(8, dto.getSupID());
            check = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ProductDTO> searchByType(String type) throws Exception {
        List<ProductDTO> searchList = null;
        try {
            String sql = "Select ProID, ProName, Price, Description, Type, Quantity, supID from Product where Type = ? and isAvailable ='True'";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, type);
            rs = preSt.executeQuery();
            searchList = new ArrayList<>();
            while(rs.next()){
                ProductDTO dto = new ProductDTO(rs.getString("ProID"), rs.getString("ProName"), rs.getString("Price"), rs.getString("Description"), rs.getString("Type"), rs.getString("Quantity"), rs.getString("supID"));
                searchList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return searchList;
    }

    public int getQuantitybyId(String id) throws Exception {
        int result = 0;
        try {
            String sql = "Select Quantity from Product where ProID = ? ";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, id);
            rs = preSt.executeQuery();
            if (rs.next()) {
                result = Integer.parseInt(rs.getString("Quantity"));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean addOrder(String customerName, LocalDate date) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into CustomerOrder(OrderDate,Customer) Values(?,?)";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, date.toString());
            preSt.setString(2, customerName);

            check = preSt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String getOrderId() throws Exception{
        String result = null;
        try {
            String sql ="Select MAX(OrderID) as OrderID from CustomerOrder";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            rs = preSt.executeQuery();
            if(rs.next()){
                result = rs.getString("OrderID");
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }

    public void addOrderedProduct(String proID, String orderID, String quantity) throws Exception{
        try {
            String sql = "Insert into OrderedProduct(ProID,OrderID,Quantity) VALUES(?,?,?)";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, proID);
            preSt.setString(2, orderID);
            preSt.setString(3, quantity);
            preSt.executeUpdate();
        } finally {
            closeConnection();
        }
    }
    public void updateProductQuantity(String quantityRemain, String id) throws Exception{
        try {
            String sql = "Update Product set Quantity = ? where ProID = ? ";
            conn = MyConnection.getConnection();
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, quantityRemain);
            preSt.setString(2, id);
            preSt.executeUpdate();
        } finally{
            closeConnection();
        }
    }
}
