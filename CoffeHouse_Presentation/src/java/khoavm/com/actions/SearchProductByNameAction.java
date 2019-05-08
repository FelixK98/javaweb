/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import khoavm.com.dto.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class SearchProductByNameAction {    

    private String search;
    private List<ProductDTO> searchList;
    
   
    private String role;
   

   

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<ProductDTO> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<ProductDTO> searchList) {
        this.searchList = searchList;
    }

    public SearchProductByNameAction() {
    }

    public String execute() throws Exception {
        ServletContext context = ServletActionContext.getServletContext();
        List<ProductDTO> productList = (List<ProductDTO>) context.getAttribute("PRODUCTS");        
        ServletActionContext.getRequest().setAttribute("SEARCHTXT", search);
        searchList = new ArrayList<>();
        for (ProductDTO dto : productList) {
            if(dto.getName().contains(search.toUpperCase())){
                searchList.add(dto);
            }
        }
        return role;
    }

}
