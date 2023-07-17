/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bmicalculator;

import classBMICalculator.User;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vince
 */
public class BMICalculatorFrame extends javax.swing.JFrame implements Runnable{
    Socket s;
    BufferedReader input;
    DataOutputStream output;
    Thread t;
    User accountAktif;
    double tinggi;
    double berat;
    
    public void run() {
        while (true) {
            getMessage();
        }
    }
    
    private void getMessage() {
        try {
            textAreaHasil.setText("");
            textAreaHasil.append(this.input.readLine()+"\n");
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
    public BMICalculatorFrame() {
        initComponents();
    }
    
    public BMICalculatorFrame(User account) {
        initComponents();
        try {
            accountAktif = account;
            String ip = "192.168.43.212";
            s = new Socket(ip, 10013); //string host dan int port
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.start();
            output = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(BMICalculatorFrame.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelJudul = new javax.swing.JPanel();
        labelJudul = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaHasil = new javax.swing.JTextArea();
        labelBeratBadan = new javax.swing.JLabel();
        textFieldBerat = new javax.swing.JTextField();
        textFieldTinggi = new javax.swing.JTextField();
        labelTinggiBadan = new javax.swing.JLabel();
        radioButtonBeratBadanIdeal = new javax.swing.JRadioButton();
        radioButtonBMI = new javax.swing.JRadioButton();
        buttonHitung = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelJudul.setBackground(new java.awt.Color(0, 0, 102));

        labelJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelJudul.setForeground(new java.awt.Color(255, 255, 255));
        labelJudul.setText("BMI CALCULATOR");

        javax.swing.GroupLayout panelJudulLayout = new javax.swing.GroupLayout(panelJudul);
        panelJudul.setLayout(panelJudulLayout);
        panelJudulLayout.setHorizontalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJudulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelJudul)
                .addGap(94, 94, 94))
        );
        panelJudulLayout.setVerticalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textAreaHasil.setColumns(20);
        textAreaHasil.setRows(5);
        jScrollPane2.setViewportView(textAreaHasil);

        labelBeratBadan.setText("Berat Badan:");

        labelTinggiBadan.setText("Tinggi Badan:");

        buttonGroup1.add(radioButtonBeratBadanIdeal);
        radioButtonBeratBadanIdeal.setText("Hitung Berat Badan Ideal");
        radioButtonBeratBadanIdeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonBeratBadanIdealActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioButtonBMI);
        radioButtonBMI.setSelected(true);
        radioButtonBMI.setText("Hitung BMI");
        radioButtonBMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonBMIActionPerformed(evt);
            }
        });

        buttonHitung.setBackground(new java.awt.Color(0, 0, 102));
        buttonHitung.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonHitung.setForeground(new java.awt.Color(255, 255, 255));
        buttonHitung.setText("Hitung");
        buttonHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHitungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(labelBeratBadan)
                                .addGap(18, 18, 18)
                                .addComponent(textFieldBerat))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTinggiBadan)
                                .addGap(17, 17, 17)
                                .addComponent(textFieldTinggi))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 24, Short.MAX_VALUE)
                                .addComponent(radioButtonBMI)
                                .addGap(37, 37, 37)
                                .addComponent(radioButtonBeratBadanIdeal)
                                .addGap(11, 11, 11))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonHitung)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioButtonBMI)
                    .addComponent(radioButtonBeratBadanIdeal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTinggiBadan)
                    .addComponent(textFieldTinggi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBeratBadan)
                    .addComponent(textFieldBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonHitung)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioButtonBMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonBMIActionPerformed
        labelBeratBadan.setEnabled(true);
        textFieldBerat.setEnabled(true);
        textFieldTinggi.setEnabled(true);
    }//GEN-LAST:event_radioButtonBMIActionPerformed

    private void radioButtonBeratBadanIdealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonBeratBadanIdealActionPerformed
        labelBeratBadan.setEnabled(false);
        textFieldBerat.setEnabled(false);
        textFieldTinggi.setEnabled(true);
    }//GEN-LAST:event_radioButtonBeratBadanIdealActionPerformed

    private void buttonHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHitungActionPerformed
        try {
            if(radioButtonBMI.isSelected()){
                this.tinggi = Double.parseDouble(textFieldTinggi.getText());
                this.berat = Double.parseDouble(textFieldBerat.getText());
                this.output.writeBytes("bmi~" + accountAktif.getId() + "~" + this.tinggi + "~" + this.berat + "\n");
            }
            else if(radioButtonBeratBadanIdeal.isSelected()){
                this.tinggi = Double.parseDouble(textFieldTinggi.getText());
                this.output.writeBytes("ideal~" + accountAktif.getId() + "~" + accountAktif.getJenis_kelamin() + "~" + this.tinggi + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(BMICalculatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonHitungActionPerformed

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
            java.util.logging.Logger.getLogger(BMICalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BMICalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BMICalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BMICalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BMICalculatorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonHitung;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelBeratBadan;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelTinggiBadan;
    private javax.swing.JPanel panelJudul;
    private javax.swing.JRadioButton radioButtonBMI;
    private javax.swing.JRadioButton radioButtonBeratBadanIdeal;
    private javax.swing.JTextArea textAreaHasil;
    private javax.swing.JTextField textFieldBerat;
    private javax.swing.JTextField textFieldTinggi;
    // End of variables declaration//GEN-END:variables
}
