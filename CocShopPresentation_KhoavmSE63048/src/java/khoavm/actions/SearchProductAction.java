/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.actions;

import java.util.List;
import khoavm.daos.ProductDAO;
import khoavm.dtos.ProductDTO;

/**
 *
 * @author MinhKhoa
 */
public class SearchProductAction {
    private String txtProductSearch;
    private String typeSelect;
    private List<ProductDTO> searchList;
    private static final String SEARCH_PAGE = "search";

    public String getTxtProductSearch() {
        return txtProductSearch;
    }

    public void setTxtProductSearch(String txtProductSearch) {
        this.txtProductSearch = txtProductSearch;
    }

    public String getTypeSelect() {
        return typeSelect;
    }

    public void setTypeSelect(String typeSelect) {
        this.typeSelect = typeSelect;
    }

    public List<ProductDTO> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<ProductDTO> searchList) {
        this.searchList = searchList;
    }
    
    public SearchProductAction() {
    }
    
    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        System.out.println(typeSelect + " at SearchProductAction");
        if(typeSelect.equals("All")){
            searchList = dao.searchByName(txtProductSearch);
        }else{
            searchList = dao.searchByNameAndType(txtProductSearch, typeSelect);
        }
        return SEARCH_PAGE;
    }
    
}
