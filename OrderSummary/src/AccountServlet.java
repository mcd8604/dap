

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if (request.getAttribute("action").equals("new account")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.createCustomer(c);
		} else if (request.getAttribute("action").equals("edit account")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.editCustomer(c);
		} else if (request.getAttribute("action").equals("delete account")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.deleteCustomer(c);
		} else if (request.getAttribute("action").equals("order history")) {
			// Create Customer c from JSP page
			Customer c = null;
			DatabaseController.getOrders(c);
		}
	}   	  	    
}
