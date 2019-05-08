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
public class UpdateProductAction {

    private static final String FAIL = "fail";

    private String type;
    private String search;
    private String err;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String name, des, price, quantity, supplier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public UpdateProductAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        if (role != null && role.equals("admin")) {
            try {
                ProductDAO dao = new ProductDAO();
                boolean rs = dao.updateProduct(name, des, price, quantity, supplier);
                if (rs) {
                    List<ProductDTO> productList = (List<ProductDTO>) ServletActionContext.getServletContext().getAttribute("PRODUCTS");
                    for (ProductDTO dto : productList) {
                        if (dto.getName().equals(name)) {
                            dto.setDes(des);
                            dto.setPrice(price);
                            dto.setQuantity(quantity);
                            dto.setSupplier(supplier);
                            break;
                        }
                    }
                    url = type;
                }
            } catch (Exception e) {
                err = e.getMessage();
            }
        } else {
            err = "You dont have permission";
        }

        return url;

    }

}
