/**
 * QueryProcessor.java
 *
 */

package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Remote Server
 */
public class QueryProcessor extends java.rmi.server.UnicastRemoteObject implements QueryProcessorInterface {
	
    private static final long serialVersionUID = -5882330627279178368L;
    
	//database startup script
    //private static String SQL_SCRIPT = "database.sql";

	public QueryProcessor() throws java.rmi.RemoteException {
    }
	
	/**
	 * Method called by Remote Objects to process a string sql query.
	 * 
	 * @param sqlQuery the query to process
	 * @return ArrayList<String> recordset where each record is a string
	 */
    public ArrayList<String> processQuery(String sqlQuery) throws RemoteException, SQLException {
        ArrayList<String> ret = null;
      
        try {  
    		System.out.println("Processing Query: " + sqlQuery);
    		ret = DatabaseController.queryToArrayList(sqlQuery);
    		if(ret.size() <= 0) {
    			ret.add("No record found"); 
    		}
    		System.out.println("Query Result:");
        	for (int i = 0; i < ret.size(); i++) {
        		System.out.println("\t" + ret.get(i));
        	}
        } catch(SQLException e){
            throw new SQLException(e.getMessage());
        }
        
        return ret;
    }
	
    /**
     * Start the Remote Server
     */
    public static void main(String [] args) {
        try {
        	System.out.println("Starting server");
        	//loadStartupSQLScript();
        	QueryProcessorInterface qp = new QueryProcessor();
            Naming.rebind("QueryProcessor", qp);
        } catch(Exception ex) {
            ex.printStackTrace();
        }	
    }

    /**
     * Reads in default SQL script and executes it
     */
    /*private static void loadStartupSQLScript() throws SQLException {
    	System.out.println("LOADING STARTUP SQL SCRIPT");
    	BufferedReader br = null;
    	String sqlScript = null;
    	
    	try {
    		FileInputStream fis = new FileInputStream(SQL_SCRIPT);
    		InputStreamReader isr = new InputStreamReader(fis);
    		br = new BufferedReader(isr);
    		
			sqlScript = new String();
			while(br.ready()) {
				String nextLine = br.readLine();
				if(nextLine.length() > 0 && nextLine.charAt(0) != '-') {
					sqlScript += nextLine + "\n";					
				}
			}
			DatabaseController.queryToArrayList(sqlScript);
			
    	} catch( FileNotFoundException fnfe ) {
    		System.out.println("MISSING FILE: " + SQL_SCRIPT);
    		fnfe.printStackTrace();
    	} catch( IOException ioe ) {
    		ioe.printStackTrace();    			
		} catch (SQLException e) {
            throw new SQLException(e.getMessage());
		}
    }*/
}
