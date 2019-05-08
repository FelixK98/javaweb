/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import java.util.List;
import javax.servlet.http.HttpSession;
import khoavm.com.daos.OrderDAO;
import khoavm.com.dto.OrderDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class GoToPaymentHistoryPageAction {
    public static final String SUCCESS = "user";
    public static final String FAIL = "notuser";
    public static final String ERROR = "dberror";
    private List<OrderDTO> orderList;
    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }
    
    
    
    public GoToPaymentHistoryPageAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession();
        String role = (String) session.getAttribute("ROLE");
        try {
            if(role != null && role.equals("user")){
                url = SUCCESS;
                String user = (String) session.getAttribute("USER");
                OrderDAO dao = new OrderDAO();
                orderList = dao.getHistoryOrderList(user);
            }
        } catch (Exception e) {
            url = ERROR;
            err = "Error " +e.getMessage();
        }
        return url;
    }
    
}
