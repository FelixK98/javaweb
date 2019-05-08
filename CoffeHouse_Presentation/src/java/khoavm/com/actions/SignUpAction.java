/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.List;

import khoavm.com.daos.AccountDAO;
import khoavm.com.dto.AccountDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class SignUpAction {

    private static final String ERROR = "error"; //WHEN DATABASE CRASHED OR DEVELOPER WRONG
    private static final String FAIL = "fail"; // WHEN DUPLICATE USERNAME
    private static final String SUCCESS = "success"; // WHEN INSERT SUCCESSFULLY
    private String username, password, fullname, email, phone;

    private String err; // TELL USER WHAT'S WRONG
    private String successNotify; // notice user that insert successful

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getSuccessNotify() {
        return successNotify;
    }

    public void setSuccessNotify(String successNotify) {
        this.successNotify = successNotify;
    }

    public SignUpAction() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        try {
            AccountDAO dao = new AccountDAO();
            boolean rs = dao.createAccount(username, password, fullname, email, phone);
            if (rs) {
                // update Account context
                List<AccountDTO> accountList = (List<AccountDTO>) ServletActionContext.getServletContext().getAttribute("ACCOUNTS");
                accountList.add(new AccountDTO(username, password, fullname, email, phone, "user"));
                ServletActionContext.getServletContext().setAttribute("ACCOUNTS", accountList);

                //go to success page
                url = SUCCESS;

                //notice to user
                successNotify = "SIGN UP SUCCESSFUL!!!!!";
            }
        } catch (Exception e) {
            
            if (e.getMessage().contains("duplicate")) {
                url = FAIL;
                err = "Username have existed";
            }
            else
                err = e.getMessage();
        }
        return url;
    }

}
