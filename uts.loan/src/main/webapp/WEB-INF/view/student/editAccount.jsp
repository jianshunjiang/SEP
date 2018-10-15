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
    <title>UTS Loan System</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>

<%@ include file="../header.jsp"%>
<%@ include file="../sidebar.jsp"%>

<body>
<div class="col-md-9">
<form:form action="/student/account/edit" method="post" role="form" id="student_form" name="student_form">
    <div class="form-group">
        <label>Name</label>
        <input class="form-control"value="${sessionScope.student.firstname}, ${sessionScope.student.lastname}" readonly="readonly" />
    </div>
    <div class="form-group">
        <label>Gender</label>
        <input type="text" class="form-control" value="${sessionScope.student.gender}" readonly="readonly" />
    </div>
    <div class="form-group">
        <label>Date of Birth</label>
        <input class="form-control" value="${sessionScope.student.dob}" readonly="readonly" />
    </div>
    <div class="form-group">
        <label>Nationality</label>
        <input class="form-control" value="${sessionScope.student.nationality}" readonly="readonly" />
    </div>
    <div class="form-group">
        <label>Start Year</label>
        <input class="form-control" value="${sessionScope.student.startYear}" readonly="readonly" />
    </div>

    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" name="email" class="form-control" id="email" value="${sessionScope.student.email}" placeholder="Email Address" />
    </div>

    <div class="form-group">
        <label for="phone">Phone</label>
        <input class="form-control" type="phone" name="phone" id="phone" value="${sessionScope.student.phone}" placeholder="Phone Number" />
    </div>

    <div class="form-group">
        <label for="faculty">Faculty</label>
        <input class="form-control" type="text" name="faculty" id="faculty" value="${sessionScope.student.faculty}" placeholder="Faculty Name" />
    </div>

    <div class="form-group">
        <label for="course">Course</label>
        <input class="form-control" type="text" name="course" id="course" value="${sessionScope.student.course}" placeholder="Course Name/Number"/>
    </div>

    <div class="form-group">
        <label for="bankaccount">Bank Account</label>
        <input class="form-control" type="text" name="bankaccount" id="bankaccount" value="${sessionScope.student.bankaccount}" placeholder="Bank Account" />
    </div>

    <div class="form-group">
        <input type="hidden" name="id" id="id" value="${sessionScope.student.id}"/>
        <input type="submit" class="btn btn-success" value="Save"/>
        <a href="/student/account" role="button" class="btn btn-primary">Return</a>
    </div>

</form:form>
</div>

</body>
</html>
<script>
    $(document).ready(function () {
        $('#student_form').bootstrapValidator({
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
                phone:{
                    validators:{
                        notEmpty: {
                            message: "Phone number is requried"
                        },
                        regexp:{
                            regexp:/^[0-9][0-9-]+$/,
                            message:"Phone number should only contains 0-9 and '-'."
                        },
                        stringLength: {
                            min: 6,
                            max: 13,
                            message: 'Phone number should be minimum 6 digits and not more than 13 digits'
                        },
                    }
                },
                bankaccount:{
                    validators:{
                        notEmpty: {
                            message: "Bank account is required."
                        },
                        regexp:{
                            regexp:/^[a-zA-Z0-9-]+$/,
                            message:"Please enter bank account with valid information."
                        },
                    }
                },
                course:{
                    validators:{
                        notEmpty: {
                            message: "Course is required."
                        },
                        regexp:{
                            regexp:/^[a-zA-Z0-9]+$/,
                            message:"Please fill in course with valid information."
                        },
                    }
                },
                faculty:{
                    validators:{
                        notEmpty: {
                            message: "Faculty is required."
                        },
                        regexp:{
                            regexp:/^[a-zA-Z0-9]+$/,
                            message:"Please fill in faculty with valid information."
                        },
                    }
                }

            }

        });
    })

</script>