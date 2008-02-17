package project4;
import java.io.Serializable;

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
public class Item implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
    private int itemID;
    private String itemName;
    private String itemDesc;
    private double salePrice;
    private double supplierPrice;
    private Supplier supplier;

    /** Creates a new instance of Item */
    public Item (int p_itemID, String p_itemName, String p_itemDesc, double p_salePrice, double p_supplierPrice) {
      itemID = p_itemID;
      itemName = p_itemName;
      itemDesc = p_itemDesc;
      salePrice = p_salePrice;
      supplierPrice = p_supplierPrice;
    }
    
    public Item (int p_itemID, String p_itemName, String p_itemDesc, double p_salePrice, double p_supplierPrice, Supplier p_supplier) {
        itemID = p_itemID;
        itemName = p_itemName;
        itemDesc = p_itemDesc;
        salePrice = p_salePrice;
        supplierPrice = p_supplierPrice;
        supplier = p_supplier;
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
    
    public String toString() {
    	return this.itemName;
    }
    
    public void setSupplier(Supplier s) {
    	this.supplier = s;
    }
    
    public Supplier getSupplier() {
    	return this.supplier;
    }

}