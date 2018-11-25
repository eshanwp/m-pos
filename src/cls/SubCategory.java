/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameSubCategory;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class SubCategory {

    String idSubCategory = null;
    String SubCategoryCode = null;
    String SubCategoryName = null;
    String idCategory = null;
    String CategoryName = null;
    String status = null;
    
    int j=0;

    //---------------------------------------------------- search ----------------------------------------------------
    public String[] search() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category "
                    + "WHERE sub_category_code='" + FrameSubCategory.jTextFieldSubCategoryCode.getText() + "' OR sub_category_name='" + FrameSubCategory.jTextFieldSubCategoryName.getText() + "' ");

            while (rs.next()) {
                idSubCategory = rs.getString("idsub_category");
                SubCategoryCode = rs.getString("sub_category_code");
                SubCategoryName = rs.getString("sub_category_name");
                idCategory = rs.getString("idcategory");

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return new String[]{idSubCategory, SubCategoryCode, SubCategoryName, idCategory};
    }
    
    //----------------------------------------------------  Search All ----------------------------------------------------
    public void searchAll() {

        
        DefaultTableModel dtm =new DefaultTableModel();
        dtm=(DefaultTableModel) FrameSubCategory.jTable1.getModel();
        dtm.setRowCount(0);
        
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category ");

            
            while (rs.next()) {
                Vector v=new Vector();
                SubCategoryCode = rs.getString("sub_category_code");
                SubCategoryName = rs.getString("sub_category_name");
                idCategory = rs.getString("idcategory");
                status = rs.getString("status");
                
                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE idcategory='" + idCategory + "'  ");

                while (rs1.next()) {
                    CategoryName = rs1.getString("category_name");
                }
                
                v.add(SubCategoryCode);
                v.add(SubCategoryName);
                v.add(CategoryName);
                v.add(status);
                v.add("DELETE");
                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    

    //---------------------------------------------------- set data jComboBoxCategory ----------------------------------------------------
    public void loadCategory() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE status='"+"ACTIVE"+"' ");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("category_name"));

            }
            FrameSubCategory.jComboBoxCategoryName.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //---------------------------------------------------- save ----------------------------------------------------
    public void save() {

        try {

            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) FrameSubCategory.jTable1.getModel();

            for (int i = 0; i < FrameSubCategory.jTable1.getRowCount(); i++) {

                SubCategoryCode = dtm.getValueAt(i, 0).toString();
                SubCategoryName = dtm.getValueAt(i, 1).toString();
                CategoryName = dtm.getValueAt(i, 2).toString();
                status = dtm.getValueAt(i, 3).toString();

                ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE category_name='" + CategoryName + "'  ");

                while (rs.next()) {
                    idCategory = rs.getString("idcategory");
                }

               j = cls.db.myConnection().createStatement().executeUpdate("INSERT INTO sub_category (sub_category_code, sub_category_name, idcategory,status) "
                        + "VALUES('" + SubCategoryCode + "' , '" + SubCategoryName + "' , '" + idCategory + "' , '" + status + "')");

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
    
    //--------------------------------------------------Update --------------------------------------------------
    
    public void update(String id_SubCategory, String id_Category) {

        String statusDb=null;
        
        if (FrameSubCategory.jCheckBox.isSelected()) {
            statusDb="INACTIVE";
        } else {
            statusDb="ACTIVE";
        }
        System.out.println(statusDb);
        idCategory = id_Category;
        idSubCategory=id_SubCategory;
        try {

            j = cls.db.myConnection().createStatement().executeUpdate("UPDATE sub_category SET sub_category_code='" + FrameSubCategory.jTextFieldSubCategoryCode.getText() + "' , "
                    + "sub_category_name='" + FrameSubCategory.jTextFieldSubCategoryName.getText()+ "' , idcategory='" +idCategory+ "' , status='" +statusDb+ "'  WHERE idsub_category='" + idSubCategory + "' ");
            
            if (j == 1) {
                JOptionPane.showMessageDialog(null, "Successfully update");
            } else {
                JOptionPane.showMessageDialog(null, "Data updation unsuccessfull");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
