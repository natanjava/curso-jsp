<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error !</title>
</head>
<body>
<h1>Error message: Please, contact system support team.</h1>

<% 
 out.print(request.getAttribute("msg"));
%>

</body>
</html>