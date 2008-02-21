<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" %>
<%@ page import="java.util.*, project4.*" %>
<jsp:useBean id="cust" class="project4.Customer" scope="session"/>
<%! ArrayList<Order> orders; %>
<% 	orders = DatabaseController.getCustomerOrders(cust.getID()); //(ArrayList<Order>)session.getAttribute("orders");
	if (orders == null) {
		System.out.println("orders is null");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Summary</title>
</head>
<body>
	<h2>Order Summary</h2>
	<table>
		<tr>
			<td>Order Number</td><td>Item</td><td>Quantity</td></tr>
			<% 
				for (Order order : orders) { %>
					<tr><td><p><%= "Order ID: " + order.getOrderID() %></p></td></tr>
					<%
						for (OrderItem orderItem : order.getOrderItems()) {
					%>
						<tr><td></td><td><p><%= orderItem.getItem().getItemName() %></p></td>
						<td><p><%= orderItem.getQuantity() %></p></td></tr>
				<%	}
				}
			%>
	</table>
</body>
</html>