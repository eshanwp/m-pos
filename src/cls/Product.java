/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import frame.FrameProductEntry;
import frame.FrameProductEntryUpdate;
import frame.FrameViewProduct;
import frame.FrameViewStock;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esh
 */
public class Product {

    String idCategory = null;
    String idSubCategory = null;
    String idSupplier = null;
    String idProduct = null;
    String date = null;
    String REF = null;

    int j=0;
    //---------------------------------------- category data to Product Entry ------------------------------------------
    public void loadCategory() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("category_name"));
            }

            FrameProductEntry.jComboBoxCategoryName.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    //---------------------------------------- category data to View Product  ------------------------------------------
    public void loadCategory2() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("category_name"));
            }

            FrameViewProduct.jComboBoxCategoryName.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public void loadCategory3() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("category_name"));
            }

            FrameProductEntryUpdate.jComboBoxCategoryName.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    //---------------------------------------- supplier data to View Product  ------------------------------------------
    public void loadSupplier() {

        String sCode = null;
        String sName = null;
        String sCompany = null;
        String result = null;
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {

                sCode = rs.getString("idsupplier");
                sName = rs.getString("name_initials");
                sCompany = rs.getString("company_name");

                v.add(sCode + " - " + sName + " - " + sCompany);

            }

            FrameViewProduct.jComboBoxSupplier.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    //---------------------------------------- sub category data ------------------------------------------
    public void loadSubCategory() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category");

            Vector v = new Vector();
            v.add("--SELECT--");
            while (rs.next()) {
                v.add(rs.getString("sub_category_name"));
            }

            FrameViewProduct.jComboBoxSubCategory.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    //-------------------------------------------- Save ---------------------------------------------------------
    public void save(String id_category, String id_subCategory, String CURRENT_DATE) {

        idCategory = id_category;
        idSubCategory = id_subCategory;
        date = CURRENT_DATE;

        ref();

        try {

            cls.db.myConnection().createStatement().executeUpdate("INSERT INTO product (p_code, p_name, idCategory, idSub_category, discription, idsupplier, cost, selling_price, op_stock, discount, vat, idstock,date,status,type)"
                    + "VALUES ('" + FrameProductEntry.jTextFieldProductCode.getText() + "' , '" + FrameProductEntry.jTextFieldProductName.getText() + "' , "
                    + "'" + idCategory + "' , '" + idSubCategory + "' , '" + FrameProductEntry.jTextAreaDiscription.getText() + "' , '" + FrameProductEntry.idSupplier + "' , "
                    + "'" + FrameProductEntry.jTextFieldCost.getText() + "' , '" + FrameProductEntry.jTextFieldSellingPrice.getText() + "' , '" + FrameProductEntry.jTextFieldOPStock.getText() + "' , "
                    + "'" + FrameProductEntry.jTextFieldDiscount.getText() + "' , '" + FrameProductEntry.jTextFieldVat.getText() + "' , '" + FrameProductEntry.jTextFieldStockId.getText() + "', '" + date + "' , '" + "ACTIVE" + "', '" + FrameProductEntry.jComboBoxType.getSelectedItem().toString() + "' )");

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE p_code='" + FrameProductEntry.jTextFieldProductCode.getText() + "' ");
            while (rs.next()) {
                idProduct = rs.getString("idproduct");
            }

            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) FrameProductEntry.jTable1.getModel();

            for (int i = 0; i < FrameProductEntry.jTable1.getRowCount(); i++) {

                String imei1 = dtm.getValueAt(i, 1).toString();
                String imei2 = dtm.getValueAt(i, 2).toString();

                cls.db.myConnection().createStatement().executeUpdate("INSERT INTO imei (idproduct, imei1, imei2, status) "
                        + "VALUES('" + idProduct + "' , '" + imei1 + "' ,'" + imei2 + "', '" + "NOTSELL" + "')");

            }

            j =cls.db.myConnection().createStatement().executeUpdate("INSERT INTO stock_entry(idstock, idsupplier, date, idproduct, opStock,qty, description,ref,cost)"
                    + "VALUES('" + FrameProductEntry.jTextFieldStockId.getText() + "' , '" + FrameProductEntry.idSupplier + "' , '" + date + "' , "
                    + "'" + idProduct + "' , '" + FrameProductEntry.jTextFieldOPStock.getText() + "' , '" + "0" + "', '" + FrameProductEntry.jTextAreaDiscription.getText() + "', '" + REF + "',  '" + FrameProductEntry.jTextFieldCost.getText() + "')");

            if (j==1) {
                JOptionPane.showMessageDialog(null, "Successfully Saved\nProduct Code=" + FrameProductEntry.jTextFieldProductCode.getText() + "\nProduct Name=" + FrameProductEntry.jTextFieldProductName.getText() + "\nReference no=" + REF + " ");

            } else {
                JOptionPane.showMessageDialog(null, "Data insertion unsuccessfull");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // ------------------------------------- search -----------------------------------------
    public String search() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE p_code='" + FrameProductEntry.jTextFieldProductCode.getText() + "' ");
            while (rs.next()) {

                idProduct = rs.getString("idproduct");
                FrameProductEntry.jTextFieldProductName.setText(rs.getString("p_name"));
                FrameProductEntry.jTextAreaDiscription.setText(rs.getString("discription"));
                FrameProductEntry.jTextFieldCost.setText(rs.getString("cost"));
                FrameProductEntry.jTextFieldSellingPrice.setText(rs.getString("selling_price"));
                FrameProductEntry.jTextFieldOPStock.setText(rs.getString("op_stock"));
                FrameProductEntry.jTextFieldDiscount.setText(rs.getString("discount"));
                FrameProductEntry.jTextFieldVat.setText(rs.getString("vat"));

                idCategory = rs.getString("idCategory");

                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE idcategory='" + idCategory + "' ");
                while (rs1.next()) {
                    FrameProductEntry.jComboBoxCategoryName.setSelectedItem(rs1.getString("category_name"));
                }

                idSubCategory = rs.getString("idSub_category");

                ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category WHERE idsub_category='" + idSubCategory + "' ");
                while (rs2.next()) {
                    FrameProductEntry.jComboBoxSubCategory.setSelectedItem(rs2.getString("sub_category_name"));
                }

                idSupplier = rs.getString("idsupplier");

                ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE idsupplier='" + idSupplier + "' ");
                while (rs3.next()) {
                    FrameProductEntry.idSupplier = rs3.getString("idsupplier");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return idProduct;
    }

    // ------------------------------------- search (Product entry update frame) -----------------------------------------
    public String searchFrameProductEntryUpdate() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameProductEntryUpdate.jTable1.getModel();
        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM stock_entry WHERE ref='" + FrameProductEntryUpdate.jTextFieldRef.getText() + "' ");
            while (rs.next()) {

                idProduct = rs.getString("idproduct");

                ResultSet rs5 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM imei WHERE idproduct='" + idProduct + "' AND status='" + "NOTSELL" + "' ");
                while (rs5.next()) {
                    Vector v = new Vector();
                    v.add(rs5.getString("idimei"));
                    v.add(rs5.getString("imei1"));
                    v.add(rs5.getString("imei2"));
                    dtm.addRow(v);
                }

                ResultSet rs4 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE idproduct='" + idProduct + "' ");
                while (rs4.next()) {

                    FrameProductEntryUpdate.jTextFieldProductCode.setText(rs4.getString("p_code"));
                    FrameProductEntryUpdate.jTextFieldProductName.setText(rs4.getString("p_name"));
                    FrameProductEntryUpdate.jTextAreaDiscription.setText(rs4.getString("discription"));
                    FrameProductEntryUpdate.jTextFieldCost.setText(rs4.getString("cost"));
                    FrameProductEntryUpdate.jTextFieldSellingPrice.setText(rs4.getString("selling_price"));
                    FrameProductEntryUpdate.jTextFieldOPStock.setText(rs4.getString("op_stock"));
                    FrameProductEntryUpdate.jTextFieldDiscount.setText(rs4.getString("discount"));
                    FrameProductEntryUpdate.jTextFieldVat.setText(rs4.getString("vat"));

                    idCategory = rs4.getString("idCategory");

                    ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE idcategory='" + idCategory + "' ");
                    while (rs1.next()) {
                        FrameProductEntryUpdate.jComboBoxCategoryName.setSelectedItem(rs1.getString("category_name"));
                    }

                    idSubCategory = rs4.getString("idSub_category");

                    ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category WHERE idsub_category='" + idSubCategory + "' ");
                    while (rs2.next()) {
                        FrameProductEntryUpdate.jComboBoxSubCategory.setSelectedItem(rs2.getString("sub_category_name"));
                    }

                    idSupplier = rs4.getString("idsupplier");

                    ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE idsupplier='" + idSupplier + "' ");
                    while (rs3.next()) {
                        FrameProductEntryUpdate.IDSupplier = rs3.getString("idsupplier");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return idProduct;
    }

    //------------------------------------------------------ Update ----------------------------------------------------------
    public void update(String id_category, String id_subCategory, String id_product) {

        idCategory = id_category;
        idSubCategory = id_subCategory;

        idProduct = id_product;

        String statusDb = null;

        if (FrameProductEntryUpdate.jCheckBox.isSelected()) {
            statusDb = "INACTIVE";
        } else {
            statusDb = "ACTIVE";
        }

        try {

            j =cls.db.myConnection().createStatement().executeUpdate("UPDATE product SET p_name='" + FrameProductEntryUpdate.jTextFieldProductName.getText() + "', p_code='" + FrameProductEntryUpdate.jTextFieldProductCode.getText() + "' , idCategory='" + idCategory + "' ,"
                    + "idSub_category='" + idSubCategory + "' , discription='" + FrameProductEntryUpdate.jTextAreaDiscription.getText() + "' ,"
                    + "idsupplier='" + FrameProductEntryUpdate.IDSupplier + "' , cost='" + FrameProductEntryUpdate.jTextFieldCost.getText() + "' ,"
                    + "selling_price='" + FrameProductEntryUpdate.jTextFieldSellingPrice.getText() + "' , op_stock='" + FrameProductEntryUpdate.jTextFieldOPStock.getText() + "' ,"
                    + "discount='" + FrameProductEntryUpdate.jTextFieldDiscount.getText() + "' , vat='" + FrameProductEntryUpdate.jTextFieldVat.getText() + "' ,"
                    + "idstock='" + FrameProductEntryUpdate.jTextFieldStockId.getText() + "' , status='" + statusDb + "' WHERE idproduct='" + idProduct + "' ");

            j =cls.db.myConnection().createStatement().executeUpdate("UPDATE stock_entry SET idstock='" + FrameProductEntryUpdate.jTextFieldStockId.getText() + "' ,"
                    + " idsupplier='" + FrameProductEntryUpdate.IDSupplier + "' , opStock='" + FrameProductEntryUpdate.jTextFieldOPStock.getText() + "' ,"
                    + "description='" + FrameProductEntryUpdate.jTextAreaDiscription.getText() + "'  WHERE ref='" + FrameProductEntryUpdate.jTextFieldRef.getText() + "' ");
            
            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) FrameProductEntryUpdate.jTable1.getModel();

            for (int i = 0; i < FrameProductEntryUpdate.jTable1.getRowCount(); i++) {

                String idimei = dtm.getValueAt(i, 0).toString();
                String imei1 = dtm.getValueAt(i, 1).toString();
                String imei2 = dtm.getValueAt(i, 2).toString();

               j = cls.db.myConnection().createStatement().executeUpdate("UPDATE imei SET imei1='" +imei1+ "' , imei2='" +imei2+ "'  WHERE idimei='" + idimei+ "' ");
                

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

    //-------------------------- ref -------------------
    public void ref() {

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT MAX(idstock_entry) FROM stock_entry");

            while (rs.next()) {

                if (rs.getInt("MAX(idstock_entry)") == 0) {
                    REF = "PE1";

                } else {
                    int i = rs.getInt("MAX(idstock_entry)") + 1;
                    REF = "PE" + i + "";

                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    //------------------------------------------- search -------------------------------------------
    public void searchViewProduct(String id_category, String id_subcategory, String supplier_code) {

        idCategory = id_category;
        idSubCategory = id_subcategory;
        idSupplier = supplier_code;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewProduct.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product WHERE p_code='" + FrameViewProduct.jTextFieldProductCode.getText() + "' "
                    + "OR p_name='" + FrameViewProduct.jTextFieldProductName.getText() + "'OR idCategory='" + idCategory + "' OR idSub_category='" + idSubCategory + "' OR idsupplier='" + idSupplier + "' ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idproduct"));
                v.add(rs.getString("p_code"));
                v.add(rs.getString("p_name"));

                String idCat = rs.getString("idCategory");
                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE idcategory='" + idCat + "' ");
                while (rs1.next()) {
                    v.add(rs1.getString("category_name"));
                }

                String idSub = rs.getString("idSub_category");
                ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category WHERE idsub_category='" + idSub + "' ");
                while (rs2.next()) {
                    v.add(rs2.getString("sub_category_name"));
                }

                v.add(rs.getString("discription"));

                String idSup = rs.getString("idsupplier");
                ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE idsupplier='" + idSup + "' ");
                while (rs3.next()) {
                    v.add(rs3.getString("name_initials"));
                }

                v.add(rs.getString("cost"));
                v.add(rs.getString("selling_price"));
                v.add(rs.getString("op_stock"));
                v.add(rs.getString("discount"));
                v.add(rs.getString("vat"));
                v.add(rs.getString("status"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //------------------------------------------- search all -------------------------------------------
    public void searchAll() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) FrameViewProduct.jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM product ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idproduct"));
                v.add(rs.getString("p_code"));
                v.add(rs.getString("p_name"));

                String idCat = rs.getString("idCategory");
                ResultSet rs1 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM category WHERE idcategory='" + idCat + "' ");
                while (rs1.next()) {
                    v.add(rs1.getString("category_name"));
                }

                String idSub = rs.getString("idSub_category");
                ResultSet rs2 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM sub_category WHERE idsub_category='" + idSub + "' ");
                while (rs2.next()) {
                    v.add(rs2.getString("sub_category_name"));
                }

                v.add(rs.getString("discription"));

                String idSup = rs.getString("idsupplier");
                ResultSet rs3 = cls.db.myConnection().createStatement().executeQuery("SELECT * FROM supplier WHERE idsupplier='" + idSup + "' ");
                while (rs3.next()) {
                    v.add(rs3.getString("name_initials"));
                }

                v.add(rs.getString("cost"));
                v.add(rs.getString("selling_price"));
                v.add(rs.getString("op_stock"));
                v.add(rs.getString("discount"));
                v.add(rs.getString("vat"));
                v.add(rs.getString("status"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
