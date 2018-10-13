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
    <title>Reset Password</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" media="all"/>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>
<%--<div class="container main">--%>
<div class="col-md-9">

    <form:form action="" method="post" role="form" id="reset_pwd" name="reset_pwd_form" >
        <div class="form-group">
            <label for="title">Current Password</label>
            <input type="password" class="form-control" id="password" name="title" value="">
            <p id="pwd"><span>Password do not correct</span></p>
        </div>
        <div class="form-group">
            <label for="title">New Password</label>
            <input type="password" class="form-control" id="repeatPassword" name="title" value="">
            <p id="repeat_pwd1"><span>Too short</span></p>
        </div>
        <div class="form-group">
            <label for="title">Re-type New Password</label>
            <input type="password" class="form-control" id="repeatPassword2" name="title" value="">
            <p id="repeat_pwd2"><span>Passwords do not match</span></p>
        </div>
    </form:form>

    <br>
    <br>
    <br>
    <br>
    <a role="button" class="btn btn-primary btn-lg btn-block" id="button"><span class="glyphicon glyphicon-pencil"></span>　Save Changes</a>

</div>
<%--</div>--%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $("#pwd").hide();
        $("#repeat_pwd1").hide();
        $("#repeat_pwd2").hide();
    });

    $("#password").change(function () {
        var input = $(this).val();
        if("111" != input) {
            $("#pwd").show();
        } else {
            $("#pwd").hide();
        }
    });

    $("#repeatPassword").change(function () {
        if($(this).val().length < 3) {
            $("#repeat_pwd1").show();
        } else {
            $("#repeat_pwd1").hide();
        }
    });

    $("#repeatPassword2").change(function () {
        var reset = $("#repeatPassword").val();
        var reset2 = $(this).val();
        if(reset != reset2) {
            $("#repeat_pwd2").show();
        } else {
            $("#repeat_pwd2").hide();
        }
    });

    $("#button").click(function(){
        $(this).submit();
        alert("Reset password success");
        window.location.href = "/admin";
    });

</script>
</body>
</html>

