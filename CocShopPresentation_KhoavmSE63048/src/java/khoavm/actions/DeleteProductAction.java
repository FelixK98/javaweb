/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import khoavm.daos.ProductDAO;

/**
 *
 * @author MinhKhoa
 */
public class DeleteProductAction {
    private String txtProductName, proId;
    private String deleteErr;
    private String typeSelected;
    
    private static final String SEARCH_PAGE ="search";

    public String getTxtProductName() {
        return txtProductName;
    }

    public void setTxtProductName(String txtProductName) {
        this.txtProductName = txtProductName;
    }

    public String getDeleteErr() {
        return deleteErr;
    }

    public void setDeleteErr(String deleteErr) {
        this.deleteErr = deleteErr;
    }

    public String getTypeSelected() {
        return typeSelected;
    }

    public void setTypeSelected(String typeSelected) {
        this.typeSelected = typeSelected;
    }


    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
    
    public DeleteProductAction() {
    }
    
    public String execute() throws Exception {
        
        ProductDAO dao = new ProductDAO();
        boolean result = dao.deleteProduct(proId);
        if(!result){
            deleteErr = "Delete failed";
        }
        return SEARCH_PAGE;
    }
    
}
