package frame;

import cls.AccessPoint;
import cls.Location;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author esh
 */
public class FrameMain extends javax.swing.JFrame {

    String USERNAME = null;
    String LOCATIONID = null;
    String idAccessPoint = null;
    String privilegeStatus = null;
    String CURRENT_DATE = null;

    /**
     * Creates new form Main_Frame
     */
    public FrameMain() {
        initComponents();
    }

    public FrameMain(String USER_NAME, String LOCATION_ID) {
        initComponents();
        USERNAME = USER_NAME;
        LOCATIONID = LOCATION_ID;
        
        jMenuItemProductSummaryReport.setVisible(false);
        jMenuItemEmployeeSummary.setVisible(false);
//        jMenuItemInventorySummary.setVisible(false);
//        

        showCurrentDate();

        jLabel2.setVisible(false);

        AccessPoint ap = new AccessPoint();

        String s5 = ap.acceesPoint_05(USER_NAME);
        String s6 = ap.acceesPoint_06(USER_NAME);
        String s7 = ap.acceesPoint_07(USER_NAME);
        String s8 = ap.acceesPoint_08(USER_NAME);
        String s9 = ap.acceesPoint_09(USER_NAME);
        String s10 = ap.acceesPoint_10(USER_NAME);
        String s11 = ap.acceesPoint_11(USER_NAME);
        String s12 = ap.acceesPoint_12(USER_NAME);
        String s13 = ap.acceesPoint_13(USER_NAME);
        String s14 = ap.acceesPoint_14(USER_NAME);
        String s15 = ap.acceesPoint_15(USER_NAME);
        String s16 = ap.acceesPoint_16(USER_NAME);
        String s17 = ap.acceesPoint_17(USER_NAME);
        String s18 = ap.acceesPoint_18(USER_NAME);
        String s19 = ap.acceesPoint_19(USER_NAME);
        String s20 = ap.acceesPoint_20(USER_NAME);
        String s21 = ap.acceesPoint_21(USER_NAME);
        String s22 = ap.acceesPoint_22(USER_NAME);
        String s23 = ap.acceesPoint_23(USER_NAME);
        
//        String s20 = ap.acceesPoint_20(USER_NAME);

       if (s5.equals("true")) {
            jMenuBranchMaster.setVisible(true);
        } else {
            jMenuBranchMaster.setVisible(false);
        }

        if (s6.equals("true")) {
            jMenuEmployeeMaster.setVisible(true);
        } else {
            jMenuEmployeeMaster.setVisible(false);
        }

        if (s7.equals("true")) {
            jMenuUnlockLogin.setVisible(true);
        } else {
            jMenuUnlockLogin.setVisible(false);
        }

        if (s8.equals("true")) {
            jMenuUserSettings.setVisible(true);
        } else {
            jMenuUserSettings.setVisible(false);
        }

        if (s9.equals("true")) {
            jMenuChangePassword.setVisible(true);
        } else {
            jMenuChangePassword.setVisible(false);
        }

        if (s10.equals("true")) {
            jMenuItemCategory.setVisible(true);
        } else {
            jMenuItemCategory.setVisible(false);
        }

        if (s11.equals("true")) {
            jMenuItemSubCategory.setVisible(true);
        } else {
            jMenuItemSubCategory.setVisible(false);
        }

        if (s12.equals("true")) {
            jMenuItemProductEntry.setVisible(true);
        } else {
            jMenuItemProductEntry.setVisible(false);
        }

        if (s13.equals("true")) {
            jMenuItemStockIn.setVisible(true);
        } else {
            jMenuItemStockIn.setVisible(false);
        }

        if (s14.equals("true")) {
            jMenuItemSupplier.setVisible(true);
        } else {
            jMenuItemSupplier.setVisible(false);
        }

        if (s15.equals("true")) {
            jButtonBill.setVisible(true);
        } else {
            jButtonBill.setVisible(false);
        }

        if (s16.equals("true")) {
            jMenuItemRePrint.setVisible(true);
        } else {
            jMenuItemRePrint.setVisible(false);
        }
        
        if (s17.equals("true")) {
            jMenuItemSearchEmployee.setVisible(true);
        } else {
            jMenuItemSearchEmployee.setVisible(false);
        }
        
        if (s18.equals("true")) {
            jMenuItemSalesSummery.setVisible(true);
        } else {
            jMenuItemSalesSummery.setVisible(false);
        }
        
        if (s19.equals("true")) {
            jMenuItemDayEnd.setVisible(true);
        } else {
            jMenuItemDayEnd.setVisible(false);
        }
        
//        if (s20.equals("true")) {
//            jMenuItemProductSummaryReport.setVisible(true);
//        } else {
//            jMenuItemProductSummaryReport.setVisible(false);
//        }
        
        if (s21.equals("true")) {
            jMenuItemInventorySummary.setVisible(true);
        } else {
            jMenuItemInventorySummary.setVisible(false);
        }
        
        if (s22.equals("true")) {
            jMenuItemCreateCustomer.setVisible(true);
        } else {
            jMenuItemCreateCustomer.setVisible(false);
        }
        
        if (s23.equals("true")) {
            jMenuItemPhoneDetails.setVisible(true);
        } else {
            jMenuItemPhoneDetails.setVisible(false);
        }
        
        

    }

    void showCurrentDate() {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dd = sdf.format(d);
        CURRENT_DATE = dd;
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
        jMenu6 = new javax.swing.JMenu();
        jMenu22 = new javax.swing.JMenu();
        jMenu23 = new javax.swing.JMenu();
        jMenu24 = new javax.swing.JMenu();
        jMenu30 = new javax.swing.JMenu();
        jMenu37 = new javax.swing.JMenu();
        jMenu39 = new javax.swing.JMenu();
        jMenu40 = new javax.swing.JMenu();
        jMenu44 = new javax.swing.JMenu();
        jMenu47 = new javax.swing.JMenu();
        jMenu48 = new javax.swing.JMenu();
        jMenu52 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu57 = new javax.swing.JMenu();
        jMenu58 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonBill = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuBranchMaster = new javax.swing.JMenu();
        jMenuEmployeeMaster = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemSearchEmployee = new javax.swing.JMenuItem();
        jMenuUnlockLogin = new javax.swing.JMenu();
        jMenuUserSettings = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemCategory = new javax.swing.JMenuItem();
        jMenuItemSubCategory = new javax.swing.JMenuItem();
        jMenuItemProductEntry = new javax.swing.JMenuItem();
        jMenuItemStockIn = new javax.swing.JMenuItem();
        jMenuItemSupplier = new javax.swing.JMenuItem();
        jMenuItemRePrint = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemCreateCustomer = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemSalesSummery = new javax.swing.JMenuItem();
        jMenuItemProductSummaryReport = new javax.swing.JMenuItem();
        jMenuItemEmployeeSummary = new javax.swing.JMenuItem();
        jMenuItemInventorySummary = new javax.swing.JMenuItem();
        jMenuItemPhoneDetails = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemDayEnd = new javax.swing.JMenuItem();
        jMenu59 = new javax.swing.JMenu();
        jMenuChangePassword = new javax.swing.JMenu();

        jPanel1.setMaximumSize(new java.awt.Dimension(1366, 768));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jMenu6.setText("jMenu6");

        jMenu22.setText("jMenu22");

        jMenu23.setText("jMenu23");

        jMenu24.setText("jMenu24");

        jMenu30.setText("jMenu30");

        jMenu37.setText("jMenu37");

        jMenu39.setText("jMenu39");

        jMenu40.setText("jMenu40");

        jMenu44.setText("jMenu44");

        jMenu47.setText("jMenu47");

        jMenu48.setText("jMenu48");

        jMenu52.setText("jMenu52");

        jMenu57.setText("File");
        jMenuBar2.add(jMenu57);

        jMenu58.setText("Edit");
        jMenuBar2.add(jMenu58);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Frame");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1234, 10, 130, 22));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 220, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/devices.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/devices.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 90, 90));

        jButtonBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smartphone.png"))); // NOI18N
        jButtonBill.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smartphone.png"))); // NOI18N
        jButtonBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBillActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 90));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/technology-1.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/technology-1.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 90, 90));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bill");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Product Details");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 110, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Stock Details");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 110, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/man1.png"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/man1.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 90, 90));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Customer");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 110, -1));

        jMenu1.setText("Admin");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuBranchMaster.setText("Location Master");
        jMenuBranchMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBranchMasterMouseClicked(evt);
            }
        });
        jMenu1.add(jMenuBranchMaster);

        jMenuEmployeeMaster.setText("Employee Master");
        jMenuEmployeeMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEmployeeMasterMouseClicked(evt);
            }
        });

        jMenuItem2.setText("Create Employee");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuEmployeeMaster.add(jMenuItem2);

        jMenuItemSearchEmployee.setText("Search Employee");
        jMenuItemSearchEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchEmployeeActionPerformed(evt);
            }
        });
        jMenuEmployeeMaster.add(jMenuItemSearchEmployee);

        jMenu1.add(jMenuEmployeeMaster);

        jMenuUnlockLogin.setText("Unlock Login");
        jMenuUnlockLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuUnlockLoginMouseClicked(evt);
            }
        });
        jMenu1.add(jMenuUnlockLogin);

        jMenuUserSettings.setText("User Settings");
        jMenuUserSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuUserSettingsMouseClicked(evt);
            }
        });
        jMenu1.add(jMenuUserSettings);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("| General");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItemCategory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemCategory.setText("Category");
        jMenuItemCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemCategoryMouseClicked(evt);
            }
        });
        jMenuItemCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoryActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCategory);

        jMenuItemSubCategory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItemSubCategory.setText("Sub Category");
        jMenuItemSubCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemSubCategoryMouseClicked(evt);
            }
        });
        jMenuItemSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSubCategoryActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSubCategory);

        jMenuItemProductEntry.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItemProductEntry.setText("Product Entry");
        jMenuItemProductEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemProductEntryMouseClicked(evt);
            }
        });
        jMenuItemProductEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductEntryActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemProductEntry);

        jMenuItemStockIn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItemStockIn.setText("Stock In");
        jMenuItemStockIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemStockInMouseClicked(evt);
            }
        });
        jMenuItemStockIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStockInActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemStockIn);

        jMenuItemSupplier.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItemSupplier.setText("Supplier");
        jMenuItemSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemSupplierMouseClicked(evt);
            }
        });
        jMenuItemSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSupplierActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSupplier);

        jMenuItemRePrint.setText("Reprint");
        jMenuItemRePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRePrintActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemRePrint);

        jMenu5.setText("Customer");

        jMenuItemCreateCustomer.setText("Create Customer");
        jMenuItemCreateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateCustomerActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemCreateCustomer);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("| Report");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItemSalesSummery.setText("Sales Summary Report");
        jMenuItemSalesSummery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalesSummeryActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemSalesSummery);

        jMenuItemProductSummaryReport.setText("Product Summary Report");
        jMenuItemProductSummaryReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductSummaryReportActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemProductSummaryReport);

        jMenuItemEmployeeSummary.setText("Employee Summary Report");
        jMenuItemEmployeeSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEmployeeSummaryActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemEmployeeSummary);

        jMenuItemInventorySummary.setText("Inventory Summary Report");
        jMenuItemInventorySummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInventorySummaryActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemInventorySummary);

        jMenuItemPhoneDetails.setText("Phone Details");
        jMenuItemPhoneDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPhoneDetailsActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemPhoneDetails);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("| Run");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItemDayEnd.setText("Day End Run");
        jMenuItemDayEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDayEndActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemDayEnd);

        jMenuBar1.add(jMenu4);

        jMenu59.setText(" |  Profile");
        jMenu59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuChangePassword.setText("Change Password");
        jMenuChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuChangePasswordMouseClicked(evt);
            }
        });
        jMenu59.add(jMenuChangePassword);

        jMenuBar1.add(jMenu59);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuBranchMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBranchMasterMouseClicked

        FrameLocationMaster bm = new FrameLocationMaster(LOCATIONID, USERNAME);
        bm.setVisible(true);

    }//GEN-LAST:event_jMenuBranchMasterMouseClicked

    private void jMenuEmployeeMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEmployeeMasterMouseClicked

    }//GEN-LAST:event_jMenuEmployeeMasterMouseClicked

    private void jMenuUnlockLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuUnlockLoginMouseClicked

        FrameUnlockLogin ul = new FrameUnlockLogin();
        ul.setVisible(true);

    }//GEN-LAST:event_jMenuUnlockLoginMouseClicked

    private void jMenuUserSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuUserSettingsMouseClicked

        FrameUserCreation uc = new FrameUserCreation(LOCATIONID, USERNAME);
        uc.setVisible(true);

    }//GEN-LAST:event_jMenuUserSettingsMouseClicked

    private void jMenuChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuChangePasswordMouseClicked

        FrameChangePassword cp = new FrameChangePassword(LOCATIONID, USERNAME);
        cp.setVisible(true);
    }//GEN-LAST:event_jMenuChangePasswordMouseClicked

    private void jMenuItemCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemCategoryMouseClicked

    }//GEN-LAST:event_jMenuItemCategoryMouseClicked

    private void jMenuItemSubCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemSubCategoryMouseClicked

    }//GEN-LAST:event_jMenuItemSubCategoryMouseClicked

    private void jMenuItemProductEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemProductEntryMouseClicked

    }//GEN-LAST:event_jMenuItemProductEntryMouseClicked

    private void jMenuItemStockInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemStockInMouseClicked

    }//GEN-LAST:event_jMenuItemStockInMouseClicked

    private void jMenuItemSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemSupplierMouseClicked

    }//GEN-LAST:event_jMenuItemSupplierMouseClicked

    private void jMenuItemCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoryActionPerformed

        FrameCategory c = new FrameCategory(LOCATIONID, USERNAME);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItemCategoryActionPerformed

    private void jMenuItemSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSubCategoryActionPerformed

        FrameSubCategory sc = new FrameSubCategory(LOCATIONID, USERNAME);
        sc.setVisible(true);
    }//GEN-LAST:event_jMenuItemSubCategoryActionPerformed

    private void jMenuItemProductEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductEntryActionPerformed

        FrameProductEntry pe = new FrameProductEntry(LOCATIONID, USERNAME);
        pe.setVisible(true);
    }//GEN-LAST:event_jMenuItemProductEntryActionPerformed

    private void jMenuItemStockInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStockInActionPerformed

        FrameStockEntry se = new FrameStockEntry(LOCATIONID, USERNAME);
        se.setVisible(true);
    }//GEN-LAST:event_jMenuItemStockInActionPerformed

    private void jMenuItemSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSupplierActionPerformed

        FrameSupplier s = new FrameSupplier(LOCATIONID, USERNAME);
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItemSupplierActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        FrameViewProduct vp = new FrameViewProduct(LOCATIONID, USERNAME);
        vp.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        FrameViewStock vs = new FrameViewStock(LOCATIONID, USERNAME);
        vs.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBillActionPerformed

        FrameBill b = new FrameBill(LOCATIONID, USERNAME);
        b.setVisible(true);
    }//GEN-LAST:event_jButtonBillActionPerformed

    private void jMenuItemRePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRePrintActionPerformed

        FrameReprintInvoice r = new FrameReprintInvoice(LOCATIONID, USERNAME);
        r.setVisible(true);
    }//GEN-LAST:event_jMenuItemRePrintActionPerformed

    private void jMenuItemSalesSummeryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalesSummeryActionPerformed

        FrameXReading x = new FrameXReading(LOCATIONID, USERNAME);
        x.setVisible(true);
    }//GEN-LAST:event_jMenuItemSalesSummeryActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        FrameEmployeeMaster em = new FrameEmployeeMaster(LOCATIONID, USERNAME);
        em.setVisible(true);

        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemSearchEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchEmployeeActionPerformed

        FrameSearchEmployee se = new FrameSearchEmployee(LOCATIONID, USERNAME);
        se.setVisible(true);
    }//GEN-LAST:event_jMenuItemSearchEmployeeActionPerformed
String dayEndStatus=null;
    private void jMenuItemDayEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDayEndActionPerformed

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM day_end WHERE day='" + CURRENT_DATE + "' ");

            while (rs.next()) {                
                dayEndStatus = rs.getString("status");
            }

            if (dayEndStatus.equals("true")) {
                JOptionPane.showMessageDialog(rootPane, "Already run the day end process");
            } else {
                
                FrameDayEnd d = new FrameDayEnd(LOCATIONID, USERNAME);
                d.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }//GEN-LAST:event_jMenuItemDayEndActionPerformed

    private void jMenuItemProductSummaryReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductSummaryReportActionPerformed

        FrameProductSummaryReport psr = new FrameProductSummaryReport(LOCATIONID, USERNAME);
        psr.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemProductSummaryReportActionPerformed

    private void jMenuItemEmployeeSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEmployeeSummaryActionPerformed

        FrameEmployeeSummaryReport esr = new FrameEmployeeSummaryReport(LOCATIONID, USERNAME);
        esr.setVisible(true);
    }//GEN-LAST:event_jMenuItemEmployeeSummaryActionPerformed

    private void jMenuItemInventorySummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInventorySummaryActionPerformed

        FrameInventorySummaryReport sr = new FrameInventorySummaryReport(LOCATIONID, USERNAME);
        sr.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemInventorySummaryActionPerformed

    private void jMenuItemCreateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreateCustomerActionPerformed
        FrameCustomer fc = new FrameCustomer(LOCATIONID, USERNAME);
        fc.setVisible(true);
    }//GEN-LAST:event_jMenuItemCreateCustomerActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        FrameCustomer fc = new FrameCustomer(LOCATIONID, USERNAME);
        fc.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItemPhoneDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPhoneDetailsActionPerformed

        FramePhoneDetails pd = new FramePhoneDetails(LOCATIONID, USERNAME);
        pd.setVisible(true);
    }//GEN-LAST:event_jMenuItemPhoneDetailsActionPerformed


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
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonBill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu30;
    private javax.swing.JMenu jMenu37;
    private javax.swing.JMenu jMenu39;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu40;
    private javax.swing.JMenu jMenu44;
    private javax.swing.JMenu jMenu47;
    private javax.swing.JMenu jMenu48;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu52;
    private javax.swing.JMenu jMenu57;
    private javax.swing.JMenu jMenu58;
    private javax.swing.JMenu jMenu59;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuBranchMaster;
    private javax.swing.JMenu jMenuChangePassword;
    private javax.swing.JMenu jMenuEmployeeMaster;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemCategory;
    private javax.swing.JMenuItem jMenuItemCreateCustomer;
    private javax.swing.JMenuItem jMenuItemDayEnd;
    private javax.swing.JMenuItem jMenuItemEmployeeSummary;
    private javax.swing.JMenuItem jMenuItemInventorySummary;
    private javax.swing.JMenuItem jMenuItemPhoneDetails;
    private javax.swing.JMenuItem jMenuItemProductEntry;
    private javax.swing.JMenuItem jMenuItemProductSummaryReport;
    private javax.swing.JMenuItem jMenuItemRePrint;
    private javax.swing.JMenuItem jMenuItemSalesSummery;
    private javax.swing.JMenuItem jMenuItemSearchEmployee;
    private javax.swing.JMenuItem jMenuItemStockIn;
    private javax.swing.JMenuItem jMenuItemSubCategory;
    private javax.swing.JMenuItem jMenuItemSupplier;
    private javax.swing.JMenu jMenuUnlockLogin;
    private javax.swing.JMenu jMenuUserSettings;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
