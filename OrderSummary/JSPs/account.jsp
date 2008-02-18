<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<jsp:useBean id="cust" class="project4.Customer" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Information</title>
</head>
<body>
	<h2>Account Information</h2>
	<table>
		<tr>
			<td>Account ID:</td><td><%= cust.getID() %></td>
		</tr>
		<tr>
			<td>Last:</td><td><%= cust.getLastName() %></td>
		</tr>
		<tr>
			<td>First:</td><td><%= cust.getFirstName() %></td>
		</tr>
		<tr>
			<td>Address:</td><td><%= cust.getAddress() %></td>
		</tr>
		<tr>
			<td>City:</td><td><%= cust.getCity() %></td>
		</tr>
		<tr>
			<td>State:</td><td><%= cust.getState() %></td>
		</tr>
		<tr>
			<td>Zip:</td><td><%= cust.getZipCode() %></td>
		</tr>
		<tr>
			<td>Phone:</td><td><%= cust.getPhone() %></td>
		</tr>
		<tr>
			<td>Email:</td><td><%= cust.getemail() %></td>
		</tr>
	</table>
	<br/>
	<table>
		<tr>
			<td><a href="edit.jsp">Edit Account Information</a></td>
		</tr>
		<tr>
			<td><a href="orderhistory.jsp">View Order History</a></td>
		</tr>
		<tr>
			<td><a href="delete.jsp">Delete Account</a></td>
		</tr>
		<tr>
			<td><a href="main.jsp">Log Out</a></td>
		</tr>
	</table>
</body>
</html>