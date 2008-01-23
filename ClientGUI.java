import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
/**
 * Client.java
 *
 * Created on January 18, 2008, 7:49 PM
 */
import java.util.ArrayList;
 
 
 /**
 *
 * @author  Mitalee Dixit
 */
 
public class ClientGUI
{
	private Client client;
	private final int NUM_PANELS = 10;
	
	//all the private variables initalized
	private JPanel holdsAll;
	private JTabbedPane tabs;
	private JPanel newCustomerPanel;
	private JPanel existingCustomer;
	private JPanel customerInfo;
	private JPanel customerInfo2;
	private JPanel submitPanel;
	private JPanel submitPanel2;
	private JButton submit;
	private JButton submit2;
	private JLabel firstNameLabel;
	private JLabel middleInitialLabel;
	private JLabel lastNameLabel;
	private JTextField firstNameInput;
	private JTextField middleNameInput;
	private JTextField lastNameInput;
	private JLabel streetAddressLabel;
	private JTextField streetAddressInput;
	private JLabel aptLabel;
	private JTextField aptInput;
	private JLabel stateLabel;
	private JComboBox stateInput;
	private JLabel cityLabel;
	private JTextField cityInput;
	private JLabel zipLabel;
	private JTextField zipInput;
	private JLabel phoneLabel;
	private JTextField phoneInput;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JPanel orderPanel;
	private JPanel orderPanel2;
	private JPanel nameLabelPanel;
	private JPanel nameInputPanel;
	private JPanel addressPanel;
	private JPanel addressInputPanel;
	private JPanel locationPanel;
	private JPanel locationInputPanel;
	private JPanel optionalPanel;
	private JPanel optionalInputPanel;
	private JPanel infoPanel;
	private JLabel infoLabel;
	private JLabel qtyLabel;
	private JLabel itemLabel;
	private JLabel qtyLabel2;
	private JLabel itemLabel2;
	
	private ArrayList<JTextField> quantities;
	private ArrayList<JComboBox> orderitems;
	private ArrayList<JTextField> quantities2;
	private ArrayList<JComboBox> orderitems2;
	private JPanel orderLabelPanel;
	private JPanel orderLabelPanel2;
	private JLabel existingLabel;
	private JTextField existingInput;
	private JPanel existingPanel;
	
	/** 
	 * Creates new form NewJFrame 
	 */
	public ClientGUI(Client p_client) {
		initComponents();
		client = p_client;
	}
	
	public ClientGUI() {
		initComponents();
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 */
	private void initComponents() {
		//Create and set up the window.
		JFrame clientFrame = new JFrame("Purchase Item");
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.setSize(900, 500);

		//creates the panels and tabbed panes
		holdsAll = new JPanel();
		tabs = new JTabbedPane();
		newCustomerPanel = new JPanel();
		existingCustomer = new JPanel();
		customerInfo = new JPanel();
		customerInfo2 = new JPanel();
		submitPanel = new JPanel();
		submitPanel2 = new JPanel();
		orderPanel = new JPanel();
		orderPanel2 = new JPanel();
		nameLabelPanel = new JPanel();
		nameInputPanel = new JPanel();
		addressPanel = new JPanel();
		addressInputPanel = new JPanel();
		locationPanel = new JPanel();
		locationInputPanel = new JPanel();
		optionalPanel = new JPanel();
		optionalInputPanel = new JPanel();
		infoPanel = new JPanel();
		

		orderLabelPanel = new JPanel();
		orderLabelPanel2 = new JPanel();
		existingPanel = new JPanel();

		//creates the buttons
		submit = new JButton("Submit");
		submit.addActionListener(client);
		submit.setActionCommand("new customer submit");
		submit2 = new JButton("Submit");
		submit2.addActionListener(client);
		submit2.setActionCommand("existing customer submit");

		//creates all the labels
		firstNameLabel = new JLabel();
		middleInitialLabel = new JLabel();
		lastNameLabel = new JLabel();
		streetAddressLabel = new JLabel();
		aptLabel = new JLabel();
		cityLabel = new JLabel();
		stateLabel = new JLabel();
		zipLabel = new JLabel();
		phoneLabel = new JLabel();
		emailLabel = new JLabel();
		infoLabel = new JLabel();
		qtyLabel = new JLabel();
		itemLabel = new JLabel();
		qtyLabel2 = new JLabel();
		itemLabel2 = new JLabel();
		existingLabel = new JLabel();

		//creates the text fields
		firstNameInput = new JTextField(15);
		middleNameInput = new JTextField(1);
		lastNameInput = new JTextField(20);
		streetAddressInput = new JTextField(30);
		aptInput = new JTextField(4);
		cityInput = new JTextField(8);
		zipInput = new JTextField(5);
		phoneInput = new JTextField(12);
		emailInput = new JTextField(25);
		existingInput = new JTextField(20);

		//creates the state selections
		String[] stateStrings = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT",
				"DE", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS",
				"KY", "LA", "MA", "MD", "MS", "MI", "MN", "MI", "MO", "MT",
				"NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
				"OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VI", "VA", "WA", "WV", "WI", "WY" };

		//creates the combo box for the state input
		stateInput = new JComboBox(stateStrings);

		//sets the texts for the labels
		firstNameLabel.setText("                   First name*");
		middleInitialLabel.setText("             Middle initial*");
		lastNameLabel.setText("             Last name*");
		streetAddressLabel
				.setText("                                           Street Address*");
		aptLabel
				.setText("                                                      APT#");
		cityLabel.setText("                          City*");
		stateLabel.setText("                                    State*");
		zipLabel.setText("                             Zip Code*");
		phoneLabel.setText("                       Phone number");
		emailLabel.setText("                           E-mail");
		infoLabel.setText("* is required information");
		qtyLabel.setText("QTY");
		itemLabel.setText("ITEM");
		qtyLabel2.setText("QTY");
		itemLabel2.setText("ITEM");
		existingLabel.setText("Customer ID");

		//makes holdsAll panel into a borderlayout
		holdsAll.setLayout(new BorderLayout());

		//creates the tabs
		tabs = new JTabbedPane();

		//creates the new customer tab
		tabs.addTab("New Customer", newCustomerPanel);

		//creates the existing customer tab
		tabs.addTab("Existing Customer", existingCustomer);

		//does the panel that creates the new customer
		newCustomerPanel.setLayout(new BorderLayout());

		//does the panel that creates the existingCustomer
		existingCustomer.setLayout(new BorderLayout());

		//sets the size of customerInfo
		customerInfo.setPreferredSize(new Dimension(600, 465));

		//sets the size of customerInfo2
		customerInfo2.setPreferredSize(new Dimension(600, 465));

		//set all the options for namePanel
		nameLabelPanel.setLayout(new GridLayout(1, 3));

		//adds all the items to namePanel
		nameLabelPanel.add(firstNameLabel);
		nameLabelPanel.add(middleInitialLabel);
		nameLabelPanel.add(lastNameLabel);

		//adds all the items to namePanel
		nameInputPanel.add(firstNameInput);
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(middleNameInput);
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(new JPanel());
		nameInputPanel.add(lastNameInput);

		//adds all the items to addressPanel
		addressPanel.setLayout(new GridLayout(1, 2));
		addressPanel.add(streetAddressLabel);
		addressPanel.add(aptLabel);

		//adds all the items to  addressInputPanel
		addressInputPanel.add(streetAddressInput);
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(aptInput);
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());
		addressInputPanel.add(new JPanel());

		//adds all the items to locationPanel
		locationPanel.setLayout(new GridLayout(1, 3));
		locationPanel.add(cityLabel);
		locationPanel.add(stateLabel);
		locationPanel.add(zipLabel);

		//adds all the items to locationInputPanel
		locationInputPanel.add(cityInput);
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(stateInput);
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(new JPanel());
		locationInputPanel.add(zipInput);

		//adds all the items to optionalPanel
		optionalPanel.setLayout(new GridLayout(1, 2));
		optionalPanel.add(phoneLabel);
		optionalPanel.add(emailLabel);

		//adds all the items to optionalInputPanel
		optionalInputPanel.add(phoneInput);
		optionalInputPanel.add(new JPanel());
		optionalInputPanel.add(new JPanel());
		optionalInputPanel.add(new JPanel());
		optionalInputPanel.add(new JPanel());
		optionalInputPanel.add(new JPanel());
		optionalInputPanel.add(emailInput);

		//adds the infoLabel to the infoPanel
		infoPanel.add(infoLabel);

		//sets the layout and adds items to customer Info panel
		customerInfo.setLayout(new GridLayout(9, 1));
		customerInfo.add(nameLabelPanel);
		customerInfo.add(nameInputPanel);
		customerInfo.add(addressPanel);
		customerInfo.add(addressInputPanel);
		customerInfo.add(locationPanel);
		customerInfo.add(locationInputPanel);
		customerInfo.add(optionalPanel);
		customerInfo.add(optionalInputPanel);
		customerInfo.add(infoPanel);

		//sets the size for the submit panel
		submitPanel.setPreferredSize(new Dimension(100, 35));

		//sets the size for the submit panel2
		submitPanel2.setPreferredSize(new Dimension(100, 35));

		//adds the submit button to the submit panel
		submitPanel.add(submit);

		//adds the submit button to the submit panel2
		submitPanel2.add(submit2);

		//sets the size of orderPanel
		orderPanel.setPreferredSize(new Dimension(300, 465));

		//sets the size of orderPanel2
		orderPanel2.setPreferredSize(new Dimension(300, 465));

		//sets title borders for customer info panel and order panel
		TitledBorder customerTitle;
		TitledBorder orderTitle;
		customerTitle = BorderFactory
				.createTitledBorder("Customer Information");
		orderTitle = BorderFactory.createTitledBorder("Order Information");
		customerInfo.setBorder(customerTitle);
		customerInfo2.setBorder(customerTitle);
		orderPanel.setBorder(orderTitle);
		orderPanel2.setBorder(orderTitle);

		//adds all the items to the orderLabelPanel
		orderLabelPanel.add(itemLabel);
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(new JPanel());
		orderLabelPanel.add(qtyLabel);

		//adds all the items to the orderLabelPanel2
		orderLabelPanel2.add(itemLabel2);
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(new JPanel());
		orderLabelPanel2.add(qtyLabel2);

		//adds all the items to the orderInputPanel1
		orderPanel.setLayout(new GridLayout(NUM_PANELS+1, 1));
		orderPanel.add(orderLabelPanel);
		
		quantities = new ArrayList<JTextField>();
		orderitems = new ArrayList<JComboBox>();
		
		for (int i=0; i<NUM_PANELS; i++) {
			JTextField text = new JTextField(2);
			JComboBox combo = new JComboBox();
			
			quantities.add(text);
			orderitems.add(combo);
			
			JPanel panel = new JPanel();
			panel.add(combo);
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(text);
			
			orderPanel.add(panel);
		}
		
		//adds all the items to the orderInputPanel2
		orderPanel2.setLayout(new GridLayout(NUM_PANELS+1, 1));
		orderPanel2.add(orderLabelPanel2);
		
		quantities2 = new ArrayList<JTextField>();
		orderitems2 = new ArrayList<JComboBox>();
		
		for (int i=0; i<NUM_PANELS; i++) {
			JTextField text = new JTextField(2);
			JComboBox combo = new JComboBox();
			
			quantities2.add(text);
			orderitems2.add(combo);
			
			JPanel panel = new JPanel();
			panel.add(combo);
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(new JPanel());
			panel.add(text);
			
			orderPanel2.add(panel);
		}

		//adds all the items to existingPanel
		existingPanel.setLayout(new GridLayout(11, 1));
		existingPanel.add(new JPanel());
		existingPanel.add(new JPanel());
		existingPanel.add(new JPanel());
		existingPanel.add(new JPanel());
		existingPanel.add(new JPanel());
		existingPanel.add(new JPanel());
		existingPanel.add(existingLabel);
		existingPanel.add(existingInput);

		//adds all the items to customerInfo2
		customerInfo2.add(existingPanel);

		//adds everything to the newCustomerPanel
		newCustomerPanel.add(customerInfo, BorderLayout.CENTER);
		newCustomerPanel.add(orderPanel, BorderLayout.EAST);
		newCustomerPanel.add(submitPanel, BorderLayout.SOUTH);

		//adds everything to the existingCustomer
		existingCustomer.add(customerInfo2, BorderLayout.CENTER);
		existingCustomer.add(orderPanel2, BorderLayout.EAST);
		existingCustomer.add(submitPanel2, BorderLayout.SOUTH);

		//adds the tabs to the holdsAll panel
		holdsAll.add(tabs, BorderLayout.CENTER);

		//adding the holdsAll panel to the frame
		clientFrame.getContentPane().add(holdsAll, BorderLayout.CENTER);

		//does the rest to get frame to show up in center
		clientFrame.setLocationRelativeTo(null);

		//Display the window
		clientFrame.setVisible(true);
	}
	
	public int getCustomerID() {
		return Integer.parseInt(existingInput.getText());
	}

	/**
	 * Display a message to the user.
	 * 
	 * @param message Message to display
	 */
	public void displayMessage(String message) {
		javax.swing.JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Populate the JComboBoxes with the possible items.
	 * 
	 * @param items List of items from Server
	 */
	public void populateItems(ArrayList<Item> items) {
		// Populate both tabs
		for (Item item : items) {
			for (JComboBox combo : orderitems) {
				combo.addItem(item);
			}
			for (JComboBox combo : orderitems2) {
				combo.addItem(item);
			}
		}
	}
	
	/**
	 * Validate the information the user has 
	 * supplied to create a new customer.
	 * 
	 * @return true if valid, false otherwise.
	 */
	public boolean validateCustomer() {
		boolean valid = true;
		String errmsg = "Error(s):\n";
		
		if (firstNameInput.getText().equals("")) {
			errmsg += "- First name is required\n";
			valid = false;
		}
		if (lastNameInput.getText().equals("")) {
			errmsg += "- Last name is required\n";
			valid = false;
		}
		if (streetAddressInput.getText().equals("")) {
			errmsg += "- Address is required\n";
			valid = false;
		}
		if (cityInput.getText().equals("")) {
			errmsg += "- City is required\n";
			valid = false;
		}
		
		//TODO do we need to check format of zip/phone/email?
		//zipInput.getText(), phoneInput.getText(), emailInput.getText()
			
		if (!valid) displayMessage(errmsg);
		
		return valid;
	}
	
	/**
	 * Assumes the user input is valid.  Constructs
	 * a Customer object out of the user data.
	 * 
	 * @return new Customer
	 */
	public Customer getCustomer() {
		Customer c = null;
		
		c = new Customer(
					lastNameInput.getText(),
					firstNameInput.getText(),
					streetAddressInput.getText(),
					cityInput.getText(),
					(String)stateInput.getSelectedItem(),
					zipInput.getText(),
					phoneInput.getText(),
					emailInput.getText()
				);
		
		return c;
	}
	
	/**
	 * Validate the order for either a new customer or 
	 * existing customer.  Boolean parameter specifies 
	 * which tab to use (new customer vs. existing).
	 * 
	 * @param newCustomer true if using new customer tab, 
	 * 			false if using existing customer tab
	 * @return true if data is valid, false otherwise
	 */
	public boolean validateOrder(boolean newCustomer) {
		boolean valid = true;
		String errmsg = "Error(s):\n";
		
		// Validate only the data on the correct tab
		if (newCustomer) {
			for (JTextField text : quantities) {
				String quantity = text.getText();
				
				try {
					int iQuantity = Integer.parseInt(quantity);
					if (iQuantity <= 0) {
						errmsg += "- Value must be 1 or more\n";
						valid = false;
					}
				} catch (NumberFormatException e) {
					errmsg += "- Value must be an integer\n";
					valid = false;
				}
			}
		} else {
			for (JTextField text : quantities2) {
				String quantity = text.getText();
				
				try {
					int iQuantity = Integer.parseInt(quantity);
					if (iQuantity <= 0) {
						errmsg += "- Value must be 1 or more\n";
						valid = false;
					}
				} catch (NumberFormatException e) {
					errmsg += "- Value must be an integer\n";
					valid = false;
				}
			}
		}
		
		if (!valid) displayMessage(errmsg);
		
		return valid;
	}
	
	/**
	 * Get the Order from the existing customer tab.
	 * 
	 * @return the Order
	 */
	public Order getOrder() {
		int id = getCustomerID();
		return getOrder(id, false);
	}
	
	/**
	 * Get the Order from the new customer tab.
	 * 
	 * @param id ID of the new customer
	 * @return the Order
	 */
	public Order getOrder(int id) {
		return getOrder(id, true);
	}
	
	/**
	 * Assumes the Order data is valid.  Returns the Order
	 * supplied by the user.  Boolean flag indicates whether 
	 * to use the new customer tab or the existing customer tab.
	 * 
	 * @param custID ID of the customer to be used in Order
	 * @param newCustomer true if using new customer tab, 
	 * 			false if using existing customer tab
	 * @return the Order
	 */
	public Order getOrder(int custID, boolean newCustomer) {
		Order o = null;
		float total = 0.0f;
		
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();
		
		// Validate only the data on the correct tab
		if (newCustomer) {
			for (int i=0; i<NUM_PANELS; i++) {
				Item item = (Item)((JComboBox)orderitems.get(i)).getSelectedItem();
				int quantity = Integer.parseInt(quantities.get(i).getText());
				
				items.add(new OrderItem(item, quantity));
				total += quantity * item.getSalePrice();
			}
		} else {
			for (int i=0; i<NUM_PANELS; i++) {
				Item item = (Item)((JComboBox)orderitems2.get(i)).getSelectedItem();
				int quantity = Integer.parseInt(quantities2.get(i).getText());
				
				items.add(new OrderItem(item, quantity));
				total += quantity * item.getSalePrice();
			}
		}
		
		o = new Order(custID, total);
		o.setOrderItems(items);
		
		return o;
	}
}