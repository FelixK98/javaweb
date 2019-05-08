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
public class LogOutAction {
    
    public LogOutAction() {
    }
    
    public String execute() throws Exception {
        HttpSession session =  ServletActionContext.getRequest().getSession();
        session.invalidate();
        return "index";
    }
    
}
