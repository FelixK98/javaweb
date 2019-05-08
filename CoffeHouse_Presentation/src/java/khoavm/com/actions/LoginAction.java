/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import khoavm.com.dto.AccountDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class LoginAction {

    private static final String ADMIN = "admin"; //admin page
    private static final String USER = "user";  //user page
    private static final String FAIL = "fail";  // comback to login page
    private static final String LOGGED = "logged";  // user have logged in
    private String username, password;
    private String err; // notice user that username or password incorrect

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        //check if user have logged in
        if (ServletActionContext.getRequest().getSession().getAttribute("ROLE") != null) {
            url = LOGGED;
        } else {
            //get list from context
            ServletContext context = ServletActionContext.getServletContext();
            List<AccountDTO> accountList = (List<AccountDTO>) context.getAttribute("ACCOUNTS");
            //check login
            for (AccountDTO dto : accountList) {
                //if log in successful
                if (dto.getUsername().equals(username) && dto.getPassword().equals(password)) {
                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.setAttribute("USER", dto.getUsername());
                    //if account is admin
                    if (dto.getRole().equals("admin")) {
                        session.setAttribute("ROLE", "admin");
                        url = ADMIN;
                        break;
                    } else { //if account is user  
                        session.setAttribute("ROLE", "user");
                        url = USER;
                        break;
                    }

                }
            }
        }
        
        // if login fail
        if (url.equals(FAIL)) {
            err = "Username or Password is incorrect";
        }
        return url;
    }

}
