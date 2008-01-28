/*
 * ClientConsumer.java
 *
 */

import javax.naming.*;
import javax.jms.*;

/**
 * ClientConsumer
 * 
 * @author  Adam Strong
 */
public class ClientConsumer {;
    
    private Context jndiContext; // JNDI context for looking up names
    private QueueConnectionFactory cf;
    private Destination dest;
    
    /** Creates a new instance of ClientConsumer */
    public ClientConsumer() {
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
           cf = (QueueConnectionFactory)jndiContext.lookup(Updater.UPDATER_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try{
           dest = (Destination)jndiContext.lookup(Updater.UPDATER_DEST);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    /** get messages from the queue */
    public void getMessages(Client p_client) {
    	//boolean running = true;
    	
		try {
			// create the connection
			QueueConnection conn = cf.createQueueConnection();
			
			// create the session
			QueueSession sess = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// create a producer
			MessageConsumer cons = sess.createConsumer(dest);
			
			// Set up the listener
			ClientObjectMessageListener cl = new ClientObjectMessageListener(p_client);
			cons.setMessageListener(cl);
			
			// start receiving messages
			conn.start();
			System.out.println("CLIENT SUBSCRIBED TO QUEUE: " + Updater.UPDATER_DEST);
			
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
