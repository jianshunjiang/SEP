<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/19/18
  Time: 2:33 PM
  To create new application for the student.
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
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<%--<div class="container main">--%>
<div class="col-md-9">
    <form:form action="/student/newApplication/submit" method="post" role="form">
        <div class="form-group">
            <label>Student ID: </label>
            <label><%=student.getId()%>
            </label>
        </div>
        <div class="form-group">
            <label>Name: </label>
            <label><%=student.getFirstname()%> <%=student.getLastname()%>
            </label>
        </div>
        <div class="form-group">
            <label>Email: </label>
            <label><%=student.getEmail()%>
            </label>
        </div>
        <div class="form-group">
            <label for="content">Description of your reason: </label>
            <textarea class="form-control" id="content" name="content" rows="10"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">Submit</button>
            <%--<button type="submit" class="btn btn-sm btn-success">Save draft</button>--%>
        </div>
    </form:form>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>