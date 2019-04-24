/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import java.util.ArrayList;
import java.util.List;
import khoavm.daos.ProductDAO;

/**
 *
 * @author MinhKhoa
 */
public class GetSupIdAction {
    private String txtInsertSearch;
    private List<String> supList;
    private List<String> typeList;

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


    
    public GetSupIdAction() {
       
    }
    
    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        supList = dao.searchSupID();
        typeList = new ArrayList<>();
        typeList.add("Food");
        typeList.add("Drink");
        return "insert";
    }
    
}
