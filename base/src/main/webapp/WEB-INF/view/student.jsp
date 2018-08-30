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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UTS loan system</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/wp-content/themes/runoob/style.css?v=1.147" type="text/css" media="all" />
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" media="all" />
</head>
<body>
<%@ include file="header.jsp" %>
<% Student student = (Student) session.getAttribute("Student");%>
<div class="container navigation">
    <ul class="nav nav-list">
        <li class="nav-header">Welcome <%=student.getFirstname()%>  <%=student.getLastname()%>
        </li>
        <li class="active"><a href="#"><i class="icon-white icon-home"></i> Home</a></li>
        <li><a href="#"><i class="icon-pencil"></i> Applications</a></li>
        <li><a href="#"><i class="icon-user"></i> Profile</a></li>
        <li><a href="#"><i class="icon-cog"></i> Settings</a></li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-flag"></i> Help</a></li>
    </ul>
</div>

</body>
</html>
