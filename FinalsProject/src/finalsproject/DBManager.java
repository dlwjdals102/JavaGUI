/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalsproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author LeeJeongMin
 */
public class DBManager {
    private static DBManager instance;
    
    String strDriver = "com.mysql.cj.jdbc.Driver";
    String strURL = "jdbc:mysql://10.200.72.191:3306/bank?characterEncoding-UTF-8&serverTimezone=UTC";
    String strUser = "root";
    String strPWD = "rootpw";
    
    Connection DB_con;
    Statement DB_stmt;
    ResultSet DB_rs;
    
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }
    
    public void dbOpen() throws IOException {
        try {
            Class.forName(strDriver);
            DB_con = DriverManager.getConnection(strURL, strUser, strPWD);
            DB_stmt = DB_con.createStatement();
        } catch (Exception e) {
            System.out.println("SQLException" + e.getMessage());
        }
    }
    
    public void dbClose() throws IOException {
        try {
            DB_stmt.close();
            DB_con.close();
        } catch (SQLException e) {
            System.out.println("SQLException" + e.getMessage());
        }
    }
    
}
