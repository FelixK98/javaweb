/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoavm.com.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MinhKhoa
 */
public class MyConnection implements Serializable{
    
    public static Connection openConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CoffeeHouse_Presentation", "sa", "123456");
        
    }
}
