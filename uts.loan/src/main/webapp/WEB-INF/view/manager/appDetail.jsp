<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
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
   <table class="table">
       <caption>Application Detail</caption>
       <tbody>
       <tr>
           <td>Application ID</td>
           <td>${application.id}</td>
       </tr>
       <tr>
           <td>Application Title</td>
           <td>${application.title}</td>
       </tr>
       <tr>
           <td>Student Name</td>
           <td>${application.student.firstname} ${application.student.lastname}</td>
       </tr>
       <tr>
           <td>Course</td>
           <td>${application.student.course}</td>
       </tr>
       <tr>
           <td>Faculty</td>
           <td>${application.student.faculty}</td>
       </td>
       <tr>
           <td>Application Content</td>
           <td>${application.content}</td>
       </tr>
       <tr>
           <td>Submit Date</td>
           <td>${application.submitDateString()}</td>
       </tr>
       </tbody>
   </table>
    <a role="button" class="btn btn-success btn-lg" href="/loanManager/applications/manage?id=${application.id}&result=Accepted" onclick="return confirm('Approve this application?')"><span class="glyphicon glyphicon-ok"></span> Approve</a>
    <a role="button" class="btn btn-danger btn-lg" href="/loanManager/applications/decline?id=${application.id}"><span class="glyphicon glyphicon-remove"></span> Decline</a>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
