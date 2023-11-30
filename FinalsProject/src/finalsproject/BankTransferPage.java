/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package finalsproject;

import javax.swing.JOptionPane;

/**
 * 송금
 * @author LeeJeongMin
 */
public class BankTransferPage extends javax.swing.JPanel {

    /**
     * Creates new form BankTransfer
     */
    public BankTransferPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnTransfer = new javax.swing.JButton();
        txtAccountNumber = new javax.swing.JTextField();
        txtMoney = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

        jLabel1.setText("계좌번호");

        jLabel2.setText("금액");

        btnTransfer.setText("송금");
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });

        btnBack.setText("뒤로가기");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addComponent(btnTransfer)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAccountNumber)
                            .addComponent(txtMoney, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnTransfer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addContainerGap(109, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        DBManager dBManager = DBManager.getInstance();
        User user = dBManager.getCurrUser();
        
        String accountNumber = txtAccountNumber.getText();
        int sendMoney = Integer.parseInt(txtMoney.getText());
        
        int result = dBManager.transfer(user.getId(), accountNumber, sendMoney);
        
        if (result == -1) {
            JOptionPane.showMessageDialog(this, "존재하지 않는 계좌번호입니다.", "Fail", JOptionPane.INFORMATION_MESSAGE);
        } else if (result == -2) {
            JOptionPane.showMessageDialog(this, "돈이 부족합니다.", "Fail", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "완료되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
            LayoutManager.getInstance().setLayout("bankMainPage");
        }
        
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        LayoutManager.getInstance().setLayout("bankMainPage");
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtAccountNumber;
    private javax.swing.JTextField txtMoney;
    // End of variables declaration//GEN-END:variables
}
