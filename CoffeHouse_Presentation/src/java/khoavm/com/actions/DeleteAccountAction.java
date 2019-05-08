/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.ArrayList;
import java.util.List;
import khoavm.com.daos.AccountDAO;
import khoavm.com.dto.AccountDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class DeleteAccountAction {

    private static final String SUCCESS = "success"; // redirect to search
    private static final String FAIL = "fail"; // go to error page

    private String name; // name of account deleted
    private String search; // use for search after delete
    private String err;
    public String getName() {
        return name;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public DeleteAccountAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        if (role != null && role.equals("admin")) {
            try {
                AccountDAO dao = new AccountDAO();
                boolean rs = dao.deleteAccount(name);
                if (rs) {
                    //update context account
                    List<AccountDTO> accountList = (List<AccountDTO>) ServletActionContext.getServletContext().getAttribute("ACCOUNTS");
                    for (AccountDTO dto : accountList) {
                        if (dto.getUsername().equals(name)) {
                            accountList.remove(dto);
                            break;
                        }
                    }
                    ServletActionContext.getServletContext().setAttribute("ACCOUNTS", accountList);
                    url = SUCCESS;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
             err = "You dont have admin permission";
        }

        return url;
    }

}
