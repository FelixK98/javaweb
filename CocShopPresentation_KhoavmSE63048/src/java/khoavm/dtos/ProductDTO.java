/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.dtos;

import java.io.Serializable;

/**
 *
 * @author MinhKhoa
 */
public class ProductDTO implements Serializable{
    private String proID, proName, description, supID, type;
    private String price, quantity;

    public ProductDTO(String proID, String proName, String price,String description, String type, String quantity, String supID) {
        this.proID = proID;
        this.proName = proName;
        this.description = description;
        this.supID = supID;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
       public ProductDTO(String proID, String proName, String price,String description, String quantity, String supID) {
        this.proID = proID;
        this.proName = proName;
        this.description = description;
        this.supID = supID;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(String proID, String proName, String price, String quantity) {
        this.proID = proID;
        this.proName = proName;
        this.price = price;
        this.quantity = quantity;
    }

  

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupID() {
        return supID;
    }

    public void setSupID(String supID) {
        this.supID = supID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

  
    
}
