/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalsproject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JPanel;

/**
 *
 * @author LeeJeongMin
 */
public class LayoutManager {
    
    private static LayoutManager instance;
    
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    private User user;
    
    private LoginPage loginPage;
    
    private BankMainPage bankMainPage;          // 메인화면
    private BankDepositPage bankDepositPage;    // 입금화면  
    private BankWithdrawPage bankWithdrawPage;  // 출금화면
    private BankTransferPage bankTransferPage;  // 송금화면
    
    public LayoutManager(){
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        loginPage = new LoginPage();
        
        bankMainPage = new BankMainPage();
        bankDepositPage = new BankDepositPage();
        bankWithdrawPage = new BankWithdrawPage();
        bankTransferPage = new BankTransferPage();
    }
    
    public static LayoutManager getInstance() {
        if (instance == null) {
            instance = new LayoutManager();
        }
        return instance;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public User getCurrentUser() {
        return user;
    }
    
     public LoginPage getLoginPage() {
        return loginPage;
    }
     

    public BankMainPage getBankMainPage() {
        return bankMainPage;
    }
    public BankDepositPage getBankDepositPage() {
        return bankDepositPage;
    }
    public BankWithdrawPage getBankWithdrawPage() {
        return bankWithdrawPage;
    }
    public BankTransferPage getBankTransferPage() {
        return bankTransferPage;
    }
    

    void setupCardLayout(Container container) {
        container.setLayout(new BorderLayout());
        container.add(cardPanel, BorderLayout.CENTER);
        
        // Add pages to the cardPanel
        cardPanel.add(loginPage, "loginPage");
        cardPanel.add(bankMainPage, "bankMainPage");
        cardPanel.add(bankDepositPage, "bankDepositPage");
        cardPanel.add(bankWithdrawPage, "bankWithdrawPage");
        cardPanel.add(bankTransferPage, "bankTransferPage");
        
        // Show the initial page
        cardLayout.show(cardPanel, "bankMainPage");
        
    }
    
    public void setLayout(String panel){
        cardLayout.show(cardPanel, panel);
    }
    
    public void previousLayout(){
        cardLayout.previous(cardPanel);
    }
    
}
