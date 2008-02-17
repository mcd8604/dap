package project4;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: OrderSummaryServlet
 *
 */
 public class OrderSummaryServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public OrderSummaryServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET THE NECESSARY LISTS
		ArrayList<Order> orders = DatabaseController.getOrdersToday();
		ArrayList<Customer> customers = DatabaseController.getCustomersToday();
		ArrayList<Supplier> suppliers = DatabaseController.getSuppliers();
		
		// GET THE WRITER
		PrintWriter out = response.getWriter();
		
		// SETUP HTML DOCUMENT
		out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out.write("<html><head><title>Order Summary</title></head><body>");

		// SETUP TABLE FOR SUMMARY
		out.write("<table bgcolor=\"#CCCCCC\" width=\"60%\" border=\"0\" align=\"center\">");
		out.write(	"<tr>");
		out.write(		"<td><p align=\"center\"><strong>Summary for Wednesday, January 30, 2008</strong></p>");
		
		////////////////////
		// ORDERS SECTION //
		////////////////////

		out.write(			"<p><strong>Orders Processed Today:</strong></p>");
		
		// ORDERS LOOP
		for (Order order : orders) {
			out.write(		"<table bgcolor=\"#FFFFFF\" width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.write(			"<tr>");
			out.write(				"<td width=\"100%\" valign=\"top\">");
			out.write(					"<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
			out.write(						"<tr>");
			out.write(							"<td width=\"20%\" valign=\"top\"><p>Order ID: " + order.getOrderID() + "</p></td>");
			out.write(							"<td width=\"40%\" valign=\"top\"><p>Last Name: " + order.getCustomer().getLastName() + "</p></td>");
			out.write(							"<td width=\"40%\" valign=\"top\"><p>First Name: " + order.getCustomer().getFirstName() + "</p></td>");
			out.write(						"</tr>");
			out.write(					"</table>");
			out.write(				"</td>");
			out.write(			"</tr>");
			out.write(  		"<tr>");
			out.write(  			"<td width=\"100%\" colspan=\"2\" valign=\"top\"><p>Item List</p>");
			out.write(      			"<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.write(          			"<tr>");
			out.write(              			"<td width=\"20%\" valign=\"top\"><p>Item ID</p></td>");
			out.write(                  		"<td width=\"60%\" valign=\"top\"><p>Item Name</p></td>");
			out.write(                  		"<td width=\"20%\" valign=\"top\"><p>Quantity</p></td>");
			out.write(          			"</tr>");
			
			// ITEMS FOR THIS ORDER LOOP
			for (OrderItem orderItem : order.getOrderItems()) {
				out.write(              	"<tr>");
				out.write(                  	"<td width=\"20%\" valign=\"top\"><p>" + orderItem.getItem().getItemID() + "</p></td>");
				out.write(                  	"<td width=\"60%\" valign=\"top\"><p>" + orderItem.getItem().getItemName() + "</p></td>");
				out.write(                  	"<td width=\"20%\" valign=\"top\"><p>" + orderItem.getQuantity() + "</p></td>");
				out.write(              	"</tr>");
			}// END ITEMS FOR THIS ORDER LOOP
			
			out.write(          		"</table>");
			out.write(					"<p>Total: $" + order.getTotal() + "</p>");
			out.write(				"</td>");
			out.write(			"</tr>");
			out.write(		"</table>");
			out.write(		"<br/>");
		} // END ORDERS LOOP
		
		///////////////////////
		// CUSTOMERS SECTION //
		///////////////////////

		out.write(      	"<p><strong>New Customers:</strong></p>");
		out.write(      	"<table bgcolor=\"#FFFFFF\" width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
		out.write(        		"<tr>");
		out.write(          		"<td valign=\"top\"><p> ID</p></td>");
		out.write(          		"<td valign=\"top\"><p>Last Name</p></td>");
		out.write(          		"<td valign=\"top\"><p>First Name</p></td>");
		out.write(          		"<td valign=\"top\"><p>Address</p></td>");
		out.write(          		"<td valign=\"top\"><p>Phone</p></td>");
		out.write(          		"<td valign=\"top\"><p>E-mail</p></td>");
		out.write(        		"</tr>");
		
		// CUSTOMER LOOP
		for (Customer customer : customers) {
			out.write(        	"<tr>");
			out.write(          	"<td valign=\"top\">" + customer.getID() + "</td>");
			out.write(          	"<td valign=\"top\">" + customer.getLastName() + "</td>");
			out.write(          	"<td valign=\"top\">" + customer.getFirstName() + "</td>");
			out.write(          	"<td valign=\"top\"><p>" + customer.getAddress() + "<br/>" + customer.getCity() + ", " + customer.getState() + " " + customer.getZipCode() + "</p></td>");
			out.write(          	"<td valign=\"top\">" + customer.getPhone() + "</td>");
			out.write(          	"<td valign=\"top\">" + customer.getemail() + "</td>");
			out.write(        	"</tr>");
		}// END CUSTOMER LOOP
		
		out.write(     		"</table>");
		      
		//////////////////////////
		// ITEM LISTING SECTION //
		//////////////////////////

		out.write(      	"<p><strong>Item Listing:</strong></p>");
		
		// SUPPLIER LOOP
		for (Supplier supplier : suppliers) {
			out.write(      "<table bgcolor=\"#FFFFFF\" width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.write(        	"<tr>");
			out.write(          	"<td width=\"100%\" valign=\"top\">");
			out.write(					"<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
			out.write(              		"<tr>");
			out.write(                			"<td width=\"20%\" valign=\"top\"><p>Supplier ID: " + supplier.getID() + "</p></td>");
			out.write(                			"<td width=\"40%\" valign=\"top\"><p>Supplier Name: " + supplier.getName() + " </p></td>");
			out.write(                			"<td width=\"40%\" valign=\"top\"><p>Supplier Address: " + supplier.getAddress() + ", " + supplier.getCity() + " " + supplier.getState() + " </p></td>");
			out.write(              		"</tr>");
			out.write(          		"</table>");
			out.write(				"</td>");
			out.write(        	"</tr>");
			out.write(        	"<tr>");
			out.write(          	"<td width=\"100%\" colspan=\"2\" valign=\"top\"><p>Item List</p>");
			out.write(              	"<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.write(                		"<tr>");
			out.write(                  		"<td width=\"20%\" valign=\"top\"><p>Item ID</p></td>");
			out.write(                  		"<td width=\"40%\" valign=\"top\"><p>Item Name</p></td>");
			out.write(                  		"<td width=\"20%\" valign=\"top\"><p>Supplier Price </p></td>");
			out.write(                  		"<td width=\"20%\" valign=\"top\"><p>Sale Price </p></td>");
			out.write(                		"</tr>");
			
			// ITEM LOOP
			for (Item item : supplier.getItems()) {
				out.write(                	"<tr>");
				out.write(                  	"<td width=\"20%\" valign=\"top\"><p>" + item.getItemID() + "</p></td>");
				out.write(                  	"<td width=\"40%\" valign=\"top\"><p>" + item.getItemName() + "</p></td>");
				out.write(                  	"<td width=\"20%\" valign=\"top\"><p>" + item.getSupplierPrice() + "</p></td>");
				out.write(                  	"<td width=\"20%\" valign=\"top\"><p>" + item.getSalePrice() + "</p></td>");
				out.write(                	"</tr>");
			} // END ITEM LOOP
			
			out.write(            		"</table>");
			out.write(				"</td>");
			out.write(        	"</tr>");
			out.write(      "</table>");
			out.write(      "<br/>");
		} // END SUPPLIER LOOP

		out.write(			"<p>&#160;</p>");
		out.write(		"</td>");
		out.write(	"</tr>");
		out.write("</table>");
		out.write("<p>&#160;</p>");
		out.write("<p>&#160;</p>");
		out.write("</body></html>");
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}   	  	    
}