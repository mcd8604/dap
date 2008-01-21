/*
 * Subscriber.java
 *
 */

import javax.naming.*;
import javax.jms.*;

/**
 * ClientSubscriber
 * 
 * @author  Adam Strong
 */
public class ClientSubscriber {
	private static final String TOPIC_NAME = "jms/Topic";
	private final String CONN_FACTORY = "jms/TopicConnection";
    
    private Context jndiContext; // JNDI context for looking up names
    private TopicConnectionFactory cf;
    private Topic dest;
    
    /** Creates a new instance of Consumer */
    public ClientSubscriber(String destName) {
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
           cf = (TopicConnectionFactory)jndiContext.lookup(CONN_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try{
           dest = (Topic)jndiContext.lookup(destName);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    /** get messages from the queue */
    public void getMessages() {
    	boolean running = true;
    	
		try {
			// create the connection
			TopicConnection conn = cf.createTopicConnection();
			
			// create the session
			TopicSession sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// create a producer
			TopicSubscriber sub = sess.createSubscriber(dest);
			
			// Set up the listener
			ClientObjectMessageListener cl = new ClientObjectMessageListener();
			sub.setMessageListener(cl);
			
			// start receiving messages
			conn.start();
			
			while(running) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//close everything down
			if (conn != null) {
				conn.close();
			}
		}
		catch(JMSException je) {
		    System.out.println("Unable to close the connection: " + je.getMessage());
		    System.exit(1);
		}
    }
    
//    /**
//     * Start the Consumer, getting the destination from the command line args
//     */
//    public static void main(String [] args){
//        
//        // create the Producer
//        Subscriber prd = new Subscriber(TOPIC_NAME);
//        
//        // send the messages
//        prd.getMessages();
//    }
}
