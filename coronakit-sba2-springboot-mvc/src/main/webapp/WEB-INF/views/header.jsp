<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="spring-form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>

<security:authorize access="hasRole('ADMIN')">
	<hr/>
	<!-- <spring-form:form action="${pageContext.request.contextPath}/admin/home" method="POST">
		<input type="submit" value="ADMIN HOME">
	</spring-form:form> -->
	<a href="${pageContext.request.contextPath}/admin/home">ADMIN HOME</a> 
</security:authorize>

<security:authorize access="hasRole('USER')">
	<hr/>
	<!-- <spring-form:form action="${pageContext.request.contextPath}/user/home" method="POST">
		<input type="submit" value="USER HOME">
	</spring-form:form> -->
	<a href="${pageContext.request.contextPath}/user/home">USER HOME</a> 
</security:authorize>
<br>

<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
<div align="left">
	<input type="submit" value="Logout">
</div>
</spring-form:form>
<!-- <a href="${pageContext.request.contextPath}/logout">Logout</a>   -->
<hr/>

</body>
</html>