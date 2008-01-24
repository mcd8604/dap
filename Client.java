/*
 * Client.java
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Client.  Acts as a Producer and Subscriber.
 * 
 * @author Adam Strong and Mitalee Mixit
 */
public class Client implements ActionListener {

	private ClientProducer producer;
	private ClientSubscriber subscriber;
	private ClientGUI gui;
	public String cookieID;
	
	/**
	 * Initialize and display the GUI
	 */
	public Client() {
		this.cookieID = CookieID.createCookieID();
		
		//listen to topic for server messages
		subscriber = new ClientSubscriber();
		subscriber.getMessages(this);
		
		gui = new ClientGUI(this);

		producer = new ClientProducer();
		
		System.out.println("Client created, requesting items from server...");
		
		//Ask server for list of items - used later
		producer.sendMessage(null, Actions.GET_ITEMS, this.cookieID);
	}
	
	/**
	 * Listens for ActionEvents.  Main purpose is
	 * to send a query to the server.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("existing customer submit")) {
			//If existing, ask Server if customer exists
			int id = gui.getCustomerID();
			producer.sendMessage(id, Actions.IS_CUSTOMER, this.cookieID);
		} else if (e.getActionCommand().equals("new customer submit")) {
			if (gui.validateCustomer()) {
				//ask Server to create customer
				Customer customer = gui.getCustomer();
				producer.sendMessage(customer, Actions.CREATE_CUSTOMER, this.cookieID);
			}
		}
//		} else if (e.getActionCommand().equals("submit order")) {
//			//TODO possibly move this based on Mitalee's answer
//			if (gui.validateOrder()) {
//				Order order = gui.getOrder();
//				producer.sendMessage(order, Actions.CREATE_ORDER, this.cookieID);
//			}
//		}
	}

	public void isCustomer_Result(boolean isCustomer) {
		// TODO Auto-generated method stub
		if(isCustomer) {
			//ID is valid, save ID
			//Continue processing order
			if (gui.validateOrder(false)) {
				Order order = gui.getOrder();
				producer.sendMessage(order, Actions.CREATE_ORDER, this.cookieID);
			}
		} else {
			//ID is invalid, display message
			gui.displayMessage("Invalid Customer ID, Please try again.");
		}
	}

	public void createCustomer_Result(Customer customer) {
		gui.displayMessage("Customer created successfully.");
		
		//Continue processing order
		if (gui.validateOrder(true)) {
			Order order = gui.getOrder(customer.getCustomerID());
			producer.sendMessage(order, Actions.CREATE_ORDER, this.cookieID);
		}
	}

	public void getItems_Result(ArrayList<Item> items) {
		gui.populateItems(items);
	}

	public void createOrder_Result(Order order) {
		// TODO Need to know if success or failure and report to user
		if(order.getCompleted()) {
			gui.displayMessage("Order created successfully.");
		} else {
			//display error
		}
	}
	
	
	/**
	 * Run the client
	 * 
	 * @param args - not used
	 */
	public static void main(String [] args) {
		// Create and display GUI
		new Client();		
	}
}
