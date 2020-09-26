<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User- Summary page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="/header" />
	
	<h4>Hello <security:authentication property="principal.username"/> !! Thank you for ordering with us :)</h4>
	<br><hr>
	
	<h4> <em>Order Summary</em></h4>

	<br> 
	<!-- <label><strong>Corona kit ID:</strong></label>
	<label>${coronaKit.getId()}</label><br>  -->
	
	<label><strong>Delivery Address:</strong></label>
	<label>${coronaKit.getDeliveryAddress()}</label><br>
	
	<label><strong>Order Date : </strong></label>
	<label>${coronaKit.getOrderDate()}</label>
	<hr>
	<h5> Products Ordered:</h5>
	<c:choose>
		<c:when test="${kitDetail == null || kitDetail.isEmpty() }">
			<p class="well well-info">No Products Found in the Kit!! <a href="${pageContext.request.contextPath}/user/show-list">Add products</a> </p>
		</c:when>
		<c:otherwise>
			<br/>
			<table class="table table-striped table-hover table-border">
				<tr>
					<th>ID#</th> 
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Amount</th>
				</tr>
				<c:forEach items="${kitDetail }" var="kit" varStatus="loop">
					<tr>
						<td>${kit.id }</td> 
						<td><c:out value="${productNames[loop.index]}"/></td>
						<td>${kit.quantity }</td>
						<td>${kit.amount}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
	<label for="totAmt"><strong>Total Bill Amount:</strong></label>
	<input type="text" id="totAmt" name="totAmt" value="${coronaKit.getTotalAmount()}" readonly>
	<br><br>
	
	<jsp:include page="/footer" />
</body>

</html>