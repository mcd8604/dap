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
	private int custID;
	public String cookieID;
	
	/**
	 * Initialize and display the GUI
	 */
	public Client() {
		this.cookieID = CookieID.createCookieID();
		this.custID = -1;

		//listen to topic for server messages
		subscriber = new ClientSubscriber();
		subscriber.getMessages(this);
		
		gui = new ClientGUI(this);

		producer = new ClientProducer();
		
		System.out.println("Client created, requesting items from server...");
		
		//Ask server for list of items - used later
		producer.sendMessage(null, Actions.GET_ITEMS, this.cookieID);
	}
	
	public int getCustID() {
		return custID;
	}
	
	/**
	 * Listens for ActionEvents.  Main purpose is
	 * to send a query to the server.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("existing customer")) {
			//If existing, ask Server if customer exists
			int id = gui.getCustomerID();
			producer.sendMessage(id, Actions.IS_CUSTOMER, this.cookieID);
		} else if (e.getActionCommand().equals("new customer")) {
			if (gui.validateCustomer()) {
				//ask Server to create customer
				Customer customer = gui.getCustomer();
				producer.sendMessage(customer, Actions.CREATE_CUSTOMER, this.cookieID);
			}
		} else if (e.getActionCommand().equals("submit order")) {
			//TODO possibly move this based on Mitalee's answer
			if (gui.validateOrder()) {
				Order order = gui.getOrder();
				producer.sendMessage(order, Actions.CREATE_ORDER, this.cookieID);
			}
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

	public void isCustomer_Result(boolean isCustomer) {
		// TODO Auto-generated method stub
		if(isCustomer) {
			//ID is valid, save ID
			custID = gui.getCustomerID();
		} else {
			//ID is invalid, display message
			gui.displayMessage("Invalid Customer ID, Please try again.");
		}
	}

	public void createCustomer_Result(Customer customer) {
		custID = customer.getCustomerID();
		gui.displayMessage("Customer created successfully.");
		
		//TODO some switch allow the user to now create order?
	}

	public void getItems_Result(ArrayList<Item> items) {
		gui.populateItems(items);
	}

	public void createOrder_Result(Order order) {
		// TODO Auto-generated method stub
		gui.displayMessage("Order created successfully.");
	}
}
