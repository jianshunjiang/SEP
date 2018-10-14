<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/19/18
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UTS loan system</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" media="all"/>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>
<div class="col-md-9">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <a role="button" class="btn btn-primary btn-lg btn-block" href="/admin/managers/add"><span class="glyphicon glyphicon-pencil"></span>　Create</a>
    <c:if test="${empty managers}"><h1>No managers</h1></c:if>
    <c:if test="${not empty managers}">
    <table class="table table-condensed">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${managers}" var="manager">
            <tr class="active">
                <td>${manager.id}</td>
                <td>${manager.firstname} ${manager.lastname}</td>
                <td>${manager.email}</td>
                <td>${manager.mobile}</td>
                <td>
                    <a role="button" class="btn btn-success" href="/admin/managers/edit?managerId=${manager.id}">Edit</a>
                </td>
                <td>
                    <form:form id="_form" method="post" action="/admin/managers/delete" onsubmit="return confirm('Do you really want to delete this manager?');">
                        <input type="hidden" name="managerId" value="${manager.id}" />
                        <input type="submit" class="btn btn-warning" value="Delete" />
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<%--</div>--%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
