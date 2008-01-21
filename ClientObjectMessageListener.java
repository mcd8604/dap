/*
 * ClientObjectMessageListener.java
 */

import java.io.Serializable;
import java.util.ArrayList;

import javax.jms.*;

/**
 * ClientObjectMessageListener
 * 
 * @author Adam Strong
 */
public class ClientObjectMessageListener implements MessageListener {
	
	public ClientObjectMessageListener() {}
	
	public void onMessage(Message m) {
		if(m != null) {
			ObjectMessage message = (ObjectMessage)m;
        	
        	try {
	        	// 1 - determine type of message
        		int action = message.getIntProperty(Actions.ACTION);

	        	// 2 - process message result
	        	switch(action) {
	        		case Actions.IS_CUSTOMER:
	                    boolean isCustomer = ((Boolean)message.getObject()).booleanValue();
	                    // pass isCustomer back to Client
	        			break;
	        		case Actions.CREATE_CUSTOMER:
	        			Customer customer = (Customer)message.getObject();
	        			// pass customer back to Client
	        			break;
	        		case Actions.GET_ITEMS:
	        			ArrayList<Item> items = (ArrayList<Item>)message.getObject();
	        			// pass items back to Client
	        			break;
	        		case Actions.CREATE_ORDER:
	        			Order order = (Order)message.getObject();
	        			// pass order back to Client
	        			break;
	        	}
			} catch (JMSException e) {
				e.printStackTrace();
			}
        }
	}

}
