<%--
  Created by IntelliJ IDEA.
  User: eric_stradlin
  Date: 11/10/18
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create Manager</title>
</head>
<body>
<form:form action="/admin/create_manager" method="post">
    <table>
        <tr>
            <td>
                <label for="email">email</label>
            </td>
            <td>
                <input type="email" id="email" name="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">password</label>
            </td>
            <td>
                <input type="password" id="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Create"/>
            </td>
        </tr>
    </table>
</form:form>
<h1>${error}</h1>
<a href="/admin/">Return</a>
</body>
</html>
