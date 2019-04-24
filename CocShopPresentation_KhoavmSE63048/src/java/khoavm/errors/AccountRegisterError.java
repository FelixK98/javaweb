/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.errors;

import khoavm.daos.AccountDAO;
import khoavm.dtos.AccountDTO;

/**
 *
 * @author MinhKhoa
 */
public class AccountRegisterError extends AccountError {

    String usernameErr, passwordErr;

    public AccountRegisterError() {

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


    public boolean checkValidation(AccountDTO dto, String passConfirm) throws Exception {
        boolean check = true;
        AccountDAO dao = new AccountDAO();
        if (dto.getUsername().length() < 3 || dto.getUsername().length() > 20) {
            usernameErr = "*Username length must be 3-20 characters*";
        }
        if (dto.getFullname().length() < 4 || dto.getFullname().length() > 30) {
            fullnameErr = "*Fullname length must be 4-30 characters*";
            check = false;
        }
        if (!dto.getEmail().matches("\\w+[@]\\w+[.]\\w+")) {
            emailErr = "*Your email must be valid(example@abc.xyz)*";
            check = false;
        } else if (dao.isDuplicate("Email", dto.getEmail(), dto.getUsername())) {
            emailErr = "*Your email is already existed*";
            check = false;
        }
        if (!dto.getPhone().matches("\\d{10,11}")) {
            phoneErr = "*Your phone number must be digits and contain 10-11 numbers*";
            check = false;
        } else if (dao.isDuplicate("Phone", dto.getPhone(), dto.getUsername())) {

            phoneErr = "*Your phone is already existed*";
            check = false;
        }
        if(dto.getPassword().length()< 6 || dto.getPassword().length() > 30){
            passwordErr = "*Password's length must be 6-30 characters*";
        }
        else if (!dto.getPassword().equals(passConfirm)) {
            passwordErr = "*Password doesnt match confirm*";
        }
        return check;
    }
}
