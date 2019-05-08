/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.List;
import khoavm.com.dto.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class EditProductController {

    private static final String SUCCESS = "update";
    private static final String FAIL = "fail";
    private String name;
     private String search;
    private String type;

    private ProductDTO dto;
    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
   

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EditProductController() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        if (role != null && role.equals(role)) {
            List<ProductDTO> productList = (List<ProductDTO>) ServletActionContext.getServletContext().getAttribute("PRODUCTS");
            for (ProductDTO productDTO : productList) {
                if (productDTO.getName().equals(name)) {
                    dto = productDTO;
                    break;
                }
            }
        }

        return "update";
    }

}
