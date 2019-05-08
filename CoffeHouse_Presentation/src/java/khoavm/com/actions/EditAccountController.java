/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.List;
import khoavm.com.dto.AccountDTO;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class EditAccountController {

    private static final String SUCCESS = "update";
    private static final String FAIL = "fail";

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    private String name;
    private String search;
    private String err;
    private AccountDTO dto;

    public AccountDTO getDto() {
        return dto;
    }

    public void setDto(AccountDTO dto) {
        this.dto = dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public EditAccountController() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        
        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        if (role != null && role.equals("admin")) {
            url = SUCCESS;
            List<AccountDTO> accountList = (List<AccountDTO>) ServletActionContext.getServletContext().getAttribute("ACCOUNTS");
            for (AccountDTO account : accountList) {
                if (account.getUsername().equals(name)) {
                    dto = account;
                }
            }
        }
        else{
            err = "You dont have permission";
        }

        return url;
    }

}
