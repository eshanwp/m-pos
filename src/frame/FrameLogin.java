package frame;

import cls.Login;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author esh
 */
public class FrameLogin extends javax.swing.JFrame {

    String CURRENT_DATE = null;
    int xMouse;
    int yMouse;
    String dayEndStatus = null;

    /**
     * Creates new form LOGIN
     */
    public FrameLogin() {
        initComponents();
        showCurrentDate();
        jTextFieldUserName.grabFocus();

//        expired();
//        dateEnd();
//        expiredNotification();

    }

    void showCurrentDate() {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dd = sdf.format(d);
        CURRENT_DATE = dd;
    }

    void expired() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM day_end");
            while (rs.next()) {
                String id = rs.getString("idday_end");

                if (id.equals("360")) {
                    JOptionPane.showMessageDialog(rootPane, "This loan system is expired.\nPlease contact the system administrator\n"
                            + "Contact no : 071 98 75 021\nE-mail : w.e.priyadarshana@gmail.com\nWeb : www.i3codes.com");
                    System.exit(0);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    void dateEnd() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM day_end WHERE status='" + "false" + "' AND day!='" + CURRENT_DATE + "' ");
            while (rs.next()) {

                dayEndStatus = rs.getString("status");
                System.out.println(dayEndStatus);
                
            }

            if (dayEndStatus.equals("false")) {

                jPasswordFieldPassword.setEnabled(false);

                JOptionPane.showMessageDialog(rootPane, "Please run the previous day end process");
                FrameDayEnd de = new FrameDayEnd(CURRENT_DATE, CURRENT_DATE);
                de.setVisible(true);

            }

        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(rootPane, e);
        }

    }
    
    void expiredNotification() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM day_end");
            while (rs.next()) {
                String id = rs.getString("idday_end");

                if (id.equals("358")) {
                    JOptionPane.showMessageDialog(rootPane, "This loan system will be expired within 2 day.\nPlease contact the system administrator\n"
                            + "Contact no : 071 98 75 021\nE-mail : w.e.priyadarshana@gmail.com\nWeb : www.i3codes.com");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
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

        jPanel1 = new javax.swing.JPanel();
        jTextFieldUserName = new javax.swing.JTextField();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jButtonLogin = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabelHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(851, 315));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldUserName.setBackground(new Color(0, 0, 0, 0));
        jTextFieldUserName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldUserName.setOpaque(false);
        jPanel1.add(jTextFieldUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 130, 190, 30));

        jPasswordFieldPassword.setBackground(new Color(0, 0, 0, 0));
        jPasswordFieldPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPasswordFieldPassword.setOpaque(false);
        jPasswordFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldPasswordKeyReleased(evt);
            }
        });
        jPanel1.add(jPasswordFieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 170, 190, 30));

        jButtonLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loginButoon.png"))); // NOI18N
        jButtonLogin.setContentAreaFilled(false);
        jButtonLogin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loginButoon1.png"))); // NOI18N
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 200, 110, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 5, 35, 35));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Login-form.jpg"))); // NOI18N
        jLabel5.setText(" ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 320));

        jLabelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabelHeaderMouseDragged(evt);
            }
        });
        jLabelHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelHeaderMousePressed(evt);
            }
        });
        jPanel1.add(jLabelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed

        Login l = new Login();
        l.login();
        this.dispose();
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jPasswordFieldPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordKeyReleased

        char c = evt.getKeyChar();
        if (c == evt.VK_ENTER) {
            Login l = new Login();
            l.login();
            this.dispose();
        }


    }//GEN-LAST:event_jPasswordFieldPasswordKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void jLabelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHeaderMouseDragged

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x, y);
    }//GEN-LAST:event_jLabelHeaderMouseDragged

    private void jLabelHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHeaderMousePressed

        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabelHeaderMousePressed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLogin().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelHeader;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPasswordField jPasswordFieldPassword;
    public static javax.swing.JTextField jTextFieldUserName;
    // End of variables declaration//GEN-END:variables
}
