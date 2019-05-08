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
public class OrderDTO implements Serializable{
    private String name, quantity, price, date;

    public OrderDTO(String name, String quantity, String price, String date) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
