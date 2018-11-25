/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameBill;
import frame.FrameBillChange;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Bill {
    
    int j=0;

    //---------------------------------------------------- set data payment mode ----------------------------------------------------
    public void loadPayMode() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM payment_mode ");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("payment_name"));

            }
            FrameBill.jComboBoxPaymentMode.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

    }
    String userIdDb = null;
    String DateDb = null;
    String timeDb = null;
    String idPaymentDb = null;
    String idInvoice = null;
    String REF = null;

    String id_productDb = null;
    String product_codeDb = null;
    String qtyDb = null;
    String selling_priceDb = null;
    String amountDb = null;
    String discountDb = null;
    String net_amtDb = null;
    String totDiscountDB=null;
    
    Double totOPstock = 0.0;
    Double qty = 0.0;
    Double totSellingQty = 0.0;
    Double tot_Qty = 0.0;
    Double avialable_Qty = 0.0;

    String idProduct = null;

    public Double availableQty(String idProduct) {

        idProduct = idProduct;

        try {

            ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(opStock) FROM stock_entry WHERE idproduct='" + idProduct + "'  ");
            while (rs3.next()) {

                totOPstock = rs3.getDouble("SUM(opStock)");
            }

            ResultSet rs4 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(qty) FROM stock_entry WHERE idproduct='" + idProduct + "'  ");
            while (rs4.next()) {

                qty = rs4.getDouble("SUM(qty)");
            }

            ResultSet rs5 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(qty) FROM invoice WHERE id_product='" + idProduct + "' ");
            while (rs5.next()) {

                totSellingQty = rs5.getDouble("SUM(qty)");

            }

            tot_Qty = totOPstock + qty;

            avialable_Qty = tot_Qty - totSellingQty;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

        return avialable_Qty;
    }

    public void billChangeSearch() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameBillChange.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM invoice WHERE (idinvoice_id='" + FrameBillChange.jTextFieldInvoiceId.getText() + "' OR product_code='"+ FrameBillChange.jTextFieldInvoiceId.getText()+"' ) AND status='"+"ACTIVE"+"'  ");
            while (rs.next()) {

                Vector v = new Vector();
                idProduct = rs.getString("id_product");
                
                v.add(idProduct);
                v.add(rs.getString("product_code"));

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE idproduct='" + idProduct + "' ");
                while (rs1.next()) {
                    v.add(rs1.getString("p_name"));
                }

                v.add(rs.getString("selling_price"));
                v.add(rs.getString("qty"));
                v.add(rs.getString("amount"));
                v.add(rs.getString("discount"));
                v.add(rs.getString("net_amt"));

                ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM invoice_header WHERE idinvoice_id='" + FrameBillChange.jTextFieldInvoiceId.getText() + "' ");
                while (rs3.next()) {
                    userIdDb = rs3.getString("idemployeeMaster");
                    
                    FrameBillChange.jTextFieldGrossAmt.setText(rs3.getString("gross_amt"));
                    FrameBillChange.jTextFieldTotalDiscount.setText(rs3.getString("discount"));
                   
                    FrameBillChange.jTextFieldNetAmt.setText(rs3.getString("net_amt"));
                    FrameBillChange.jTextFieldDate.setText(rs3.getString("date"));
                    FrameBillChange.jTextFieldTime.setText(rs3.getString("time"));

                }
                
                ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM user WHERE iduser='" + userIdDb + "' ");
                while (rs2.next()) {

                    FrameBillChange.jTextFieldCashier.setText(rs2.getString("userName"));
                    String locationId = rs2.getString("location");

                    ResultSet rs4 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM location WHERE idlocation='" + locationId + "' ");
                    while (rs4.next()) {
                        FrameBillChange.jTextFieldLocation.setText(rs4.getString("locationName"));
                    }

                }
                
                
                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
