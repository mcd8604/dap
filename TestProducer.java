/*
 * Producer.java
 *
 * This class produce text messages that will be consumed by the Consumer object
 *
 * Created on February 10, 2005, 9:48 AM
 */

/**
 *
 * @author  kjb
 */

import javax.naming.*;
import javax.jms.*;

public class TestProducer {
    private final int NUM_MSGS = 10; // generate N messages
    private final String QUEUE_NAME = "jms/Queue";
    private final String CONN_FACTORY = "jms/QueueConnection";
    
    private Context jndiContext; // JNDI context for looking up names
    private ConnectionFactory cf;
    private Destination dest;
    
    /** Creates a new instance of Producer */
    public TestProducer(String destName) {
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
    
    /** create the connection, session, and send messages */
    public void sendMessages() {
        try {
            // create the connection
            Connection conn = cf.createConnection();
        
            // create the session
            Session sess = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        
            // create a producer
            MessageProducer prod = sess.createProducer(dest);
            
            Customer cust = new Customer(0);
            ObjectMessage msg = sess.createObjectMessage(cust);
            msg.setIntProperty(Actions.ACTION, Actions.IS_CUSTOMER);
            prod.send(msg);
            
            System.out.println("OBJECT MESSAGE SENT - IS_CUSTOMER: 0");
            
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
    
    /**
     * Start the Producer, getting the destination from the command line args
     */
    public static void main(String [] args){
        // make sure there an argument
        if(args.length < 1){
            System.out.println("You must specify a destination.");
            System.exit(1);
        }
        
        // create the Producer
        TestProducer prd = new TestProducer(args[0]);
        
        // send the messages
        prd.sendMessages();
    }
}
