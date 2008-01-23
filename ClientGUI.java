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
	private JTextField qtyInput1;
	private JComboBox itemInput1;
	private JTextField qtyInput2;
	private JComboBox itemInput2;
	private JTextField qtyInput3;
	private JComboBox itemInput3;
	private JTextField qtyInput4;
	private JComboBox itemInput4;
	private JTextField qtyInput5;
	private JComboBox itemInput5;
	private JTextField qtyInput6;
	private JComboBox itemInput6;
	private JTextField qtyInput7;
	private JComboBox itemInput7;
	private JTextField qtyInput8;
	private JComboBox itemInput8;
	private JTextField qtyInput9;
	private JComboBox itemInput9;
	private JTextField qtyInput11;
	private JComboBox itemInput11;
	private JTextField qtyInput12;
	private JComboBox itemInput12;
	private JTextField qtyInput13;
	private JComboBox itemInput13;
	private JTextField qtyInput14;
	private JComboBox itemInput14;
	private JTextField qtyInput15;
	private JComboBox itemInput15;
	private JTextField qtyInput16;
	private JComboBox itemInput16;
	private JTextField qtyInput17;
	private JComboBox itemInput17;
	private JTextField qtyInput18;
	private JComboBox itemInput18;
	private JTextField qtyInput19;
	private JComboBox itemInput19;
	private JPanel orderLabelPanel;
	private JPanel orderLabelPanel2;
	private JPanel orderInputPanel1;
	private JPanel orderInputPanel2;
	private JPanel orderInputPanel3;
	private JPanel orderInputPanel4;
	private JPanel orderInputPanel5;
	private JPanel orderInputPanel6;
	private JPanel orderInputPanel7;
	private JPanel orderInputPanel8;
	private JPanel orderInputPanel9;
	private JPanel orderInputPanel11;
	private JPanel orderInputPanel12;
	private JPanel orderInputPanel13;
	private JPanel orderInputPanel14;
	private JPanel orderInputPanel15;
	private JPanel orderInputPanel16;
	private JPanel orderInputPanel17;
	private JPanel orderInputPanel18;
	private JPanel orderInputPanel19;
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
		orderInputPanel1 = new JPanel();
		orderInputPanel2 = new JPanel();
		orderInputPanel3 = new JPanel();
		orderInputPanel4 = new JPanel();
		orderInputPanel5 = new JPanel();
		orderInputPanel6 = new JPanel();
		orderInputPanel7 = new JPanel();
		orderInputPanel8 = new JPanel();
		orderInputPanel9 = new JPanel();
		orderInputPanel11 = new JPanel();
		orderInputPanel12 = new JPanel();
		orderInputPanel13 = new JPanel();
		orderInputPanel14 = new JPanel();
		orderInputPanel15 = new JPanel();
		orderInputPanel16 = new JPanel();
		orderInputPanel17 = new JPanel();
		orderInputPanel18 = new JPanel();
		orderInputPanel19 = new JPanel();
		existingPanel = new JPanel();

		//creates the buttons
		submit = new JButton("Submit");
		submit.addActionListener(client);
		submit.setActionCommand("new customer");
		submit2 = new JButton("Submit");
		submit2.addActionListener(client);
		submit2.setActionCommand("existing customer");

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
		qtyInput1 = new JTextField(2);
		qtyInput2 = new JTextField(2);
		qtyInput3 = new JTextField(2);
		qtyInput4 = new JTextField(2);
		qtyInput5 = new JTextField(2);
		qtyInput6 = new JTextField(2);
		qtyInput7 = new JTextField(2);
		qtyInput8 = new JTextField(2);
		qtyInput9 = new JTextField(2);
		qtyInput11 = new JTextField(2);
		qtyInput12 = new JTextField(2);
		qtyInput13 = new JTextField(2);
		qtyInput14 = new JTextField(2);
		qtyInput15 = new JTextField(2);
		qtyInput16 = new JTextField(2);
		qtyInput17 = new JTextField(2);
		qtyInput18 = new JTextField(2);
		qtyInput19 = new JTextField(2);
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
		itemInput1 = new JComboBox();
		itemInput2 = new JComboBox();
		itemInput3 = new JComboBox();
		itemInput4 = new JComboBox();
		itemInput5 = new JComboBox();
		itemInput6 = new JComboBox();
		itemInput7 = new JComboBox();
		itemInput8 = new JComboBox();
		itemInput9 = new JComboBox();
		itemInput11 = new JComboBox();
		itemInput12 = new JComboBox();
		itemInput13 = new JComboBox();
		itemInput14 = new JComboBox();
		itemInput15 = new JComboBox();
		itemInput16 = new JComboBox();
		itemInput17 = new JComboBox();
		itemInput18 = new JComboBox();
		itemInput19 = new JComboBox();

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
		orderInputPanel1.add(itemInput1);
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(new JPanel());
		orderInputPanel1.add(qtyInput1);

		//adds all the items to the orderInputPanel2
		orderInputPanel2.add(itemInput2);
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(new JPanel());
		orderInputPanel2.add(qtyInput2);

		//adds all the items to the orderInputPanel3
		orderInputPanel3.add(itemInput3);
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(new JPanel());
		orderInputPanel3.add(qtyInput3);

		//adds all the items to the orderInputPanel4
		orderInputPanel4.add(itemInput4);
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(new JPanel());
		orderInputPanel4.add(qtyInput4);

		//adds all the items to the orderInputPanel5
		orderInputPanel5.add(itemInput5);
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(new JPanel());
		orderInputPanel5.add(qtyInput5);

		//adds all the items to the orderInputPanel6
		orderInputPanel6.add(itemInput6);
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(new JPanel());
		orderInputPanel6.add(qtyInput6);

		//adds all the items to the orderInputPanel7
		orderInputPanel7.add(itemInput7);
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(new JPanel());
		orderInputPanel7.add(qtyInput7);

		//adds all the items to the orderInputPanel8
		orderInputPanel8.add(itemInput8);
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(new JPanel());
		orderInputPanel8.add(qtyInput8);

		//adds all the items to the orderInputPanel9
		orderInputPanel9.add(itemInput9);
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(new JPanel());
		orderInputPanel9.add(qtyInput9);

		//adds all the items to the orderInputPanel1
		orderInputPanel11.add(itemInput11);
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(new JPanel());
		orderInputPanel11.add(qtyInput11);

		//adds all the items to the orderInputPanel2
		orderInputPanel12.add(itemInput12);
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(new JPanel());
		orderInputPanel12.add(qtyInput12);

		//adds all the items to the orderInputPanel3
		orderInputPanel13.add(itemInput13);
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(new JPanel());
		orderInputPanel13.add(qtyInput13);

		//adds all the items to the orderInputPanel4
		orderInputPanel14.add(itemInput14);
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(new JPanel());
		orderInputPanel14.add(qtyInput14);

		//adds all the items to the orderInputPanel5
		orderInputPanel15.add(itemInput15);
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(new JPanel());
		orderInputPanel15.add(qtyInput15);

		//adds all the items to the orderInputPanel6
		orderInputPanel16.add(itemInput16);
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(new JPanel());
		orderInputPanel16.add(qtyInput16);

		//adds all the items to the orderInputPanel7
		orderInputPanel17.add(itemInput17);
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(new JPanel());
		orderInputPanel17.add(qtyInput17);

		//adds all the items to the orderInputPanel8
		orderInputPanel18.add(itemInput18);
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(new JPanel());
		orderInputPanel18.add(qtyInput18);

		//adds all the items to the orderInputPanel19
		orderInputPanel19.add(itemInput19);
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(new JPanel());
		orderInputPanel19.add(qtyInput19);

		//adds items to orderPanel
		orderPanel.setLayout(new GridLayout(10, 1));
		orderPanel.add(orderLabelPanel);
		orderPanel.add(orderInputPanel1);
		orderPanel.add(orderInputPanel2);
		orderPanel.add(orderInputPanel3);
		orderPanel.add(orderInputPanel4);
		orderPanel.add(orderInputPanel5);
		orderPanel.add(orderInputPanel6);
		orderPanel.add(orderInputPanel7);
		orderPanel.add(orderInputPanel8);
		orderPanel.add(orderInputPanel9);

		//adds items to orderPanel2
		orderPanel2.setLayout(new GridLayout(10, 1));
		orderPanel2.add(orderLabelPanel2);
		orderPanel2.add(orderInputPanel11);
		orderPanel2.add(orderInputPanel12);
		orderPanel2.add(orderInputPanel13);
		orderPanel2.add(orderInputPanel14);
		orderPanel2.add(orderInputPanel15);
		orderPanel2.add(orderInputPanel16);
		orderPanel2.add(orderInputPanel17);
		orderPanel2.add(orderInputPanel18);
		orderPanel2.add(orderInputPanel19);

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

	public void displayMessage(String message) {
		javax.swing.JOptionPane.showMessageDialog(null, message);
	}

	public void populateItems(ArrayList<Item> items) {
		// TODO Auto-generated method stub
		// how do we do this if there are more than 19 items?....
	}
	
	public int getCustomerID() {
		return Integer.parseInt(existingInput.getText());
	}
	
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
	
	public boolean validateOrder() {
		boolean valid = true;
		String errmsg = "Error(s):\n";
		
		//TODO check that no negative #'s were input into any of the boxes
		//how can we loop through these?
			
		if (!valid) displayMessage(errmsg);
		
		return valid;
	}
	
	public Order getOrder() {
		Order o = null;
		int total = 0;
		
		//TODO finish this loop - how loop?
		//add each OrderItem to items and increment total
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();
		
		o = new Order(client.getCustID(), total);
		o.setOrderItems(items);
		
		return o;
	}
}