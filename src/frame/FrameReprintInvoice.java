/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frame;

import cls.db;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author esh
 */
public class FrameReprintInvoice extends javax.swing.JFrame {

    String LOCATION_ID=null;
    String USER_NAME=null;
    /**
     * Creates new form ReprintInvoice
     */
    public FrameReprintInvoice() {
        initComponents();
    }
    
    public FrameReprintInvoice(String LOCATIONID, String USERNAME) {
        initComponents();
        USER_NAME=USERNAME;
        LOCATION_ID=LOCATIONID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldInvoiceId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabelInvoiceID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Invoice Reprint");
        setPreferredSize(new java.awt.Dimension(400, 130));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldInvoiceId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldInvoiceId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldInvoiceIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldInvoiceIdFocusLost(evt);
            }
        });
        jTextFieldInvoiceId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldInvoiceIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldInvoiceIdKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldInvoiceId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 210, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print1.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 60, 74, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Invoide ID *");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabelInvoiceID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelInvoiceID.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelInvoiceID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldInvoiceIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldInvoiceIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInvoiceIdFocusGained

    private void jTextFieldInvoiceIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldInvoiceIdFocusLost

    }//GEN-LAST:event_jTextFieldInvoiceIdFocusLost

    private void jTextFieldInvoiceIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInvoiceIdKeyReleased
        
        jLabelInvoiceID.setText("");
      
    }//GEN-LAST:event_jTextFieldInvoiceIdKeyReleased

    private void jTextFieldInvoiceIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInvoiceIdKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' & c <= '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldInvoiceIdKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jTextFieldInvoiceId.getText().length()==0) {
            jLabelInvoiceID.setText("Required");
        } else {
            
            try {

                    InputStream is = new FileInputStream(new File("C:/i3Codes/CellNet/bill.jrxml"));
                    JasperReport jr = JasperCompileManager.compileReport(is);

                    Map m = new HashMap();
                    m.put("InvoiceId", jTextFieldInvoiceId.getText());
                    m.put("userName", USER_NAME);

                    JasperPrint jp = JasperFillManager.fillReport(jr, m, db.myConnection());
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setTitle("Invoice");
                    jv.setVisible(true);
                    JasperPrintManager.printReport(jp, true);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, e);
                }
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrameReprintInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameReprintInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameReprintInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameReprintInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameReprintInvoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel38;
    public static javax.swing.JLabel jLabelInvoiceID;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField jTextFieldInvoiceId;
    // End of variables declaration//GEN-END:variables
}
