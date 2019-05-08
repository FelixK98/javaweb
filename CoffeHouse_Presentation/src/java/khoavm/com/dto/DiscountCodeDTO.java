/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.dto;

/**
 *
 * @author MinhKhoa
 */
public class DiscountCodeDTO {
    private String code, discount, quantity;

    public DiscountCodeDTO(String code, String discount, String quantity) {
        this.code = code;
        this.discount = discount;
        this.quantity = quantity;
    }

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
}
