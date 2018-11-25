/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameSupplier;
import frame.FrameViewSupplier;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Supplier {

    String idCountry = null;
    String nameInitial = null;
    String idSupplier = null;

    int j =0;
    //---------------------------------------- country data ------------------------------------------
    public void loadCountry() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM country");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("country_name"));
            }

            FrameSupplier.jComboBoxCountry.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    //------------------------------------------------- save ---------------------------------------------------
    public void save(String id_country, String name_initials) {

        idCountry = id_country;
        nameInitial = name_initials;

        try {

            j =cls.db.myConnection().createStatement().executeUpdate("INSERT INTO supplier "
                    + "(supplier_code, supplier_name, last_name, name_initials, idcountry, add1, add2, add3, phone, mobile1, mobile2, fax, email, company_name, branch,status)"
                    + "VALUES('" + FrameSupplier.jTextFieldSupplierCode.getText() + "' , '" + FrameSupplier.jTextFieldSupplierName.getText() + "',"
                    + "'" + FrameSupplier.jTextFieldLastName.getText() + "', '" + nameInitial + "' , "
                    + "'" + idCountry + "' , '" + FrameSupplier.jTextFieldAdd1.getText() + "' , '" + FrameSupplier.jTextFieldAdd2.getText() + "' ,"
                    + " '" + FrameSupplier.jTextFieldAdd3.getText() + "' , '" + FrameSupplier.jTextFieldPhone.getText() + "' , '" + FrameSupplier.jTextFieldMobile1.getText() + "' , "
                    + "'" + FrameSupplier.jTextFieldMobile2.getText() + "' , '" + FrameSupplier.jTextFieldFax.getText() + "' , '" + FrameSupplier.jTextFieldEmail.getText() + "' , "
                    + "'" + FrameSupplier.jTextFieldCompanyName.getText() + "' , '" + FrameSupplier.jTextFieldBranch.getText() + "' , '" +"ACTIVE"+ "') ");

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

    //--------------------------------------- search --------------------------------------------------
    public void search() {
        String country_name = null;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewSupplier.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE supplier_code='" + FrameViewSupplier.jTextFieldSupplierCode.getText() + "'"
                    + "OR supplier_name='" + FrameViewSupplier.jTextFieldSupplierName.getText() + "' OR company_name='" + FrameViewSupplier.jTextFieldCompanyName.getText() + "'  ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idsupplier"));
                v.add(rs.getString("supplier_code"));
                v.add(rs.getString("supplier_name"));
                v.add(rs.getString("name_initials"));
                v.add(rs.getString("last_name"));
                v.add(rs.getString("company_name"));
                v.add(rs.getString("branch"));

                idCountry = rs.getString("idcountry");

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM country WHERE idcountry='" + idCountry + "' ");
                while (rs1.next()) {
                    country_name = rs1.getString("country_name");
                }
                v.add(country_name);
                v.add(rs.getString("add1"));
                v.add(rs.getString("add2"));
                v.add(rs.getString("add3"));
                v.add(rs.getString("phone"));
                v.add(rs.getString("mobile1"));
                v.add(rs.getString("mobile2"));
                v.add(rs.getString("fax"));
                v.add(rs.getString("email"));
                v.add(rs.getString("status"));

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
        dtm = (DefaultTableModel) FrameViewSupplier.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idsupplier"));
                v.add(rs.getString("supplier_code"));
                v.add(rs.getString("supplier_name"));
                v.add(rs.getString("name_initials"));
                v.add(rs.getString("last_name"));
                v.add(rs.getString("company_name"));
                v.add(rs.getString("branch"));

                idCountry = rs.getString("idcountry");

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM country WHERE idcountry='" + idCountry + "' ");
                while (rs1.next()) {
                    country_name = rs1.getString("country_name");
                }
                v.add(country_name);
                v.add(rs.getString("add1"));
                v.add(rs.getString("add2"));
                v.add(rs.getString("add3"));
                v.add(rs.getString("phone"));
                v.add(rs.getString("mobile1"));
                v.add(rs.getString("mobile2"));
                v.add(rs.getString("fax"));
                v.add(rs.getString("email"));
                v.add(rs.getString("status"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //--------------------------------------- search jTextField Supplier Code --------------------------------------------------
    public String searchTextField() {
        String country_name = null;

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE supplier_code='" + FrameSupplier.jTextFieldSupplierCode.getText() + "'  ");

            while (rs.next()) {

                idSupplier = rs.getString("idsupplier");
                FrameSupplier.jTextFieldSupplierName.setText(rs.getString("supplier_name"));
                FrameSupplier.jTextFieldLastName.setText(rs.getString("last_name"));

                idCountry = rs.getString("idcountry");

                FrameSupplier.jTextFieldAdd1.setText(rs.getString("add1"));
                FrameSupplier.jTextFieldAdd2.setText(rs.getString("add2"));
                FrameSupplier.jTextFieldAdd3.setText(rs.getString("add3"));
                FrameSupplier.jTextFieldPhone.setText(rs.getString("phone"));
                FrameSupplier.jTextFieldMobile1.setText(rs.getString("mobile1"));
                FrameSupplier.jTextFieldMobile2.setText(rs.getString("mobile2"));
                FrameSupplier.jTextFieldFax.setText(rs.getString("fax"));
                FrameSupplier.jTextFieldEmail.setText(rs.getString("email"));
                FrameSupplier.jTextFieldCompanyName.setText(rs.getString("company_name"));
                FrameSupplier.jTextFieldBranch.setText(rs.getString("branch"));
                
                String status = rs.getString("status");
                
                if (status.equals("ACTIVE")) {
                    FrameSupplier.jCheckBox.setSelected(true);
                } else {
                    FrameSupplier.jCheckBox.setSelected(false);
                }

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM country WHERE idcountry='" + idCountry + "' ");
                while (rs1.next()) {
                    country_name = rs1.getString("country_name");
                    FrameSupplier.jComboBoxCountry.setSelectedItem(country_name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return idSupplier;

    }

    //-------------------------------------------- update ------------------------------------------
    public void update(String id_country, String name_initials, String id_Supplier) {

        idCountry = id_country;
        nameInitial = name_initials;
        idSupplier = id_Supplier;

        String statusDb=null;
        
        if (FrameSupplier.jCheckBox.isSelected()) {
            statusDb="INACTIVE";
        } else {
            statusDb="ACTIVE";
        }
        
        try {

            j =cls.db.myConnection().createStatement().executeUpdate("UPDATE supplier SET supplier_code='" + FrameSupplier.jTextFieldSupplierCode.getText() + "' , supplier_name='" + FrameSupplier.jTextFieldSupplierName.getText() + "' ,"
                    + "last_name='" + FrameSupplier.jTextFieldLastName.getText() + "' , name_initials='" + nameInitial + "' , idcountry='" + idCountry + "' ,"
                    + "add1='" + FrameSupplier.jTextFieldAdd1.getText() + "' , add2='" + FrameSupplier.jTextFieldAdd2.getText() + "' , add3='" + FrameSupplier.jTextFieldAdd3.getText() + "' ,"
                    + "phone='" + FrameSupplier.jTextFieldPhone.getText() + "' , mobile1='" + FrameSupplier.jTextFieldMobile1.getText() + "' , mobile2='" + FrameSupplier.jTextFieldMobile2.getText() + "' , "
                    + "fax='" + FrameSupplier.jTextFieldFax.getText() + "' , email='" + FrameSupplier.jTextFieldEmail.getText() + "' ,company_name='" + FrameSupplier.jTextFieldCompanyName.getText() + "' , "
                    + "branch='" + FrameSupplier.jTextFieldBranch.getText() + "' , status='" +statusDb+ "' WHERE idsupplier='" + idSupplier + "' ");

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
