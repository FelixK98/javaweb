/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import java.time.LocalDate;
import javax.servlet.http.HttpSession;
import khoavm.daos.ProductDAO;
import khoavm.dtos.CartObj;
import khoavm.dtos.ProductDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class OrderAction {

    private String quantityErr;
 

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public OrderAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession();
        CartObj cart = (CartObj) session.getAttribute("CART");
        ProductDAO dao = new ProductDAO();
        for (ProductDTO item : cart.getCart().values()) {
            if (Integer.parseInt(item.getQuantity()) > dao.getQuantitybyId(item.getProID())) {
                quantityErr = "We dont have enough quantity for " + item.getProName();
                return url;
            }
        }
        boolean checkOrder = dao.addOrder(cart.getCustomerName(), LocalDate.now()); //insert order to database
        if (checkOrder) {
            for (ProductDTO item : cart.getCart().values()) { //for each product
                String orderID = dao.getOrderId();
                dao.addOrderedProduct(item.getProID(), orderID, item.getQuantity());// insert order product
                Integer quantityRemain = dao.getQuantitybyId(item.getProID()) - Integer.parseInt(item.getQuantity());
                dao.updateProductQuantity(quantityRemain.toString(),item.getProID()); //update product quantity
                if(quantityRemain == 0){
                    dao.deleteProduct(item.getProID());// make product unvailable
                }
            }
            url = SUCCESS;
        }
        return url;

    }

}
