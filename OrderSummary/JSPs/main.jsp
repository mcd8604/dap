<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Team 4 - Main Page</title>
</head>
<body>
	<h2>Login</h2>
	<form enctype="text/plain" method="post"
	 action="http://localhost:8080/OrderSummary/AccountServlet">
	 
	 	<table>
	 		<tr>
	 			<td>ID:</td><td><input type="text" maxlength="20" size="20" name="id"></td>
			</tr>
		</table>
		<br />
	  	<input type="submit" value="Login">
	 	<input type="hidden" name="action" value="getCustomer"/>
	</form>
	
	<a href="newaccount.jsp">Create new account</a>
</body>
</html>