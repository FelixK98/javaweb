/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import khoavm.daos.ProductDAO;
import khoavm.dtos.ProductDTO;
import khoavm.errors.InsertProductError;

/**
 *
 * @author MinhKhoa
 */
public class InsertAction {

    private String proId, proName, price, description, type, quantity, supId;
    private ProductDTO dto;
    private InsertProductError errs;
    private String insertSuccess;
    private List<String> typeList;
    private List<String> supList;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public InsertProductError getErrs() {
        return errs;
    }

    public void setErrs(InsertProductError errs) {
        this.errs = errs;
    }

    public String getInsertSuccess() {
        return insertSuccess;
    }

    public void setInsertSuccess(String insertSuccess) {
        this.insertSuccess = insertSuccess;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<String> getSupList() {
        return supList;
    }

    public void setSupList(List<String> supList) {
        this.supList = supList;
    }

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public InsertAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        ProductDAO dao = new ProductDAO();
        try {
            dto = new ProductDTO(proId, proName, price, description, type, quantity, supId);
            System.out.println(proId + " at InsertAction");            
            errs = new InsertProductError();
            if (errs.checkValidation(dto)) {
                boolean result = dao.insertProduct(dto);
                if (result) {
                    insertSuccess = "Insert successful!";
                    url = SUCCESS;
                } else {
                    errs.setExecuteErr("Update failed");
                }
            } else {
                supList = dao.searchSupID(supId);
                supList.add(0, supId);
                typeList = new ArrayList<>();
                typeList.add(dto.getType());
                if (dto.getType().equals("Food")) {
                    typeList.add("Drink");
                } else {
                    typeList.add("Food");
                }
            }
        } catch (Exception ex) {
            if (ex.getMessage().contains("duplicate")) {
                supList = dao.searchSupID(supId);
                supList.add(0, supId);
                typeList = new ArrayList<>();
                typeList.add(dto.getType());
                if (dto.getType().equals("Food")) {
                    typeList.add("Drink");
                } else {
                    typeList.add("Food");
                }
                errs.setProIdErr("(Product ID is already existed)");
            }
        }

        return url;
    }

}
