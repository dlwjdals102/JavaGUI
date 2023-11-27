/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package finalsproject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *  회원가입
 * @author LeeJeongMin
 */
public class SignUpPage extends javax.swing.JPanel {

    /**
     * Creates new form BankCreateAccount
     */
    public SignUpPage() {
        initComponents();
    }
    
    private int duplicateCheck = 0;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDuplicate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtPasswordCheck = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();

        btnDuplicate.setText("중복확인");
        btnDuplicate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicateActionPerformed(evt);
            }
        });

        jLabel1.setText("아이디");

        jLabel2.setText("비밀번호");

        jLabel3.setText("비밀번호 확인");

        jLabel4.setText("이름");

        btnSignUp.setText("회원가입");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(260, 260, 260)))
                        .addComponent(btnDuplicate))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSignUp)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtPasswordCheck)
                                    .addComponent(txtName)
                                    .addComponent(txtID))))
                        .addGap(131, 131, 131)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDuplicate)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPasswordCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSignUp)
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDuplicateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuplicateActionPerformed
        // 중복확인버튼
        
        String id = txtID.getText();
        
        if (duplicateCheck == 0) {
            duplicateCheck++;
        }
        try {
            DBManager dbManager = DBManager.getInstance();
            dbManager.dbOpen();
            String query = "SELECT COUNT(*) FROM User WHERE id='"+id+"'";
            
            dbManager.DB_rs = dbManager.DB_stmt.executeQuery(query);
            
            if (dbManager.DB_rs.next()) {
                int count = dbManager.DB_rs.getInt(1);
                
                // 이미 존재하는 아이디
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "이미 존재하는 아이디 입니다. 다시 입력해주세요", 
                                "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    duplicateCheck++;    
                }
            }
            dbManager.dbClose();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다.", "Valid ID", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnDuplicateActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        // 회원가입버튼
        
        if (duplicateCheck < 2) {
            JOptionPane.showMessageDialog(this, "중복확인을 확인하세요.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = txtID.getText();
        String pw = txtPassword.getText();
        String pwCheck = txtPasswordCheck.getText();
        String name = txtName.getText();
        
        if (id.isBlank()|| pw.isBlank() || pwCheck.isBlank() || name.isBlank()) {
            JOptionPane.showMessageDialog(this, "입력란을 확인해주세요.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // db정보 입력
        try {
            DBManager dbManager = DBManager.getInstance();
            dbManager.dbOpen();
            
            String accountNumber = "";
            boolean isUnique = false;
            while (!isUnique) {
                // 계좌 번호 생성
                accountNumber = UUID.randomUUID().toString();
                // DB에서 고유성 확인
                if (checkUniquenessInDB(accountNumber)) {
                    isUnique = true;
                }
            }
            
            String query = "Insert Into User Values(";
            query += "'" + id + "', ";
            query += "'" + pw + "', ";
            query += "'" + name + "', ";
            query += "'" + accountNumber + "')";
            
            dbManager.DB_stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dbManager.dbClose();
        } catch (IOException  | SQLException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        // 로그인 화면으로 이동
        LayoutManager.getInstance().setLayout("loginPage");
        
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void txtIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyPressed
        duplicateCheck = 0;
    }//GEN-LAST:event_txtIDKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDuplicate;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPasswordCheck;
    // End of variables declaration//GEN-END:variables

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
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}