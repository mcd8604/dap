/*
 * Item.java
 *
 * This class holds Item data
 *
 * Created on January 16, 2008, 2:55 PM
 */

/**
 *
 * @author mcd
 */
public class Item {
  
    private int itemID;
    private String itemName;
    private String itemDesc;
    private double salePrice;
    private double supplierPrice;

    /** Creates a new instance of Item */
    public Item (int p_itemID, String p_itemName, String p_itemDesc, double p_salePrice, double p_supplierPrice) {
      itemID = p_itemID;
      itemName = p_itemName;
      itemDesc = p_itemDesc;
      salePrice = p_salePrice;
      supplierPrice = p_supplierPrice;
    }
    
    public int getItemID() {
      return itemID;
    }
    
    public String getItemName() {
      return itemName;
    }
    
    public String getItemDesc() {
      return itemDesc;
    }
    
    public double getSalePrice() {
      return salePrice;
    }
    
    public double getSupplierPrice() {
      return supplierPrice;
    }

}