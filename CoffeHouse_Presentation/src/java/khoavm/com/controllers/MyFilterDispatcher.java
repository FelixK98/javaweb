/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.controllers;


import java.util.List;


import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import khoavm.com.daos.AccountDAO;
import khoavm.com.daos.ProductDAO;
import khoavm.com.daos.SupplierDAO;
import khoavm.com.dto.AccountDTO;
import khoavm.com.dto.ProductDTO;
import khoavm.com.dto.SupplierDTO;



import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 *
 * @author MinhKhoa
 */
public class MyFilterDispatcher extends StrutsPrepareAndExecuteFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
     
        try {                     

            // ADD Accounts table to context
            AccountDAO accountDAO = new AccountDAO();
            List<AccountDTO> accountList = accountDAO.getAccountList();
             filterConfig.getServletContext().setAttribute("ACCOUNTS", accountList);
            //ADD Products table  to context
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> productList = productDAO.getProductList();
           filterConfig.getServletContext().setAttribute("PRODUCTS", productList);
           //ADD Supplier table to context
            SupplierDAO supplierDAO = new SupplierDAO();
            List<SupplierDTO> supplierList = supplierDAO.getSupplier();
            filterConfig.getServletContext().setAttribute("SUPPLIERS", supplierList);
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    

}
