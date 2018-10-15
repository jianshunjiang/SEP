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
            <td>Student</td>
        </tr>
        <tr>
            <td><strong>Student ID</strong></td>
            <td>${sessionScope.student.id}</td>
        </tr>
        <tr>
            <td><strong>Name</strong></td>
            <td>${sessionScope.student.firstname} ${sessionScope.student.lastname}</td>
        </tr>
        <tr>
            <td><strong>Gender</strong></td>
            <td>${sessionScope.student.gender}</td>
        </tr>
        <tr>
            <td><strong>Nationality</strong></td>
            <td>${sessionScope.student.nationality}</td>
        </tr>
        <tr>
            <td><strong>Bank Account</strong></td>
            <td>${sessionScope.student.bankaccount}</td>
        </tr>
        <tr>
            <td><strong>Faculty</strong></td>
            <td>${sessionScope.student.faculty}</td>
        </tr>
        <tr>
            <td><strong>Phone</strong></td>
            <td>${sessionScope.student.phone}</td>

        </tr>
        <tr>
            <td><strong>Course</strong></td>
            <td>${sessionScope.student.course}</td>
        </tr>
        <tr>
            <td><strong>Date of birth</strong></td>
            <td>${sessionScope.student.dob}</td>
        </tr>
        <tr>
            <td><strong>Start Year</strong></td>
            <td>${sessionScope.student.startYear}</td>
        </tr>
        </tbody>
    </table>
    <div class="form-group">
        <a role="button" class="btn btn-primary" href="/student/account/edit"><span
                class="glyphicon glyphicon-pencil"></span>Edit</a>
        <a role="button" class="btn btn-warning" href="/student/account/resetPassword"><span
                class="glyphicon glyphicon-lock"></span>Reset Password</a>
    </div>

</div>
</body>
</html>
