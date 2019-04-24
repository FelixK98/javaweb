/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import com.opensymphony.xwork2.ActionSupport;
import khoavm.daos.AccountDAO;
import khoavm.dtos.AccountDTO;
import khoavm.errors.AccountError;

/**
 *
 * @author MinhKhoa
 */
public class UpdateAction extends ActionSupport {

    private String txtUpdateSearch;
    private String username, fullname, email, phone;
    private AccountError errs = new AccountError();
    private AccountDTO dto;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public String getTxtUpdateSearch() {
        return txtUpdateSearch;
    }

    public void setTxtUpdateSearch(String txtUpdateSearch) {
        this.txtUpdateSearch = txtUpdateSearch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public AccountError getErrs() {
        return errs;
    }

    public void setErrs(AccountError errs) {
        this.errs = errs;
    }

    public AccountDTO getDto() {
        return dto;
    }

    public void setDto(AccountDTO dto) {
        this.dto = dto;
    }

    public UpdateAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        dto = new AccountDTO(username, fullname, email, phone);
        boolean valid = errs.checkValidation(dto);
        if (valid) {
            AccountDAO dao = new AccountDAO();
            if (dao.updateAccount(dto)) {
                url = SUCCESS;
            } else {
                errs.setExecuteErr("Update failed!!");
            }

        }
        return url;
    }

    @Override
    public void validate() {

    }

}
