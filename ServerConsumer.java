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
        
            // start receiving messages
            conn.start();
        
            // loop to create and send the messages
            while(true) {
                Message m = cons.receive(); // block until a message appears
                if(m != null) {

                    if(m instanceof CustomerMessage){

                        CustomerMessage msg = (CustomerMessage)m;
                        System.out.println("ServerConsumer received " + msg.getText());

                        //DatabaseController.isCustomer(msg.getCustomer());
                        //DatabaseController.createCustomer(msg.getCustomer());

                    } else if(m instanceof ItemMessage){

                        ItemMessage msg = (ItemMessage)m;
                        System.out.println("ServerConsumer received " + msg.getText());

                        //DatabaseController.getItems();

                    } else if(m instanceof OrderMessage){

                        OrderMessage msg = (OrderMessage)m;
                        System.out.println("ServerConsumer received " + msg.getText());

                        //DatabaseController.createOrder(msg.getOrder());
                    }

                }
                else {
                    break;
                }
            }
            
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
        
        // create the Producer
        Consumer prd = new Consumer(args[0]);
        
        // send the messages
        prd.getMessages();
    }
}