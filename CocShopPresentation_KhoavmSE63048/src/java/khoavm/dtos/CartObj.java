/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author MinhKhoa
 */
public class CartObj implements Serializable{
    private String customerName;
    private HashMap<String,ProductDTO> cart;
    

    public CartObj(String customerName) {
        this.customerName = customerName;        
        this.cart = new HashMap<>();
    }

    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, ProductDTO> cart) {
        this.cart = cart;
    }
    public void addtoCart(ProductDTO dto){
        if(cart == null){
            cart = new HashMap<>();
        }
        if(cart.containsKey(dto.getProID())){
            Integer quantity =  Integer.parseInt(cart.get(dto.getProID()).getQuantity()) +Integer.parseInt(dto.getQuantity());
            dto.setQuantity(quantity.toString());
        }
        cart.put(dto.getProID(), dto);
    }
    public void removeItems(String id){
        if(cart.containsKey(id)){
            cart.remove(id);
        }
        if(cart.isEmpty()){
            cart = null;
        }
    }
    public int getTotal(){
        int total = 0;
        for (ProductDTO dto : cart.values()) {
            total += Integer.parseInt(dto.getQuantity()) * Integer.parseInt(dto.getPrice());
        }
        return total;
    }
}
