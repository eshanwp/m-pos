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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author esh
 */
public class FrameXReport extends javax.swing.JFrame {

    String LOCATION_ID=null;
    String USER_NAME=null;
    /**
     * Creates new form FrameXReport
     */
    public FrameXReport() {
        initComponents();
    }
    
    public FrameXReport(String LOCATIONID, String USERNAME) {
        initComponents();
        USER_NAME=USERNAME;
        LOCATION_ID=LOCATIONID;
        
        showCurrentDate();
    }
    
    void showCurrentDate() {

        Date d = new Date();
        jDateChooserFrom.setDate(d);
        jDateChooserTo.setDate(d);
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
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabelFrom = new javax.swing.JLabel();
        jLabelTo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales Summary Report");
        setPreferredSize(new java.awt.Dimension(400, 250));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooserFrom.setDateFormatString("dd/MM/yyyy");
        jDateChooserFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserFromPropertyChange(evt);
            }
        });
        jPanel1.add(jDateChooserFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 207, 22));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("From Date *");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jDateChooserTo.setDateFormatString("dd/MM/yyyy");
        jDateChooserTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserToPropertyChange(evt);
            }
        });
        jPanel1.add(jDateChooserTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 207, 22));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("To Date *");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print1.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 74, 30));

        jLabelFrom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFrom.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 15));

        jLabelTo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelTo.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 125, 200, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooserFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserFromPropertyChange

        if (jDateChooserFrom.getDate() == null) {

        } else {
            jLabelFrom.setText("");
        }
    }//GEN-LAST:event_jDateChooserFromPropertyChange

    private void jDateChooserToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserToPropertyChange

         if (jDateChooserTo.getDate() == null) {

        } else {
            jLabelTo.setText("");
        }
         
    }//GEN-LAST:event_jDateChooserToPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jDateChooserFrom.getDate()==null || jDateChooserTo.getDate()==null) {
            
            if (jDateChooserFrom.getDate() == null) {
                jLabelFrom.setText("Required");
            }
            
            if (jDateChooserTo.getDate() == null) {
                jLabelTo.setText("Required");
            }
            
        } else {
        
            Date d = jDateChooserFrom.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dd = sdf.format(d);
            
            Date d1 = jDateChooserTo.getDate();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            String dd1 = sdf1.format(d1);
            
        
            try {

                    InputStream is = new FileInputStream(new File("C:/i3Codes/CellNet/XReport.jrxml"));
                    JasperReport jr = JasperCompileManager.compileReport(is);

                    Map m = new HashMap();
                    m.put("FromDate", dd);
                    m.put("ToDate", dd1);

                    JasperPrint jp = JasperFillManager.fillReport(jr, m, db.myConnection());
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setTitle("X Report");
                    jv.setVisible(true);
//                    JasperPrintManager.printReport(jp, true);

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
            java.util.logging.Logger.getLogger(FrameXReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameXReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameXReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameXReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameXReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public static com.toedter.calendar.JDateChooser jDateChooserFrom;
    public static com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    public static javax.swing.JLabel jLabelFrom;
    public static javax.swing.JLabel jLabelTo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
