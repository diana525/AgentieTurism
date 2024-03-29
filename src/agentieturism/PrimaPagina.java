/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentieturism;

import static agentieturism.ConexiuneBD.CONN_STRING;
import static agentieturism.ConexiuneBD.PASSWORD;
import static agentieturism.ConexiuneBD.USERNAME;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Diana
 */

public class PrimaPagina extends javax.swing.JFrame {
   
    /**
     * Creates new form PrimaPagina
     */
    public static String agentieLabel = "";
    public static int codAgentie;
    
    public PrimaPagina() {
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

        jLabel2 = new javax.swing.JLabel();
        bunVenitPanel = new javax.swing.JPanel();
        textBunVenit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        campUser = new javax.swing.JTextField();
        campParola = new javax.swing.JPasswordField();
        contLabel = new javax.swing.JLabel();
        parolaLabel = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));
        setForeground(new java.awt.Color(0, 255, 255));
        setMinimumSize(new java.awt.Dimension(700, 550));
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agentieturism/banner2.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 700, 200);

        bunVenitPanel.setBackground(new java.awt.Color(255, 255, 255));
        bunVenitPanel.setForeground(new java.awt.Color(153, 255, 255));
        bunVenitPanel.setLayout(null);

        textBunVenit.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        textBunVenit.setForeground(new java.awt.Color(0, 102, 102));
        textBunVenit.setText("BINE AȚI VENIT!");
        bunVenitPanel.add(textBunVenit);
        textBunVenit.setBounds(254, 18, 165, 21);

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Logare");
        bunVenitPanel.add(jLabel1);
        jLabel1.setBounds(300, 50, 50, 17);

        campUser.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        bunVenitPanel.add(campUser);
        campUser.setBounds(250, 90, 170, 30);

        campParola.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        bunVenitPanel.add(campParola);
        campParola.setBounds(250, 140, 170, 30);

        contLabel.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        contLabel.setForeground(new java.awt.Color(0, 153, 153));
        contLabel.setText("Cont");
        bunVenitPanel.add(contLabel);
        contLabel.setBounds(200, 100, 32, 17);

        parolaLabel.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        parolaLabel.setForeground(new java.awt.Color(0, 153, 153));
        parolaLabel.setText("Parola");
        bunVenitPanel.add(parolaLabel);
        parolaLabel.setBounds(190, 150, 50, 17);

        submit.setBackground(new java.awt.Color(0, 102, 102));
        submit.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        bunVenitPanel.add(submit);
        submit.setBounds(290, 190, 100, 40);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agentieturism/logo.jpg"))); // NOI18N
        bunVenitPanel.add(logo);
        logo.setBounds(580, 190, 110, 130);

        getContentPane().add(bunVenitPanel);
        bunVenitPanel.setBounds(0, 200, 700, 360);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
       
       String cont = campUser.getText();
       String parola = new String(campParola.getPassword());
       String denumireAgentie = "";
      
        try {
            Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("Select cont, parola from angajat where cont=? and parola=? and cod_functie=1");
            stmt.setString(1, cont);
            stmt.setString(2, parola);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JFrame admin = new PaginaAdmin();
                admin.setVisible(true);
                this.dispose();
            } else {

                try {
                    Connection conn3 = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                    PreparedStatement stmt3 = conn3.prepareStatement("Select cont, parola, cod_agentie from angajat where cont=? and parola=? and cod_functie=2");
                    stmt3.setString(1, cont);
                    stmt3.setString(2, parola);
                    ResultSet rs3 = stmt3.executeQuery();

                    if (rs3.next()) {
                        getAgentie(denumireAgentie, conn3, rs3);
                        JFrame paginaTicketing = new PaginaTicketing();                 
                        paginaTicketing.setVisible(true);
                        this.dispose();
                    } else {
                        try {
                            Connection conn1 = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                            PreparedStatement stmt1 = conn1.prepareStatement("Select cont, parola, cod_agentie from angajat where cont=? and parola=? and cod_functie=3");
                            stmt1.setString(1, cont);
                            stmt1.setString(2, parola);
                            ResultSet rs1 = stmt1.executeQuery();

                            if (rs1.next()) {
                                getAgentie(denumireAgentie, conn3, rs1);
                                JFrame paginaTurism = new PaginaTurism();
                                paginaTurism.setVisible(true);
                                this.dispose();
                            } else {
                                try {
                                    Connection conn2 = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                                    PreparedStatement stmt2 = conn2.prepareStatement("Select cont, parola from client where cont=? and parola=?");
                                    stmt2.setString(1, cont);
                                    stmt2.setString(2, parola);
                                    ResultSet rs2 = stmt2.executeQuery();

                                    if (rs2.next()) {
                                        JFrame paginaClient = new PaginaClient();
                                        paginaClient.setVisible(true);
                                        this.dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "User sau parola invalide");
                                        campUser.setText("");
                                        campParola.setText("");
                                    }
                                } catch (SQLException | HeadlessException e) {
                                    e.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Eroare conectivitate");
                                }
                            }
                        } catch (SQLException | HeadlessException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Eroare conectivitate");
                        }
                    }
                } catch (SQLException | HeadlessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Eroare conectivitate");
                }
            }
        } catch (SQLException | HeadlessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Eroare conectivitate");
        }

 
            
            
       
    }//GEN-LAST:event_submitActionPerformed

    private void getAgentie(String denumireAgentie, Connection conn3, ResultSet rs3) throws SQLException {
        codAgentie = rs3.getInt("cod_agentie");
        PreparedStatement pstmAgentie = conn3.prepareStatement("select denumire from agentie where cod_agentie=?");
        pstmAgentie.setInt(1, codAgentie);
        ResultSet rsAgentie = pstmAgentie.executeQuery();
        if(rsAgentie.next()) {
            denumireAgentie = rsAgentie.getString("denumire");
        }
        agentieLabel = denumireAgentie;
    }

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
            java.util.logging.Logger.getLogger(PrimaPagina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrimaPagina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrimaPagina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrimaPagina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrimaPagina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bunVenitPanel;
    public static javax.swing.JPasswordField campParola;
    public static javax.swing.JTextField campUser;
    private javax.swing.JLabel contLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel parolaLabel;
    private javax.swing.JButton submit;
    private javax.swing.JLabel textBunVenit;
    // End of variables declaration//GEN-END:variables
}
