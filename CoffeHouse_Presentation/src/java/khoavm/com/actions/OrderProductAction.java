/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import khoavm.com.daos.DisCountCodeDAO;
import khoavm.com.daos.OrderDAO;
import khoavm.com.daos.ProductDAO;
import khoavm.com.dto.CartObj;
import khoavm.com.dto.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class OrderProductAction {

    private static final String SUCCESS = "user";
    private static final String QUANTITYERROR = "quantityfail";
    private static final String FAIL = "notuser";
    private static final String DBERROR = "dberror";

    private String disCountPrice;
    private String code;

    public String getDisCountPrice() {
        return disCountPrice;
    }

    public void setDisCountPrice(String disCountPrice) {
        this.disCountPrice = disCountPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderProductAction(String disCountPrice, String err) {
        this.disCountPrice = disCountPrice;
        this.err = err;
    }

    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public OrderProductAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession();
        String role = (String) session.getAttribute("ROLE");
        try {
            if (role != null && role.equals("user")) {
                url = SUCCESS;
                CartObj cart = (CartObj) session.getAttribute("CART");
                List<ProductDTO> productList = (List<ProductDTO>) ServletActionContext.getServletContext().getAttribute("PRODUCTS");
                HashMap<String, ProductDTO> cartList = cart.getCart();
                //CHECK IF product quantity is enough
                for (ProductDTO cartDTO : cartList.values()) {
                    for (ProductDTO productDTO : productList) {
                        if (productDTO.getName().equals(cartDTO.getName())) {
                            Integer cartQuantity = Integer.parseInt(cartDTO.getQuantity());
                            Integer productQuantity = Integer.parseInt(productDTO.getQuantity());
                            if (cartQuantity > productQuantity) {
                                err = productDTO.getName() + " don't have enough quantity";
                                return QUANTITYERROR;
                            }
                        }
                    }

                }
                //Insert order product into database

                //insert to customerORDER
                OrderDAO orderDAO = new OrderDAO();
                ProductDAO productDAO = new ProductDAO();
                String user = (String) session.getAttribute("USER");
                if (disCountPrice == null || disCountPrice.equals("")) {
                    orderDAO.addCustomerOrder(user, cart.computeTotalPayment());
                } else {
                    orderDAO.addCustomerOrder(user, Integer.parseInt(disCountPrice));
                }
                //get id of customerorder
                String orderID = orderDAO.getOrderID();
                //insert to OrderProduct
                //cart object
                for (ProductDTO cartDTO : cartList.values()) {
                    Integer dtoPrice = Integer.parseInt(cartDTO.getQuantity()) * Integer.parseInt(cartDTO.getPrice());
                    orderDAO.addOderedProduct(cartDTO.getName(), cartDTO.getQuantity(), dtoPrice.toString(), orderID);
                    // product object
                    for (ProductDTO productDTO : productList) {
                        //update quantity
                        if (productDTO.getName().equals(cartDTO.getName())) {
                            Integer cartQuantity = Integer.parseInt(cartDTO.getQuantity());
                            Integer productQuantity = Integer.parseInt(productDTO.getQuantity());
                            Integer remainQuantity = productQuantity - cartQuantity;
                            productDAO.updateQuantity(productDTO.getName(), remainQuantity);
                            productDTO.setQuantity(remainQuantity.toString());
                        }
                    }
                }

                //add INFO INTO USED CODE
                if (disCountPrice != null && !disCountPrice.equals("")) {
                    DisCountCodeDAO discountDAO = new DisCountCodeDAO();
                    discountDAO.addUsedCode(code, user);
                    discountDAO.updateCodeQuantity(code);
                }

                //AFTER ADD INFO TO DATABASE
                session.removeAttribute("CART"); // REMOVE CART
                ServletActionContext.getServletContext().setAttribute("PRODUCTS", productList); //UPDATE CONTEXT PRODUCT

            }
        } catch (SQLException e) {
            err = "Error at OrderAction: " + e.getMessage();
            url = DBERROR;
        }
        return url;
    }

}
