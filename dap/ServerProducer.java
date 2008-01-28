/*
 * ServerProducer.java
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

public class ServerProducer {
    
    private Context jndiContext; // JNDI context for looking up names
    private QueueConnectionFactory cf;
    private Destination dest;
    
    private QueueConnection conn;
    private QueueSession sess;
    private MessageProducer pro;
    
    /** Creates a new instance of ServerProducer */
    public ServerProducer() {
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
           dest = (Topic)jndiContext.lookup(Updater.UPDATER_DEST);
           
           // create the connection
           conn = cf.createQueueConnection();
       
           // create the session
           sess = conn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
       
           // create a producer
           pro = sess.createProducer(dest);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
        System.out.println("SERVER PRODUCER STARTED");
    }
    
    /** create the connection, session, and send messages 
     * @param cookieID */
    public void sendMessage(Serializable object, int action, String cookieID) {
        try {            
            // create an object message
            ObjectMessage om = sess.createObjectMessage(object);
            
            // set the action for the message
            om.setIntProperty(Actions.ACTION, action);
            
            // set the cookieID for the message
            om.setStringProperty(CookieID.COOKIE_ID, cookieID);
            
            // publish the message
            pro.send(om);
            System.out.println("SERVER PRODUCER SENT MESSAGE");
        }
        catch(JMSException je) {
            System.out.println("Unable to close the connection: " + je.getMessage());
            System.exit(1);
        }
    }
    
    public void close() {
        //close everything down
        if(conn != null) {
            try {
				conn.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    /**
     * Start the ServerPublisher
     */
    public static void main(String [] args){
        
        // create the Producer
        //ServerProducer prd = new ServerProducer();
        
        // send the messages
        //prd.sendMessage();
    }
}
