/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import khoavm.daos.AccountDAO;

/**
 *
 * @author MinhKhoa
 */
public class DeleteAction {
    private String id, txtLastName;
    private String deleteFail;
    
    private static final String SEARCH_PAGE ="search";

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
    
    public DeleteAction() {
        
    }
    
    public String execute() throws Exception {
        AccountDAO dao =  new AccountDAO();
        boolean check = dao.deleteAccount(id);
        if(!check){
            deleteFail = "Delete failed";
        }
        return SEARCH_PAGE;
    }
    
}
