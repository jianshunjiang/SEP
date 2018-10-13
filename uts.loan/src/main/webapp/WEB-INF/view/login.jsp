<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/29/18
  Time: 12:29 PM
  The log in page of this web app.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UTS loan system</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!--  Bootstrap Validator JS文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
    　　<!-- Bootstrap Validator 样式文件 -->
    <script type="text/javascript" src="/static/js/login.js"></script>

</head>
<body>
<div class="container">
    <h2>UTS Loan System</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form:form action="login" method="post" role="form" id="login_form">
        <div class="form-group">

            <div class="form-group">
                <label for="userType">Log in as: </label>
                <select class="form-control" id="userType" name="userType" onchange=refreshLabel()>
                    <option selected="selected" value="student">Student</option>
                    <option value="manager">Loan Manager</option>
                    <option value="admin">System Administrator</option>
                </select>
            </div>
            <label for="username" id="username_label">Student ID: </label>
            <input type="text" class="form-control"
                   id="username" name="username"/>
        </div>
        <div class="form-group">
            <label for="password">Password: </label>
            <input type="password" class="form-control" id="password"
                   name="password" placeholder="Enter Password:"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">Login</button>
        </div>
    </form:form>
</div>

</body>
</html>
