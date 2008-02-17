

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Database
 */
public class DatabaseController {
    
    //Attributes for Connector/J
    private static String CATALOG_NAME = "orderhandle";
    private static String DATASOURCE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static String DATASOURCE_URL = "jdbc:mysql://localhost:3306/" + CATALOG_NAME;
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    
    //Sync object
    private static Object stmtLock = new Object();
    
    //Singleton
    private static DatabaseController _instance = new DatabaseController();
    private Connection conn;
    
    /**
     * Constructor
     */
    private DatabaseController() {
    }

    /**
     * Gets singleton instance
     * @return singleton instance
     */
    private static DatabaseController getInstance() {
    	if(_instance == null) {
    		_instance = new DatabaseController();
    	}
    	return _instance;
    }
    
    /**
     * Executes String query
     * 
     * @param sqlQuery String sql query
     * @return ArrayList of string records
     */
    /*public static void executeUpdate(String query) throws SQLException {
    	Statement statement = null;
		try {
			getInstance().getConnection();
	    	synchronized(stmtLock) {
	            statement = getInstance().conn.createStatement();                
	            statement.executeUpdate(query);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
            throw new SQLException(e.getMessage());
		} finally {
			getInstance().closeConnection();
		}
    }*/
    
    /**
     * Converts String query to ArrayList result
     * 
     * @param sqlQuery String sql query
     * @return ArrayList of string records
     * @throws SQLException
     */
    public static ArrayList<String> queryToArrayList(String sqlQuery) throws SQLException {
        ArrayList<String> ret = null;
    	ResultSet rs = null;
    	Statement statement = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
	            statement = getInstance().conn.createStatement();                
	            if(statement.execute(sqlQuery)) {
	            	rs = statement.getResultSet();
	            } else {
	        		ret = new ArrayList<String>();
	        		ret.add("Successful update");
	            }
        	}
        	
            if(rs != null) {
                ret = makeDataTable(rs);
            }
        } catch(SQLException e){
			e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
			closeConnection();;
		}
        
        return ret;
    }
    
    /**
     * Returns table (ResultSet) as a two dimensional ArrayList 
     */
    public static ArrayList<String> makeDataTable(ResultSet rs) throws SQLException {
        ArrayList<String> ret = new ArrayList<String>();
        String row = "";
        int i = 0;
        
        int colcount = rs.getMetaData().getColumnCount();
        while( rs.next() ) {
        	for (i=1; i < colcount+1; i++) {
        		row += rs.getString(i);
        	}
        	
        	ret.add(row);
        }
        
        return ret;
    }
	
    /**
     * Gets the connection for the instance
     */
    private static void getConnection() throws SQLException {      
        try {
            Class.forName(DATASOURCE_DRIVER_CLASS);
        } catch (ClassNotFoundException cnfe) {
        	System.out.println("MYSQL LIBRARY NOT FOUND: " + cnfe.getMessage());
        }
        if(getInstance().conn != null) {
        	getInstance().conn = DriverManager.getConnection( DATASOURCE_URL, USERNAME, PASSWORD );	
        } else {
        	throw new SQLException("DATABASE CONNECTION FAILED");
        }
    }
    
    /**
     * Closes the connection for the instance
     */
    private static void closeConnection() {
        try {
            if( (getInstance().conn!= null) && (!getInstance().conn.isClosed()) )
            	getInstance().conn.close();
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
    }
    
    /**
     * Checks if Customer exists in database by CustomerID
     * 
     * @param c the customer to check
     * @return true if the customer exists, false if not
     */
    public static Boolean isCustomer(Customer c) {
    	Boolean ret = false;
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
        		// *** do we need PreparedStatement?
	            statement = getInstance().conn.createStatement(); 
	            sqlQuery = "SELECT * FROM Customer WHERE ID='" + c.getID() + "';";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
	            // Evaluate results
	            ret = rs.next();
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
        return ret;
    }
    
	public static Customer getCustomer(int customerID) {
    	Customer ret = null;
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	synchronized(stmtLock) {
	            statement = getStatement(); 
        		
	            sqlQuery = "SELECT * FROM Customer WHERE ID=" + customerID;
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
				if(rs.next()) {		
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					String address = rs.getString("Address");
					String city = rs.getString("City");
					String state = rs.getString("State");
					String zipcode = rs.getString("Zipcode");
					String phone = rs.getString("Phone");
					String email = rs.getString("Email");
					Date customerDate = rs.getDate("CrDate");
		        	
					ret = new Customer(customerID, lastName, firstName, address, city, state, zipcode, phone, email, customerDate);
				}
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
		return ret;
	}

    /**
     * Creates a customer in the database,
     * updates the customer object with a
     * new CustomerID and returns in.
     * 
     * @param c the customer to add
     * @return the customer that was added
     */
    public static Customer createCustomer(Customer c) {
    	Customer ret = null;
    	ResultSet rs = null;
    	// *** Do we need PreparedStatement?
    	Statement insertStatement = null;
    	String sqlInsert = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
        		// 1 - Insert New Customer
        		insertStatement = getStatement(); 

	            sqlInsert = "INSERT INTO Customer (FirstName, LastName, Address, City, State, Zipcode, Phone, Email) VALUES " + 
	            	" ('" + c.getFirstName() + 
	            	"', '" + c.getLastName() + 
	            	"', '" + c.getAddress() + 
	            	"', '" + c.getCity() + 
	            	"', '" + c.getState() + 
	            	"', '" + c.getZipCode() + 
	            	"', '" + c.getPhone() + 
	            	"', '" + c.getemail() + "');";
	            
	            insertStatement.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
        		 
	            // 2 - Return Customer with ID
	            rs = insertStatement.getGeneratedKeys();
	            rs.next();
	        	c.setID(rs.getInt(1));
            	ret = c;
        	}
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
        return ret;
    }
    
    public static boolean editCustomer(Customer c) {
    	boolean ret = false;
    	Statement updateStatement = null;
    	String updateSql = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
        		updateStatement = getStatement(); 

        		//TODO update sql
        		updateSql = "UPDATE Customer " + 
	            	"SET Firstname = '" + c.getFirstName() + 
	            	"', LastName = '" + c.getLastName() + 
	            	"', Address = '" + c.getAddress() + 
	            	"', City = '" + c.getCity() + 
	            	"', State = '" + c.getState() + 
	            	"', ZipCode = '" + c.getZipCode() + 
	            	"', Phone = '" + c.getPhone() + 
	            	"', Email = '" + c.getemail() +
	            	"' WHERE ID = " + c.getID();
	            
	            updateStatement.executeUpdate(updateSql);
	            
            	ret = true;
        	}
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
        return ret;    	
    }
    
    public static boolean deleteCustomer(Customer c) {
    	boolean ret = false;
    	Statement deleteStatement = null;
    	String deleteSql = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
        		//delete sequence: 
        		//(1)OrderItems 
        		deleteSql = "DELETE FROM OrderItem WHERE OrderItem.OrderID = (SELECT OrderID FROM Ordr WHERE ID = " + c.getID() + ")";
	            deleteStatement.executeUpdate(deleteSql);
	            //(2)Orders 
	            deleteSql = "DELETE FROM Ordr WHERE Ordr.ID = " + c.getID();
	            deleteStatement.executeUpdate(deleteSql);
	            //(3)Customer
	            deleteSql = "DELETE FROM Customer WHERE ID = " + c.getID();
	            deleteStatement.executeUpdate(deleteSql);
	            
            	ret = true;
        	}
        } catch(SQLException e){
			ret = false;
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
        return ret;
    	
    }

    /**
     * Grabs the list of items from the database
     * and returns an ArrayList of Item objects.
     * 
     * @return an ArrayList of Item objects.
     */
    public static ArrayList<Item> getItems() {
    	ArrayList<Item> ret = new ArrayList<Item>();
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
	            statement = getStatement();
        		sqlQuery = "SELECT * FROM Item";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
				while(rs.next()) {
					// Parse Strings into items			        
		        	int id = rs.getInt("ItemID");
		        	String name = rs.getString("ItemName");
		        	String desc = rs.getString("ItemDesc");
		        	double salePrice = rs.getDouble("SalePrice");
		        	double supplierPrice = rs.getDouble("SupplierPrice");
		        	
					Item item = new Item(id, name, desc, salePrice, supplierPrice);
					ret.add(item);
				}
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
		return ret;
    }

    /**
     * Adds a new order to the database
     * (Adds the Order's list of OrderItems first)
     * 
     * @param the order to add to the database
     * @return the order that was added
     */
    public static Order createOrder(Order o) {
    	ResultSet rs = null;

    	Statement insertOrderStatement = null;
    	String sqlInsertOrder = null;

    	Statement insertOrderItemsStatement = null;
    	String sqlInsertOrderItems = null;
    	
        try {
        	getConnection();
        	synchronized(stmtLock) {
        		o.setCompleted(false);
        		// 1 - Insert New Order
        		insertOrderStatement = getStatement(); 

        		sqlInsertOrder = "INSERT INTO Ordr (ID, Total) VALUES ('" + o.getCustomerID() + "', '" + o.getTotal() + "');";
        		
        		insertOrderStatement.executeUpdate(sqlInsertOrder, Statement.RETURN_GENERATED_KEYS);
        		
	            // 2 - Get Order ID
	            rs = insertOrderStatement.getGeneratedKeys();
	            rs.next();
        		int orderID = rs.getInt(1);
	        	
            	// 3 - Insert all OrderItems
        		for(OrderItem oItem : o.getOrderItems()) {
            		insertOrderItemsStatement = getStatement();
        			Item i = oItem.getItem();
        			int itemID = i.getItemID();
        			int quantity = oItem.getQuantity();
        			sqlInsertOrderItems = "INSERT INTO OrderItem VALUES ('" + orderID + "','" + itemID + "','" + quantity + "'); ";
            		insertOrderItemsStatement.execute(sqlInsertOrderItems);	
        		} 
        		o.setOrderID(orderID);
        		o.setCompleted(true);
        	}
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
        return o;
    }

    /**
     * Gets all orders processed this day. Each order
     * contains a Customer Object and list of OrderItem
     * objects.
     * 
     * @return a list of Orders
     */
	public static ArrayList<Order> getOrdersToday() {
    	ArrayList<Order> ret = new ArrayList<Order>();
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
	            statement = getStatement();
        		
	            sqlQuery = "SELECT *, sum(OrderItem.Quantity * Item.SalePrice) AS CalcTotal FROM Ordr RIGHT JOIN " +
	            		"(OrderItem LEFT JOIN Item ON OrderItem.ItemID = Item.ItemID) ON Ordr.OrderID = OrderItem.OrderID LEFT JOIN " +
	            		"Customer ON(Ordr.ID = Customer.ID) WHERE DATEDIFF(NOW(),Ordr.CrDate) = 0 GROUP BY OrderItem.OrderID ";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
	            Order currentOrder = new Order();
	            
				while(rs.next()) {        
		        	int orderID = rs.getInt("OrderID");	 
		        	//Boolean completed = Boolean.parseBoolean(rs.getString("Completed"));
		        	double total = rs.getDouble("CalcTotal");
		        	Date orderDate = rs.getDate("Ordr.CrDate");
		            
		        	int customerID = rs.getInt("Customer.ID");
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					String address = rs.getString("Address");
					String city = rs.getString("City");
					String state = rs.getString("State");
					String zipcode = rs.getString("Zipcode");
					String phone = rs.getString("Phone");
					String email = rs.getString("Email");
		        	Date customerDate = rs.getDate("Customer.CrDate");

		        	int itemID = rs.getInt("ItemID");
		        	String itemName = rs.getString("ItemName");
		        	String itemDesc = rs.getString("ItemDesc");
		        	double salePrice = rs.getDouble("SalePrice");
		        	double supplierPrice = rs.getDouble("SupplierPrice");
		        	Item item = new Item(itemID, itemName, itemDesc, salePrice, supplierPrice);

		        	int quantity = rs.getInt("Quantity");

					if(orderID != currentOrder.getOrderID()) {
						Customer customer = new Customer(customerID, lastName, firstName, address, city, state, zipcode, phone, email, customerDate);
						currentOrder = new Order(orderID, customer, total, orderDate);
					} 

					currentOrder.addOrderItem(new OrderItem(orderID, item, quantity));
					
					ret.add(currentOrder);
				}
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
		return ret;
	}

	public static ArrayList<Customer> getCustomersToday() {
    	ArrayList<Customer> ret = new ArrayList<Customer>();
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	getConnection();
        	synchronized(stmtLock) {
	            statement = getStatement();
        		
	            sqlQuery = "SELECT * FROM Customer WHERE DATEDIFF(NOW(), CrDate) = 0";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
				while(rs.next()) {
					// Parse Strings into items		
		        	int customerID = rs.getInt("Customer.ID");
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					String address = rs.getString("Address");
					String city = rs.getString("City");
					String state = rs.getString("State");
					String zipcode = rs.getString("Zipcode");
					String phone = rs.getString("Phone");
					String email = rs.getString("Email");
					Date customerDate = rs.getDate("CrDate");
		        	
					Customer customer = new Customer(customerID, lastName, firstName, address, city, state, zipcode, phone, email, customerDate);
					ret.add(customer);
				}
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
		return ret;
	}

	public static ArrayList<Supplier> getSuppliers() {
    	ArrayList<Supplier> ret = new ArrayList<Supplier>();
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	
        	synchronized(stmtLock) {
	            statement = getStatement();
        		sqlQuery = "SELECT * FROM Supplier LEFT JOIN Item ON (Supplier.SupplierID = Item.SupplierID)";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();

	            Supplier currentSupplier = new Supplier();
				while(rs.next()) {
					// Parse result into suppliers			        
		        	int id = rs.getInt("SupplierID");
		        	String name = rs.getString("SupplierName");
		        	String address = rs.getString("SupplierAddr");
		        	String city = rs.getString("SupplierCity");
		        	String state= rs.getString("SupplierState");
		        	String zipcode = rs.getString("SupplierZipcode");
		        	String phone = rs.getString("SupplierPhone");
		        	String email = rs.getString("SupplierEmail");
		        	
		        	int itemID = rs.getInt("ItemID");
		        	String itemName = rs.getString("ItemName");
		        	String itemDesc = rs.getString("ItemDesc");
		        	double salePrice = rs.getDouble("SalePrice");
		        	double supplierPrice = rs.getDouble("SupplierPrice");
		        	Item item = new Item(itemID, itemName, itemDesc, salePrice, supplierPrice);
		        	
					if(currentSupplier.getID() == id) {
					} else {
						currentSupplier = new Supplier(id, name, address, city, state, zipcode, phone, email); 
						ret.add(currentSupplier);
					}
					currentSupplier.addItem(item);					
				}
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();;
		}
        
		return ret;
	}

    /**
     * Gets all orders and items in the order for
     * a certain customer.
     * 
     * @return a list of Orders
     */
	public static ArrayList<Order> getCustomerOrders(int customerID) {
    	ArrayList<Order> ret = new ArrayList<Order>();
    	ResultSet rs = null;
    	Statement statement = null;
    	String sqlQuery = null;

        try {
        	
        	synchronized(stmtLock) {
	            statement = getStatement(); 
        		
	            sqlQuery = "SELECT *, sum(OrderItem.Quantity * Item.SalePrice) AS CalcTotal FROM Ordr RIGHT JOIN " +
	            		"(OrderItem LEFT JOIN Item ON OrderItem.ItemID = Item.ItemID) ON Ordr.OrderID = OrderItem.OrderID LEFT JOIN " +
	            		"Customer ON(Ordr.ID = Customer.ID) WHERE Ordr.ID = " + customerID + " GROUP BY OrderItem.OrderID ";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
	            Order currentOrder = new Order();
	            
				while(rs.next()) {        
		        	int orderID = rs.getInt("OrderID");	 
		        	//Boolean completed = Boolean.parseBoolean(rs.getString("Completed"));
		        	double total = rs.getDouble("CalcTotal");
		        	Date orderDate = rs.getDate("Ordr.CrDate");
		            
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					String address = rs.getString("Address");
					String city = rs.getString("City");
					String state = rs.getString("State");
					String zipcode = rs.getString("Zipcode");
					String phone = rs.getString("Phone");
					String email = rs.getString("Email");
		        	Date customerDate = rs.getDate("Customer.CrDate");

		        	int itemID = rs.getInt("ItemID");
		        	String itemName = rs.getString("ItemName");
		        	String itemDesc = rs.getString("ItemDesc");
		        	double salePrice = rs.getDouble("SalePrice");
		        	double supplierPrice = rs.getDouble("SupplierPrice");
		        	Item item = new Item(itemID, itemName, itemDesc, salePrice, supplierPrice);

		        	int quantity = rs.getInt("Quantity");

					if(orderID != currentOrder.getOrderID()) {
						Customer customer = new Customer(customerID, lastName, firstName, address, city, state, zipcode, phone, email, customerDate);
						currentOrder = new Order(orderID, customer, total, orderDate);
					} 

					currentOrder.addOrderItem(new OrderItem(orderID, item, quantity));
					
					ret.add(currentOrder);
				}
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			closeConnection();
		}
        
		return ret;
	}
	
    private static Statement getStatement() throws SQLException {
		getConnection();
    	Statement ret = null;
    	if(getInstance().conn != null) {
			ret = getInstance().conn.createStatement();
    	} else {
    		throw new SQLException("Could not get connection to database");
    	}
		
    	return ret;
	}

	/**
     * TEST CASE
     */
    public static void main(String [] args) {
        //String sqlQuery = "SELECT * FROM customer;";
        try {
        	//TEST isCustomer
        	//System.out.println(isCustomer(new Customer(1)));
        	
        	//TEST getItems
        	/*ArrayList<Item> items = getItems();
        	for(Item i : items) {
        		System.out.println(i.getItemID());
        	}*/
        	
        	//TEST createCustomer
        	//Customer c = new Customer("first", "last", "123 fake street", "cityville", "state", "12342", "(432) - 523 - 4567", "email@gmail.com");
        	//Customer c2 = createCustomer(c);
        	//System.out.println(c2.getCustomerID());
        	
        	//TEST createOrder
        	//Order order = new Order(c2.getID(), 100);
        	//ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        	//for(int i = 0; i < items.size(); i++) {
        	//	orderItems.add(new OrderItem(items.get(i), i + 1));
        	//}
        	//order.setOrderItems(orderItems);
        	//createOrder(order);
        	
        	//TEST getSuppliers
        	/*ArrayList<Supplier> suppliers = getSuppliers();
        	Iterator<Supplier> iter = suppliers.iterator();
        	while(iter.hasNext()) {
        		Supplier s = iter.next();
        		System.out.println(s.getName());
        		ArrayList<Item> items = s.getItems();
        		for(int i = 0; i < items.size(); i++) {
            		System.out.println("\t" + items.get(i));
        		}
        	}*/
        	
        	//TEST getOrdersToday
        	/*ArrayList<Order> orders = getOrdersToday();
        	Iterator<Order> iter = orders.iterator();
        	while(iter.hasNext()) {
        		Order o = iter.next();
        		System.out.println("ORDER ID: " + o.getOrderID());
        		System.out.println("\tCREATED: " + o.getCreated() + " :: ORDERED BY: " + o.getCustomer().getLastName() + " :: TOTAL: " + o.getTotal());
        		ArrayList<OrderItem> orderItems = o.getOrderItems();
        		for(int i = 0; i < orderItems.size(); i++) {
        			OrderItem orderItem = orderItems.get(i);
            		System.out.println("\t\t" + orderItem.getQuantity() + " - "+ orderItem.getItem().getItemName());
        		}
        	}*/
        	
        	//TEST getCustomersToday
        	ArrayList<Customer> customers = getCustomersToday();
        	Iterator<Customer> iter = customers.iterator();
        	while(iter.hasNext()) {
        		Customer c = iter.next();
        		System.out.println(c.getLastName() + ", " + c.getFirstName() + " :: CREATED: " + c.getCreated());
        		
        	}
        	
        } catch(Exception ex) {
            ex.printStackTrace();
        }	
    }
}

