/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.ArrayList;
import java.util.List;
import khoavm.com.dto.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class SearchDrinkByNameAction {
    private String search; // input of user
    private String role; // page to send result
    private String type; // food
    private List<ProductDTO> searchList; // search result

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductDTO> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<ProductDTO> searchList) {
        this.searchList = searchList;
    }
    
    public SearchDrinkByNameAction() {
    }
    
    public String execute() throws Exception {
        
         // call context product
        List<ProductDTO> list = (List<ProductDTO>) ServletActionContext.getServletContext().getAttribute("PRODUCTS");
        searchList = new ArrayList<>();
        for (ProductDTO dto : list) {
            if( dto.getName().contains(search.toUpperCase()) && dto.getType().equals("drink")){
                searchList.add(dto);
            }
        }
        return role;
    }
    
}
