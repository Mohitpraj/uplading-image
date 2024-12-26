<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Image</title>
</head>
<body>

	<h2>upload Image</h2>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" required>
		<button type="submit">upload</button><br><br>
		<a href="download-page">click for download image</a><br><br><br>
		<a href="http://localhost:8080/image-upload-download/view-image?id=1">view image</a>

	</form>
	<p>${message}</p> 
	<img src="data:image/png;base64,${image}">

</body>
</html>