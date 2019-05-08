/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.actions;

import javax.servlet.http.HttpSession;
import khoavm.com.daos.DisCountCodeDAO;
import khoavm.com.dto.CartObj;
import khoavm.com.dto.DiscountCodeDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author MinhKhoa
 */
public class CheckDiscountCodeAction {
    private static final String SUCCESS = "user";
    private static final String FAIL = "notuser";
    private static final String DBERROR = "dberror";
    private String code;
    private String codeChk;
    private Integer disCountPrice;
    private DiscountCodeDTO codeDTO;
    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    

    public Integer getDisCountPrice() {
        return disCountPrice;
    }

    public void setDisCountPrice(Integer disCountPrice) {
        this.disCountPrice = disCountPrice;
    }

  

    public DiscountCodeDTO getCodeDTO() {
        return codeDTO;
    }

    public void setCodeDTO(DiscountCodeDTO codeDTO) {
        this.codeDTO = codeDTO;
    }
    

    public String getCodeChk() {
        return codeChk;
    }

    public void setCodeChk(String codeChk) {
        this.codeChk = codeChk;
    }

  
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public CheckDiscountCodeAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            String role = (String) session.getAttribute("ROLE");
            if(role != null && role.equals("user")){
                url = SUCCESS;
                DisCountCodeDAO  discountCodeDAO = new DisCountCodeDAO();
                codeDTO = discountCodeDAO.checkDiscountCode(code);
                if(codeDTO == null){
                    codeChk = "You code is incorrect!";
                }
                else if(codeDTO.getQuantity().equals("0")){
                   codeChk = "This code has been used up";
                }
                else{
                    String user = (String) session.getAttribute("USER");
                    boolean usedChk = discountCodeDAO.checkUsedCode(codeDTO.getCode(), user);
                    if(usedChk){
                        codeChk = "You have already used this code";
                    }
                    else{
                        ServletActionContext.getRequest().setAttribute("isCodeAvailable", "OK");
                        codeChk = "Your code is available(" +  codeDTO.getDiscount() +"%)";
                        CartObj cart = (CartObj) session.getAttribute("CART");
                        Integer discountPercent = Integer.parseInt(codeDTO.getDiscount());
                        Integer totalPrice = cart.computeTotalPayment();                        
                        disCountPrice = (totalPrice *(100 - discountPercent))/100;
                        
                    }   
                }
                
                    
            }
        } catch (Exception e) {
            url = DBERROR;
            err = "Error : " +e.getMessage();
        }
        return url;
    }
    
}
