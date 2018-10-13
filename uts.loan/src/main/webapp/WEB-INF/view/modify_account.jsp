<%--
  Created by IntelliJ IDEA.
  User: eric_stradlin
  Date: 8/10/18
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify Account</title>
</head>
<body>
<form:form action="/loanManager/modify_account" method="post">
    <table>
        <tr>
            <td>
                <label>ID</label>
            </td>
            <td>
                <input type="text" id="id" name="id" value="${manager.id}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <td>
                <lable>Name</lable>
            </td>
            <td>
                <input type="text" id="name" name="name" value="${manager.firstname}, ${manager.lastname}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <td>
                <label>Password</label>
            </td>
            <td>
                <input type="password" id="password" name="password" value="${manager.password}" />
            </td>
        </tr>
        <tr>
            <td>
                <label>Repeat Password</label>
            </td>
            <td>
                <input type="password" id="repeatpassword" name="repeatpassword" value="${manager.password}" />
            </td>
        </tr>
        <tr>
            <td>
                <label>Mobile Number</label>
            </td>
            <td>
                <input type="number" id="mobile" name="mobile" value="${manager.mobile}" />
            </td>
        </tr>
        <tr>
            <td>
                <label>Email Address</label>
            </td>
            <td>
                <input type="email" id="email" name="email" value="${manager.email}">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Confirm">
            </td>
        </tr>
    </table>
</form:form>
<h1>${error}</h1>
<a href="/loanManager/"></a>
</body>
</html>
