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
    <title>UTS loan system</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" media="all"/>
</head>

<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>

<body>

<div class="col-md-9">
    <table class="table table-bordered">
        <caption style="align-content: center; text-align: center"><h2>Profile</h2></caption>
        <tbody>
        <tr>
            <td><strong>Account type</strong></td>
            <td>Loan Manager</td>
        </tr>
        <tr>
            <td><strong>Loan Manager ID</strong></td>
            <td>${sessionScope.manager.id}</td>
        </tr>
        <tr>
            <td><strong>Name</strong></td>
            <td>${sessionScope.manager.firstname} ${sessionScope.manager.lastname}</td>
        </tr>
        <tr>
            <td><strong>Mobile</strong></td>
            <td>${sessionScope.manager.mobile}</td>
        </tr>
        <tr>
            <td><strong>Email</strong></td>
            <td>${sessionScope.manager.email}</td>
        </tr>
        </tbody>
    </table>
    <div class="form-group">
        <a role="button" class="btn btn-primary" href="/loanManager/account/edit"><span
                class="glyphicon glyphicon-pencil"></span>Edit</a>
        <a role="button" class="btn btn-warning" href="/loanManager/account/resetPassword"><span
                class="glyphicon glyphicon-lock"></span>Reset Password</a>
    </div>
</div>
</body>
</html>
