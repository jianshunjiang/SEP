<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="static com.loan.uts.model.Application.REFUSED" %><%--
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
    <div class="form-group">
        <form:form action="/loanManager/applications/manage" method="post">
            <div class="form-group">
                <label for="comment"><h1>Comment</h1></label>
                <textarea name="comment" id="comment" class="form-control" rows="8" style="margin-bottom: 10px;">${application.comment}</textarea>
            </div>
            <div class="form-group">
                <c:if test="${result.equals('Refused')}"><input type="submit" class="btn btn-danger btn-lg" value="Decline"></c:if>
                <c:if test="${result.equals('Replied')}"><input type="submit" class="btn btn-primary btn-lg" value="Reply"></c:if>
                <input type="hidden" name="id" id="id" value="${application.id}">
                <input type="hidden" name="result" id="result" value="${result}">
                <a role="button" class="btn btn-success btn-lg" href="/loanManager/applications/detail?id=${application.id}">Back</a>
            </div>

        </form:form>

    </div>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
