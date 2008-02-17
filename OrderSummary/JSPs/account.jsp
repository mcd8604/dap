<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" %>
<jsp:useBean id="cust" class="project4.Customer" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Information</title>
</head>
<body>
	<h2>Account Information</h2>
	<%= cust.getLastName() %>
	<table>
		<tr>
			<td>User</td><td>Info</td>
		</tr>
	</table>
	
	<a href="edit.jsp">Edit Account Information</a>
	<br/><br/>
	<a href="orderhistory.jsp">View Order History</a>
	<br/><br/>
	<a href="main.jsp">Log Out</a>
	
	<form enctype="text/plain" method="post"
	 action="http://localhost:8080/OrderSummary/AccountServlet" name="delete">
	 
	  	<input type="submit" value="Delete Account">
	</form>
	
</body>
</html>