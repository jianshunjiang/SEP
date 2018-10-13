<%--
  Created by IntelliJ IDEA.
  User: eric_stradlin
  Date: 11/10/18
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp"%>

<form:form action="/admin/managers/edit" method="post">
    <div class="form-group">
        <input type="hidden" name="id" id="id" value="${manager.id}">
        <label for="firstname">Firstname</label>
        <input type="text" class="form-control" id="firstname" name="firstname" value="${manager.firstname}">
    </div>
    <div class="form-group">
        <label for="lastname">Lastname</label>
        <input type="text" class="form-control" id="lastname" name="lastname" value="${manager.lastname}">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="text" class="form-control" id="email" name="email" value="${manager.email}">
    </div>
    <div class="form-group">
        <label for="password">Email</label>
        <input type="password" class="form-control" id="password" name="password" value="${manager.password}">
    </div>
    <div class="form-group">
        <label for="repeatpassword">Email</label>
        <input type="password" class="form-control" id="repeatpassword" name="repeatpassword" value="${manager.password}">
    </div>
    <div class="form-group">
        <label for="mobile">Email</label>
        <input type="text" class="form-control" id="mobile" name="mobile" value="${manager.mobile}">
    </div>
    <div class="form-group">
        <input type="submit" class="form-control" value="Save" >
    </div>
</form:form>
<h1>${error}</h1>
<a href="/admin/">Return</a>
</body>
</html>
