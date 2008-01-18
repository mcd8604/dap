/*
 * ServerConsumer.java
 *
 * Created on January 17, 2008, 9:48 AM
 */

/**
 *
 * @author  mcd
 */

import javax.naming.*;
import javax.jms.*;

public class ServerConsumer {
     private final String QUEUE_NAME = "jms/Queue";
     private final String CONN_FACTORY = "jms/QueueConnection";
    
    private Context jndiContext; // JNDI context for looking up names
    private ConnectionFactory cf;
    private Destination dest;
    
    /** Creates a new instance of ServerConsumer*/
    public ServerConsumer(String destName) {
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
           dest = (Destination)jndiContext.lookup(destName);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    /** get messages from the queue */
    public void getMessages() {
         try {
            // create the connection
            Connection conn = cf.createConnection();
        
            // create the session
            Session sess = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        
            // create a consumer
            MessageConsumer cons = sess.createConsumer(dest);
                    
            // set the message listener
            cons.setMessageListener(new ServerObjectMessageListener());
        
            // start receiving messages
            conn.start();
            
            //close everything down
            if(conn != null) {
                conn.close();
            }
        }
        catch(JMSException je) {
            System.out.println("Unable to close the connection: " + je.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Start the ServerConsumer, getting the destination from the command line args
     */
    public static void main(String [] args){
        // make sure there an argument
        if(args.length < 1){
            System.out.println("You must specify a destination.");
            System.exit(1);
        }
        
        // create the ServerConsumer
        ServerConsumer scon = new ServerConsumer(args[0]);
        
        // continually check for messages
        while(true) {
        	scon.getMessages();
        }
    }
}
