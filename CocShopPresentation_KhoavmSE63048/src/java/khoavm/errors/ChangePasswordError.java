/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.errors;

import java.io.Serializable;

/**
 *
 * @author MinhKhoa
 */
public class ChangePasswordError implements Serializable{
    private String usernameErr, passwordErr, newpasswordErr,confirmErr, incorectUsernameOrPasswordErr;

    public String getConfirmErr() {
        return confirmErr;
    }

    public void setConfirmErr(String confirmErr) {
        this.confirmErr = confirmErr;
    }

    public String getUsernameErr() {
        return usernameErr;
    }

    public void setUsernameErr(String usernameErr) {
        this.usernameErr = usernameErr;
    }

    public String getPasswordErr() {
        return passwordErr;
    }

    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

    public String getNewpasswordErr() {
        return newpasswordErr;
    }

    public void setNewpasswordErr(String newpasswordErr) {
        this.newpasswordErr = newpasswordErr;
    }

    public String getIncorectUsernameOrPasswordErr() {
        return incorectUsernameOrPasswordErr;
    }

    public void setIncorectUsernameOrPasswordErr(String incorectUsernameOrPasswordErr) {
        this.incorectUsernameOrPasswordErr = incorectUsernameOrPasswordErr;
    }
    public boolean checkValidate(String username, String password,String newPassword ,String confirm){
        boolean check = true;
        if(username.isEmpty()){
            usernameErr = "*Username can't be blank*";
            check = false;
        }
        if(password.isEmpty()){
           passwordErr = "*Password can't be blank*";
           check = false;
        }
         if(confirm.isEmpty()){
            confirmErr = "*Confirm can't be blank*";
            check = false;
        }
        if(newPassword.length() < 6 || newPassword.length() > 30){
            newpasswordErr = "*Length must be 6-30 characters*";
            check = false;
        }
        else if(password.equals(newPassword)){
            newpasswordErr = "*You must enter new password!*";
            check = false;
        }
       else if(!newPassword.equals(confirm)){
            newpasswordErr = "*Password and Confirm is not matched*";
            check = false;
        }
        return check;
    }
}
