<%--
  Created by IntelliJ IDEA.
  User: eric_stradlin
  Date: 14/10/18
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify Student Account</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!--  Bootstrap Validator JS文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>

<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<body>
<div class="col-md-9">
    <c:url var="saveUrl" value="/student/editStudentAccount?id=${userAttribute.id}" />
    <form:form modelAttribute="userAttribute" action="${saveUrl}" method="post">

    <div class="form-group">
        <input type="hidden" name="id" id="id" value="${student.id}"/>
        <input type="text" class="form-control" name="name" id="name" value="${student.firstname}, ${student.lastname}" readonly="readonly" />
        <input type="text" class="form-control" name="gender" id="gender" value="${student.gender}" readonly="readonly" />
        <input type="date" class="form-control" name="dob" id="dob" value="${student.dob}" readonly="readonly" />
        <input type="text" class="form-control" name="nationality" id="nationality" value="${student.nationality}" readonly="readonly" />
        <input type="date" class="form-control" name="start_year" id="start_year" value="${student.start_year}" readonly="readonly" />
    </div>

    <div class="form-group">
        <input type="password" name="password" id="password" value="${student.password}" placeholder="Password" />
    </div>

    <div class="form-group">
        <input type="password" name="repeatpassword" id="repeatpassword" value="${student.password}" placeholder="Confirm Password" />
    </div>

    <div class="form-group">
        <input type="email" name="email" id="email" value="${student.email}" placeholder="Email Address" />
    </div>

    <div class="form-group">
        <input type="phone" name="phone" id="phone" value="${student.phone}" placeholder="Phone Number" />
    </div>

    <div class="form-group">
        <input type="text" name="faculty" id="faculty" value="${student.faculty}" placeholder="Faculty Name" />
    </div>

    <div class="form-group">
        <input type="text" name="course" id="course" value="${student.course}" placeholder="Course Name/Number"/>
    </div>

    <div class="form-group">
        <input type="text" name="bankaccount" id="bankaccount" value="${student.bankaccount}" placeholder="Bank Account" />
    </div>

    <div class="form-group">
        <input type="submit" class="btn btn-success" value="Save"/>
        <a href="/student/editStudentAccount" role="button" class="btn btn-primary">Return</a>
    </div>

</div>
</form:form>
<script>
    $(document).ready(function () {
        $('#manager_form').bootstrapValidator({
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
