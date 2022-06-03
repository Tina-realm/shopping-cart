<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Shopping Cart Application</title>
</head>
<body>
	<center>
		<h1>Shopping Cart</h1>
        <h2>
        	<a href="new">Add New Item</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Items</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="checkout">Check Out</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Items</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="item" items="${listItem}">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.name}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td><c:out value="${item.quantity}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${item.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${item.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
    
    
    
</body>
</html>
