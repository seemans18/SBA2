<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error page</title>
</head>
<body>
<jsp:include page="/header" />

<h1>Un-Authorized Access...</h1>
<a href="${pageContext.request.contextPath}/">HOME</a>
</body>
</html>