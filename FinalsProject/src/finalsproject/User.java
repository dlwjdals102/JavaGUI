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
    private String name;
    private String accountNumber;
    private int accountMoney;
    
    private int haveMoney;
    
    public User(String id, String password, String name, String accountNumber, int accountMoney){
        this.id = id;
        this.password = password;
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountMoney = accountMoney;
        
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

    public int getMoney() {
        return accountMoney;
    }
    
    public int getHaveMoney() {
        return haveMoney;
    }
    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
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

    public void setMoney(int accountMoney) {
        this.accountMoney = accountMoney;
    }
    public void setHaveMoney(int haveMoney) {
        this.haveMoney = haveMoney;
    }
}
