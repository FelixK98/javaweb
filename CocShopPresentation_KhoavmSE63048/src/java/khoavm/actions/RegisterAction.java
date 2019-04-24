/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import khoavm.daos.AccountDAO;
import khoavm.dtos.AccountDTO;
import khoavm.errors.AccountRegisterError;

/**
 *
 * @author MinhKhoa
 */
public class RegisterAction {

    private String username, password, fullname, email, phone;
    private String confirm;
    private String registerSuccess;
    private AccountDTO dto;
    private AccountRegisterError errs;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRegisterSuccess() {
        return registerSuccess;
    }

    public void setRegisterSuccess(String registerSuccess) {
        this.registerSuccess = registerSuccess;
    }

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

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

    public AccountDTO getDto() {
        return dto;
    }

    public void setDto(AccountDTO dto) {
        this.dto = dto;
    }

    public AccountRegisterError getErrs() {
        return errs;
    }

    public void setErrs(AccountRegisterError errs) {
        this.errs = errs;
    }

    public RegisterAction() {

    }

    public String execute() {
        String url = FAIL;
        errs = new AccountRegisterError();
        try {
            dto = new AccountDTO(username, fullname, email, phone);
            dto.setPassword(password);

            if (errs.checkValidation(dto, confirm)) {
                AccountDAO dao = new AccountDAO();
                boolean result = dao.registerUser(dto);
                if (result) {
                    registerSuccess = "Register successful";
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                errs.setUsernameErr("(Username is already existed)");
            }
        }

        return url;
    }

}
