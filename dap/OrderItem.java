import java.io.Serializable;



/**
 *
 * @author mcd
 */
public class OrderItem  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private int orderItemID;
    private Item item;
    private int quantity;

    /** Creates a new instance of OrderItem */
    public OrderItem(int p_OrderItemID) {
	orderItemID= p_OrderItemID;
    }

    /** Creates a new instance of Customer */
    public OrderItem(Item p_item, int p_quantity) {
      item = p_item;
      quantity= p_quantity;
    }

    /** Creates a new instance of Customer */
    public OrderItem(int p_OrderItemID, Item p_item, int p_quantity) {
	orderItemID = p_OrderItemID;
      item = p_item;
      quantity = p_quantity;
    }
    
    public int getOrderItemID() {
      return orderItemID;
    }

    public Item getItem() {
      return item;
    }
    
    public int getQuantity() {
      return quantity;
    }
}
