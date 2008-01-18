/*
 * ServerPublisher.java
 *
 * This class produce object messages that will be consumed by the ClientConsumer object
 *
 * Created on February 10, 2005, 9:48 AM
 * Modified on January 17, 2008, 7:14 PM
 */

/**
 *
 * @author  kjb
 * @modified mcd
 */

import java.io.Serializable;

import javax.naming.*;
import javax.jms.*;

public class ServerPublisher {

	private static final String TOPIC_NAME = "jms/Topic";
	private static final String CONN_FACTORY = "jms/TopicConnection";
    
    private Context jndiContext; // JNDI context for looking up names
    private TopicConnectionFactory cf;
    private Topic dest;
    
    /** Creates a new instance of Producer */
    public ServerPublisher() {
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
           dest = (Topic)jndiContext.lookup(TOPIC_NAME);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    /** create the connection, session, and send messages */
    public void sendMessage(Serializable object, int action) {
        try {
            // create the connection
            TopicConnection conn = cf.createTopicConnection();
        
            // create the session
            TopicSession sess = conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
        
            // create a producer
            TopicPublisher pub = sess.createPublisher(dest);
            
            // create an object message
            ObjectMessage om = sess.createObjectMessage(object);
            
            // set the action for the message
            om.setIntProperty(Actions.ACTION, action);
            
            // publish the message
            pub.publish(om);
            
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
     * Start the ServerPublisher
     */
    public static void main(String [] args){
        
        // create the Producer
        //ServerPublisher prd = new ServerPublisher();
        
        // send the messages
        //prd.sendMessage();
    }
}
