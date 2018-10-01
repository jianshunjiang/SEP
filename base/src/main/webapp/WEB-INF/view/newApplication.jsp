<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/19/18
  Time: 2:33 PM
  To create new application for the student.
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
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="col-md-9">
    <form:form action="" method="post" role="form" id="app_form" name="app_form">
        <div class="form-group">
            <label for="title">Application title</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label>Student ID: </label>
            <label><%=student.getId()%>
            </label>
        </div>
        <div class="form-group">
            <label>Name: </label>
            <label><%=student.getFirstname()%> <%=student.getLastname()%>
            </label>
        </div>
        <div class="form-group">
            <label>Email: </label>
            <label><%=student.getEmail()%>
            </label>
        </div>
        <div class="form-group">
            <label for="content">Description of your reason: </label>
            <textarea class="form-control" id="content" name="content" rows="10"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success" id="app_btn"
                    onclick="submitApp()">Submit</button>
            <button type="submit" class="btn btn-sm btn-warning" id="draft_btn"
                    onclick="saveDraft()">Save draft</button>
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
        $('#app_form').bootstrapValidator({
            message: 'This value is not valid',        //验证错误时的信息
            feedbackIcons: {        //验证时显示的图标
                valid: 'glyphicon glyphicon-ok',      //正确图标
                invalid: 'glyphicon glyphicon-remove',        //错误图标
                validating: 'glyphicon glyphicon-refresh'        //正在更新图标
            },
            fields: {
                content: {
                    validators: {
                        notEmpty: {
                            message: 'Please enter the description of your application.'
                        },
                    }
                }
            }
        });
    });

    function submitApp() {
        document.getElementById("app_form").action = "/student/applications/add";
        document.getElementById("app_form").submit();
        console.log("Submit application");
    }

    function saveDraft() {
        document.getElementById("app_form").action = "/student/draft/save";
        document.getElementById("app_form").submit();
        console.log("Submit draft")
    }


</script>

</body>
</html>
