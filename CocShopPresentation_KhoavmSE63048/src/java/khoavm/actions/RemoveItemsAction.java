/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import javax.servlet.http.HttpSession;
import khoavm.dtos.CartObj;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class RemoveItemsAction {

    private String[] selectedItem;

    public String[] getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String[] selectedItem) {
        this.selectedItem = selectedItem;
    }

    public RemoveItemsAction() {

    }

    public String execute() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        CartObj cart = (CartObj) session.getAttribute("CART");
        if (selectedItem != null) {
            for (String item : selectedItem) {
                cart.removeItems(item);
            }
        }

        return "viewcart";
    }

}
