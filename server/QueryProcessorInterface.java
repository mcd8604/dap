package server;

import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryProcessorInterface extends Remote {
	
    /**
     * Process a query String
     *
     * @return Query result as an ArrayList
     */
    public ArrayList<String> processQuery(String query)throws RemoteException, SQLException;
}