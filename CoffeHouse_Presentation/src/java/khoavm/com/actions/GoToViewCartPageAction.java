/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class GoToViewCartPageAction {
    private static final String SUCCESS = "user";
    private static final String FAIL = "notuser";
    
    public GoToViewCartPageAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession();
        String role = (String) session.getAttribute("ROLE");
        try {
            if(role.equals("user")){
                url = SUCCESS;
            }
        } catch (Exception e) {
        }
        return url;
    }
    
}
