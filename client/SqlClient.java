package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.BorderLayout;

import server.*;

/**
 * Client
 */
public class SqlClient implements ActionListener {
	
	/**
	 * Initialize and display the GUI
	 */
	
	private JTextArea input;
	private JTextArea output;
	
	public SqlClient() {
		// GUI CODE HERE
		JFrame frame = new JFrame("SQL Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets the frame size
        frame.setSize(800, 400);
        
        //creates the panel that holds it all
        JPanel holdsAll = new JPanel(new BorderLayout());
        
        //Makes North and center panels
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel(new BorderLayout());
        
        //Creates the components to be put in northPanel
        JPanel topNorth = new JPanel();
        JPanel bottomNorth = new JPanel();
        JLabel sqlCommand = new JLabel("Please enter the SQL command:");
        JButton send = new JButton("Submit");
        input = new JTextArea(3,45);
        
        //adds send listeners
        send.addActionListener(this);
        send.setActionCommand("process");
        
        //adds the label to the top panel
        topNorth.add(sqlCommand);
        
        //adds everything else to the bottom panel
        bottomNorth.add(input);
        bottomNorth.add(send);
        
        //adds everything to the northPanel
        northPanel.add(topNorth,BorderLayout.NORTH);
        northPanel.add(bottomNorth,BorderLayout.SOUTH);
        
        //Creates the components to be put in southPanel
        JLabel outputLabel = new JLabel("Output:");
        output = new JTextArea(15,70);
        JPanel topSouth = new JPanel();
        JPanel bottomSouth = new JPanel();
        
        //Put the output in a JScrollPane so all text can be read
        JScrollPane scroll = new JScrollPane(output);
        scroll.setAutoscrolls(true);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //adds the label to the top panel
        topSouth.add(outputLabel);
        
        //adds everything else to the bottom panel
        bottomSouth.add(scroll);
        
        //adds everything to the northPanel
        southPanel.add(topSouth,BorderLayout.NORTH);
        southPanel.add(bottomSouth,BorderLayout.CENTER);
        
        
        holdsAll.add(northPanel,BorderLayout.NORTH);
        holdsAll.add(southPanel,BorderLayout.CENTER);
        frame.getContentPane().add(holdsAll);

        //Display the window.
        frame.setVisible(true);
		System.out.println("Client created");
	}
	
	/**
	 * Listens for ActionEvents.  Main purpose is
	 * to send a query to the server.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("process")) {
			String query = input.getText(); // get query from GUI
			
			// reference to the server
			QueryProcessorInterface qp = null;
			try {
				// get a reference to the server
				qp = (QueryProcessorInterface)Naming.lookup("rmi://localhost/QueryProcessor");
			}
			catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("Program exiting - no server access");
				//System.exit(1);
			}
			
			// process the query
			ArrayList<String> recordSet = null;
			try {
				recordSet = qp.processQuery(query);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("Program exiting - error analyzing message");
				javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
				//System.exit(1);
			}
			
			// display the results
			displayResults(recordSet);
		}
	}
	
	/**
	 * Display the results to the GUI
	 * 
	 * @param records list of records
	 */
	public void displayResults(ArrayList<String> records) {
		// this for loop is temporary
		System.out.println("Displaying results:");
		for(String s : records) {
			output.append(s);
		}
		if(records.size() > 0) {
			output.append("\n");
		}
	}
	
	/**
	 * Run the client
	 * 
	 * @param args - not used
	 */
	public static void main(String [] args) {
		// Create and display GUI
		new SqlClient();
	}
}
