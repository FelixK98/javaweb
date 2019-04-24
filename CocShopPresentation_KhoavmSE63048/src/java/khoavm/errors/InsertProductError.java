/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.errors;

import khoavm.dtos.ProductDTO;

/**
 *
 * @author MinhKhoa
 */
public class InsertProductError extends ProductError {

    String proIdErr;


    public InsertProductError() {
    }

    public String getProIdErr() {
        return proIdErr;
    }

    public void setProIdErr(String proIdErr) {
        this.proIdErr = proIdErr;
    }

    @Override
    public boolean checkValidation(ProductDTO dto) {
        boolean check = true;
        if(dto.getProID().length() <2 || dto.getProID().length() > 15 ){
            proIdErr = "(Product id length must be 2-15 characters)";
            check = false;
        }
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
