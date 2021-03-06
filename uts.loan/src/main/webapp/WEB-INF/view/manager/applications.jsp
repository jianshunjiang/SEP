<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.loan.uts.model.Student" %><%--
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
    <c:if test="${empty applications}"><h1>No unhandled applications.</h1></c:if>
    <c:if test="${not empty applications}">
    <table class="table table-condensed">
        <thead>
        <tr>
            <th>Application</th>
            <th>Title</th>
            <th>Submit Date</th>
            <th>Student</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${applications}" var="application">
            <tr class="active">
                <td>${application.id}</td>
                <td>${application.title}</td>
                <td>${application.submitDateString()}</td>
                <td>${application.student.firstname} ${application.student.lastname}</td>
                <td>
                    <a role="button" class="btn btn-primary btn-lg btn-block" href="/loanManager/applications/detail?id=${application.id}">View</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
