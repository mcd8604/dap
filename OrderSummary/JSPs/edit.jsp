<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<jsp:useBean id="cust" class="project4.Customer" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Account</title>
<script src="validation.js" />
<script type="text/javascript">
<!--
function confirmation() {
	if (confirm("Update Account Information?")){
		return true;
	}
	else{
		return false;
	}
	
	return true;
}
//-->
</script>
</head>
<body>
	<h2>Edit Account Information</h2>
	
	<form enctype="text/plain" method="post" onsubmit="return validate(this)"
	 action="/OrderSummary/AccountServlet">
	 
		<table>
			<tr>
				<td>Account ID:</td><td><%= cust.getID() %></td>
			</tr>
			<tr>
				<td>Last:</td><td><input type="text" name="Last" value="<%=cust.getLastName() %>" /></td>
			</tr>
			<tr>
				<td>First:</td><td><input type="text" name="First" value="<%=cust.getFirstName() %>" /></td>
			</tr>
			<tr>
				<td>Address:</td><td><input type="text" name="Address" value="<%=cust.getAddress() %>" /></td>
			</tr>
			<tr>
				<td>City:</td><td><input type="text" name="City" value="<%=cust.getCity() %>" /></td>
			</tr>
			<tr>
				<td>State:</td><td><input type="text" name="State" value="<%=cust.getState() %>" /></td>
			</tr>
			<tr>
				<td>Zip:</td><td><input type="text" name="Zip" value="<%=cust.getZipCode() %>" /></td>
			</tr>
			<tr>
				<td>Phone:</td><td><input type="text" name="Phone" value="<%=cust.getPhone() %>" /></td>
			</tr>
			<tr>
				<td>Email:</td><td><input type="text" name="Email" value="<%=cust.getemail() %>" /></td>
			</tr>
		</table>
		
		<input type="submit" onclick="return  confirmation()" value="Update Account Information">
	 	<input type="hidden" name="action" value="editCustomer"/>
	</form>
	
	<a href="account.jsp">Back to Account</a>
</body>
</html>