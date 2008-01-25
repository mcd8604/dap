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
public class ClientSubscriber {;
    
    private Context jndiContext; // JNDI context for looking up names
    private TopicConnectionFactory cf;
    private Topic dest;
    
    /** Creates a new instance of Consumer */
    public ClientSubscriber() {
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
           cf = (TopicConnectionFactory)jndiContext.lookup(Server.UPDATER_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try{
           dest = (Topic)jndiContext.lookup(Server.UPDATER_DEST);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    /** get messages from the queue */
    public void getMessages(Client p_client) {
    	boolean running = true;
    	
		try {
			// create the connection
			TopicConnection conn = cf.createTopicConnection();
			
			// create the session
			TopicSession sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// create a producer
			TopicSubscriber sub = sess.createSubscriber(dest);
			
			// Set up the listener
			ClientObjectMessageListener cl = new ClientObjectMessageListener(p_client);
			sub.setMessageListener(cl);
			
			// start receiving messages
			conn.start();
			System.out.println("CLIENT SUBSCRIBED TO TOPIC: " + Server.UPDATER_DEST);
			
			/*while(running) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//close everything down
			if (conn != null) {
				conn.close();
			}*/
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
