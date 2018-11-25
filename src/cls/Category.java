/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameCategory;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Category {

    private String categoryName = null;
    private String idCategory = null;
    private String categoryCode = null;
    private String status = null;
    int j = 0;

    public void save() {

        try {

            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) FrameCategory.jTable1.getModel();

            for (int i = 0; i < FrameCategory.jTable1.getRowCount(); i++) {

                categoryCode = dtm.getValueAt(i, 0).toString();
                categoryName = dtm.getValueAt(i, 1).toString();
                status = dtm.getValueAt(i, 2).toString();

                j = cls.db.myConnection().createStatement().executeUpdate("INSERT INTO category (category_code, category_name,status) "
                        + "VALUES('" + categoryCode + "' , '" + categoryName + "' ,'" + status + "')");

            }

            if (j == 1) {
                JOptionPane.showMessageDialog(null, "Successfully Saved");
            } else {
                JOptionPane.showMessageDialog(null, "Data insertion unsuccessfull");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String[] search() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category "
                    + "WHERE category_code='" + FrameCategory.jTextFieldCategoryCode.getText() + "' OR category_name='" + FrameCategory.jTextFieldCategoryName.getText() + "' ");

            while (rs.next()) {
                idCategory = rs.getString("idcategory");
                categoryCode = rs.getString("category_code");
                categoryName = rs.getString("category_name");

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return new String[]{idCategory, categoryCode, categoryName};
    }

    public void searchAll() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameCategory.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category ");

            while (rs.next()) {
                Vector v = new Vector();
                categoryCode = rs.getString("category_code");
                categoryName = rs.getString("category_name");
                status = rs.getString("status");

                v.add(categoryCode);
                v.add(categoryName);
                v.add(status);
                v.add("DELETE");
                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void update(String id_Category) {

        String statusDb = null;

        if (FrameCategory.jCheckBox.isSelected()) {
            statusDb = "INACTIVE";
        } else {
            statusDb = "ACTIVE";
        }

        idCategory = id_Category;
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category WHERE idcategory='" + idCategory + "' ");
            while (rs.next()) {
                status = rs.getString("status");
                System.out.println(status);
            }
            System.out.println(status);
            if (status == null || status.equals("INACTIVE")) {

                j = cls.db.myConnection().createStatement().executeUpdate("UPDATE category SET category_code='" + FrameCategory.jTextFieldCategoryCode.getText() + "' , "
                        + "category_name='" + FrameCategory.jTextFieldCategoryName.getText() + "' , status='" + statusDb + "' WHERE idcategory='" + idCategory + "' ");

                if (j == 1) {
                    JOptionPane.showMessageDialog(null, "Successfully update");
                } else {
                    JOptionPane.showMessageDialog(null, "Data updation unsuccessfull");
                }

            } else if (status.equals("ACTIVE")) {
                JOptionPane.showMessageDialog(null, "Unable to update. Already in use in sub category");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
