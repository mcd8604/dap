import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project4.*;

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
		
		HttpSession sess = request.getSession(false);
		if(sess == null){
			response.sendRedirect("/error.jsp");		
		}
		
		while ((line = in.readLine()) != null) {
			lines.add(line);
		}
		
		String action = "";
		
		if (lines.size() > 0) {
			action = lines.get(lines.size()-1).split("=")[1];
		}
		
		if (action.equals("getCustomer")) {
			// Create Customer c from JSP page
			int id = Integer.parseInt(lines.get(0).split("=")[1]);
			Customer c = new Customer(id);
			
			if (DatabaseController.isCustomer(c)) {
				// get info
				Customer cust = DatabaseController.getCustomer(id);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account.jsp");
				sess.setAttribute("cust", cust);
				dispatcher.forward(request, response);
			} else {
				// error message
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				sess.setAttribute("error", "Invalid user id: " + id);
				dispatcher.forward(request, response);
			}
		} else if (action.equals("createCustomer")) {
			// Create Customer c from newaccount.jsp
			Customer c = new Customer();
			
			//READ IN ALL THE UPDATES AND MODIFY c
			c.setLastName(lines.get(0).split("=")[1]);
			c.setFirstName(lines.get(1).split("=")[1]);
			c.setAddress(lines.get(2).split("=")[1]);
			c.setCity(lines.get(3).split("=")[1]);
			c.setState(lines.get(4).split("=")[1]);
			c.setZipCode(lines.get(5).split("=")[1]);
			if (lines.get(6).split("=").length == 2) {
				c.setPhone(lines.get(6).split("=")[1]);
			}
			if (lines.get(7).split("=").length == 2) {
				c.setEmail(lines.get(7).split("=")[1]);
			}

			DatabaseController.createCustomer(c);
			System.out.println("Created Customer: " + c.getID());	

			sess.setAttribute("cust", c);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account.jsp");
			dispatcher.forward(request, response);	
		} else if (action.equals("editCustomer")) {
			Customer c = (Customer)sess.getAttribute("cust");
			
			//READ IN ALL THE UPDATES AND MODIFY c
			c.setLastName(lines.get(0).split("=")[1]);
			c.setFirstName(lines.get(1).split("=")[1]);
			c.setAddress(lines.get(2).split("=")[1]);
			c.setCity(lines.get(3).split("=")[1]);
			c.setState(lines.get(4).split("=")[1]);
			c.setZipCode(lines.get(5).split("=")[1]);
			if (lines.get(6).split("=").length == 2) {
				c.setPhone(lines.get(6).split("=")[1]);
			}
			if (lines.get(7).split("=").length == 2) {
				c.setEmail(lines.get(7).split("=")[1]);
			}
			
			DatabaseController.editCustomer(c);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm.jsp");
			sess.setAttribute("confirmMsg", "Customer " + c.getID() + " has been updated.<br/><br/><a href=\"account.jsp\">Back to Account</a>");
			dispatcher.forward(request, response);
		} else if (action.equals("removeCustomer")) {
			Customer c = (Customer)sess.getAttribute("cust");
			DatabaseController.deleteCustomer(c);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm.jsp");
			sess.setAttribute("confirmMsg", "The Customer with ID " + c.getID() + " has been removed.");
			dispatcher.forward(request, response);
		} else if (action.equals("getOrders")) {
			Customer c = (Customer)sess.getAttribute("cust");
			ArrayList<Order> orders = DatabaseController.getCustomerOrders(c.getID());
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/orderhistory.jsp");
			sess.setAttribute("orders", orders);
			dispatcher.forward(request, response);
		}
	}   	  	    
}