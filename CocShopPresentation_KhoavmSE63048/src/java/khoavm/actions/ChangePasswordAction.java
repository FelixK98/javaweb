/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import khoavm.daos.AccountDAO;
import khoavm.errors.ChangePasswordError;

/**
 *
 * @author MinhKhoa
 */
public class ChangePasswordAction {
    private String username, password, newPassword, confirm;
    private ChangePasswordError errs;
    private String successChangePassword;
    
    private final static String SUCCESS ="success";
    private final static String FAIL = "fail";

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public ChangePasswordError getErrs() {
        return errs;
    }

    public void setErrs(ChangePasswordError errs) {
        this.errs = errs;
    }

    public String getSuccessChangePassword() {
        return successChangePassword;
    }

    public void setSuccessChangePassword(String successChangePassword) {
        this.successChangePassword = successChangePassword;
    }
    
    public ChangePasswordAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        errs = new ChangePasswordError();
        if(errs.checkValidate(username, password, newPassword, confirm)){
            AccountDAO dao = new AccountDAO();
            boolean result = dao.updatePassword(username, password, newPassword);
            if(result){
                successChangePassword = "Change password successful";
                url = SUCCESS;
            }else{
                errs.setIncorectUsernameOrPasswordErr("Username or Password is not correct");
            }                    
        }
        return url;
    }
    
}
