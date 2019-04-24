/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpSession;
import khoavm.daos.AccountDAO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private String err;
    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String ERROR = "input";

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public LoginAction() {
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

    public String execute() throws Exception {
        String url = ERROR;
        HttpSession session = ServletActionContext.getRequest().getSession();
        //check session
        if (session != null) { //check session in client side
            if (session.getAttribute("USERNAME") != null) { //check session in server side
                String roleCheck = (String) session.getAttribute("ROLE");
                if (roleCheck.equals("Admin")) {
                    url = ADMIN;
                } else {
                    url = USER;
                }
            } else {//check user input
                if (username != null || password != null) {
                    if (username.equals("") || password.equals("")) {
                        err = "Username and Password can't blank!";
                    } else {
                        AccountDAO dao = new AccountDAO();
                        String role = dao.checkLogin(username, password);
                        if (role.equals("failed")) {
                            err = "Your username or password is not correct!";
                        } else {
                            session = ServletActionContext.getRequest().getSession(true);
                            session.setAttribute("USERNAME", username);
                            session.setAttribute("ROLE", role);
                            if (role.equals("Admin")) {
                                url = ADMIN;
                            } else if (role.equals("User")) {
                                url = USER;
                            } else {
                                err = "Your role is not supported";
                            }
                        }
                    }
                }
            }

        } //check username input

        return url;
    }

}
