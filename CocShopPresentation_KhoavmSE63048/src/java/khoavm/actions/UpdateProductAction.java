/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import java.util.ArrayList;
import java.util.List;
import khoavm.daos.ProductDAO;
import khoavm.dtos.ProductDTO;
import khoavm.errors.ProductError;

/**
 *
 * @author MinhKhoa
 */
public class UpdateProductAction {
    
    private String txtUpdateSearch;
    private String proId, proName, description, type, supId;
    private String price, quantity;
    private List<String> typeList;
    private List<String> supList;
    private ProductError errs;
    private ProductDTO dto;

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

    public String getTxtUpdateSearch() {
        return txtUpdateSearch;
    }

    public void setTxtUpdateSearch(String txtUpdateSearch) {
        this.txtUpdateSearch = txtUpdateSearch;
    }

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ProductError getErrs() {
        return errs;
    }

    public void setErrs(ProductError errs) {
        this.errs = errs;
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

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public UpdateProductAction() {
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public String execute() throws Exception {
        String url = FAIL;
        dto = new ProductDTO(proId, proName, price, description, type, quantity, supId);
        errs = new ProductError();
        ProductDAO dao = new ProductDAO();
        if (errs.checkValidation(dto)) {
            boolean result = dao.updateProduct(dto);
            if (result) {
                url = SUCCESS;
            } else {
                errs.setExecuteErr("Update failed");
            }
        } else {
            supList = dao.searchSupID(dto.getSupID());
            supList.add(0, dto.getSupID());
            typeList = new ArrayList<>();
            typeList.add(dto.getType());
            if (dto.getType().equals("Food")) {
                typeList.add("Drink");
            } else {
                typeList.add("Food");
            }
        }

        return url;
    }

}
