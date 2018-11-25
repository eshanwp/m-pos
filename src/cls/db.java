/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cls;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author esh
 */
public class db {
    
    public static Connection c;

    public static Connection myConnection() throws Exception {

        try {

            if (c == (null)) {

                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection("jdbc:mysql://localhost:3307/cellnet", "root", "1234");
                
                //pasword=esh@0326
                //check expired and day end
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return c;
    }
    
}
