/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author MinhKhoa
 */
public class CartObj implements Serializable{
    private String name;
    private HashMap<String, ProductDTO> cart;

    public CartObj(String name) {
        this.name = name;
        this.cart =  new HashMap<>();        
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public void addItemsToCart(ProductDTO dto){
        if(cart.containsKey(dto.getName())){
            Integer quantity = Integer.parseInt(cart.get(dto.getName()).getQuantity()) + Integer.parseInt(dto.getQuantity());
            dto.setQuantity(quantity.toString());
        }
        cart.put(dto.getName(), dto);
    }
    public void removeItem(String name){
        cart.remove(name);
    }
    public Integer computeTotalPayment(){
        Integer total = 0;
        for (ProductDTO dto : cart.values()) {
            total = total + Integer.parseInt(dto.getPrice()) * Integer.parseInt(dto.getQuantity());
        }
        return total;
    }
}
