<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/view-image" method="get">
    <label>Enter image id:</label>
    <input type="text" name="id" required>
    <button type="submit">View Image</button>
    
</form>



<p>${message}</p>
</body>
</html>