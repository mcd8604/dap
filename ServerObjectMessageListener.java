import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


public class ServerObjectMessageListener implements MessageListener {
	
	private ServerPublisher spub;
	
	public ServerObjectMessageListener(ServerPublisher p_spub) {
        // create the ServerPublisher
        spub = p_spub;
	}

	public void onMessage(Message m) {
		if(m != null) {
			System.out.println("MESSAGE RECEIVED: " + m.toString());

			ObjectMessage om = (ObjectMessage)m;
			
			String cookieID = null;
			try {
				cookieID = om.getStringProperty(CookieID.COOKIE_ID);
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        	Serializable result = null;
        	int action;
			try {
	        	// 1 - determine type of message
				action = m.getIntProperty(Actions.ACTION);

	        	// 2 - process message using DatabaseController
	        	switch(action) {
	        		case Actions.IS_CUSTOMER:
	                    result = DatabaseController.isCustomer((Customer)om.getObject());
	        			break;
	        		case Actions.CREATE_CUSTOMER:
	        			result = DatabaseController.createCustomer((Customer)om.getObject());
	        			break;
	        		case Actions.GET_ITEMS:
	        			result = DatabaseController.getItems();
	        			break;
	        		case Actions.CREATE_ORDER:
	        			result = DatabaseController.createOrder((Order)om.getObject());
	        			break;
	        	}
				
	        	// 3 - send the result back using a ServerPublisher
	        	if(result != null) {
		            spub.sendMessage(result, action, cookieID);
	        	}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
