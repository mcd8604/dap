<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Account</title>
</head>
<body>
	<h2>Create New Account</h2>
	<form enctype="text/plain" method="post"
	 action="http://localhost:8080/OrderSummary/AccountServlet" name="createCustomer">
	<table>
	<tr><td>Last Name*: </td><td><input type="text" name="lastname" /> </td></tr>
 	<tr><td>First Name*: </td><td><input type="text" name="firstname" /> </td></tr>
    <tr><td>Address*: </td><td><input type="text" name="address" /> </td></tr>
    <tr><td>City*: </td><td><input type="text" name="city" /> </td></tr>
    <tr><td>State*: </td><td><input type="text" name="state" /> </td></tr>
    <tr><td>Zip Code*: </td><td><input type="text" name="zipcode" /> </td></tr>
    <tr><td>Phone #: </td><td><input type="text" name="phone" /> </td></tr>
    <tr><td>E-Mail: </td><td><input type="text" name="email" /> </td></tr>
    <tr><td>* Items are required </td><td></td></tr>
    <tr><td></td><td><input type="submit" name="Submit" value="Submit" /> </td></tr>
	</table>
	<input type="hidden" name="action" value="createCustomer"/>
	</form>
</body>
</html>