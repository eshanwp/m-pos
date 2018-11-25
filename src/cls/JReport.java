/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cls;

import frame.FrameBill;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author esh
 */
public class JReport {
    
    String IDINVOICE, USER_NAME=null;
    public void report(String IDINVOICE, String USER_NAME ){
    
        IDINVOICE=IDINVOICE;
        USER_NAME=USER_NAME;
        
        try {
            
            InputStream is = new FileInputStream(new File("C:/i3Codes/Inventory/bill.jrxml"));
                    JasperReport jr = JasperCompileManager.compileReport(is);

                    Map m = new HashMap();
                    m.put("InvoiceId", IDINVOICE);
                    m.put("userName", USER_NAME);
//
                    JasperPrint jp = JasperFillManager.fillReport(jr, m, db.myConnection());
////                    JasperViewer jv = new JasperViewer(jp, false);
////                    jv.setTitle("Bill");
////                    jv.setVisible(false);
                    JasperPrintManager.printReport(jp, true);
////                    jv.setVisible(false);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    public void print(String USER_NAME){
        
        String IDINVOICE = null;
        USER_NAME=USER_NAME;
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT idinvoice_id FROM invoice_header");

            while (rs.next()) {
                IDINVOICE = rs.getString("idinvoice_id");
            }

            InputStream is = new FileInputStream(new File("C:/i3Codes/CellNet/bill.jrxml"));
            JasperReport jr = JasperCompileManager.compileReport(is);

            Map m = new HashMap();
            System.out.println("ID INVOCE=" + IDINVOICE);
            m.put("InvoiceId", IDINVOICE);
            m.put("userName", USER_NAME);
            JasperPrint jp = JasperFillManager.fillReport(jr, m, db.myConnection());
//                    JasperViewer jv = new JasperViewer(jp, false);
//                    jv.setTitle("Bill");
//                    jv.setVisible(false);
            JasperPrintManager.printReport(jp, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
