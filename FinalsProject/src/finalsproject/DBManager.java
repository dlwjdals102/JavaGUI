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
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LeeJeongMin
 */
public class DBManager {
    private static DBManager instance;
    
    String strDriver = "com.mysql.cj.jdbc.Driver";
    //String strURL = "jdbc:mysql://127.0.0.1:3306/bank?characterEncoding-UTF-8&serverTimezone=UTC";
    String strURL = "jdbc:mysql://localhost:3306/bank?characterEncoding-UTF-8&serverTimezone=UTC";
    String strUser = "root";
    String strPWD = "rootpw";
    
    Connection DB_con;
    Statement DB_stmt;
    ResultSet DB_rs;
    
    String currID = "";
    
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
    
    public User getCurrUser() {
        if (currID.isBlank()) {
            return null; // 로그인이 안되어있으면
        }
        
        String query = "select * from user where id='"+currID+"'";
        User user = new User();
        
        try {
            dbOpen();
            DB_rs = DB_stmt.executeQuery(query);
            
            if (DB_rs.next()) {
                user.setId(DB_rs.getString("id"));
                user.setPassword(DB_rs.getString("password"));
                user.setName(DB_rs.getString("name"));
                user.setAccountNumber(DB_rs.getString("account"));
                user.setMoney(DB_rs.getInt("money"));
                dbClose();
                
                return user;
            }
            dbClose();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public int login(String id, String password) {
        String query = "select password from user where id='"+id+"'";
        
        try {
            dbOpen();
            DB_rs = DB_stmt.executeQuery(query);
            
            if (DB_rs.next()) {
                if (DB_rs.getString(1).equals(password)){
                    currID = id;
                    return 1;   // 로그인 성공
                } else {
                    return 0;   // 실패
                }
            }
            dbClose();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return -1;  // db오류
    }
    
    public int logout(){
        currID = "";
        LayoutManager.getInstance().getCaptchaPage().refresh();
        return 1;
    }
    
    public int join(User user){
        String query = "insert into user values('" + user.getId()+ "', '" 
                + user.getPassword() + "', '" 
                + user.getName() + "', '" 
                + user.getAccountNumber() + "', " 
                + 0 + ")";
        
        int result = 0;
        try {
            dbOpen();
            result = DB_stmt.executeUpdate(query);
            dbClose();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return result;
    }
    
    public String getAccount(){
        String accountNumber = "";
        boolean isUnique = false;
            while (!isUnique) {
                // 계좌 번호 생성
                Random random = new Random();
                int min = 10000000; // Minimum 8-digit number
                int max = 99999999; // Maximum 8-digit number
                
                accountNumber = String.valueOf(random.nextInt(max - min + 1) + min);
                // DB에서 고유성 확인
                if (checkUniquenessInDB(accountNumber)) {
                    isUnique = true;
                }
            }
        return accountNumber;
    }
    
    private boolean checkUniquenessInDB(String accountNumber) {
        try {
            DBManager dbManager = DBManager.getInstance();
            dbManager.dbOpen();
            String query = "SELECT COUNT(*) FROM User WHERE account='"+accountNumber+"'";
            
            dbManager.DB_rs = dbManager.DB_stmt.executeQuery(query);
            
            if (dbManager.DB_rs.next()) {
                int count = dbManager.DB_rs.getInt(1);
                
                // 이미 존재하는 아이디
                if (count > 0) {
                    return false;
                } else {
                    return true;
                }
            }
            dbManager.dbClose();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(JoinPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public int withdraw(String id, int takeOutMoeny){
        int haveMoney = 0;
        int result = 0;
        
        String query = "select money from user where id = '" + id + "'";
        
        try {
            dbOpen();
            DB_rs = DB_stmt.executeQuery(query);
            if (DB_rs.next()) {
                haveMoney = DB_rs.getInt(1);
                if (haveMoney < takeOutMoeny){
                return -1;  // 빼낼돈이 더 많으면 실패
                }
            }
            query = "update user set money=money-" + takeOutMoeny + " where id='" + id + "'";
            result = DB_stmt.executeUpdate(query);
            dbClose();
        } catch (IOException  | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int transfer(String id, String targetAccount, int sendMoney) {
        
        String query = "select count(*) from user where account='"+targetAccount+"'";
        int result = 0;
        try {
            dbOpen();
            
            // 상대방 계좌가 있나 확인
            DB_rs = DB_stmt.executeQuery(query);
            if (DB_rs.next()) {
                int count = DB_rs.getInt(1);
                
                if (count == 0) {
                    return -1;
                }
            }
            
            // 내 계좌에 돈이 있나 확인
            query = "select money from user where id = '" + id + "'";
            DB_rs = DB_stmt.executeQuery(query);
            if (DB_rs.next()) {
                int money = DB_rs.getInt(1);
                
                if (money < sendMoney) {
                    return -2;
                }
            }
            
            // 모든 조건을 넘겼을 때 (송금)
            query = "update user set money=money+" + sendMoney + " where account='" + targetAccount + "'";  // 상대방 계좌 돈++
            result = DB_stmt.executeUpdate(query);
            query = "update user set money=money-"+ sendMoney + " where id='"+ id + "'";  // 내 계좌 돈--
            result = DB_stmt.executeUpdate(query);
            
            dbClose();
        } catch (IOException  | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public int deposit(String id, int depositMoney) {
        int result = 0;
        String query = "update user set money=money+" + depositMoney + " where id='" + id + "'";
        try {
            dbOpen();
            result = DB_stmt.executeUpdate(query);
            dbClose();
        } catch (IOException  | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int scanHistory(String id, String content){
        
        String query = "insert into history values(" + getNext() + ", '" 
                + id + "', '" 
                + getDate() + "', '"
                + content + "')";
        
        int result = 0;
        try {
            dbOpen();
            result = DB_stmt.executeUpdate(query);
            dbClose();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return result;
        
    }
    
    public int getNext() {
        String query = "select id from history order by id desc";
        int result = 1;
        try {
            dbOpen();
            DB_rs = DB_stmt.executeQuery(query);
            
            if (DB_rs.next()) {
                result =  DB_rs.getInt(1) + 1;
            }
            dbClose();
        } catch (IOException  | SQLException ex) {
            result = -1;
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String getDate(){
        String query = "select now()";
        String result = "";
        try {
            dbOpen();
            DB_rs = DB_stmt.executeQuery(query);
            
            if (DB_rs.next()) {
                result = DB_rs.getString(1);
            }
            dbClose();
        } catch (IOException  | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public ArrayList<History> printHistory(String id){
        ArrayList<History> historys = new ArrayList<History>();
        
        String query = "select * from history where userID='" + id + "'";
        
        
        return null;
    }
}

