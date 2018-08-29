<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/29/18
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UTS loan system</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<div class="container">
    <h2>UTS Loan System</h2>
    <form:form action="loginAction" method="post" role="form">
        <div class="form-group">
            <label for="studentid">Student ID: </label>
            <input type="text" class="form-control" id="studentid" name="studentid" placeholder="Enter Student ID:"/>
        </div>
        <div class="form-group">
            <label for="password">Password: </label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password:"/>
        </div>
    <div class="form-group">
        <label for="userType">Log in as: </label>
        <select class="form-control" id="userType" name="userType">
            <option>Student</option>
            <option>Loan Manager</option>
            <option>System Administrator</option>
        </select>
    </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">Login</button>
        </div>
    </form:form>
</div>

</body>
</html>
