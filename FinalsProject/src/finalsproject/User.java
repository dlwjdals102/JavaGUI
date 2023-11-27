/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalsproject;

/**
 *
 * @author LeeJeongMin
 */
public class User {
    private String id;
    private String password;
    private String accountNumber;
    private String accountMoney;
    
    private int haveMoney;
    
    public User(String id, String password, String accountNumber, String money){
        this.id = id;
        this.password = password;
        this.accountNumber = accountNumber;
        this.accountMoney = money;
        
        haveMoney = 100000;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getMoney() {
        return accountMoney;
    }
    
    public int getHaveMoney() {
        return haveMoney;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setMoney(String money) {
        this.accountMoney = money;
    }
    public void setHaveMoney(int haveMoney) {
        this.haveMoney = haveMoney;
    }
}
