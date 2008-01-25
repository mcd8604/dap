import java.io.Serializable;
import java.util.ArrayList;
/*
 * Order.java
 *
 * This class holds Order data
 *
 * Created on January 17, 2008, 12:51 PM
 */

/**
 *
 * @author mcd
 */
public class Order  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private int orderID;
    private int customerID;
    private float total;
    private ArrayList<OrderItem> orderItems;

	private boolean completed;

    /** Creates a new instance of Order */
    public Order(int p_customerID, float p_total) {
      customerID = p_customerID;
      total = p_total;
      orderItems = new ArrayList<OrderItem>();
    }
    
    public Order(int p_orderID, int p_customerID, float p_total) {
	orderID = p_orderID;
      customerID = p_customerID;
      total = p_total;
      orderItems = new ArrayList<OrderItem>();
    }
    
    public int getOrderID() {
      return orderID;
    }
    
    public int getCustomerID() {
      return customerID;
    }

    public float getTotal() {
      return total;
    }
    
    public ArrayList<OrderItem> getOrderItems() {
      return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> p_OrderItems) {
    	orderItems = p_OrderItems;
    }

	public void setCompleted(boolean b) {
		// TODO Auto-generated method stub
		completed = b;
	}
	
	public Boolean getCompleted() {
		return completed;
	}

	public void setOrderID(int p_orderID) {
		// TODO Auto-generated method stub
		this.orderID = p_orderID;
	}
}

