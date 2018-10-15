<%--
  Created by IntelliJ IDEA.
  User: eric_stradlin
  Date: 8/10/18
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify Account</title>
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
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
<form:form  action="/loanManager/account/edit" method="post" id="manager_form" name="manager_form">
    <div class="form-group">
        <input type="hidden" name="id" id="id" value="${sessionScope.manager.id}"/>
        <label for="firstname">Firstname: </label>
        <input class="form-control" name="firstname" id="firstname" value="${sessionScope.manager.firstname}" placeholder="Firstname..."/>
    </div>

    <div class="form-group">
        <label for="lastname">Firstname: </label>
        <input class="form-control" name="lastname" id="lastname" value="${sessionScope.manager.lastname}" placeholder="Lastname..."/>
    </div>

    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" name="email" id="email" value="${sessionScope.manager.email}" placeholder="Email Address...">
    </div>

    <div class="form-group">
        <label for="mobile">Mobile</label>
        <input class="form-control" name="mobile" id="mobile" value="${sessionScope.manager.mobile}" placeholder="Mobile number..."/>
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-success" value="Save"/>
        <a href="/loanManager/account" role="button" class="btn btn-primary">Return</a>
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
                email:{
                    validators:{
                        notEmpty:{
                            message: "Email is required",

                        },
                        regexp:{
                            regexp:/^[a-zA-Z0-9]+[.a-zA-Z0-9_-]*@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                            message:"Email should only contains character, number, '.', '-', '_' and in right format"
                        }
                    }
                },
                mobile:{
                    validators:{
                        notEmpty: {
                            message: "Mobile number is requried"
                        },
                        regexp:{
                            regexp:/^[0-9][0-9-]+$/,
                            message:"Mobile number should only contains 0-9 and '-'."
                        },
                        stringLength: {
                            min: 6,
                            max: 13,
                            message: 'Mobile number should be minimum 6 digits and not more than 13 digits'
                        },
                    }
                },
                firstname:{
                    validators:{
                        notEmpty: {
                            message: "Firstname is required."
                        },
                        regexp:{
                            regexp:/^[A-Z][a-zA-Z\s]+$/,
                            message:"Firstname should only contain characters."
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: 'Firstname should not be too long and the first letter should be capitalized..'
                        },
                    }
                },
                lastname:{
                    validators:{
                        notEmpty: {
                            message: "Lastname is required."
                        },
                        regexp:{
                            regexp:/^[A-Z][a-zA-Z\s]+$/,
                            message:"Lastname should only contain characters and the first letter should be capitalized.."
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: 'Lastname should not be too long.'
                        },
                    }
                }
            }

        });
    })

</script>
</body>
</html>
