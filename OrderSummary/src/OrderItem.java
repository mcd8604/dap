import java.io.Serializable;



/**
 * Contains an Item object and the quantity
 * associated with the Item.
 *
 * @author mcd
 */
public class OrderItem  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private int orderID;
    private Item item;
    private int quantity;

    /** Creates a new instance of OrderItem */
    public OrderItem(int p_orderID) {
    	orderID = p_orderID;
    }

    /** Creates a new instance of Customer */
    public OrderItem(Item p_item, int p_quantity) {
      item = p_item;
      quantity= p_quantity;
    }

    /** Creates a new instance of Customer */
    public OrderItem(int p_orderID, Item p_item, int p_quantity) {
    orderID = p_orderID;
      item = p_item;
      quantity = p_quantity;
    }
    
    public int getOrderItemID() {
      return orderID;
    }

    public Item getItem() {
      return item;
    }
    
    public int getQuantity() {
      return quantity;
    }
}
