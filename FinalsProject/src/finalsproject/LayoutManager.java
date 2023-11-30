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
    
    private LoginPage loginPage;                // 로그인 화면
    private JoinPage joinPage;              // 회원가입 화면
    
    private BankMainPage bankMainPage;          // 메인 화면
    private BankDepositPage bankDepositPage;    // 입금 화면  
    private BankWithdrawPage bankWithdrawPage;  // 출금 화면
    private BankTransferPage bankTransferPage;  // 송금 화면
    private BankInquiry bankInquiry;
    private BankTransactionHistoryPage bankTransactionHistoryPage;  // 거래내역 화면    
    private CaptChaPage captchaPage;
    
    public LayoutManager(){
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        loginPage = new LoginPage();
        joinPage = new JoinPage();
        
        bankMainPage = new BankMainPage();
        bankDepositPage = new BankDepositPage();
        bankWithdrawPage = new BankWithdrawPage();
        bankTransferPage = new BankTransferPage();
        bankInquiry = new BankInquiry();
        bankTransactionHistoryPage = new BankTransactionHistoryPage();
        captchaPage = new CaptChaPage();
    }
    
    public static LayoutManager getInstance() {
        if (instance == null) {
            instance = new LayoutManager();
        }
        return instance;
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
    public CaptChaPage getCaptchaPage(){
        return captchaPage;
    }
    

    void setupCardLayout(Container container) {
        container.setLayout(new BorderLayout());
        container.add(cardPanel, BorderLayout.CENTER);
        
        // Add pages to the cardPanel
        cardPanel.add(loginPage, "loginPage");
        cardPanel.add(joinPage, "joinPage");
        
        cardPanel.add(bankMainPage, "bankMainPage");
        cardPanel.add(bankDepositPage, "bankDepositPage");
        cardPanel.add(bankWithdrawPage, "bankWithdrawPage");
        cardPanel.add(bankTransferPage, "bankTransferPage");
        cardPanel.add(bankInquiry, "bankInquiry");
        cardPanel.add(bankTransactionHistoryPage, "bankTransactionHistoryPage");
        cardPanel.add(captchaPage, "captchaPage");
        
        // Show the initial page
        cardLayout.show(cardPanel, "loginPage");
        
    }
    
    public void setLayout(String panel){
        bankMainPage.setUserInfo();
        bankDepositPage.setUserInfo();
        bankWithdrawPage.setUserInfo();
        bankInquiry.setUserInfo();
        
        cardLayout.show(cardPanel, panel);
    }
    
}
