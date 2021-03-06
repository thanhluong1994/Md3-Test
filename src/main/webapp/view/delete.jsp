<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13/04/2022
  Time: 11:35 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="/ProductServlet?action=delete=product">List All Product</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Are you sure?
                </h2>
            </caption>
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.id_product}' />"/>
            </c:if>
            <tr>
                <th> Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${product.getName_product()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th> Price:</th>
                <td>
                    <input type="text" name="price" size="45"
                           value="<c:out value='${product.getPrice()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td>
                    <input type="text" name="quantity" size="15"
                           value="<c:out value='${product.getQuantity()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Color:</th>
                <td>
                    <input type="text" name="color" size="15"
                           value="<c:out value='${product.getColor()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Description:</th>
                <td>
                    <input type="text" name="description" size="15"
                           value="<c:out value='${product.getDescription()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Category:</th>
                <td>
                    <select name="category" id="">
                        <c:forEach items="${category}" var="c">
                            <option value="${c.getId_category()}">${c.getName_category()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Delete"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
