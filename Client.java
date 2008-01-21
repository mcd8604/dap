/*
 * Client.java
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.BorderLayout;

import server.*;

/**
 * Client.  Acts as a Producer and Subscriber.
 * 
 * @author Adam Strong and Mitalee Mixit
 */
public class Client implements ActionListener {
	
	//GENERAL IDEA:
	//Ask server for list of items - used later
	//Check if existing customer or new (in UI)
	//If existing, ask Server if customer exists - isCustomer(int id)
		//If exists, display order screen
		//If does not exist, re-prompt for existing versus new
	//If new
		//collect customer info through UI
		//validate input
		//ask Server to create customer - createCustomer(Customer c)
	//Display order screen - populated with items
	//On user submit, send Order to Server via Queue
	//Get response from Server via Topic
	
	private final String DESTINATION = "";
	private ClientProducer producer;
	
	/**
	 * Initialize and display the GUI
	 */
	public Client() {
		displayWelcome();
		
		producer = new ClientProducer(DESTINATION);
		
		//Ask server for list of items - used later
		ArrayList<Object> items = getItems();
		
		System.out.println("Client created");
	}
	
	/**
	 * Displayed to collect customer information.
	 */
	public void displayWelcome() {
		// simple GUI for allowing the user to decide if they are 
		// existing or new customer
	}
	
	/**
	 * Displayed to collect customer information.
	 */
	public void newCustomer() {
		// GUI that gathers new customer info
	}
	
	/**
	 * Displayed to create Order.
	 */
	public void createOrder() {
		// GUI to build order
	}
	
	/**
	 * Called to request the list of Order Items 
	 * from the Server
	 * 
	 * @return list of items
	 */
	public ArrayList<Object> getItems() {
		//Produce RequestMessage to Queue
		producer.sendMessage(null, Actions.GET_ITEMS);
		
		//Receive ResponseMessage from Topic
		
		
		return new ArrayList<Object>();
	}
	
	/**
	 * Listens for ActionEvents.  Main purpose is
	 * to send a query to the server.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("existing customer")) {
			//If existing, ask Server if customer exists
			//Produce RequestMessage to Queue
			int id = 0;
			producer.sendMessage(id, Actions.IS_CUSTOMER);
			
			//Receive ResponseMessage from Topic
				//If exists, display order screen
					//createOrder();
				//If does not exist, re-prompt for existing vs new
					//displayWelcome();
			
		} else if (e.getActionCommand().equals("new customer")) {
			//If new
				//collect customer info through UI
				//validate input
				//ask Server to create customer - createCustomer(Customer c)
					//Produce RequestMessage to Queue
					Customer customer = null;
					producer.sendMessage(customer, Actions.CREATE_CUSTOMER);
			
					//Receive ResponseMessage from Topic
				//display order screen
				//createOrder();
		} else if (e.getActionCommand().equals("submit order")) {
			//validate order
			
			//Send Order to Server via Queue
			Order order = null;
			producer.sendMessage(order, Actions.CREATE_ORDER);
			
			//Get response from Server via Topic
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
