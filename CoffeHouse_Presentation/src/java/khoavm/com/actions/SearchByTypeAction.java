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
public class SearchByTypeAction {

    private List<ProductDTO> searchList;
    private String type;
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

  

    public SearchByTypeAction() {
    }

    public String execute() throws Exception {
        ServletContext context = ServletActionContext.getServletContext();
        searchList = new ArrayList<>();
        List<ProductDTO> productList = (List<ProductDTO>) context.getAttribute("PRODUCTS");
        // if search food
        if (type.equals("food")) {
            
            for (ProductDTO dto : productList) {
                if (dto.getType().equals("food")) {
                    searchList.add(dto);
                }
            }            
        }
        //if search drink
        else{
            for(ProductDTO dto : productList) {
                if(dto.getType().equals("drink")){
                    searchList.add(dto);
                }
            }
        }

        return role;
    }

}
