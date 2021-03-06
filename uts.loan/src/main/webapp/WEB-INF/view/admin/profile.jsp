<%--
  Created by IntelliJ IDEA.
  User: lushan
  Date: 2018/10/13
  Time: 下午11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" media="all"/>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>

<div class="col-md-9">
    <table class="table table-bordered">
        <caption style="align-content: center; text-align: center"><h2>Profile</h2></caption>
        <tbody>
        <tr>
            <td><strong>Account type</strong></td>
            <td>System Administrator</td>
        </tr>
        <tr>
            <td><strong>Username</strong></td>
            <td>${sessionScope.admin.username}</td>
        </tr>
        </tbody>
    </table>
    <a role="button" class="btn btn-warning btn-lg btn-block" href="/admin/resetPassword"><span class="glyphicon glyphicon-lock"></span> Reset Password</a>
</div>
<%--</div>--%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
