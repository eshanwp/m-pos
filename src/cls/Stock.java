/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameStockEntry;
import frame.FrameStockUpdate;
import frame.FrameViewStock;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Stock {

    int j=0;
    String idSupplier = null;
    String supplier_code = null;
    String supplier_name = null;
    String discription = null;
    String date = null;
    String idStock = null;
    String idProduct = null;
    String productCode = null;
    String productName = null;
    String sellingPrice = null;
    String totAmount = null;
    String avialableQty = null;
    String cost = null;

    String REF = null;

    Double totSellingQty = 0.0;
    Double totOPstock = 0.0;
    Double qty = 0.0;

    String sellingPriceDb = null;
    String totAmountDb = null;
    String avialableQtyDb = null;
    String totOPstockDb = null;
    String qtyDb = null;
    String totSellingQtyDb = null;
    String tot_QtyDb = null;

    Double selling_Price = 0.0;
    Double tot_Amount = 0.0;
    Double avialable_Qty = 0.0;
    Double tot_OPstock = 0.0;
    Double Qty = 0.0;
    Double tot_SellingQty = 0.0;
    Double tot_Qty = 0.0;

    //------------------------------------------------ search ----------------------------------------------------
    public void search() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewStock.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE (p_name='" + FrameViewStock.jTextFieldProductName.getText() + "' "
                    + "OR p_code='" + FrameViewStock.jTextFieldProductCode.getText() + "') AND status='" + "ACTIVE" + "' ");
            while (rs.next()) {
                Vector v = new Vector();
                idProduct = rs.getString("idproduct");
                productCode = rs.getString("p_code");
                productName = rs.getString("p_name");
                sellingPrice = rs.getString("selling_price");

                ResultSet rs7 = cls.db.myConnection().createStatement().executeQuery("SELECT DISTINCT idstock FROM stock_entry WHERE idproduct='" + idProduct + "'  ");

                while (rs7.next()) {
                    idStock = rs7.getString("idstock");
                }

                ResultSet rs8 = cls.db.myConnection().createStatement().executeQuery("SELECT DISTINCT selling_price FROM product WHERE idproduct='" + idProduct + "'  ");

                while (rs8.next()) {
                    sellingPriceDb = rs8.getString("selling_price");
                }

                ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(opStock) FROM stock_entry WHERE idproduct='" + idProduct + "'  ");
                while (rs3.next()) {

                    totOPstock = rs3.getDouble("SUM(opStock)");
                }

                ResultSet rs4 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(qty) FROM stock_entry WHERE idproduct='" + idProduct + "'  ");
                while (rs4.next()) {

                    qty = rs4.getDouble("SUM(qty)");
                }

                ResultSet rs5 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(qty) FROM invoice WHERE id_product='" + idProduct + "' ");
                while (rs5.next()) {

                    totSellingQty = rs5.getDouble("SUM(qty)");

                }

                tot_Qty = totOPstock + qty;

                avialable_Qty = tot_Qty - totSellingQty;
                DecimalFormat df1 = new DecimalFormat("0.00");
                avialableQtyDb = df1.format(avialable_Qty);

                DecimalFormat df = new DecimalFormat("0.00");
                tot_QtyDb = df.format(tot_Qty);

                v.add(idStock);
                v.add(productCode);
                v.add(productName);
                v.add(sellingPriceDb);
                v.add(tot_QtyDb);
                v.add(avialableQtyDb);

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //------------------------------------------------ search All ----------------------------------------------------
    public void searchAll() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewStock.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE status='" + "ACTIVE" + "' ");
            while (rs.next()) {
                Vector v = new Vector();
                idProduct = rs.getString("idproduct");
                productCode = rs.getString("p_code");
                productName = rs.getString("p_name");
                sellingPrice = rs.getString("selling_price");

                ResultSet rs7 = cls.db.myConnection().createStatement().executeQuery("SELECT DISTINCT idstock FROM stock_entry WHERE idproduct='" + idProduct + "'  ");

                while (rs7.next()) {
                    idStock = rs7.getString("idstock");
                }

                ResultSet rs8 = cls.db.myConnection().createStatement().executeQuery("SELECT DISTINCT selling_price FROM product WHERE idproduct='" + idProduct + "'  ");

                while (rs8.next()) {
                    sellingPriceDb = rs8.getString("selling_price");
                }

                ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(opStock) FROM stock_entry WHERE idproduct='" + idProduct + "'  ");
                while (rs3.next()) {

                    totOPstock = rs3.getDouble("SUM(opStock)");
                }

                ResultSet rs4 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(qty) FROM stock_entry WHERE idproduct='" + idProduct + "'  ");
                while (rs4.next()) {

                    qty = rs4.getDouble("SUM(qty)");
                }

                ResultSet rs5 = cls.db.myConnection().createStatement().executeQuery("SELECT SUM(qty) FROM invoice WHERE id_product='" + idProduct + "' ");
                while (rs5.next()) {

                    totSellingQty = rs5.getDouble("SUM(qty)");

                }

                tot_Qty = totOPstock + qty;

                avialable_Qty = tot_Qty - totSellingQty;
                DecimalFormat df1 = new DecimalFormat("0.00");
                avialableQtyDb = df1.format(avialable_Qty);

                DecimalFormat df = new DecimalFormat("0.00");
                tot_QtyDb = df.format(tot_Qty);

                v.add(idStock);
                v.add(productCode);
                v.add(productName);
                v.add(sellingPriceDb);
                v.add(tot_QtyDb);
                v.add(avialableQtyDb);

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //------------------------------------ Save ---------------------------------------------
    public void save() {

        try {

            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) FrameStockEntry.jTable1.getModel();

            DefaultTableModel dtm1 = new DefaultTableModel();
            dtm1 = (DefaultTableModel) FrameStockEntry.jTableimei.getModel();
            
            for (int i = 0; i < FrameStockEntry.jTable1.getRowCount(); i++) {

                idStock = FrameStockEntry.jTable1.getValueAt(i, 0).toString();
                idSupplier = FrameStockEntry.jTable1.getValueAt(i, 1).toString();
                date = FrameStockEntry.jTable1.getValueAt(i, 2).toString();
                idProduct = FrameStockEntry.jTable1.getValueAt(i, 3).toString();
                String qty_Db = FrameStockEntry.jTable1.getValueAt(i, 4).toString();
                discription = FrameStockEntry.jTable1.getValueAt(i, 5).toString();
                cost = FrameStockEntry.jTable1.getValueAt(i, 7).toString();

                ref();

                ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE idproduct='" + idProduct + "' ");
                while (rs.next()) {
                    productCode = rs.getString("p_code");
                    productName = rs.getString("p_name");
                }

                j =cls.db.myConnection().createStatement().executeUpdate("INSERT INTO stock_entry (idstock, idsupplier, date, idproduct, qty, description,ref,cost)"
                        + "VALUES('" + idStock + "' , '" + idSupplier + "' , '" + date + "' , '" + idProduct + "' , '" + qty_Db + "' , '" + discription + "' , '" + REF + "', '"+cost+"')");

                if (j == 1) {
                    JOptionPane.showMessageDialog(null, "Successfully Saved\nProduct Code=" + productCode + "\nProduct Name=" + productName + "\nReference no=" + REF + " ");

                } else {
                    JOptionPane.showMessageDialog(null, "Data insertion unsuccessfull");
                }
                
                
            }
            
            for (int i = 0; i < FrameStockEntry.jTableimei.getRowCount(); i++) {
                
                String idProduct=FrameStockEntry.jTableimei.getValueAt(i, 0).toString();
                String imei1=FrameStockEntry.jTableimei.getValueAt(i, 1).toString();
                String imei2=FrameStockEntry.jTableimei.getValueAt(i, 2).toString();
                
                cls.db.myConnection().createStatement().executeUpdate("INSERT INTO imei (idproduct, imei1, imei2, status)"
                        + "VALUES('" + idProduct + "' , '" + imei1 + "' , '" + imei2 + "','"+"NOTSELL"+"')");
            }

            dtm.setRowCount(0);
            dtm1.setRowCount(0);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ref() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT MAX(idstock_entry) FROM stock_entry");

            while (rs.next()) {

                if (rs.getInt("MAX(idstock_entry)") == 0) {
                    REF = "SE1";

                } else {
                    int i = rs.getInt("MAX(idstock_entry)") + 1;
                    REF = "SE" + i + "";

                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    //---------------------------------------- search from stock update frame ---------------------------
    public String searchFrameStockUpdate() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameStockUpdate.jTableimei.getModel();
        
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM stock_entry WHERE ref='" + FrameStockUpdate.jTextFieldRefNo.getText() + "' ");
            while (rs.next()) {
                idSupplier = rs.getString("idsupplier");
                idProduct = rs.getString("idproduct");
                
                ResultSet rs5 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM imei WHERE idproduct='" + idProduct + "' AND status='" + "NOTSELL" + "' ");
                while (rs5.next()) {
                    Vector v = new Vector();
                    v.add(rs5.getString("idimei"));
                    v.add(rs5.getString("imei1"));
                    v.add(rs5.getString("imei2"));
                    dtm.addRow(v);
                }

                FrameStockEntry.idSupplier=idSupplier;
                
                FrameStockUpdate.jTextFieldQty.setText(rs.getString("qty"));
                FrameStockUpdate.jTextAreaDiscription.setText(rs.getString("description"));
                FrameStockUpdate.jTextFieldCost.setText(rs.getString("cost"));

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE idproduct='" + idProduct + "' ");
                while (rs1.next()) {
                    FrameStockUpdate.jTextFieldProductName.setText(rs1.getString("p_name"));
                    FrameStockUpdate.jTextFieldPricePerUnit.setText(rs1.getString("selling_price"));
                    String s = rs1.getString("p_code");
                    FrameStockUpdate.jComboBoxProductCode.setSelectedItem(s);
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return idProduct;
    }

    //------------------------------------------- update ----------------------------------
    public void update(String date, String id_product,String idSupplier) {
        date = date;
        idProduct = id_product;
        idSupplier=idSupplier;
        
        try {

            cls.db.myConnection().createStatement().executeUpdate("UPDATE stock_entry SET idsupplier='" +idSupplier+ "' ,"
                    + "date='" + date + "' , idproduct='" + idProduct + "' , qty='" + FrameStockUpdate.jTextFieldQty.getText() + "' ,cost='" + FrameStockUpdate.jTextFieldCost.getText() + "' , description='" + FrameStockUpdate.jTextAreaDiscription.getText() + "' "
                    + " WHERE ref='" + FrameStockUpdate.jTextFieldRefNo.getText() + "' ");
            
            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) FrameStockUpdate.jTableimei.getModel();

            for (int i = 0; i < FrameStockUpdate.jTableimei.getRowCount(); i++) {

                String idimei = dtm.getValueAt(i, 0).toString();
                String imei1 = dtm.getValueAt(i, 1).toString();
                String imei2 = dtm.getValueAt(i, 2).toString();

                j =cls.db.myConnection().createStatement().executeUpdate("UPDATE imei SET imei1='" +imei1+ "' , imei2='" +imei2+ "'  WHERE idimei='" + idimei+ "' ");

            }
            
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
