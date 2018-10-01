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
        <div class="form-group">

            <div class="form-group">
                <label for="userType">Log in as: </label>
                <select class="form-control" id="userType" name="userType" onchange=refreshLabel()>
                    <option selected="selected">Student</option>
                    <option>Loan Manager</option>
                    <option>System Administrator</option>
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

        $('#login_form').bootstrapValidator({
            message: 'This value is not valid',        //验证错误时的信息
            feedbackIcons: {        //验证时显示的图标
                valid: 'glyphicon glyphicon-ok',      //正确图标
                invalid: 'glyphicon glyphicon-remove',        //错误图标
                validating: 'glyphicon glyphicon-refresh'        //正在更新图标
            },
            fields: {
                password: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter password'
                        },
                    }
                },
                username: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter a valid value.'
                        },
                        callback: {
                            message: "Please enter a valid value",
                            callback: function (value, validator) {
                                var reg_str;

                                var usertype = document.getElementById("userType");
                                var index = usertype.selectedIndex;
                                var option = usertype.options[index];

                                if (option.value === "Student") {
                                    reg_str = /^[0-9]{8}/;
                                }
                                if (option.value === "Loan Manager") {
                                    reg_str = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                                }
                                if (option.value === "System Administrator") {
                                    reg_str = /^[a-zA-Z0-9_]+$/;
                                }
                                console.log("test something, reg: " + reg_str + ", value: "+value + ", result: "+reg_str.test(value));
                                return reg_str.test(value);

                            }
                        }
                    }
                }

            }

        });
    });
</script>
<script>
    function refreshLabel() {
        // var label=document.getElementById("username_label");
        // var options=$("#userType option:selected");
        var label = document.getElementById("username_label");
        var usertype = document.getElementById("userType");
        var index = usertype.selectedIndex;

        if (usertype.options[index].value === "Student") {
            label.innerText = "Student id";
            $("#username_label").html("Student Id");
            document.getElementById("username").setAttribute("placeholder", "Enter student ID...");
        }
        if (usertype.options[index].value === "Loan Manager") {
            label.innerText = "Email";
            $("#username_label").html("Email");
            document.getElementById("username").setAttribute("placeholder", "Enter email...");
        }
        if (usertype.options[index].value === "System Administrator") {
            label.innerText = "Username";
            $("#username_label").html("Username");
            document.getElementById("username").setAttribute("placeholder", "Enter username...");
        }
        document.getElementById("username").value ="";

    }
</script>

<script language="JavaScript">
</script>
</body>
</html>
