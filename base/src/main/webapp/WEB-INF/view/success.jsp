<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.loan.uts.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: tong
  To change this template use File | Settings | File Templates.
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
<%--<div class="container main">--%>
<div class="col-md-9">
    <h1>You application is successfully submitted.</h1>
    <p style="text-indent: 2em; margin-top: 30px;">
        The system will go back in <span id="time">5</span> seconds automatically.
        If it didn't go back, click <a href="/student/applications" title="Go back">link</a> to get back</p>
    <script type="text/javascript">
        delayURL();
        function delayURL() {
            var delay = document.getElementById("time").innerHTML;
            var t = setTimeout("delayURL()", 1000);
            if (delay > 0) {
                delay--;
                document.getElementById("time").innerHTML = delay;
            } else {
                clearTimeout(t);
                window.location.href = "/student/applications";
            }
        }
    </script>
<%--<%response.setHeader("refresh","3; /student/applications");%>--%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
