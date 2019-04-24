/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import java.util.List;
import khoavm.daos.AccountDAO;
import khoavm.dtos.AccountDTO;

/**
 *
 * @author MinhKhoa
 */
public class SearchAction {
    private String txtSearch;
    private List<AccountDTO> accountList;
    private static final String SUCCESS = "success";
    public SearchAction() {
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    
    public String execute() throws Exception {
     AccountDAO dao = new AccountDAO();
     accountList = dao.searchByFullName(txtSearch);
     return SUCCESS;
         
    }
    
}
