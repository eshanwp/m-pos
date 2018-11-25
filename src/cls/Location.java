/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameLocationMaster;
import static frame.FrameLocationMaster.jTable1;
import static frame.FrameLocationMaster.jTextFieldBranchCode;
import static frame.FrameLocationMaster.jTextFieldBranchName;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Location {
    
    int j=0;

    DefaultTableModel dtm = new DefaultTableModel();

    public void refresh() {

        DefaultTableModel dtm = (DefaultTableModel) FrameLocationMaster.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM location ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idlocation"));
                v.add(rs.getString("locationName"));
                v.add(rs.getString("locationRef"));
                v.add(rs.getString("status"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void search() {

        try {
            
            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM location WHERE locationName='" + FrameLocationMaster.jTextFieldBranchName.getText() + "' OR locationRef='" + FrameLocationMaster.jTextFieldBranchCode.getText() + "' ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idlocation"));
                v.add(rs.getString("locationName"));
                v.add(rs.getString("locationRef"));
                v.add(rs.getString("status"));

                dtm.addRow(v);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    public void insert() {

        try {

            dtm = (DefaultTableModel) FrameLocationMaster.jTable1.getModel();

            for (int i = 0; i < FrameLocationMaster.jTable1.getRowCount(); i++) {
                String col1 = dtm.getValueAt(i, 0).toString();
                String col2 = dtm.getValueAt(i, 1).toString();
                String col3 = dtm.getValueAt(i, 2).toString();
                String col4 = dtm.getValueAt(i, 3).toString();

                j =cls.db.myConnection().createStatement().executeUpdate("INSERT INTO location VALUES('" + col1 + "', '" + col4 + "', '" + col2 + "', '" + col3 + "')");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (j==1) {
                JOptionPane.showMessageDialog(null, "Successfully Saved");
            } else {
                JOptionPane.showMessageDialog(null, "Data insertion unsuccessfull");
            }
        refresh();

    }

    public void update() {

        String Status = null;
        if (FrameLocationMaster.jCheckBoxInactive.isSelected()) {
            Status = "INACTIVE";
        } else {
            Status = "ACTIVE";
        }
        
        try {

            j =cls.db.myConnection().createStatement().executeUpdate("UPDATE location SET locationName='" + FrameLocationMaster.jTextFieldBranchName.getText() + "', locationRef='" + FrameLocationMaster.jTextFieldBranchCode.getText() + "', status='" + Status + "' WHERE idlocation='" +FrameLocationMaster.idLocation+ "' ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        if (j==1) {
                JOptionPane.showMessageDialog(null, "Successfully Update");
            } else {
                JOptionPane.showMessageDialog(null, "Data updation unsuccessfull");
            }
        
        refresh();

    }

}
