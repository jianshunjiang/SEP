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
</head>
<body>
<%@ include file="header.jsp" %>
<% Student student = (Student)session.getAttribute("Student");%>
    <div class="container">
        <h1>Welcome <%=student.getFirstname()%>  <%=student.getLastname()%></h1>
    </div>

</body>
</html>
