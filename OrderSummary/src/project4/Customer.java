package project4;
import java.io.Serializable;
import java.sql.Date;
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
	
	private int id;
	private Date created;
    private String lastName;
    private String firstName;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String email;
    
    private ArrayList<Order> orders;
    
    public Customer() {
    	
    }

    /** Creates a new instance of Customer */
    public Customer(int p_customerID) {
	id = p_customerID;

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
    
    public Customer(int p_id, String p_lastName, String p_firstName, String p_address, String p_city, String p_state, String p_zipcode, String p_phone, String p_email, Date p_created) {
    	id = p_id;
    	lastName = p_lastName;
        firstName = p_firstName;
        address = p_address;
        city = p_city;
        state = p_state;
        zipcode = p_zipcode;
        phone = p_phone;
        email = p_email;
        created = p_created;
        
        orders = new ArrayList<Order>();
    }
    
    public int getID() {
      return id;
    }

	public void setID(int i) {
	  id = i;
	}

    public String getLastName() {
      return lastName;
    }
    
    public void setLastName(String l) {
    	lastName = l;
    }
    
    public String getFirstName() {
      return firstName;
    }
    
    public void setFirstName(String f) {
    	firstName = f;
    }
    
    public String getAddress() {
      return address;
    }
    
    public void setAddress(String a) {
    	address = a;
    }
    
    public String getCity() {
      return city;
    }
    
    public void setCity(String c) {
    	city = c;
    }
    
    public String getState() {
      return state;
    }
    
    public void setState(String s) {
    	state = s;
    }
    
    public String getZipCode() {
      return zipcode;
    }
    
    public void setZipCode(String z) {
    	zipcode = z;
    }
    
    public String getPhone() {
      return phone;
    }
    
    public void setPhone(String p) {
    	phone = p;
    }
    
    public String getemail() {
      return email;
    }
    
    public void setEmail(String e) {
    	email = e;
    }
    
    public ArrayList<Order> getOrders() {
      return orders;
    }
    
    public void setOrders(ArrayList<Order> p_orders) {
      orders = p_orders;
    }
    
    public Date getCreated() {
    	return created;
    }

}

