/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.List;
import khoavm.com.daos.ProductDAO;
import khoavm.com.dto.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class DeleteProductAction {

    private static final String FAIL = "fail";

    private String search; //search after delete
    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    private String type; // show food or drink
    private String name; // name of product to delete

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

    public DeleteProductAction() {
    }

    public String execute() throws Exception {

        String url = FAIL;
        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        if (role != null && role.equals("admin")) {
            try {
                ProductDAO dao = new ProductDAO();
                boolean rs = dao.deleteProduct(name);
                if (rs) {
                    // update context product
                    List<ProductDTO> productList = (List<ProductDTO>) ServletActionContext.getServletContext().getAttribute("PRODUCTS");
                    for (ProductDTO dto : productList) {
                        if (dto.getName().equals(name)) {
                            productList.remove(dto);
                            break;
                        }
                    }
                    ServletActionContext.getServletContext().setAttribute("PRODUCTS", productList);
                    url = type;

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            err = "You dont have admin permission";
        }

        return url;
    }

}
