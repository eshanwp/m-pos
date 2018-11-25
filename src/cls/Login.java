/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import frame.FrameLogin;
import frame.FrameMain;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author esh
 */
public class Login {

    String PW = null;
    String DB_PW = null;
    String USER_NAME = null;
    String PRI_ID = null;
    String LOCK_STATUS = null;
    String LOCATION_ID = null;
    String CURRENT_DATE = null;
    String dayEndStatus = null;
    String status = null;
    Boolean LOCK;
    static int i=0;

    public void login() {

        try {
            System.out.println(FrameLogin.jTextFieldUserName.getText());

            PW = new String(FrameLogin.jPasswordFieldPassword.getPassword());

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM user WHERE userName='" + FrameLogin.jTextFieldUserName.getText() + " ' ");

            if (rs.next()) {
                USER_NAME = rs.getString("userName");
                DB_PW = rs.getString("password2");
                LOCATION_ID = rs.getString("location");
                LOCK_STATUS = rs.getString("lockStatus");
                status = rs.getString("status");

            }

            if (FrameLogin.jTextFieldUserName.getText().equals(USER_NAME) && PW.equals(DB_PW) && status.equals("ACTIVE")) {

                if (LOCK_STATUS.equals("UNLOCK")) {

                    FrameMain mf = new FrameMain(USER_NAME, LOCATION_ID);
                    mf.setVisible(true);
                    
                    FrameLogin l = new FrameLogin();
                    l.setVisible(false);


                } else if (LOCK_STATUS.equals("LOCK")) {
                    JOptionPane.showMessageDialog(null, "User account currently lock.\nPlease contact the administrater");
                    
                }

            } else {
                
                JOptionPane.showMessageDialog(null, "User name or Password is incorrect");

                if (FrameLogin.jTextFieldUserName.getText().equals(USER_NAME)) {
                    
                    ++i;
                    System.out.println(i);
                    if (i >= 3) {
                        System.out.println("hi");
                        LOCK = false;
                        String UN = FrameLogin.jTextFieldUserName.getText();

                        if (LOCK == false) {
                            cls.db.myConnection().createStatement().executeUpdate("UPDATE user SET lockStatus='" + "LOCK" + "' WHERE userName='" + FrameLogin.jTextFieldUserName.getText() + "' ");

                            JOptionPane.showMessageDialog(null, "User account currently lock");

                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please contact the administrater");
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

}
