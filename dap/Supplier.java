import java.io.Serializable;

public class Supplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phone;
	private String email;
	
	public Supplier() {
		
	} 
	
	public Supplier(String p_name, String p_address, String p_city, String p_state, String p_zipcode, String p_phone, String p_email) {
	      name = p_name;
	      address = p_address;
	      city = p_city;
	      state = p_state;
	      zipcode = p_zipcode;
	      phone = p_phone;
	      email = p_email;
	    }
    
    public int getID() {
      return id;
    }

	public void setID(int i) {
	  id = i;
	}

    public String getName() {
      return name;
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

}
