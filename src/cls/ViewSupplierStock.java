/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameSupplier;
import frame.FrameViewSupplier;
import frame.FrameViewSupplierStock;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class ViewSupplierStock {

    String idCountry = null;
    String nameInitial = null;
    String idSupplier = null;

    public void search() {
        String country_name = null;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewSupplierStock.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE (supplier_code='" + FrameViewSupplierStock.jTextFieldSupplierCode.getText() + "'"
                    + "OR supplier_name='" + FrameViewSupplierStock.jTextFieldSupplierName.getText() + "' OR company_name='" + FrameViewSupplierStock.jTextFieldCompanyName.getText() + "') AND status='"+"ACTIVE"+"'  ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idsupplier"));
                v.add(rs.getString("supplier_code"));
                v.add(rs.getString("name_initials"));
                v.add(rs.getString("company_name"));
                v.add(rs.getString("branch"));
                v.add(rs.getString("phone"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

        //--------------------------------------- search all --------------------------------------------------
    public void searchAll() {
        String country_name = null;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewSupplierStock.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE status='"+"ACTIVE"+"' ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idsupplier"));
                v.add(rs.getString("supplier_code"));
                v.add(rs.getString("name_initials"));
                v.add(rs.getString("company_name"));
                v.add(rs.getString("branch"));
                v.add(rs.getString("phone"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
