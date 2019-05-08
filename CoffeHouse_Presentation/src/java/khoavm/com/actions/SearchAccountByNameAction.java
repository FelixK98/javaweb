/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.ArrayList;
import java.util.List;

import khoavm.com.dto.AccountDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class SearchAccountByNameAction {
    private String search;
    private List<AccountDTO> searchList;
    
    
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

  
    
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<AccountDTO> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<AccountDTO> searchList) {
        this.searchList = searchList;
    }

    
    public SearchAccountByNameAction(String search) {
        this.search = search;
    }
    
    public SearchAccountByNameAction() {
    }
    
    public String execute() throws Exception {                        
        // Call Account context
        List<AccountDTO> accountList = (List<AccountDTO>) ServletActionContext.getServletContext().getAttribute("ACCOUNTS");
        searchList = new ArrayList<>();
        for (AccountDTO dto : accountList) {
            if(dto.getUsername().contains(search) && dto.getRole().equals("user")){
                searchList.add(dto);
            }
        }
        return role;
    }
    
}
