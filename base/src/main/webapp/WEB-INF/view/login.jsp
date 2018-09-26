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
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>UTS Loan System</h2>
    <c:if test="${! empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form:form action="login" method="post" role="form" id="login_form">
        <%--The form that used for log in action.--%>
        <div class="form-group">
                <%--TODO:当user type 为student时，label显示为student id,检查Input是不是全为数字。
                    TODO：当user type为 loan manager时，label显示为email，检查input是不是为email.
                    TODO: 当user type为system admin时， label显示为username。
                    TODO：对于所有input，检查其是否为空。
                    --%>
                <%--<script language="JavaScript" class="loginController">--%>
                <%--var result = LoginController.login();--%>
                <%--function userType() {--%>
                <%--if(result != null && login().equals(student)) {--%>

                <%--}--%>
                <%--if(result != null && login().equals(manager)) {--%>

                <%--}--%>
                <%--if(result != null && login().equals(admin)) {--%>

                <%--}--%>
                <%--else {--%>
                <%----%>
                <%--}--%>
                <%--}--%>
                <%--</script>--%>

                <%--<script language="JavaScript">--%>
                <%--function checkEmail( str ){--%>
                <%--var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;--%>
                <%--if(myReg.test(str)) {--%>
                <%--return true;--%>
                <%--}--%>
                <%--else {--%>
                <%--alert("Please enter email address only");--%>
                <%--return false;--%>
                <%--}--%>
                <%--}--%>
                <%--</script>--%>
                <%----%>
                <%--<script language="JavaScript">--%>
                <%--function checkNumber() {--%>
                <%--if(parseFloat(inputData).toString() == "NaN") {--%>
                <%--alert("Please enter numbers only");--%>
                <%--return false;--%>
                <%--}--%>
                <%--else {--%>
                <%--return true;--%>
                <%--}--%>
                <%--}--%>
                <%--</script>--%>

                <%--<script language="JavaScript">--%>
                <%--function checkInput(form) {--%>
                <%--if(form.username.value=="") {--%>
                <%--alert("User Name CANNOT be empty");--%>
                <%--form.username.focus();--%>
                <%--return false;--%>
                <%--}--%>
                <%--if(form.password.value=="") {--%>
                <%--alert("Password CANNOT be empty");--%>
                <%--form.password.focus();--%>
                <%--return false;--%>
                <%--}--%>
                <%--return true;--%>
                <%--}--%>
                <%--</script>--%>

            <div class="form-group">
                <label for="userType">Log in as: </label>
                <select class="form-control" id="userType" name="userType" onchange="refreshLabel()">
                    <option>Student</option>
                    <option>Loan Manager</option>
                    <option>System Administrator</option>
                </select>
            </div>
            <label for="username" id="username_label">Student ID: </label>
            <input type="text" onsubmit="checkInput()" class="form-control" id="username" name="username"
                   placeholder="Enter Student ID:"/>
                <%--<input type="text" class="form-control" id="username" name="username" placeholder="Enter email for manager, student id for student, username for admin"/>--%>
        </div>
        <div class="form-group">
            <label for="password">Password: </label>
            <input type="password" onsubmit="checkInput()" class="form-control" id="password" name="password"
                   placeholder="Enter Password:"/>
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
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!--  Bootstrap Validator JS文件 -->
<script src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
　　<!-- Bootstrap Validator 样式文件 -->
<link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
　　
<script>
    $(document).ready(function () {
        var msg;

        $('#login_form').bootstrapValidator({
            message: 'This value is not valid',        //验证错误时的信息
            feedbackIcons: {        //验证时显示的图标
                valid: 'glyphicon glyphicon-ok',      //正确图标
                invalid: 'glyphicon glyphicon-remove',        //错误图标
                validating: 'glyphicon glyphicon-refresh'        //正在更新图标
            },
            fields: {
                username: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter username.'
                        },
                        // callback: {
                        //     //callback方式
                        //     message: msg,
                        //     callback: function (value, validator) { //您可以在这里实现自定义功能
                        //         var reg_str;
                        //
                        //         if ($("#userType option:selected").text() === "Student") {
                        //             reg_str = /^[0-9]{8}/;
                        //             msg = "Please enter 8 numbers for student number."
                        //         }
                        //         if ($("#userType option:selected").text() === "Loan Manager") {
                        //             reg_str = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$/;
                        //             msg = "Please valid email to login."
                        //         }
                        //         if ($("#userType option:selected").text() === "System Administrator") {
                        //             reg_str = /^[a-zA-Z0-9_]+$/;
                        //             msg = "Username should only contain number and characters."
                        //         }
                        //         return reg_str.test(value);
                        //     }
                        // }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter password'
                        },
                    }
                },
            }

        });
    });
</script>
<script>
    function refreshLabel() {
        // var label=document.getElementById("username_label");
        var options=$("#test user:selected");

        if(options.text() === "Student") $("#username_label").html("Student id");
        if (options.text() === "Loan Manager")  $("#username_label").html("Email");
        if (options.text() === "System Administrator")  $("#username_label").html("Username");

    }
</script>
</body>
</html>
