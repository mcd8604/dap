import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


public class ObjectMessageListener implements MessageListener {
	
	public ObjectMessageListener() {
		
	}

	@Override
	public void onMessage(Message m) {
        if(m != null) {
        	
        	// 1 - determine type of message
        	// 2 - process message using DatabaseController
        	// 3 - send the result back using a topic
        	
            /*if(m instanceof CustomerMessage){

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
            }*/
        }
	}

}
