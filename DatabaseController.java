

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
        	getInstance().getConnection();
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
			getInstance().closeConnection();
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
    private void getConnection() throws SQLException {      
        try {
            Class.forName(DATASOURCE_DRIVER_CLASS);
            conn = DriverManager.getConnection( DATASOURCE_URL, USERNAME, PASSWORD );
        } catch (SQLException se) {
            System.out.println("DATABASE CONNECTION FAILED: " + se.getMessage());
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    /**
     * Closes the connection for the instance
     */
    private void closeConnection() {
        try {
            if( (conn!= null) && (!conn.isClosed()) )
                conn.close();
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
        	getInstance().getConnection();
        	synchronized(stmtLock) {
        		// *** do we need PreparedStatement?
	            statement = getInstance().conn.createStatement(); 
	            sqlQuery = "SELECT * FROM Customer WHERE ID='" + c.getCustomerID() + "';";
	            statement.execute(sqlQuery);
	            rs = statement.getResultSet();
	            
	            // TODO Evaluate results
	            
        	}        	
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			getInstance().closeConnection();
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
    	Statement queryStatement = null;
    	String sqlQuery = null;

        try {
        	getInstance().getConnection();
        	synchronized(stmtLock) {
        		insertStatement = getInstance().conn.createStatement();  

	            // TODO finish insert values
	            sqlInsert = "INSERT values() into customer";
	            
	            //if update
            	if(insertStatement.execute(sqlInsert)) {
            		queryStatement = getInstance().conn.createStatement(); 
            		
                	//TODO finish query to get recently created customer ID
            		sqlQuery = "";
            		
            		queryStatement.execute(sqlQuery);
            		
            		c.setCustomerID(0);
                	ret = c;
            	}
        	}
        } catch(SQLException e){
			e.printStackTrace();
        } finally {
			getInstance().closeConnection();
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
    	String sqlQuery = "SELECT * FROM item";
    	try {
			ArrayList<String> a = queryToArrayList(sqlQuery);
			Iterator<String> iter = a.iterator();
			while(iter.hasNext()) {
				String i = iter.next();
				System.out.println(i);
				
				// TODO Parse Strings into items
				Item item = new Item(0, "", "", 0, 0);
				ret.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    	//insert order
    	//get orderid
    	//insert all orderitems
    	return null;
    }

    /**
     * TEST CASE
     */
    public static void main(String [] args) {
        String sqlQuery = "SELECT * FROM customer;";
        try {
	          ArrayList<String> ret = DatabaseController.queryToArrayList(sqlQuery); 
	          System.out.println("Results:");
	          for (String s : ret) {
	        	  System.out.println(s);
	          }
        } catch(Exception ex) {
            ex.printStackTrace();
        }	
    }
}

