/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bmicalculator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vince
 */
public class BMIChatGroup extends javax.swing.JFrame implements Runnable{
    Socket s;
    BufferedReader input;
    DataOutputStream output;
    Thread t;
    User accountAktif;

    /**
     * Creates new form BMIChatGroup
     */
    
    public void run() {
        while (true) {
            getMessage();
        }
    }
    
    private void getMessage() {
        try {
            String message = this.input.readLine();
            String[] part = message.split("~");
            
            ArrayList<Integer> kumpulanBlockedId = new ArrayList<>();
            kumpulanBlockedId.add(0);
            
            if(part[0].equals("chat")){ //kalau bmi
                for (int i = 4; i < part.length; i++) {
                    kumpulanBlockedId.add(Integer.parseInt(part[i]));
                }
                
                boolean cekStatus = true;
                for (int i = 0; i < kumpulanBlockedId.size(); i++) {
                    if (kumpulanBlockedId.get(i).equals(accountAktif.getId())) {
                        cekStatus = false; //kalau ada di blok
                        break;
                    } else{
                        cekStatus = true; //kalau aman
                    }
                }
                
                if(cekStatus == false){
                    jTextAreaDisplay.append("You have blocked this user!\n");
                }
                else if(cekStatus == true){
                    jTextAreaDisplay.append(part[2] + " : " + part[3] + "\n");
                }
            }
            else if(part[0].equals("block")){ //kalau ideal
                if(part[1].equals("unknown")){
                    jOptionPane1.showMessageDialog(this, "Gagal blokir! User tidak ditemukan");
                }
                else if(part[1].equals("berhasil")){
                    jOptionPane1.showMessageDialog(this, "User berhasil diblokir!");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(BMILoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        if (this.t == null) {
            this.t = new Thread(this, "myThread");
            this.t.start();
        }
    }
    
    public BMIChatGroup() {
        initComponents();
    }
    
    public BMIChatGroup(User account) {
        initComponents();
        try {
            accountAktif = account;
            String ip = "192.168.117.85";
            s = new Socket(ip, 10013); //string host dan int port
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.start();
            this.jTextAreaDisplay.append("Welcome to the group chat!, " + accountAktif.getEmail() + "\n");
            output = new DataOutputStream(s.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(BMIChatGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        panelJudul = new javax.swing.JPanel();
        labelJudul = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDisplay = new javax.swing.JTextArea();
        labelTinggiBadan = new javax.swing.JLabel();
        textFieldChat = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();
        buttonBlock = new javax.swing.JButton();
        buttonBack1 = new javax.swing.JButton();
        textFieldChatUser = new javax.swing.JTextField();
        labelTinggiBadan1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelJudul.setBackground(new java.awt.Color(0, 0, 102));

        labelJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelJudul.setForeground(new java.awt.Color(255, 255, 255));
        labelJudul.setText("GROUP CHAT");

        javax.swing.GroupLayout panelJudulLayout = new javax.swing.GroupLayout(panelJudul);
        panelJudul.setLayout(panelJudulLayout);
        panelJudulLayout.setHorizontalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudulLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(labelJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJudulLayout.setVerticalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextAreaDisplay.setColumns(20);
        jTextAreaDisplay.setRows(5);
        jTextAreaDisplay.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaDisplay);

        labelTinggiBadan.setText("Chat :");

        buttonSend.setBackground(new java.awt.Color(0, 0, 102));
        buttonSend.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonSend.setForeground(new java.awt.Color(255, 255, 255));
        buttonSend.setText("Send");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        buttonBlock.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonBlock.setForeground(new java.awt.Color(204, 0, 0));
        buttonBlock.setText("Block");
        buttonBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBlockActionPerformed(evt);
            }
        });

        buttonBack1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonBack1.setForeground(new java.awt.Color(0, 0, 102));
        buttonBack1.setText("Back");
        buttonBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBack1ActionPerformed(evt);
            }
        });

        labelTinggiBadan1.setText("User :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonBack1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSend))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTinggiBadan)
                            .addComponent(labelTinggiBadan1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldChat)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldChatUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonBlock)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTinggiBadan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldChatUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonBlock))
                    .addComponent(labelTinggiBadan1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSend)
                    .addComponent(buttonBack1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        // TODO add your handling code here:
        try {
            this.output.writeBytes("chat~" + String.valueOf(accountAktif.getId()) + "~" + accountAktif.getEmail() + "~" + textFieldChat.getText() + "\n");
        } catch (IOException ex) {
            Logger.getLogger(BMIChatGroup.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }//GEN-LAST:event_buttonSendActionPerformed

    private void buttonBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBlockActionPerformed
        try {
            this.output.writeBytes("block~" + String.valueOf(accountAktif.getId()) + "~" + textFieldChatUser.getText() + "\n");
        } catch (IOException ex) {
            Logger.getLogger(BMIChatGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonBlockActionPerformed

    private void buttonBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBack1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_buttonBack1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BMIChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BMIChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BMIChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BMIChatGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BMIChatGroup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack1;
    private javax.swing.JButton buttonBlock;
    private javax.swing.JButton buttonSend;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDisplay;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelTinggiBadan;
    private javax.swing.JLabel labelTinggiBadan1;
    private javax.swing.JPanel panelJudul;
    private javax.swing.JTextField textFieldChat;
    private javax.swing.JTextField textFieldChatUser;
    // End of variables declaration//GEN-END:variables
}
