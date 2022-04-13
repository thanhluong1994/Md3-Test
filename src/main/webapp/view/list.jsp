<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/04/2022
  Time: 9:50 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="/ProductServlet?action=create">Add New Product</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Product</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Description</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td><c:out value="${product.id_product}"/></td>
                <td><c:out value="${product.name_product}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><c:out value="${product.quantity}"/></td>
                <td><c:out value="${product.color}"/></td>
                <td><c:out value="${product.description}"/></td>
                <td><c:out value="${product.getCategory().getName_category()}"/></td>
                <td>
                    <a href="/ProductServlet?action=edit&id=${product.id_product}">Edit</a>
                    <a href="/ProductServlet?action=delete&id=${product.id_product}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
