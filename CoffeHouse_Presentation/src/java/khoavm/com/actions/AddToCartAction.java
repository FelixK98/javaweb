/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import javax.servlet.http.HttpSession;
import khoavm.com.dto.CartObj;
import khoavm.com.dto.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class AddToCartAction {

    private static final String SUCCESS = "user";
    private static final String FAIL = "notuser";

    private String name;
    private String price;
    private String cartQuantity;  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AddToCartAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            String role = (String) session.getAttribute("ROLE");
            if (role.equals("user")) {
                url = SUCCESS;                
                String username = (String) session.getAttribute("USER");
                CartObj cartObj = (CartObj) session.getAttribute("CART");
                if (cartObj == null) {
                    cartObj = new CartObj(username);
                }
                ProductDTO dto = new ProductDTO(name, price, "1");
                cartObj.addItemsToCart(dto);
               
                session.setAttribute("CART", cartObj);
            }
            
        } catch (Exception e) {            
        }
        return "user";
    }

}
