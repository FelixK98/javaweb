/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import khoavm.daos.AccountDAO;
import khoavm.dtos.AccountDTO;

/**
 *
 * @author MinhKhoa
 */
public class EditAction {
    private String id, txtLastName;
    private AccountDTO dto;
    private static final String UPDATE = "update";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(String txtLastName) {
        this.txtLastName = txtLastName;
    }

    public AccountDTO getDto() {
        return dto;
    }

    public void setDto(AccountDTO dto) {
        this.dto = dto;
    }
    
    public EditAction() {
    }
    
    public String execute() throws Exception {
        AccountDAO dao = new AccountDAO();
        dto = dao.searchByUsername(id);
        return UPDATE;
    }
    
}
