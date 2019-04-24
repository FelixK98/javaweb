/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.errors;

import java.io.Serializable;
import khoavm.dtos.ProductDTO;

/**
 *
 * @author MinhKhoa
 */
public class ProductError implements Serializable {

    protected String proNameErr, priceErr, descriptionErr, quantityError, executeErr;

    public ProductError() {
    }

    public String getPriceErr() {
        return priceErr;
    }

    public void setPriceErr(String priceErr) {
        this.priceErr = priceErr;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getExecuteErr() {
        return executeErr;
    }

    public void setExecuteErr(String executeErr) {
        this.executeErr = executeErr;
    }

    public String getProNameErr() {
        return proNameErr;
    }

    public void setProNameErr(String proNameErr) {
        this.proNameErr = proNameErr;
    }

   
    public String getDescriptionErr() {
        return descriptionErr;
    }

    public void setDescriptionErr(String descriptionErr) {
        this.descriptionErr = descriptionErr;
    }

    public boolean checkValidation(ProductDTO dto) {
        boolean check = true;
        if (dto.getProName().length() < 4 || dto.getProName().length() > 30) {
            proNameErr = "(Product name length must be 4-30 characters)";
            check = false;
        }
        if (dto.getDescription().length() > 50 || dto.getDescription().length() < 5) {
            descriptionErr = "(Description length must be 5-50 characters)";
            check = false;
        }
        if (!dto.getPrice().matches("\\d+") || Integer.parseInt(dto.getPrice()) < 1000 || Integer.parseInt(dto.getPrice()) > 1000000) {
            priceErr = "(Price must be from 1000-1000000)";
            check = false;
        } 
        if (!dto.getQuantity().matches("\\d+")) {
            quantityError = "(Quantity must be integer numbers)";
            check = false;
        } else if (Integer.parseInt(dto.getQuantity()) < 1 || Integer.parseInt(dto.getQuantity()) > 100) {
            quantityError = "(Quantity must be from 1-100)";
            check = false;
        }
        return check;
    }
}
