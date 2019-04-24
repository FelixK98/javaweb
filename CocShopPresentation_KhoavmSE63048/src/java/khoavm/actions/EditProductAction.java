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

/**
 *
 * @author MinhKhoa
 */
public class EditProductAction {
    private String txtProductName, proId;
    private String UpdateErr;
    private String typeSelected;
    private ProductDTO dto;
    private List<String> supList;
    private List<String> typeList;
    private static final String UPDATE ="update";

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public List<String> getSupList() {
        return supList;
    }

    public void setSupList(List<String> supList) {
        this.supList = supList;
    }
    
    public String getTxtProductName() {
        return txtProductName;
    }

    public void setTxtProductName(String txtProductName) {
        this.txtProductName = txtProductName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getUpdateErr() {
        return UpdateErr;
    }

    public void setUpdateErr(String UpdateErr) {
        this.UpdateErr = UpdateErr;
    }

    public String getTypeSelected() {
        return typeSelected;
    }

    public void setTypeSelected(String typeSelected) {
        this.typeSelected = typeSelected;
    }
    
    public EditProductAction() {
    }
    
    public String execute() throws Exception {
           ProductDAO dao = new ProductDAO();
           dto = dao.searchByProductId(proId);
           supList = dao.searchSupID(dto.getSupID());
           supList.add(0, dto.getSupID());
           typeList = new ArrayList<>();
           typeList.add(dto.getType());
           if(dto.getType().equals("Food")){
               typeList.add("Drink");
           }else
               typeList.add("Food");
           return UPDATE;     
    }
    
}
