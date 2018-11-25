/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameEmployeeMaster;
import static frame.FrameEmployeeMaster.CivilStatus;
import static frame.FrameEmployeeMaster.Designation;
import static frame.FrameEmployeeMaster.LocationID;
import static frame.FrameEmployeeMaster.Title;
import static frame.FrameEmployeeMaster.jComboBoxCivilStatus;
import static frame.FrameEmployeeMaster.jComboBoxDesignation;
import static frame.FrameEmployeeMaster.jComboBoxTitle;
import static frame.FrameEmployeeMaster.jTextAreaIntials;
import static frame.FrameEmployeeMaster.jTextFieldAdd1;
import static frame.FrameEmployeeMaster.jTextFieldAdd2;
import static frame.FrameEmployeeMaster.jTextFieldAdd3;
import static frame.FrameEmployeeMaster.jTextFieldConfirmNIC;
import static frame.FrameEmployeeMaster.jTextFieldDOB;
import static frame.FrameEmployeeMaster.jTextFieldFullName;
import static frame.FrameEmployeeMaster.jTextFieldGender;
import static frame.FrameEmployeeMaster.jTextFieldMobile1;
import static frame.FrameEmployeeMaster.jTextFieldMobile2;
import static frame.FrameEmployeeMaster.jTextFieldNICno;
import static frame.FrameEmployeeMaster.jTextFieldTelephone;
import frame.FrameSearchEmployee;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Employee {
    
    int j=0;

    public void insert() {

        try {

            j =cls.db.myConnection().createStatement().executeUpdate("INSERT INTO employeemaster (location_idlocation, designation, nic, "
                    + "gender, title, fullName, intials, dob, civilStatus, mobileNo1,mobileNo2, address1, address2, address3, telephone, status) "
                    + "VALUES('" + FrameEmployeeMaster.LocationID + "' , '" + FrameEmployeeMaster.Designation + "' ,'" + FrameEmployeeMaster.jTextFieldConfirmNIC.getText() + "', "
                    + "'" + FrameEmployeeMaster.jTextFieldGender.getText() + "', '" + FrameEmployeeMaster.Title + "','" + FrameEmployeeMaster.jTextFieldFullName.getText() + "', '" + FrameEmployeeMaster.jTextAreaIntials.getText() + "', "
                    + "'" + FrameEmployeeMaster.jTextFieldDOB.getText() + "','" + FrameEmployeeMaster.CivilStatus + "', '" + FrameEmployeeMaster.jTextFieldMobile1.getText() + "', '" + FrameEmployeeMaster.jTextFieldMobile2.getText() + "', '" + FrameEmployeeMaster.jTextFieldAdd1.getText() + "', "
                    + "'" + FrameEmployeeMaster.jTextFieldAdd2.getText() + "', '" + FrameEmployeeMaster.jTextFieldAdd3.getText() + "', '" + FrameEmployeeMaster.jTextFieldTelephone.getText() + "', '" + "ACTIVE" + "') ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        if (j==1) {
                JOptionPane.showMessageDialog(null, "Successfully Saved");
            } else {
                JOptionPane.showMessageDialog(null, "Data insertion unsuccessfull");
            }

    }

    String idEmployee = null;

    public String search() {

        String NIC = null;
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM employeemaster WHERE nic='" + jTextFieldConfirmNIC.getText() + "' ");

            while (rs.next()) {
                NIC = rs.getString("nic");
                String title = rs.getString("title");
                String civilStatus = rs.getString("civilStatus");
                String designation = rs.getString("designation");

                jTextFieldNICno.setVisible(true);

                jComboBoxTitle.setSelectedItem(title);
                jComboBoxCivilStatus.setSelectedItem(civilStatus);
                jComboBoxDesignation.setSelectedItem(designation);

                idEmployee = rs.getString("idemployeeMaster");
                jTextFieldNICno.setText(rs.getString("nic"));
                jTextFieldGender.setText(rs.getString("gender"));
                jTextFieldFullName.setText(rs.getString("fullName"));
                jTextAreaIntials.setText(rs.getString("intials"));
                jTextFieldDOB.setText(rs.getString("dob"));
                jTextFieldMobile1.setText(rs.getString("mobileNo1"));
                jTextFieldMobile2.setText(rs.getString("mobileNo2"));
                jTextFieldAdd1.setText(rs.getString("address1"));
                jTextFieldAdd2.setText(rs.getString("address2"));
                jTextFieldAdd3.setText(rs.getString("address3"));
                jTextFieldTelephone.setText(rs.getString("telephone"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return NIC;
    }

    public void update() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM employeemaster WHERE nic='" + jTextFieldConfirmNIC.getText() + "' ");
            while (rs.next()) {
                idEmployee = rs.getString("idemployeeMaster");
            }

            j =cls.db.myConnection().createStatement().executeUpdate("UPDATE employeemaster SET location_idlocation='" + LocationID + "' , designation='" + Designation + "' ,nic='" + jTextFieldConfirmNIC.getText() + "',"
                    + "gender='" + jTextFieldGender.getText() + "', title='" + Title + "',fullName='" + jTextFieldFullName.getText() + "',"
                    + "intials= '" + jTextAreaIntials.getText() + "',dob= '" + jTextFieldDOB.getText() + "',civilStatus='" + CivilStatus + "',"
                    + "mobileNo1= '" + jTextFieldMobile1.getText() + "',mobileNo2= '" + jTextFieldMobile2.getText() + "', address1='" + jTextFieldAdd1.getText() + "',"
                    + "address2= '" + jTextFieldAdd2.getText() + "', address3='" + jTextFieldAdd3.getText() + "',telephone= '" + jTextFieldTelephone.getText() + "', "
                    + "status='" + FrameEmployeeMaster.Status + "'  WHERE idemployeeMaster='" + idEmployee + "' ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        
        if (j==1) {
                JOptionPane.showMessageDialog(null, "Successfully update");
            } else {
                JOptionPane.showMessageDialog(null, "Data updation unsuccessfull");
            }
    }

    public void searchEmp() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameSearchEmployee.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM employeemaster WHERE nic='" + FrameSearchEmployee.jTextFieldNICno.getText() + "' ");
            while (rs.next()) {
                Vector v = new Vector();
                
                String locationID=rs.getString("location_idlocation");

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM location WHERE idlocation='" +locationID+ "' ");
                while (rs1.next()) {
                    v.add(rs1.getString("locationName"));
                }

                v.add(rs.getString("designation"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("gender"));
                v.add(rs.getString("title"));
                v.add(rs.getString("fullName"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("civilStatus"));
                v.add(rs.getString("mobileNo1"));
                v.add(rs.getString("mobileNo2"));
                v.add(rs.getString("address1"));
                v.add(rs.getString("address2"));
                v.add(rs.getString("address3"));
                v.add(rs.getString("telephone"));
                v.add(rs.getString("status"));
                
                dtm.addRow(v);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void searchAll() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameSearchEmployee.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM employeemaster ");
            while (rs.next()) {
                Vector v = new Vector();
                
                String locationID=rs.getString("location_idlocation");

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM location WHERE idlocation='" +locationID+ "' ");
                while (rs1.next()) {
                    v.add(rs1.getString("locationName"));
                }

                v.add(rs.getString("designation"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("gender"));
                v.add(rs.getString("title"));
                v.add(rs.getString("fullName"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("civilStatus"));
                v.add(rs.getString("mobileNo1"));
                v.add(rs.getString("mobileNo2"));
                v.add(rs.getString("address1"));
                v.add(rs.getString("address2"));
                v.add(rs.getString("address3"));
                v.add(rs.getString("telephone"));
                v.add(rs.getString("status"));
                
                dtm.addRow(v);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
