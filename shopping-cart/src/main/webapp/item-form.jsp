<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Shopping Cart Application</title>
</head>
<body>
	<center>
		<h1>Shopping Cart</h1>
		<h2>
			<a href="new">Add New Item</a> &nbsp;&nbsp;&nbsp; <a href="list">List
				All Items</a>

		</h2>
	</center>
	<div align="center">
		<c:if test="${item != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${item == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${item != null}">
            			Edit Item
            		</c:if>
					<c:if test="${item == null}">
            			Add New Item
            		</c:if>
				</h2>
			</caption>
			<c:if test="${item != null}">
				<input type="hidden" name="id" value="<c:out value='${item.id}' />" />
			</c:if>
			<tr>
				<th>Item Name:</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${item.name}' />" /></td>
			</tr>
			<tr>
				<th>Item Price:</th>
				<td><input type="text" name="price" size="45"
					value="<c:out value='${item.price}' />" /></td>
			</tr>
			<tr>
				<th>Quantity:</th>
				<td><input type="text" name="quantity" size="15"
					value="<c:out value='${item.quantity}' />" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
