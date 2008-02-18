<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<jsp:useBean id="cust" class="project4.Customer" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Account</title>
</head>
<body>
	<h2>Confirm Delete</h2>
	<form enctype="text/plain" method="post"
	 action="http://localhost:8080/OrderSummary/AccountServlet">
	 	<p>Are you sure you want to delete account <%= cust.getID() %>?</p>
	 	<input type="submit" value="Delete Account">
	 	<input type="hidden" name="action" value="removeCustomer"/>
	</form>
	
	<a href="account.jsp">Back to Account Page</a>
</body>
</html>