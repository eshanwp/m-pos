/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import AppPackage.AnimationClass;
import cls.AccessPoint;
import cls.Bill;
import cls.JReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author esh
 */
public class FrameBill extends javax.swing.JFrame {

    String LOCATION_ID = null;
    String USER_NAME = null;
    String date = null;
    String user_id = null;
    String time = null;
    String gender = null;

    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm1 = new DefaultTableModel(); // hold tb
    DefaultTableModel dtm2 = new DefaultTableModel(); // payment details
    AnimationClass ac = new AnimationClass();

    /**
     * Creates new form FrameBill
     */
    public FrameBill() {
        initComponents();
    }

    public FrameBill(String LOCATIONID, String USERNAME) {
        initComponents();
        LOCATION_ID = LOCATIONID;
        USER_NAME = USERNAME;

        jTextAreaIntials.setVisible(false);

        ref();
        holdid();
        idpaymentDetails();

        Bill b = new Bill();
        b.loadPayMode();
        jComboBoxPaymentMode.setSelectedIndex(1);

        showCurrentDate();
        userId();
        showTimer();
        jTextFieldCashier.setText(USER_NAME);
        loadLocation();

        loadProduct();
        jComboBoxProductCode.grabFocus();

        jButtonPayOld.setVisible(false);
        jTextField1.setVisible(false);

        jScrollPane2.setVisible(false);
        jTableHoldTb.setVisible(false);

        AutoCompleteDecorator.decorate(jComboBoxProductCode);

    }

    void showCurrentDate() {

        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jTextFieldDate.setText(sdf.format(d));
            }
        }).start();

    }

    void userId() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM user WHERE userName='" + USER_NAME + "' ");
            while (rs.next()) {
                user_id = rs.getString("iduser");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    void showTimer() {
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String dd = sdf.format(d);
                time = dd;
                jTextFieldTime.setText(time);
            }
        }).start();
    }

    void loadProduct() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE type='" + "OTHER" + "' AND status='" + "ACTIVE" + "' ");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {

                v.add(rs.getString("p_code"));
            }

            ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM imei WHERE status='" + "NOTSELL" + "' ");
            while (rs1.next()) {
                v.add(rs1.getString("imei1"));
            }

            jComboBoxProductCode.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    void loadLocation() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM location WHERE idlocation='" + LOCATION_ID + "'  ");

            Vector v = new Vector();
            while (rs.next()) {

                jTextFieldLocation.setText(rs.getString("locationName"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    String REF = null;

    public void ref() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT MAX(idinvoice_id) FROM invoice_header");

            while (rs.next()) {

                if (rs.getInt("MAX(idinvoice_id)") == 0) {
                    REF = "1";

                } else {
                    int i = rs.getInt("MAX(idinvoice_id)") + 1;
                    REF = i + "";

                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }
    String idpayment_details = null;

    public void idpaymentDetails() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT MAX(idpayment_details) FROM payment_details");

            while (rs.next()) {

                if (rs.getInt("MAX(idpayment_details)") == 0) {
                    idpayment_details = "1";

                } else {
                    int i = rs.getInt("MAX(idpayment_details)") + 1;
                    idpayment_details = i + "";

                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    String id_hold = null;

    public void holdid() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT MAX(idhold_bill) FROM hold_bill");

            while (rs.next()) {

                if (rs.getInt("MAX(idhold_bill)") == 0) {
                    id_hold = "1";

                } else {
                    int i = rs.getInt("MAX(idhold_bill)") + 1;
                    id_hold = i + "";

                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int j;

    public int getYear() {
        j = (1900 + Integer.parseInt(jTextFieldConfirmNIC.getText().substring(0, 2)));
        return j;
    }

    public int getYear2() {
        j = (1900 + Integer.parseInt(jTextFieldConfirmNIC.getText().substring(2, 4)));
        return j;
    }

    public int getDays() {
        int d = Integer.parseInt(jTextFieldConfirmNIC.getText().substring(2, 5));
        if (d > 500) {
            return (d - 500);
        } else {
            return d;
        }
    }

    public int getDays2() {
        int d = Integer.parseInt(jTextFieldConfirmNIC.getText().substring(4, 7));
        if (d > 500) {
            return (d - 500);
        } else {
            return d;
        }
    }

    int mo = 0, da = 0;
    String mo1 = null;
    String da1 = null;

    public void setMonth() {

        int days = getDays();

        for (int i = 0; i < month.length; i++) {
            if (days < month[i]) {
                mo = i + 1;
                da = days;
                break;
            } else {
                days = days - month[i];
            }
        }
        if (mo == 0) {
            mo1 = "00";
        } else if (mo == 1) {
            mo1 = "01";
        } else if (mo == 2) {
            mo1 = "02";
        } else if (mo == 3) {
            mo1 = "03";
        } else if (mo == 4) {
            mo1 = "04";
        } else if (mo == 5) {
            mo1 = "05";
        } else if (mo == 6) {
            mo1 = "06";
        } else if (mo == 7) {
            mo1 = "07";
        } else if (mo == 8) {
            mo1 = "08";
        } else if (mo == 9) {
            mo1 = "09";
        } else {
            mo1 = mo + "";
        }

        if (da == 0) {
            da1 = "00";
        } else if (da == 1) {
            da1 = "01";
        } else if (da == 2) {
            da1 = "02";
        } else if (da == 3) {
            da1 = "03";
        } else if (da == 4) {
            da1 = "04";
        } else if (da == 5) {
            da1 = "05";
        } else if (da == 6) {
            da1 = "06";
        } else if (da == 7) {
            da1 = "07";
        } else if (da == 8) {
            da1 = "08";
        } else if (da == 9) {
            da1 = "09";
        } else {
            da1 = da + "";
        }

    }

    public void setMonth2() {

        int days = getDays2();

        for (int i = 0; i < month.length; i++) {
            if (days < month[i]) {
                mo = i + 1;
                da = days;
                break;
            } else {
                days = days - month[i];
            }
        }

        if (mo == 0) {
            mo1 = "00";
        } else if (mo == 1) {
            mo1 = "01";
        } else if (mo == 2) {
            mo1 = "02";
        } else if (mo == 3) {
            mo1 = "03";
        } else if (mo == 4) {
            mo1 = "04";
        } else if (mo == 5) {
            mo1 = "05";
        } else if (mo == 6) {
            mo1 = "06";
        } else if (mo == 7) {
            mo1 = "07";
        } else if (mo == 8) {
            mo1 = "08";
        } else if (mo == 9) {
            mo1 = "09";
        } else {
            mo1 = mo + "";
        }

        if (da == 0) {
            da1 = "00";
        } else if (da == 1) {
            da1 = "01";
        } else if (da == 2) {
            da1 = "02";
        } else if (da == 3) {
            da1 = "03";
        } else if (da == 4) {
            da1 = "04";
        } else if (da == 5) {
            da1 = "05";
        } else if (da == 6) {
            da1 = "06";
        } else if (da == 7) {
            da1 = "07";
        } else if (da == 8) {
            da1 = "08";
        } else if (da == 9) {
            da1 = "09";
        } else {
            da1 = da + "";
        }

    }

    public String getSex() {
        String M = "MALE", F = "FEMALE";
        int d = Integer.parseInt(jTextFieldConfirmNIC.getText().substring(2, 5));
        if (d > 500) {
            gender = "FEMALE";
            return F;
        } else {
            gender = "MALE";
            return M;
        }
    }

    public String getSex2() {
        String M = "MALE", F = "FEMALE";
        int d = Integer.parseInt(jTextFieldConfirmNIC.getText().substring(4, 7));
        if (d > 500) {
            gender = "FEMALE";
            return F;
        } else {
            gender = "MALE";
            return M;
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
        jLabel33 = new javax.swing.JLabel();
        jLabelProductCode = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldSellingPrice = new javax.swing.JTextField();
        jLabelPayAmt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBoxPaymentMode = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldGrossAmt = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldTotalDiscount = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextFieldNetAmt = new javax.swing.JTextField();
        jButtonPayNew = new javax.swing.JButton();
        jLabelAmt = new javax.swing.JLabel();
        jTextFieldBalance = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabelGrossAmt = new javax.swing.JLabel();
        jLabelDiscountAmt = new javax.swing.JLabel();
        jLabelNetAmt = new javax.swing.JLabel();
        jLabelBalance = new javax.swing.JLabel();
        jLabelPayMode = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextFieldCashier = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextFieldLocation = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jTextFieldDate = new javax.swing.JTextField();
        jTextFieldTime = new javax.swing.JTextField();
        jComboBoxProductCode = new javax.swing.JComboBox();
        jTextFieldPayAmt = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jButtonCalculator = new javax.swing.JButton();
        jButtonViewStock = new javax.swing.JButton();
        jButtonReprint = new javax.swing.JButton();
        jButtonTakingBill = new javax.swing.JButton();
        jButtonHoldBill = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxSalesType = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHoldTb = new javax.swing.JTable();
        jTextFieldConfirmNIC = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabelConfirmNIC = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldFullName = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaIntials = new javax.swing.JTextArea();
        jButtonPayOld = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePayentDetails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bill");
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Payment Mode *");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        jLabelProductCode.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelProductCode.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelProductCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 210, 15));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Product Code*");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jTextFieldSellingPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSellingPriceActionPerformed(evt);
            }
        });
        jTextFieldSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSellingPriceKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldSellingPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 210, -1));

        jLabelPayAmt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPayAmt.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelPayAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, 200, 15));

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDPRODUCT", "PRODUCT CODE", "DISCRIPTION", "SELLING PRICE", "QTY", "AMOUNT", "DISCOUNT AMOUNT", "NET AMT", "REMOVE", "STATUS", "COST", "IMEI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(9).setMinWidth(0);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(10).setMinWidth(0);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 940, 270));

        jComboBoxPaymentMode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxPaymentModeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxPaymentModeFocusLost(evt);
            }
        });
        jComboBoxPaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPaymentModeActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxPaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 210, 22));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Pay Amount *");
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Date");
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, -1));

        jTextFieldGrossAmt.setEditable(false);
        jTextFieldGrossAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextFieldGrossAmt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextFieldGrossAmt.setBorder(null);
        jTextFieldGrossAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldGrossAmtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldGrossAmtFocusLost(evt);
            }
        });
        jTextFieldGrossAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldGrossAmtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldGrossAmtKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldGrossAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 150, 30));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Discount Amt *");
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, -1, -1));

        jTextFieldTotalDiscount.setEditable(false);
        jTextFieldTotalDiscount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextFieldTotalDiscount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextFieldTotalDiscount.setBorder(null);
        jTextFieldTotalDiscount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTotalDiscountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTotalDiscountFocusLost(evt);
            }
        });
        jTextFieldTotalDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTotalDiscountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTotalDiscountKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldTotalDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 150, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Net Amt *");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, -1, -1));

        jTextFieldNetAmt.setEditable(false);
        jTextFieldNetAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextFieldNetAmt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextFieldNetAmt.setBorder(null);
        jTextFieldNetAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNetAmtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNetAmtFocusLost(evt);
            }
        });
        jTextFieldNetAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNetAmtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNetAmtKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldNetAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 150, 30));

        jButtonPayNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay1.png"))); // NOI18N
        jButtonPayNew.setContentAreaFilled(false);
        jButtonPayNew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay2.png"))); // NOI18N
        jButtonPayNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonPayNewFocusLost(evt);
            }
        });
        jButtonPayNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayNewActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPayNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 530, 74, 30));

        jLabelAmt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelAmt.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 720, 200, 15));

        jTextFieldBalance.setEditable(false);
        jTextFieldBalance.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextFieldBalance.setBorder(null);
        jTextFieldBalance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBalanceFocusLost(evt);
            }
        });
        jTextFieldBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBalanceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBalanceKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, 150, 30));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Balance *");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 360, -1, -1));

        jLabelGrossAmt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelGrossAmt.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelGrossAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 200, 15));

        jLabelDiscountAmt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelDiscountAmt.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelDiscountAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 200, 15));

        jLabelNetAmt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNetAmt.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelNetAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 200, 15));

        jLabelBalance.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelBalance.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 390, 210, 15));

        jLabelPayMode.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPayMode.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelPayMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 200, 15));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("Gross Amt *");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jTextFieldCashier.setEditable(false);
        jTextFieldCashier.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldCashier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCashierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCashierFocusLost(evt);
            }
        });
        jTextFieldCashier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCashierKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCashierKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldCashier, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 210, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Location");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jTextFieldLocation.setEditable(false);
        jTextFieldLocation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLocationFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldLocationFocusLost(evt);
            }
        });
        jTextFieldLocation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldLocationKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldLocationKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 210, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Cashier");
        jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jTextFieldDate.setEditable(false);
        jTextFieldDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDateFocusLost(evt);
            }
        });
        jTextFieldDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDateKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 210, -1));

        jTextFieldTime.setEditable(false);
        jTextFieldTime.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTimeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTimeFocusLost(evt);
            }
        });
        jTextFieldTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTimeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTimeKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 210, -1));

        jComboBoxProductCode.setEditable(true);
        jComboBoxProductCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBoxProductCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProductCodeActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxProductCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 210, 21));

        jTextFieldPayAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldPayAmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPayAmtActionPerformed(evt);
            }
        });
        jTextFieldPayAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPayAmtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPayAmtKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldPayAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, 210, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("Price+Qty+Discount*");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, -1, -1));

        jButtonCalculator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calculating.png"))); // NOI18N
        jButtonCalculator.setToolTipText("Calculator");
        jButtonCalculator.setContentAreaFilled(false);
        jButtonCalculator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalculatorActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCalculator, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 600, 64, 64));

        jButtonViewStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Stocks1.png"))); // NOI18N
        jButtonViewStock.setToolTipText("View Stock");
        jButtonViewStock.setContentAreaFilled(false);
        jButtonViewStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewStockActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonViewStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, 64, 64));

        jButtonReprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer.png"))); // NOI18N
        jButtonReprint.setToolTipText("Reprint");
        jButtonReprint.setContentAreaFilled(false);
        jButtonReprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReprintActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonReprint, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 600, 64, 64));

        jButtonTakingBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/taking bill.png"))); // NOI18N
        jButtonTakingBill.setToolTipText("Taking Bill");
        jButtonTakingBill.setContentAreaFilled(false);
        jButtonTakingBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTakingBillActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTakingBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 600, 64, 64));

        jButtonHoldBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hold bill.png"))); // NOI18N
        jButtonHoldBill.setToolTipText("Hold Bill");
        jButtonHoldBill.setContentAreaFilled(false);
        jButtonHoldBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoldBillActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonHoldBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, 64, 64));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hold Bill");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 670, 50, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Taking Bill");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 670, 60, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("View Stock");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 670, 80, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Bill Reprint");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 670, 80, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setLabelFor(jButtonCalculator);
        jLabel6.setText("Calculator");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 670, 80, -1));

        jComboBoxSalesType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBoxSalesType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sales", "Return" }));
        jComboBoxSalesType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSalesTypeActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxSalesType, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 210, 22));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Mode");
        jPanel1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 210, -1));

        jTableHoldTb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTableHoldTb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID HOLD BILL", "DATE", "TIME", "NET AMOUNT", "STATUS", "TAKING"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHoldTb.setRowHeight(20);
        jTableHoldTb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHoldTbMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableHoldTb);
        if (jTableHoldTb.getColumnModel().getColumnCount() > 0) {
            jTableHoldTb.getColumnModel().getColumn(0).setMinWidth(0);
            jTableHoldTb.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableHoldTb.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableHoldTb.getColumnModel().getColumn(2).setMinWidth(0);
            jTableHoldTb.getColumnModel().getColumn(2).setPreferredWidth(0);
            jTableHoldTb.getColumnModel().getColumn(2).setMaxWidth(0);
            jTableHoldTb.getColumnModel().getColumn(4).setMinWidth(0);
            jTableHoldTb.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTableHoldTb.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 410, 200, 160));

        jTextFieldConfirmNIC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldConfirmNICFocusLost(evt);
            }
        });
        jTextFieldConfirmNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldConfirmNICKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldConfirmNICKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldConfirmNICKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldConfirmNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 210, 22));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("NIC No");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabelConfirmNIC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelConfirmNIC.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(jLabelConfirmNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 200, 15));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Full Name");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, -1));

        jTextFieldFullName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldFullNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldFullNameFocusLost(evt);
            }
        });
        jTextFieldFullName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFullNameKeyReleased(evt);
            }
        });
        jPanel1.add(jTextFieldFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 210, 22));

        jTextAreaIntials.setColumns(20);
        jTextAreaIntials.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaIntials.setRows(1);
        jScrollPane3.setViewportView(jTextAreaIntials);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 710, 207, 22));

        jButtonPayOld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay1.png"))); // NOI18N
        jButtonPayOld.setContentAreaFilled(false);
        jButtonPayOld.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay2.png"))); // NOI18N
        jButtonPayOld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonPayOldFocusLost(evt);
            }
        });
        jButtonPayOld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayOldActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPayOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 530, 74, 30));

        jTablePayentDetails.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTablePayentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID PAYMENT DETAILS", "ID PAYMENT TYPE", "ID INVOICE", "PAYMENT TYPE", "PAYMENT", "DISCOUNT", "DATE", "REMOVE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePayentDetails.setRowHeight(20);
        jTablePayentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePayentDetailsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTablePayentDetails);
        if (jTablePayentDetails.getColumnModel().getColumnCount() > 0) {
            jTablePayentDetails.getColumnModel().getColumn(0).setMinWidth(0);
            jTablePayentDetails.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTablePayentDetails.getColumnModel().getColumn(0).setMaxWidth(0);
            jTablePayentDetails.getColumnModel().getColumn(7).setMinWidth(60);
            jTablePayentDetails.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTablePayentDetails.getColumnModel().getColumn(7).setMaxWidth(60);
        }

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 500, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1073, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    String amount = null;
    String totAmtdb = null;
    private void jComboBoxPaymentModeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxPaymentModeFocusGained

        jTextFieldPayAmt.setText("");
    }//GEN-LAST:event_jComboBoxPaymentModeFocusGained

    private void jComboBoxPaymentModeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxPaymentModeFocusLost

    }//GEN-LAST:event_jComboBoxPaymentModeFocusLost
    String id_Payment = null;
    private void jComboBoxPaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPaymentModeActionPerformed

        if (jComboBoxPaymentMode.getSelectedIndex() > 0) {
            jLabelPayMode.setText("");
        }

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM payment_mode WHERE payment_name='" + jComboBoxPaymentMode.getSelectedItem().toString() + "'  ");

            while (rs.next()) {
                id_Payment = rs.getString("idpayment_mode");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_jComboBoxPaymentModeActionPerformed

    private void jTextFieldGrossAmtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldGrossAmtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGrossAmtFocusGained

    private void jTextFieldGrossAmtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldGrossAmtFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGrossAmtFocusLost

    private void jTextFieldGrossAmtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGrossAmtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGrossAmtKeyReleased

    private void jTextFieldGrossAmtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGrossAmtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGrossAmtKeyTyped

    private void jTextFieldTotalDiscountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTotalDiscountFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalDiscountFocusGained

    private void jTextFieldTotalDiscountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTotalDiscountFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalDiscountFocusLost

    private void jTextFieldTotalDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalDiscountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalDiscountKeyReleased

    private void jTextFieldTotalDiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalDiscountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalDiscountKeyTyped

    private void jTextFieldNetAmtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNetAmtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNetAmtFocusGained

    private void jTextFieldNetAmtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNetAmtFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNetAmtFocusLost

    private void jTextFieldNetAmtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNetAmtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNetAmtKeyReleased

    private void jTextFieldNetAmtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNetAmtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNetAmtKeyTyped
    String idProduct = null;
    double grossAmt = 0.0;
    double discountAmt = 0.0;
    double netAmt = 0.0;
    String grossDb = null;
    String discountAmtDb = null;
    String netAmtDb = null;

    String disAmtTable = null;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        dtm = (DefaultTableModel) jTable1.getModel();

        int i = jTable1.getSelectedRow();
        int j = jTable1.getSelectedColumn();

        String s = dtm.getValueAt(i, j).toString();

        String s1 = "REMOVE";

        if (s.equals(s1)) {

            String amt = jTable1.getValueAt(i, 5).toString();
            String discount_amt = jTable1.getValueAt(i, 6).toString();
            String net_amt = jTable1.getValueAt(i, 7).toString();

            grossAmt = Double.parseDouble(jTextFieldGrossAmt.getText());
            Double grossAmtText = grossAmt - Double.parseDouble(amt);

            discountAmt = Double.parseDouble(jTextFieldTotalDiscount.getText());
            Double discountAmtText = discountAmt - Double.parseDouble(discount_amt);

            netAmt = Double.parseDouble(jTextFieldNetAmt.getText());
            Double netAmtText = netAmt - Double.parseDouble(net_amt);

            discountAmt = discountAmt + Double.parseDouble(discount_amt);
            netAmt = netAmt + Double.parseDouble(net_amt);

            DecimalFormat df = new DecimalFormat("0.00");
            grossDb = df.format(grossAmtText);

            DecimalFormat df1 = new DecimalFormat("0.00");
            discountAmtDb = df1.format(discountAmtText);

            DecimalFormat df2 = new DecimalFormat("0.00");
            netAmtDb = df2.format(netAmtText);

            jTextFieldGrossAmt.setText(grossDb);
            jTextFieldTotalDiscount.setText(discountAmtDb);
            jTextFieldNetAmt.setText(netAmtDb);

            dtm.removeRow(i);
            dtm2.setRowCount(0);

            jTextFieldPayAmt.setText("");
            jTextFieldBalance.setText("");

        }

        grossAmt = 0.0;
        discountAmt = 0.0;
        netAmt = 0.0;

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextFieldBalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBalanceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBalanceFocusGained

    private void jTextFieldBalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBalanceFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBalanceFocusLost

    private void jTextFieldBalanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBalanceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBalanceKeyReleased

    private void jTextFieldBalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBalanceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBalanceKeyTyped
    Double totDiscountDB = 0.00;
    Double pay_Amt, net_Amt = 0.0;
    String title = null;
    String address1 = null;
    String address2 = null;
    String address3 = null;
    String mobile1 = null;
    String mobile2 = null;
    String idcustomer = null;
    private void jButtonPayNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayNewActionPerformed

        if (jComboBoxPaymentMode.getSelectedIndex() == 0 || jTextFieldGrossAmt.getText().length() == 0
                || jTextFieldTotalDiscount.getText().length() == 0 || jTextFieldNetAmt.getText().length() == 0 || jTextFieldBalance.getText().length() == 0) {

            if (jComboBoxPaymentMode.getSelectedIndex() == 0) {
                jLabelPayMode.setText("Required");
            }

            if (jTextFieldGrossAmt.getText().length() == 0) {
                jLabelGrossAmt.setText("Required");
            }

            if (jTextFieldTotalDiscount.getText().length() == 0) {
                jLabelDiscountAmt.setText("Required");
            }

            if (jTextFieldNetAmt.getText().length() == 0) {
                jLabelNetAmt.setText("Required");
            }

            if (jTextFieldBalance.getText().length() == 0) {
                jLabelBalance.setText("Required");
            }

        } else {

            pay_Amt = Double.parseDouble(jTextFieldBalance.getText());
            net_Amt = Double.parseDouble(jTextFieldNetAmt.getText());

            if (pay_Amt < 0) {

                JOptionPane.showMessageDialog(rootPane, "Unable to pay this payment. Please check the pay amount");

                jTextFieldPayAmt.grabFocus();

            } else if (pay_Amt >= 0) {

                AccessPoint ap = new AccessPoint();
                String status = ap.acceesPoint_01(USER_NAME);

                if (status.equals("true")) {

                    int s = JOptionPane.showConfirmDialog(null, "Are you sure, you want to pay?");

                    if (s == JOptionPane.OK_OPTION) {

                        try {
                            String nic = null;
                            if (jTextFieldConfirmNIC.getText().length() == 0) {
                                nic = "000000000V";
                            } else if (jTextFieldConfirmNIC.getText().length() > 0) {
                                nic = jTextFieldConfirmNIC.getText();
                            }

                            cls.db.myConnection().createStatement().executeUpdate("INSERT INTO customer(location_idlocation, nic, tite, full_name, last_name, name_with_intials, gender, address_line1, address_line2, address_line3, mobile1, mobile2,intials,dob,status)"
                                    + "VALUES('" + LOCATION_ID + "', '" + nic + "', '" + title + "', '" + jTextFieldFullName.getText() + "', "
                                    + "'" + lastName + "', '" + initials + "', '" + gender + "', '" + address1 + "', '" + address2 + "', "
                                    + "'" + address3 + "', '" + mobile1 + "', '" + mobile2 + "' , '" + jTextAreaIntials.getText() + "' , '" + dob + "', '" + "ACTIVE" + "' )");

                            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT idcustomer FROM customer");

                            while (rs.next()) {
                                idcustomer = rs.getString("idcustomer");
                            }

                            cls.db.myConnection().createStatement().executeUpdate("INSERT INTO invoice_header (idinvoice_id, idemployeeMaster, date, time, gross_amt, discount, net_amt,balance, idpayment_details,idcustomer)"
                                    + "VALUES('" + REF + "' ,'" + user_id + "' , '" + jTextFieldDate.getText() + "' , '" + time + "', '" + FrameBill.jTextFieldGrossAmt.getText() + "' , '" + FrameBill.jTextFieldTotalDiscount.getText() + "' ,"
                                    + " '" + FrameBill.jTextFieldNetAmt.getText() + "' ,  '" + FrameBill.jTextFieldBalance.getText() + "' ,  '" + idpayment_details + "', '" + idcustomer + "') ");

                            DefaultTableModel dtm = new DefaultTableModel();
                            dtm = (DefaultTableModel) FrameBill.jTable1.getModel();

                            for (int i = 0; i < FrameBill.jTable1.getRowCount(); i++) {

                                String id_productDb = dtm.getValueAt(i, 0).toString();
                                String product_codeDb = dtm.getValueAt(i, 1).toString();
                                String selling_priceDb = dtm.getValueAt(i, 3).toString();
                                String qtyDb = dtm.getValueAt(i, 4).toString();
                                String amountDb = dtm.getValueAt(i, 5).toString();
                                String discountDb = dtm.getValueAt(i, 6).toString();
                                String net_amtDb = dtm.getValueAt(i, 7).toString();
                                String Status = dtm.getValueAt(i, 9).toString();
                                String cost = dtm.getValueAt(i, 10).toString();
                                String imei = dtm.getValueAt(i, 11).toString();

                                cls.db.myConnection().createStatement().executeUpdate("INSERT INTO invoice (id_product, product_code, qty, selling_price, amount, discount, net_amt, idinvoice_id,status,date,cost,idemployeeMaster)"
                                        + "VALUES('" + id_productDb + "' ,'" + product_codeDb + "' , '" + qtyDb + "' , '" + selling_priceDb + "', '" + amountDb + "' , '" + discountDb + "' ,"
                                        + " '" + net_amtDb + "' , '" + REF + "' , '" + Status + "', '" + jTextFieldDate.getText() + "', '" + cost + "', '" + user_id + "') ");
                                
                                cls.db.myConnection().createStatement().executeUpdate("UPDATE imei SET status='" + "SELL" + "' WHERE imei1='" + imei + "' ");

                            }
                            
                             cls.db.myConnection().createStatement().executeUpdate("UPDATE hold_bill SET status='" + "TAKING" + "' WHERE idhold_bill='" + id_hold_bill + "' ");

                            dtm2 = (DefaultTableModel) jTablePayentDetails.getModel();

                            for (int i = 0; i < jTablePayentDetails.getRowCount(); i++) {

                                String idpayment_details = dtm2.getValueAt(i, 0).toString();
                                String idpayment_mode = dtm2.getValueAt(i, 1).toString();
                                String idinvoice_id = dtm2.getValueAt(i, 2).toString();
                                String payment = dtm2.getValueAt(i, 4).toString();
                                String discount = dtm2.getValueAt(i, 5).toString();
                                String date = dtm2.getValueAt(i, 6).toString();

                                cls.db.myConnection().createStatement().executeUpdate("INSERT INTO payment_details (idpayment_details, idpayment_mode, idinvoice_id, payment, discount, date)"
                                        + "VALUES('" + idpayment_details + "' ,'" + idpayment_mode + "' , '" + idinvoice_id + "' , '" + payment + "', '" + discount + "' , '" + date + "' ) ");

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, e);
                        }

                        JReport jp = new JReport();
                        jp.print(USER_NAME);

                        System.gc();

                        this.dispose();
                        FrameBill b1 = new FrameBill(LOCATION_ID, USER_NAME);
                        b1.setVisible(true);

                    }

                } else {
                    jButtonPayNew.setVisible(false);
                    JOptionPane.showMessageDialog(rootPane, "This access is not grant you.");

                }

            }

        }

    }//GEN-LAST:event_jButtonPayNewActionPerformed

    private void jTextFieldCashierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCashierFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCashierFocusGained

    private void jTextFieldCashierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCashierFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCashierFocusLost

    private void jTextFieldCashierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCashierKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCashierKeyReleased

    private void jTextFieldCashierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCashierKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCashierKeyTyped

    private void jTextFieldLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocationFocusGained

    private void jTextFieldLocationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLocationFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocationFocusLost

    private void jTextFieldLocationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLocationKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocationKeyReleased

    private void jTextFieldLocationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLocationKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocationKeyTyped

    private void jTextFieldDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateFocusGained

    private void jTextFieldDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateFocusLost

    private void jTextFieldDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateKeyReleased

    private void jTextFieldDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateKeyTyped

    private void jTextFieldTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTimeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimeFocusGained

    private void jTextFieldTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTimeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimeFocusLost

    private void jTextFieldTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimeKeyReleased

    private void jTextFieldTimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimeKeyTyped

    String P_Name = null;
    String P_price = null;
    String P_Code = null;
    String P_Cost = null;
    String IMEI = null;
    private void jComboBoxProductCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProductCodeActionPerformed

        try {

            ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM imei WHERE imei1='" + jComboBoxProductCode.getSelectedItem().toString() + "'");
            if (rs1.next()) {
                String idProduct = rs1.getString("idproduct");
                String IMEI = rs1.getString("imei1");
                System.out.println(IMEI);

                ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE idproduct='" +idProduct+ "'");
                if (rs2.next()) {
                    P_Code = rs2.getString("p_code");

                }
            }

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE p_code='" + jComboBoxProductCode.getSelectedItem().toString() + "' OR p_code='" +P_Code+ "' ");

            while (rs.next()) {
                P_Code = rs.getString("p_code");
                P_Name = rs.getString("p_name");
                P_price = rs.getString("selling_price");
                idProduct = rs.getString("idproduct");
                P_Cost = rs.getString("cost");

                jTextFieldSellingPrice.setText(P_price);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }


    }//GEN-LAST:event_jComboBoxProductCodeActionPerformed

    String net = null;
    Double dis = 0.00;
    private void jTextFieldSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSellingPriceKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' & c <= '9' || c == '.' || c == '+' || c == '*')) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldSellingPriceKeyTyped

    private void jTextFieldSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSellingPriceActionPerformed

        Bill b = new Bill();
        Double avbQty = b.availableQty(idProduct);

        try {

            if (jTextFieldSellingPrice.getText().length() >= 5) {

                String str = jTextFieldSellingPrice.getText();

                char lasetChar = str.charAt(str.length() - 1);
                System.out.println(lasetChar);

                String price = str.substring(0, str.indexOf('+'));
                String qty = str.substring(str.indexOf('+') + 1, str.lastIndexOf('+'));
                String discount = str.substring(str.lastIndexOf('+') + 1, str.length());

                if (avbQty < Double.parseDouble(qty)) {

                    JOptionPane.showMessageDialog(rootPane, "Available quantity =" + avbQty + "\nPlease enter correct quantity");
                    jTextFieldSellingPrice.setText("");
                    jComboBoxProductCode.grabFocus();

                } else {

                    if (lasetChar == '*') {

                        StringBuilder s = new StringBuilder(discount);
                        String discount1 = s.deleteCharAt(s.lastIndexOf("*")).toString();

                        DecimalFormat df = new DecimalFormat("0.00");
                        String priceText = df.format(Double.parseDouble(price));
                        P_price = priceText;

                        Double PRICE = Double.parseDouble(price) * Double.parseDouble(qty);

                        Double COST = Double.parseDouble(P_Cost) * Double.parseDouble(qty);

                        Double DISCOUNT = PRICE * Double.parseDouble(discount1) / 100;

                        Double NET_AMT = PRICE - DISCOUNT;

                        DecimalFormat df1 = new DecimalFormat("0.00");
                        String amtText = df1.format(PRICE);

                        DecimalFormat df2 = new DecimalFormat("0.00");
                        String discountText = df2.format(DISCOUNT);

                        DecimalFormat df3 = new DecimalFormat("0.00");
                        String netAmtText = df3.format(NET_AMT);

                        dtm = (DefaultTableModel) jTable1.getModel();

                        Vector v = new Vector();
                        v.add(idProduct);
                        v.add(jComboBoxProductCode.getSelectedItem().toString());
                        v.add(P_Name);
                        v.add(P_price);
                        v.add(qty);
                        v.add(amtText);
                        v.add(discountText);
                        v.add(netAmtText);
                        v.add("REMOVE");
                        v.add("ACTIVE");
                        v.add(COST);
                        v.add(jComboBoxProductCode.getSelectedItem().toString());

                        dtm.addRow(v);

                    } else {

                        DecimalFormat df = new DecimalFormat("0.00");
                        String priceText = df.format(Double.parseDouble(price));
                        P_price = priceText;

                        Double PRICE = Double.parseDouble(price) * Double.parseDouble(qty);

                        Double COST = Double.parseDouble(P_Cost) * Double.parseDouble(qty);

                        Double NET_AMT = PRICE - Double.parseDouble(discount);

                        DecimalFormat df1 = new DecimalFormat("0.00");
                        String amtText = df1.format(PRICE);

                        DecimalFormat df2 = new DecimalFormat("0.00");
                        String discountText = df2.format(Double.parseDouble(discount));

                        DecimalFormat df3 = new DecimalFormat("0.00");
                        String netAmtText = df3.format(NET_AMT);

                        dtm = (DefaultTableModel) jTable1.getModel();

                        Vector v = new Vector();
                        v.add(idProduct);
                        v.add(jComboBoxProductCode.getSelectedItem().toString());
                        v.add(P_Name);
                        v.add(P_price);
                        v.add(qty);
                        v.add(amtText);
                        v.add(discountText);
                        v.add(netAmtText);
                        v.add("REMOVE");
                        v.add("ACTIVE");
                        v.add(COST);
                        v.add(jComboBoxProductCode.getSelectedItem().toString());

                        dtm.addRow(v);

                    }

                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        String amt = jTable1.getValueAt(i, 5).toString();
                        String discount_amt = jTable1.getValueAt(i, 6).toString();
                        String net_amt = jTable1.getValueAt(i, 7).toString();

                        grossAmt = grossAmt + Double.parseDouble(amt);
                        discountAmt = discountAmt + Double.parseDouble(discount_amt);
                        netAmt = netAmt + Double.parseDouble(net_amt);

                        DecimalFormat df4 = new DecimalFormat("0.00");
                        grossDb = df4.format(grossAmt);

                        DecimalFormat df5 = new DecimalFormat("0.00");
                        discountAmtDb = df5.format(discountAmt);

                        DecimalFormat df6 = new DecimalFormat("0.00");
                        netAmtDb = df6.format(netAmt);

                        jTextFieldGrossAmt.setText(grossDb);
                        jTextFieldTotalDiscount.setText(discountAmtDb);
                        jTextFieldNetAmt.setText(netAmtDb);
                        jTextFieldBalance.setText("-" + netAmtDb);

                    }
                    grossAmt = 0.0;
                    discountAmt = 0.0;
                    netAmt = 0.0;
                    totAmtdb = "0.0";

                    jTextFieldSellingPrice.setText("");
                    jComboBoxProductCode.grabFocus();

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Incorect format. Please follow this format.\nPRICE+QTY+DISCOUNT* OR PRICE+QTY+DISCOUNT");

        }

    }//GEN-LAST:event_jTextFieldSellingPriceActionPerformed

    private void jTextFieldPayAmtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPayAmtKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' & c <= '9' || c == '.' || c == '+' || c == '*')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPayAmtKeyTyped

    private void jTextFieldPayAmtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPayAmtKeyReleased

        char c = evt.getKeyChar();
        if (c == evt.VK_BACK_SPACE) {

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String amt = jTable1.getValueAt(i, 5).toString();
                String discount_amt = jTable1.getValueAt(i, 6).toString();
                String net_amt = jTable1.getValueAt(i, 7).toString();

                grossAmt = grossAmt + Double.parseDouble(amt);
                discountAmt = discountAmt + Double.parseDouble(discount_amt);
                netAmt = netAmt + Double.parseDouble(net_amt);

                DecimalFormat df4 = new DecimalFormat("0.00");
                grossDb = df4.format(grossAmt);

                DecimalFormat df5 = new DecimalFormat("0.00");
                discountAmtDb = df5.format(discountAmt);

                DecimalFormat df6 = new DecimalFormat("0.00");
                netAmtDb = df6.format(netAmt);

                jTextFieldGrossAmt.setText(grossDb);
                jTextFieldTotalDiscount.setText(discountAmtDb);
                jTextFieldNetAmt.setText(netAmtDb);

            }
            grossAmt = 0.0;
            discountAmt = 0.0;
            netAmt = 0.0;
            totAmtdb = "0.0";

        }

    }//GEN-LAST:event_jTextFieldPayAmtKeyReleased

    private void jTextFieldPayAmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPayAmtActionPerformed
        try {

            String str = jTextFieldPayAmt.getText();

            char lasetChar = str.charAt(str.length() - 1);
            System.out.println(lasetChar);

            String payAmount = str.substring(0, str.indexOf('+'));
            String discount = str.substring(str.lastIndexOf('+') + 1, str.length());

            System.out.println(payAmount);
            System.out.println(discount);

            if (payAmount.length() > 0) {

                pay_Amt = Double.parseDouble(payAmount);
                net_Amt = Double.parseDouble(jTextFieldNetAmt.getText());

//                if (pay_Amt < net_Amt) {
//
//                    JOptionPane.showMessageDialog(rootPane, "Unable to pay this payment. Please check the pay amount");
//
//                    jTextFieldPayAmt.grabFocus();
//
//                }
//                else {
                if (lasetChar == '*') {

                    StringBuilder s = new StringBuilder(discount);
                    String discount1 = s.deleteCharAt(s.lastIndexOf("*")).toString();

                    Double NET_AMT = 0.0;

                    if (discount.equals("0")) {

                        for (int i = 0; i < jTable1.getRowCount(); i++) {
                            String amt = jTable1.getValueAt(i, 5).toString();
                            String discount_amt = jTable1.getValueAt(i, 6).toString();
                            String net_amt = jTable1.getValueAt(i, 7).toString();

                            grossAmt = grossAmt + Double.parseDouble(amt);
                            discountAmt = discountAmt + Double.parseDouble(discount_amt);
                            netAmt = netAmt + Double.parseDouble(net_amt);

                            DecimalFormat df4 = new DecimalFormat("0.00");
                            grossDb = df4.format(grossAmt);

                            DecimalFormat df5 = new DecimalFormat("0.00");
                            discountAmtDb = df5.format(discountAmt);

                            DecimalFormat df6 = new DecimalFormat("0.00");
                            netAmtDb = df6.format(netAmt);

                            jTextFieldGrossAmt.setText(grossDb);
                            jTextFieldTotalDiscount.setText(discountAmtDb);
                            jTextFieldNetAmt.setText(netAmtDb);

                        }
                        grossAmt = 0.0;
                        discountAmt = 0.0;
                        netAmt = 0.0;
                        totAmtdb = "0.0";

                    } else if (discount.length() > 0) {

                        //            System.out.println(netAmtDb);
                        dis = Double.parseDouble(netAmtDb) * Double.parseDouble(discount1) / 100;

                        NET_AMT = Double.parseDouble(netAmtDb) - dis;

                        DecimalFormat df = new DecimalFormat("0.00");
                        net = df.format(NET_AMT);

                        jTextFieldNetAmt.setText(net);

                        DecimalFormat df4 = new DecimalFormat("0.00");
                        String s1 = df4.format(dis);
                        jTextFieldTotalDiscount.setText(s1);

                    }

                    dtm2 = (DefaultTableModel) jTablePayentDetails.getModel();

                    Vector v = new Vector();
                    v.add(idpayment_details);
                    v.add(id_Payment);
                    v.add(REF);
                    v.add(jComboBoxPaymentMode.getSelectedItem().toString());
                    v.add(payAmount);
                    v.add(discount1);
                    v.add(jTextFieldDate.getText());
                    v.add("REMOVE");

                    dtm2.addRow(v);

                } else {

                    Double NET_AMT = 0.0;

                    if (discount.equals("0")) {

                        for (int i = 0; i < jTable1.getRowCount(); i++) {
                            String amt = jTable1.getValueAt(i, 5).toString();
                            String discount_amt = jTable1.getValueAt(i, 6).toString();
                            String net_amt = jTable1.getValueAt(i, 7).toString();

                            grossAmt = grossAmt + Double.parseDouble(amt);
                            discountAmt = discountAmt + Double.parseDouble(discount_amt);
                            netAmt = netAmt + Double.parseDouble(net_amt);

                            DecimalFormat df4 = new DecimalFormat("0.00");
                            grossDb = df4.format(grossAmt);

                            DecimalFormat df5 = new DecimalFormat("0.00");
                            discountAmtDb = df5.format(discountAmt);

                            DecimalFormat df6 = new DecimalFormat("0.00");
                            netAmtDb = df6.format(netAmt);

                            jTextFieldGrossAmt.setText(grossDb);
                            jTextFieldTotalDiscount.setText(discountAmtDb);
                            jTextFieldNetAmt.setText(netAmtDb);

                        }
                        grossAmt = 0.0;
                        discountAmt = 0.0;
                        netAmt = 0.0;
                        totAmtdb = "0.0";

                    } else if (discount.length() > 0) {

                        //            System.out.println(netAmtDb);
                        NET_AMT = Double.parseDouble(netAmtDb) - Double.parseDouble(discount);
                        DecimalFormat df = new DecimalFormat("0.00");
                        net = df.format(NET_AMT);

                        jTextFieldNetAmt.setText(net);

                        Double currentdis = Double.parseDouble(discount);
                        DecimalFormat df4 = new DecimalFormat("0.00");
                        String s = df4.format(currentdis);

                        jTextFieldTotalDiscount.setText(s);

                    }

                    dtm2 = (DefaultTableModel) jTablePayentDetails.getModel();

                    Vector v = new Vector();
                    v.add(idpayment_details);
                    v.add(id_Payment);
                    v.add(REF);
                    v.add(jComboBoxPaymentMode.getSelectedItem().toString());
                    v.add(payAmount);
                    v.add(discount);
                    v.add(jTextFieldDate.getText());
                    v.add("REMOVE");

                    dtm2.addRow(v);

                }

                if (payAmount.length() > 0 && jTextFieldNetAmt.getText().length() > 0) {

                    jLabelPayAmt.setText("");
                    jLabelBalance.setText("");

                    Double payAmt = 0.0;
                    String balDB = null;
                    Double netAmt = 0.0;
                    Double bal = 0.0;

                    netAmt = Double.parseDouble(jTextFieldNetAmt.getText());

                    for (int i = 0; i < jTablePayentDetails.getRowCount(); i++) {
                        String amt = jTablePayentDetails.getValueAt(i, 4).toString();

                        payAmt = payAmt + Double.parseDouble(amt);
                        bal = payAmt - netAmt;

                        DecimalFormat df = new DecimalFormat("0.00");
                        balDB = df.format(bal);

                        jTextFieldBalance.setText(balDB);

                    }

                }

                if (jTextFieldPayAmt.getText().length() == 0) {
                    jTextFieldBalance.setText(jTextFieldNetAmt.getText());
                }

                if (customerStatus.equals("new")) {
                    jButtonPayNew.grabFocus();
                } else if (customerStatus.equals("old")) {
                    jButtonPayOld.grabFocus();
                }

//                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Incorect format. Please follow this format.\nPAY AMOUNT+DISCOUNT* OR PAY AMOUNT+DISCOUNT");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextFieldPayAmtActionPerformed

    private void jButtonCalculatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalculatorActionPerformed

        try {

            Runtime r = Runtime.getRuntime();
            r.exec("C:\\Windows\\system32\\calc.exe");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButtonCalculatorActionPerformed

    private void jButtonViewStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewStockActionPerformed

        FrameViewStock vs = new FrameViewStock(LOCATION_ID, USER_NAME);
        vs.setVisible(true);

    }//GEN-LAST:event_jButtonViewStockActionPerformed

    private void jButtonReprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReprintActionPerformed

        FrameReprintInvoice r = new FrameReprintInvoice(LOCATION_ID, USER_NAME);
        r.setVisible(true);
    }//GEN-LAST:event_jButtonReprintActionPerformed

    private void jComboBoxSalesTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSalesTypeActionPerformed

        if (jComboBoxSalesType.getSelectedIndex() == 1) {
            jTextField1.setVisible(true);
            jComboBoxProductCode.setEnabled(false);
        } else {
            jTextField1.setVisible(false);
            jComboBoxProductCode.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBoxSalesTypeActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        try {
            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM invoice WHERE idinvoice_id='" + jTextField1.getText() + "' AND status='" + "ACTIVE" + "' ");
            while (rs.next()) {
                Vector v = new Vector();

                String id_product = rs.getString("id_product");

                v.add(rs.getString("id_product"));

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE idproduct='" + id_product + "' ");
                while (rs1.next()) {
                    v.add(rs1.getString("p_code"));
                    v.add(rs1.getString("p_name"));
                }

                v.add(rs.getString("selling_price"));

                String qty = rs.getString("qty");
                v.add("-" + qty);

                String amount = rs.getString("amount");
                v.add("-" + amount);

                v.add(rs.getString("discount"));

                String net_amt = rs.getString("net_amt");
                v.add("-" + net_amt);

                v.add("REMOVE");
                v.add("CHANGE");

                v.add(rs.getString("cost"));

                dtm.addRow(v);

            }

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String amt = jTable1.getValueAt(i, 5).toString();
                String discount_amt = jTable1.getValueAt(i, 6).toString();
                String net_amt = jTable1.getValueAt(i, 7).toString();

                grossAmt = grossAmt + Double.parseDouble(amt);
                discountAmt = discountAmt + Double.parseDouble(discount_amt);
                netAmt = netAmt + Double.parseDouble(net_amt);

                DecimalFormat df4 = new DecimalFormat("0.00");
                grossDb = df4.format(grossAmt);

                DecimalFormat df5 = new DecimalFormat("0.00");
                discountAmtDb = df5.format(discountAmt);

                DecimalFormat df6 = new DecimalFormat("0.00");
                netAmtDb = df6.format(netAmt);

                jTextFieldGrossAmt.setText(grossDb);
                jTextFieldTotalDiscount.setText(discountAmtDb);
                jTextFieldNetAmt.setText(netAmtDb);

            }
            grossAmt = 0.0;
            discountAmt = 0.0;
            netAmt = 0.0;
            totAmtdb = "0.0";

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e + "\nCan't change the item\nPreviously change this bill item");
            this.dispose();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        char c = evt.getKeyChar();
        if (c == evt.VK_BACK_SPACE) {
            dtm.setRowCount(0);
            jTextFieldGrossAmt.setText("");
            jTextFieldTotalDiscount.setText("");
            jTextFieldNetAmt.setText("");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButtonHoldBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoldBillActionPerformed

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dd = sdf.format(d);

        if (dtm.getRowCount() > 0 && jTextFieldGrossAmt.getText().length() > 0 && jTextFieldTotalDiscount.getText().length() > 0 && jTextFieldNetAmt.getText().length() > 0) {

            int s = JOptionPane.showConfirmDialog(null, "Are you sure, you want to hold the bill?");

            if (s == JOptionPane.OK_OPTION) {

                try {

                    cls.db.myConnection().createStatement().executeUpdate("INSERT INTO hold_bill (idhold_bill, gross_amt, discount_amt, net_amt, date, time, status,date1)"
                            + "VALUES('" + id_hold + "' ,'" + jTextFieldGrossAmt.getText() + "' , '" + jTextFieldTotalDiscount.getText() + "' , '" + jTextFieldNetAmt.getText() + "',"
                            + " '" + jTextFieldDate.getText() + "' , '" + time + "' , '" + "HOLD" + "', '" + dd + "' ) ");

                    dtm = (DefaultTableModel) FrameBill.jTable1.getModel();

                    for (int i = 0; i < FrameBill.jTable1.getRowCount(); i++) {

                        String id_productDb = dtm.getValueAt(i, 0).toString();
                        String product_codeDb = dtm.getValueAt(i, 1).toString();
                        String discription = dtm.getValueAt(i, 2).toString();
                        String selling_priceDb = dtm.getValueAt(i, 3).toString();
                        String qtyDb = dtm.getValueAt(i, 4).toString();
                        String amountDb = dtm.getValueAt(i, 5).toString();
                        String discountDb = dtm.getValueAt(i, 6).toString();
                        String net_amtDb = dtm.getValueAt(i, 7).toString();
                        String Status = dtm.getValueAt(i, 9).toString();
                        String cost = dtm.getValueAt(i, 10).toString();

                        cls.db.myConnection().createStatement().executeUpdate("INSERT INTO hold_bill_header (id_product, product_code, discription, selling_price, qty, amount, discount, net_amt, idhold_bill,cost)"
                                + "VALUES('" + id_productDb + "', '" + product_codeDb + "' , '" + discription + "' , '" + selling_priceDb + "', '" + qtyDb + "' , '" + amountDb + "' , '" + discountDb + "' , '" + net_amtDb + "' , '" + id_hold + "', '" + cost + "') ");

                    }

                    this.dispose();
                    FrameBill b1 = new FrameBill(LOCATION_ID, USER_NAME);
                    b1.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e + "\nPlease add the item details.");
                }
            }

        }
    }//GEN-LAST:event_jButtonHoldBillActionPerformed

    private void jButtonTakingBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTakingBillActionPerformed

        jScrollPane2.setVisible(true);
        jTableHoldTb.setVisible(true);

        dtm1 = (DefaultTableModel) jTableHoldTb.getModel();
        dtm1.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM hold_bill WHERE status='" + "HOLD" + "' ");
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("idhold_bill"));
                v.add(rs.getString("date"));
                v.add(rs.getString("time"));
                v.add(rs.getString("net_amt"));
                v.add(rs.getString("status"));
                v.add("TAKING");

                dtm1.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButtonTakingBillActionPerformed
    String id_hold_bill = null;
    private void jTableHoldTbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHoldTbMouseClicked

        dtm = (DefaultTableModel) jTable1.getModel();
        try {

            int i = jTableHoldTb.getSelectedRow();
            int j = jTableHoldTb.getSelectedColumn();

            String s = dtm1.getValueAt(i, j).toString();

            String s1 = "TAKING";

            if (s.equals(s1)) {
                id_hold_bill = jTableHoldTb.getValueAt(i, 0).toString();

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM hold_bill WHERE idhold_bill='" + id_hold_bill + "'  ");
                while (rs1.next()) {
                    jTextFieldGrossAmt.setText(rs1.getString("gross_amt"));
                    jTextFieldTotalDiscount.setText(rs1.getString("discount_amt"));
                    jTextFieldNetAmt.setText(rs1.getString("net_amt"));
                }

                dtm.setRowCount(0);

                ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM hold_bill_header WHERE idhold_bill='" + id_hold_bill + "'  ");

                while (rs2.next()) {
                    Vector v = new Vector();
                    v.add(rs2.getString("id_product"));
                    v.add(rs2.getString("product_code"));
                    v.add(rs2.getString("discription"));
                    v.add(rs2.getString("selling_price"));
                    v.add(rs2.getString("qty"));
                    v.add(rs2.getString("amount"));
                    v.add(rs2.getString("discount"));
                    v.add(rs2.getString("net_amt"));
                    v.add("REMOVE");
                    v.add("ACTIVE");
                    v.add(rs2.getString("cost"));

                    dtm.addRow(v);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_jTableHoldTbMouseClicked
    String dob = null;
    String NIC;
    private void jTextFieldConfirmNICFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldConfirmNICFocusLost

    }//GEN-LAST:event_jTextFieldConfirmNICFocusLost

    private void jTextFieldConfirmNICKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldConfirmNICKeyPressed

    }//GEN-LAST:event_jTextFieldConfirmNICKeyPressed
    String customerStatus = "new";
    private void jTextFieldConfirmNICKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldConfirmNICKeyReleased
        String s = jTextFieldConfirmNIC.getText();
        jTextFieldConfirmNIC.setText(s.toUpperCase());

        if (jTextFieldConfirmNIC.getText().length() == 12) {

            getSex2();
            getYear2();
            setMonth2();
            getDays2();
            dob = (j + "" + "-" + mo1 + "" + "-" + da1);

        } else if (jTextFieldConfirmNIC.getText().length() == 10) {

            getSex();
            getYear();
            setMonth();
            getDays();
            dob = (j + "" + "-" + mo1 + "" + "-" + da1);

        }

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM customer WHERE nic='" + jTextFieldConfirmNIC.getText() + "'  ");

            while (rs.next()) {
                NIC = rs.getString("nic");
                jTextFieldFullName.setText(rs.getString("full_name"));
                idcustomer = rs.getString("idcustomer");

                jButtonPayOld.setVisible(true);
                jButtonPayNew.setVisible(false);
                customerStatus = "old";

            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
        }

        char c = evt.getKeyChar();
        if (c == evt.VK_BACK_SPACE || c == evt.VK_TAB) {
            idcustomer = null;
            jTextFieldFullName.setText("");
            NIC = null;
            jButtonPayOld.setVisible(false);
            jButtonPayNew.setVisible(true);
            customerStatus = "new";
        }


    }//GEN-LAST:event_jTextFieldConfirmNICKeyReleased

    private void jTextFieldConfirmNICKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldConfirmNICKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= 'v' & c <= 'x' || c >= 'V' & c <= 'X' || c >= '0' & c <= '9')) {
            evt.consume();
        }

        if (jTextFieldConfirmNIC.getText().length() > 11) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldConfirmNICKeyTyped

    private void jTextFieldFullNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldFullNameFocusGained

        jTextAreaIntials.setText("");
    }//GEN-LAST:event_jTextFieldFullNameFocusGained
    String lastName = null;
    String initials = null;
    private void jTextFieldFullNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldFullNameFocusLost

        String s = jTextFieldFullName.getText();

        String[] arr = s.split(" ");
        // print all the initials
        for (int i = 0; i < arr.length - 1; i++) {

            jTextAreaIntials.append(arr[i].charAt(0) + ".");
        }

        // print the last name
        lastName = (arr[arr.length - 1]);

        try {

            initials = jTextAreaIntials.getText() + lastName;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e + "\nPlease contact the system administrator");
            this.dispose();
        }

    }//GEN-LAST:event_jTextFieldFullNameFocusLost

    private void jTextFieldFullNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFullNameKeyReleased

        String s = jTextFieldFullName.getText();
        jTextFieldFullName.setText(s.toUpperCase());
    }//GEN-LAST:event_jTextFieldFullNameKeyReleased

    private void jButtonPayOldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayOldActionPerformed

        if (jComboBoxPaymentMode.getSelectedIndex() == 0 || jTextFieldGrossAmt.getText().length() == 0
                || jTextFieldTotalDiscount.getText().length() == 0 || jTextFieldNetAmt.getText().length() == 0 || jTextFieldBalance.getText().length() == 0) {

            if (jComboBoxPaymentMode.getSelectedIndex() == 0) {
                jLabelPayMode.setText("Required");
            }

            if (jTextFieldGrossAmt.getText().length() == 0) {
                jLabelGrossAmt.setText("Required");
            }

            if (jTextFieldTotalDiscount.getText().length() == 0) {
                jLabelDiscountAmt.setText("Required");
            }

            if (jTextFieldNetAmt.getText().length() == 0) {
                jLabelNetAmt.setText("Required");
            }

            if (jTextFieldBalance.getText().length() == 0) {
                jLabelBalance.setText("Required");
            }

        } else {

            pay_Amt = Double.parseDouble(jTextFieldBalance.getText());

            if (pay_Amt < 0) {

                JOptionPane.showMessageDialog(rootPane, "Unable to pay this payment. Please check the pay amount");

                jTextFieldPayAmt.grabFocus();

            } else if (pay_Amt >= 0) {

                AccessPoint ap = new AccessPoint();
                String status = ap.acceesPoint_01(USER_NAME);

                if (status.equals("true")) {

                    int s = JOptionPane.showConfirmDialog(null, "Are you sure, you want to pay?");

                    if (s == JOptionPane.OK_OPTION) {

                        try {

                            cls.db.myConnection().createStatement().executeUpdate("INSERT INTO invoice_header (idinvoice_id, idemployeeMaster, date, time, gross_amt, discount, net_amt,balance, idpayment_details,idcustomer)"
                                    + "VALUES('" + REF + "' ,'" + user_id + "' , '" + jTextFieldDate.getText() + "' , '" + time + "', '" + FrameBill.jTextFieldGrossAmt.getText() + "' , '" + FrameBill.jTextFieldTotalDiscount.getText() + "' ,"
                                    + " '" + FrameBill.jTextFieldNetAmt.getText() + "' ,  '" + FrameBill.jTextFieldBalance.getText() + "' ,  '" + idpayment_details + "', '" + idcustomer + "') ");

                            DefaultTableModel dtm = new DefaultTableModel();
                            dtm = (DefaultTableModel) FrameBill.jTable1.getModel();

                            for (int i = 0; i < FrameBill.jTable1.getRowCount(); i++) {

                                String id_productDb = dtm.getValueAt(i, 0).toString();
                                String product_codeDb = dtm.getValueAt(i, 1).toString();
                                String selling_priceDb = dtm.getValueAt(i, 3).toString();
                                String qtyDb = dtm.getValueAt(i, 4).toString();
                                String amountDb = dtm.getValueAt(i, 5).toString();
                                String discountDb = dtm.getValueAt(i, 6).toString();
                                String net_amtDb = dtm.getValueAt(i, 7).toString();
                                String Status = dtm.getValueAt(i, 9).toString();
                                String cost = dtm.getValueAt(i, 10).toString();
                                String imei = dtm.getValueAt(i, 11).toString();

                                cls.db.myConnection().createStatement().executeUpdate("INSERT INTO invoice (id_product, product_code, qty, selling_price, amount, discount, net_amt, idinvoice_id,status,date,cost,idemployeeMaster)"
                                        + "VALUES('" + id_productDb + "' ,'" + product_codeDb + "' , '" + qtyDb + "' , '" + selling_priceDb + "', '" + amountDb + "' , '" + discountDb + "' ,"
                                        + " '" + net_amtDb + "' , '" + REF + "' , '" + Status + "', '" + jTextFieldDate.getText() + "', '" + cost + "', '" + user_id + "') ");
                                
                                 cls.db.myConnection().createStatement().executeUpdate("UPDATE imei SET status='" + "SELL" + "' WHERE imei1='" + imei + "' ");
                            }
                            cls.db.myConnection().createStatement().executeUpdate("UPDATE hold_bill SET status='" + "TAKING" + "' WHERE idhold_bill='" + id_hold_bill + "' ");

                            dtm2 = (DefaultTableModel) jTablePayentDetails.getModel();

                            for (int i = 0; i < jTablePayentDetails.getRowCount(); i++) {

                                String idpayment_details = dtm2.getValueAt(i, 0).toString();
                                String idpayment_mode = dtm2.getValueAt(i, 1).toString();
                                String idinvoice_id = dtm2.getValueAt(i, 2).toString();
                                String payment = dtm2.getValueAt(i, 4).toString();
                                String discount = dtm2.getValueAt(i, 5).toString();
                                String date = dtm2.getValueAt(i, 6).toString();

                                cls.db.myConnection().createStatement().executeUpdate("INSERT INTO payment_details (idpayment_details, idpayment_mode, idinvoice_id, payment, discount, date)"
                                        + "VALUES('" + idpayment_details + "' ,'" + idpayment_mode + "' , '" + idinvoice_id + "' , '" + payment + "', '" + discount + "' , '" + date + "' ) ");

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, e);
                        }

                        JReport jp = new JReport();
                        jp.print(USER_NAME);

                        this.dispose();
                        FrameBill b1 = new FrameBill(LOCATION_ID, USER_NAME);
                        b1.setVisible(true);

                    }

                } else {
                    jButtonPayNew.setVisible(false);
                    JOptionPane.showMessageDialog(rootPane, "This access is not grant you.");

                }

            }

        }

    }//GEN-LAST:event_jButtonPayOldActionPerformed

    private void jTablePayentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePayentDetailsMouseClicked

        dtm2 = (DefaultTableModel) jTablePayentDetails.getModel();

        int k = jTablePayentDetails.getSelectedRow();
        int j = jTablePayentDetails.getSelectedColumn();

        String s = dtm2.getValueAt(k, j).toString();

        String s1 = "REMOVE";

        if (s.equals(s1)) {

            Double payAmt = 0.0;
            String balDB = null;
            Double netAmt = 0.0;
            Double currentbal = 0.0;
            Double bal = 0.0;

            netAmt = Double.parseDouble(jTextFieldNetAmt.getText());

            String amt = jTablePayentDetails.getValueAt(k, 4).toString();

            payAmt = Double.parseDouble(amt);
            currentbal = Double.parseDouble(jTextFieldBalance.getText());
            bal = currentbal - payAmt;

            DecimalFormat df = new DecimalFormat("0.00");
            balDB = df.format(bal);

            jTextFieldBalance.setText(balDB);

            dtm2.removeRow(k);
        }
    }//GEN-LAST:event_jTablePayentDetailsMouseClicked

    private void jButtonPayNewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonPayNewFocusLost
        jComboBoxPaymentMode.grabFocus();
    }//GEN-LAST:event_jButtonPayNewFocusLost

    private void jButtonPayOldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonPayOldFocusLost

        jComboBoxPaymentMode.grabFocus();
    }//GEN-LAST:event_jButtonPayOldFocusLost

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
            java.util.logging.Logger.getLogger(FrameBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalculator;
    private javax.swing.JButton jButtonHoldBill;
    private javax.swing.JButton jButtonPayNew;
    private javax.swing.JButton jButtonPayOld;
    private javax.swing.JButton jButtonReprint;
    private javax.swing.JButton jButtonTakingBill;
    private javax.swing.JButton jButtonViewStock;
    public static javax.swing.JComboBox jComboBoxPaymentMode;
    private javax.swing.JComboBox jComboBoxProductCode;
    private javax.swing.JComboBox jComboBoxSalesType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabelAmt;
    public static javax.swing.JLabel jLabelBalance;
    public static javax.swing.JLabel jLabelConfirmNIC;
    public static javax.swing.JLabel jLabelDiscountAmt;
    public static javax.swing.JLabel jLabelGrossAmt;
    public static javax.swing.JLabel jLabelNetAmt;
    public static javax.swing.JLabel jLabelPayAmt;
    public static javax.swing.JLabel jLabelPayMode;
    public static javax.swing.JLabel jLabelProductCode;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTable jTableHoldTb;
    private javax.swing.JTable jTablePayentDetails;
    public static javax.swing.JTextArea jTextAreaIntials;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextFieldBalance;
    public static javax.swing.JTextField jTextFieldCashier;
    public static javax.swing.JTextField jTextFieldConfirmNIC;
    public static javax.swing.JTextField jTextFieldDate;
    public static javax.swing.JTextField jTextFieldFullName;
    public static javax.swing.JTextField jTextFieldGrossAmt;
    public static javax.swing.JTextField jTextFieldLocation;
    public static javax.swing.JTextField jTextFieldNetAmt;
    public static javax.swing.JTextField jTextFieldPayAmt;
    public static javax.swing.JTextField jTextFieldSellingPrice;
    public static javax.swing.JTextField jTextFieldTime;
    public static javax.swing.JTextField jTextFieldTotalDiscount;
    // End of variables declaration//GEN-END:variables
}
