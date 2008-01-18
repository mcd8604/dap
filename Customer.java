import java.io.Serializable;
import java.util.ArrayList;
/*
 * Customer.java
 *
 * This class holds Customer data
 *
 * Created on January 16, 2008, 2:51 PM
 */

/**
 *
 * @author mcd
 */
public class Customer implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int customerID;
    private String lastName;
    private String firstName;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String email;
    
    private ArrayList<Order> orders;

    /** Creates a new instance of Customer */
    public Customer(int p_customerID) {
	customerID = p_customerID;

      orders = new ArrayList<Order>();
    }

    /** Creates a new instance of Customer */
    public Customer(String p_lastName, String p_firstName, String p_address, String p_city, String p_state, String p_zipcode, String p_phone, String p_email) {
      lastName = p_lastName;
      firstName = p_firstName;
      address = p_address;
      city = p_city;
      state = p_state;
      zipcode = p_zipcode;
      phone = p_phone;
      email = p_email;
      
      orders = new ArrayList<Order>();
    }
    
    public int getCustomerID() {
      return customerID;
    }

    public String getLastName() {
      return lastName;
    }
    
    public String getFirstName() {
      return firstName;
    }
    
    public String getAddress() {
      return address;
    }
    
    public String getCity() {
      return city;
    }
    
    public String getState() {
      return state;
    }
    
    public String getZipCode() {
      return zipcode;
    }
    
    public String getPhone() {
      return phone;
    }
    
    public String getemail() {
      return email;
    }
    
    public ArrayList<Order> getOrders() {
      return orders;
    }
    
    public void setOrders(ArrayList<Order> p_orders) {
      orders = p_orders;
    }

}

