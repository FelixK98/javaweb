/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;



import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class StartUpAction {
    private static final String INDEX = "index";
    private static final String ADMIN = "admin";
    private static final String USER = "user";    
 
    public StartUpAction() {
    }
    
    public String execute() throws Exception {
        
        String url = INDEX;
        HttpSession session = ServletActionContext.getRequest().getSession();
        if(session.getAttribute("ROLE") != null){
            String role = (String) session.getAttribute("ROLE");
            if(role.equals("admin")){
                url = ADMIN;
            }
            else{
                url = USER;
            }
        }
        
        return url;
        
    }
    
}
