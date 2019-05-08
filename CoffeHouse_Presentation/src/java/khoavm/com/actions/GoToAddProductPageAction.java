/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class GoToAddProductPageAction {
    private static final String FAIL="fail";
    private static final String SUCCESS="success";
    
    private String type;
    private String err;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    
    
    public GoToAddProductPageAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        String role = (String) ServletActionContext.getRequest().getSession().getAttribute("ROLE");
        try {
            if(role.equals("admin")){
                url = SUCCESS;
            }
            else
              err = "You dont have permission";  
        } catch (Exception e) {
            err = "You dont have permission";
        }
        return url;
    }
    
}
