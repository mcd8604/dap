/*
 * ServerConsumer.java
 *
 * Created on January 17, 2008, 9:48 AM
 */

/**
 *
 * @author  mcd
 */

import java.util.Scanner;

import javax.naming.*;
import javax.jms.*;

public class ServerConsumer {
     private final String QUEUE_NAME = "jms/Queue";
     private final String CONN_FACTORY = "jms/QueueConnection";
    
    private Context jndiContext; // JNDI context for looking up names
    private ConnectionFactory cf;
    private Destination dest;
    
    private Connection conn;
    private Session sess;
    private MessageConsumer cons;
    
    private ServerPublisher spub;
    
    /** Creates a new instance of ServerConsumer*/
    public ServerConsumer() {
        // get a JNDI naming context
        try {
            jndiContext = new InitialContext();
        }
        catch(NamingException ne){
            System.out.println("Unable to get a JNDI context for naming");
            System.exit(1);
        }
        
        // set up a ConnectionFactory and destination
        try {
           cf = (ConnectionFactory)jndiContext.lookup(CONN_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try{
           dest = (Destination)jndiContext.lookup(QUEUE_NAME);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    public void start() {
        // create the connection
        try {
			conn = cf.createConnection();
    
	        // create the session
	        sess = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
	    
	        // create a consumer (queue receiver)
	        cons = sess.createConsumer(dest);
	        
	        // create a publisher (topic sender)
	        spub = new ServerPublisher();
	        
	        // set the message listener
	        cons.setMessageListener(new ServerObjectMessageListener(spub));
	    
	        // start receiving messages
	        conn.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void close() {
    	//close everything down
        if(conn != null) {
        	try {
        		spub.close();
				conn.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    /**
     * Start the ServerConsumer, getting the destination from the command line args
     */
    public static void main(String [] args){        
        // create the ServerConsumer
        ServerConsumer scon = new ServerConsumer();
        scon.start();
        
        //loop this until user enters input
        Scanner s = new Scanner(System.in);
        System.out.println("To exit, type 'exit' then hit enter.");
        
        while(!s.hasNext()) {
        }
        
        scon.close();
    }
}
