<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation Page</title>
</head>
<body>
<h2>Confirmation</h2>
<p>
	<%= session.getAttribute("confirmMsg") %>
</p>
<a href="main.jsp">Main Page</a>
</body>
</html>