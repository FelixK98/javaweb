/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.errors;

import java.io.Serializable;
import khoavm.daos.AccountDAO;
import khoavm.dtos.AccountDTO;

/**
 *
 * @author MinhKhoa
 */
public class AccountError implements Serializable {

    protected String fullnameErr, emailErr, phoneErr;
    protected String executeErr;

    public String getPhoneErr() {
        return phoneErr;
    }

    public void setPhoneErr(String phoneErr) {
        this.phoneErr = phoneErr;
    }

    public String getExecuteErr() {
        return executeErr;
    }

    public void setExecuteErr(String executeErr) {
        this.executeErr = executeErr;
    }



    public String getFullnameErr() {
        return fullnameErr;
    }

    public void setFullnameErr(String fullnameErr) {
        this.fullnameErr = fullnameErr;
    }

    public String getEmailErr() {
        return emailErr;
    }

    public void setEmailErr(String emailErr) {
        this.emailErr = emailErr;
    }

    public boolean checkValidation(AccountDTO dto) throws Exception {
        boolean check = true;
        AccountDAO dao = new AccountDAO();
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
        return check;
    }
}
