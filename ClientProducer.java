/*
 * Producer.java
 *
 */

import java.io.Serializable;

import javax.naming.*;
import javax.jms.*;

/**
 * ClientProducer
 * 
 * @author  Adam Strong
 */
public class ClientProducer {
    
    private Context jndiContext; // JNDI context for looking up names
    private ConnectionFactory cf;
    private Destination dest;
    
    /** Creates a new instance of Producer */
    public ClientProducer() {
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
           cf = (ConnectionFactory)jndiContext.lookup(Server.QUEUE_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try{
           dest = (Destination)jndiContext.lookup(Server.QUEUE_DEST);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
		System.out.println("ClientProducer initialized");
    }
    
    /** create the connection, session, and send messages 
     * @param cookieID */
    public void sendMessage(Serializable object, int action, String cookieID) {
        try {
            // create the connection
            Connection conn = cf.createConnection();
        
            // create the session
            Session sess = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        
            // create a producer
            MessageProducer prod = sess.createProducer(dest);
        
            // create an object message
            ObjectMessage message = sess.createObjectMessage(object);
            
            // set the action for the message
            message.setIntProperty(Actions.ACTION, action);
            
            // set the cookieID for the message
            message.setStringProperty(CookieID.COOKIE_ID, cookieID);
            System.out.println(cookieID);
            
            prod.send(message);
			System.out.println("CLIENT SENT MESSAGE");
        
            // send an empty message to indicate nothing more is coming
            //prod.send(sess.createMessage());
        
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
    
//    /**
//     * Start the Producer, getting the destination from the command line args
//     */
//    public static void main(String [] args){
//        // make sure there an argument
//        if(args.length < 1){
//            System.out.println("You must specify a destination.");
//            System.exit(1);
//        }
//        
//        // create the Producer
//        Producer prd = new Producer(args[0]);
//        
//        // send the messages
//        prd.sendMessages();
//    }
}
