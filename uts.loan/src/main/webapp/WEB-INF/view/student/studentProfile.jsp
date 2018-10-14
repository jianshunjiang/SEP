<%--
  Created by IntelliJ IDEA.
  User: eric_stradlin
  Date: 14/10/18
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student Profile</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" media="all"/>
</head>

<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>

<body>

<div class="col-md-9">
    <div class="form-group">
        <label>Account type</label>
        <span>Student</span>
    </div>
    <div class="form-group">
        <label>Student ID</label>
        <span>${sessionScope.student.id}</span>
    </div>
    <a role="button" class="btn btn-primary btn-lg btn-block" href="/student/editStudentAccount"><span class="glyphicon glyphicon-pencil"></span>Edit Account</a>
</div>

</body>
</html>
