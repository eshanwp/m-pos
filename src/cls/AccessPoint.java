/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author esh
 */
public class AccessPoint {

    private String privilegeStatus = null;

//    ------------------------------- Insert ----------------------------------------------------
    public String acceesPoint_01(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "001" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

    //    ------------------------------- Update ----------------------------------------------------
    public String acceesPoint_02(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "002" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;

    }

    //    ------------------------------- Delete ----------------------------------------------------
    public String acceesPoint_03(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "003" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

    //    ------------------------------- Search ----------------------------------------------------
    public String acceesPoint_04(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "004" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

    //    ------------------------------- Location Master ----------------------------------------------------
    public String acceesPoint_05(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "005" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

    //    ------------------------------- Employee Master ----------------------------------------------------
    public String acceesPoint_06(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "006" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

    //    ------------------------------- Unlock Login ----------------------------------------------------
    public String acceesPoint_07(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "007" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

    //    ------------------------------- User Setting ----------------------------------------------------
    public String acceesPoint_08(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "008" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Changing Password ----------------------------------------------------
    public String acceesPoint_09(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "009" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Category ----------------------------------------------------
    public String acceesPoint_10(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "010" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Sub Category ----------------------------------------------------
    public String acceesPoint_11(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "011" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Product Entry ----------------------------------------------------
    public String acceesPoint_12(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "012" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Stock In ----------------------------------------------------
    public String acceesPoint_13(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "013" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Supplier ----------------------------------------------------
    public String acceesPoint_14(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "014" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Bill ----------------------------------------------------
    public String acceesPoint_15(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "015" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Bill  Re-Print----------------------------------------------------
    public String acceesPoint_16(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "016" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Search Employee----------------------------------------------------
    public String acceesPoint_17(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "017" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Sales Summery---------------------------------------------------
    public String acceesPoint_18(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "018" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Day End---------------------------------------------------
    public String acceesPoint_19(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "019" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Product Summary---------------------------------------------------
    public String acceesPoint_20(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "020" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Inventory Summary ---------------------------------------------------
    public String acceesPoint_21(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "021" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    //    ------------------------------- Create Customer ---------------------------------------------------
    public String acceesPoint_22(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "022" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }
    
    public String acceesPoint_23(String USER_NAME) {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM accesspoint WHERE username='" + USER_NAME + "' AND idaccessPoint='" + "023" + "'  ");

            while (rs.next()) {

                privilegeStatus = rs.getString("privilegeStatus");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return privilegeStatus;
    }

}
