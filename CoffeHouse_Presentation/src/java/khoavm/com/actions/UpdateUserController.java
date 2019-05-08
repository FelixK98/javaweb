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
public class UpdateUserController {

    private static final String FAIL = "fail";
    private String username, password, fullname, email, phone;
    private String search;
    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

    public UpdateUserController() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        if (role != null) {
            try {
                AccountDAO dao = new AccountDAO();
                boolean rs = dao.updateAccount(username, password, fullname, email, phone);
                if (rs) {
                    List<AccountDTO> accountList = (List<AccountDTO>) ServletActionContext.getServletContext().getAttribute("ACCOUNTS");
                    for (AccountDTO dto : accountList) {
                        if (dto.getUsername().equals(username)) {
                            accountList.remove(dto);
                            accountList.add(new AccountDTO(username, password, fullname, email, phone, "user"));
                            break;
                        }
                    }
                    url = role;
                }
            } catch (Exception e) {
                err = e.getMessage();
            }
        }
        else{
            err = "You dont have permission";
        }
            

        return url;

    }

}
