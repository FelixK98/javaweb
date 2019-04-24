/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import javax.servlet.http.HttpSession;
import khoavm.dtos.CartObj;
import khoavm.dtos.ProductDTO;
import org.apache.struts2.ServletActionContext;


/**
 *
 * @author MinhKhoa
 */
public class AddToCartAction {
    private String product;
    
    private static final String CART_PAGE = "cart";

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    
    public AddToCartAction() {
    }
    
    public String execute() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String username = (String) session.getAttribute("USERNAME");
        CartObj cart = (CartObj) session.getAttribute("CART");
        if(cart == null){
            cart = new CartObj(username);
        }            
        String[] tmp = product.split("-");
        String id = tmp[0];
        String name = tmp[1];
        String price = tmp[2];      
        ProductDTO dto = new ProductDTO(id, name, price, "1");
        cart.addtoCart(dto);
        session.setAttribute("CART", cart);        
        return CART_PAGE;
    }
    
}
