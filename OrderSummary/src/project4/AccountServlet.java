package project4;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: AccountServlet
 *
 */
 public class AccountServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader in = request.getReader();
		String line = "";
		
		
		HttpSession  sess = request.getSession(false);
		
		//System.out.println(request.getParameter("method"));
		
		while ((line = in.readLine()) != null) {
			lines.add(line);
		}
		
		String action = lines.get(lines.size()-1).split("=")[1];
		
		if (action.equals("getCustomer")) {
			// Create Customer c from JSP page
			int id = Integer.parseInt(lines.get(0).split("=")[1]);
			Customer c = new Customer(id);
			boolean isCustomer = DatabaseController.isCustomer(c);
			
			if (isCustomer) {
				// get info
				System.out.println("Valid customer");
				Customer cust = new Customer("Last", "First", "Address", "City", "State", "Zip", "Phone", "Email");
				//Customer cust = DatabaseController.getCustomer(id);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("../account.jsp");
				request.setAttribute("customer", cust);
				dispatcher.forward(request, response);
			} else {
				// error message
				System.out.println("Inalid customer");
			}
		} else if (action.equals("createCustomer")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.createCustomer(c);
		} else if (action.equals("editCustomer")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.editCustomer(c);
		} else if (action.equals("removeCustomer")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.deleteCustomer(c);
		} else if (action.equals("getCustomerOrders")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.getOrders(c);
		}
	}   	  	    
}