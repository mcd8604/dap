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

public class ServerSubscriber {    
    private Context jndiContext; // JNDI context for looking up names
    private TopicConnectionFactory cf;
    private Topic dest;
    
    private TopicConnection conn;
    private TopicSession sess;
    private MessageConsumer cons;
    
    private ServerPublisher spub;
    
    /** Creates a new instance of ServerConsumer
     * @param p_sPub */
    public ServerSubscriber() {
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
           cf = (TopicConnectionFactory)jndiContext.lookup(Updater.CLIENT_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try{
           dest = (Topic)jndiContext.lookup(Updater.CLIENT_DEST);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    public void start(ServerPublisher spub) {
        // create the connection
        try {
			conn = cf.createTopicConnection();

			// create the session
			sess = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// create a producer
			cons = sess.createSubscriber(dest);
			
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
}
