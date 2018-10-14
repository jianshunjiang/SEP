<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2018/10/14
  Time: 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UTS Loan System</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!--  Bootstrap Validator JS文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>
<%--<div class="container main">--%>
<div class="col-md-9">
    <h1>Reset password for ${sessionScope.admin.username}</h1>
    <form:form action="/admin/resetPassword" method="post" role="form" id="reset_pwd" name="reset_pwd" >
        <div class="form-group">
            <label for="newPassword">New Password</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" value="${admin.password}">
        </div>
        <div class="form-group">
            <label for="repeatPassword">Re-type New Password</label>
            <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" value="${admin.password}">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Save">
            <a href="/admin/modify_account" role="button" class="btn btn-primary">Return</a>
        </div>
    </form:form>

</div>
<script>
    $(document).ready(function () {
        $('#reset_pwd').bootstrapValidator({
            message: 'This value is not valid',        //验证错误时的信息
            feedbackIcons: {        //验证时显示的图标
                valid: 'glyphicon glyphicon-ok',      //正确图标
                invalid: 'glyphicon glyphicon-remove',        //错误图标
                validating: 'glyphicon glyphicon-refresh'        //正在更新图标
            },
            fields: {
                newPassword: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter password'
                        },
                        stringLength: {
                            min: 6,
                            max: 18,
                            message: 'Password should be longer than 6 characters and less than 18 characters'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: 'Password should only contains characters, number and underscore character.'
                        }
                    }
                },
                repeatPassword: {
                    validators: {
                        notEmpty: {
                            message: 'Repeat password should not be null.'
                        },
                        callback: {
                            message: "Inconsistent input before and after.",
                            callback: function (value, validator) {
                                var newPassword = document.getElementById("newPassword");
                                return value === newPassword.value;

                            }
                        }
                    }
                }

            }

        });
    })

</script>
</body>
</html>

