<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/29/18
  Time: 12:29 PM
  The log in page of this web app.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UTS loan system</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2>UTS Loan System</h2>

    <%--The form that used for log in action.--%>
    <form:form action="loginAction" method="post" role="form">
        <div class="form-group">
            <%--TODO:当user type 为student时，label显示为student id,检查Input是不是全为数字。
                TODO：当user type为 loan manager时，label显示为email，检查input是不是为email.
                TODO: 当user type为system admin时， label显示为username。
                TODO：对于所有input，检查其是否为空。
                --%>
         <div class="form-group">
             <label for="userType">Log in as: </label>
                    <select class="form-control" id="userType" name="userType">
                        <option>Student</option>
                        <option>Loan Manager</option>
                        <option>System Administrator</option>
                    </select>
         </div>
            <label for="username">Student ID: </label>
            <input type="text" class="form-control" id="username" name="username" pattern="[0-9]{8}" placeholder="Enter Student ID"/>
        </div>
        <div class="form-group">
            <label for="password">Password: </label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password"/>
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="remember"> Remember me</label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">Login</button>
        </div>
    </form:form>
    <%--TODO:Display log in fail meassage--%>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
