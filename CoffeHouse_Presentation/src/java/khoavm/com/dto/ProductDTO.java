/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.dto;

import java.io.Serializable;

/**
 *
 * @author MinhKhoa
 */
public class ProductDTO implements Serializable{
   private String name, des, price, img, type, quantity, supplier;

    public ProductDTO(String name, String des, String price, String img, String type, String quantity, String supplier) {
        this.name = name;
        this.des = des;
        this.price = price;
        this.img = img;
        this.type = type;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public ProductDTO(String name, String des, String price, String quantity, String supplier) {
        this.name = name;
        this.des = des;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public ProductDTO(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
 
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
  

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  

    
   
}
