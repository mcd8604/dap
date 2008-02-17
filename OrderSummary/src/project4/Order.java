package project4;
import java.io.Serializable;
import java.sql.Date;
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
    private Customer customer;
    private double total;
	private Date created;

    private ArrayList<OrderItem> orderItems;

	private boolean completed;

	public Order() {}
	
    /** Creates a new instance of Order */
    public Order(int p_customerID, double p_total) {
      setCustomerID(p_customerID);
      total = p_total;
      orderItems = new ArrayList<OrderItem>();
    }
    
    public Order(int p_orderID, int p_customerID, double p_total) {
	orderID = p_orderID;
      setCustomerID(p_customerID);
      total = p_total;
      orderItems = new ArrayList<OrderItem>();
    }
    
    public Order(int p_orderID, Customer p_customer, double p_total, Date p_created) {
    	orderID = p_orderID;
    	setCustomerID(p_customer.getID());
    	customer = p_customer;
    	total = p_total;
    	created = p_created;
    	orderItems = new ArrayList<OrderItem>();
    }
    
    public int getOrderID() {
      return orderID;
    }
    
    public int getCustomerID() {
      return customerID;
    }

    public double getTotal() {
      return total;
    }
    
    public ArrayList<OrderItem> getOrderItems() {
      return orderItems;
    }

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

    public void setOrderItems(ArrayList<OrderItem> p_OrderItems) {
    	orderItems = p_OrderItems;
    }

	public void setCompleted(boolean b) {
		completed = b;
	}
	
	public Boolean getCompleted() {
		return completed;
	}

	public void setOrderID(int p_orderID) {
		this.orderID = p_orderID;
	}
	
	public void setCustomer(Customer c) {
		this.customer = c;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
    
    public Date getCreated() {
    	return created;
    }

	private void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}

