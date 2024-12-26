<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Downlaod Image</title>
</head>
<body>

<h2>Download Image</h2>
<form id="downloadImageForm">
    <label>Enter image id:</label>
    <input type="text" id="imageId" required>
    <button type="button" onclick="downloadImage()">Download Image</button>
</form>

<script>
    function downloadImage() {
        var id = document.getElementById("imageId").value;
        window.location.href = "/image-upload-download/download-image/" + id;
    }
</script>

<p>${message}</p>

</body>
</html>