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
public class GetProductAction {
    private List<ProductDTO> foodList;
    private List<ProductDTO> drinkList;
    
    private static final String USER = "user";
    public List<ProductDTO> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<ProductDTO> foodList) {
        this.foodList = foodList;
    }

    public List<ProductDTO> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(List<ProductDTO> drinkList) {
        this.drinkList = drinkList;
    }
    
    public GetProductAction() {
    }
    
    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        foodList = dao.searchByType("Food");
        drinkList = dao.searchByType("Drink");
        return USER;
    }
    
}
