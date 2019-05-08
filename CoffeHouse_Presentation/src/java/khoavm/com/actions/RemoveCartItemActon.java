/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import javax.servlet.http.HttpSession;
import khoavm.com.dto.CartObj;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class RemoveCartItemActon {

    private static final String SUCCESS = "user";
    private static final String FAIL = "notuser";

    private String[] removeItem;

    public String[] getRemoveItem() {
        return removeItem;
    }

    public void setRemoveItem(String[] removeItem) {
        this.removeItem = removeItem;
    }

    public RemoveCartItemActon() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession();
        String role= (String) session.getAttribute("ROLE");
        try {
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (role.equals("user")) {
                url = SUCCESS;
                if (removeItem != null) {
                    for (int i = 0; i < removeItem.length; i++) {
                        cart.removeItem(removeItem[i]);
                    }
                }

            }
        } catch (Exception e) {
        }
        return url;
    }

}
