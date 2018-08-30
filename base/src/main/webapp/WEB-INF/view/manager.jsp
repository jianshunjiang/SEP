<%@ page import="com.loan.uts.model.Manager" %>
<%@ page import="static com.loan.uts.controller.LoginController.LOAN_MANAGER" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/29/18
  Time: 5:12 PM
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
<%Manager manager= (Manager) session.getAttribute(LOAN_MANAGER);%>
<h1>Welcome <%=manager.getFirstname()%> <%=manager.getLastname()%></h1>

</body>
</html>
