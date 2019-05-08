/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class GoToRegisterPageAction {

    private static final String GUEST = "guest";
    private static final String LOGGED = "logged";

    public GoToRegisterPageAction() {
    }

    public String execute() throws Exception {
        String url = GUEST;
        if (ServletActionContext.getRequest().getSession().getAttribute("ROLE") != null) {
           url = LOGGED;
        }

        return url;
    }

}
