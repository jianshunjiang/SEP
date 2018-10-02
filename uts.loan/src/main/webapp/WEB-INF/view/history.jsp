<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 10/1/18
  Time: 3:49 PM
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

<div class="col-md-9">
    <form:form action="/student/history" method="post" role="form">
        <div class="row">
            <div class="col-md-2">
                <select id="type" name="type" onchange="refreshSearchBar()" class="selectpicker show-tick form-control">
                    <option value="1" selected="selected">By month (Submit date)</option>
                    <option value="0">Title</option>
                </select>
            </div>
            <div class="col-md-4" >
                <input type="text" id="title" name="title" placeholder="Search by title..." class="form-control"
                       style="display: none">
                <input type="month" id="month" class="form-control" name="month">
            </div>
            <div class="col-md-2" >
                <input type="submit" class="form-control" value="Search" style="margin-left:3px">
            </div>
        </div>
    </form:form>
    <c:if test="${empty applications}"><h1>No result found.</h1></c:if>
    <c:if test="${not empty applications}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Application</th>
                <th>Title</th>
                <th>Submit Date</th>
                <th>Result Date</th>
                <th>Loan Manager</th>
                <th>Result</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applications}" var="application">
                <tr class="active">
                    <td>${application.id}</td>
                    <td>${application.title}</td>
                    <td>${application.submitDateString()}</td>
                    <td>${application.resultDateString()}</td>
                    <td>${application.manager.firstname} ${application.manager.lastname}</td>
                    <td>${application.status}</td>
                    <td>
                        <button type="button" class="btn btn-info">Detail</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<%--</div>--%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    function refreshSearchBar() {
        var type = document.getElementById("type");
        var index = type.selectedIndex;
        var option = type[index];
        var month = document.getElementById("month");
        var title = document.getElementById("title");
        if (option.value === 1) {
            title.style.display = "none";
            month.style.display = "inline"
        }
        else {
            month.style.display = "none";
            title.style.display = "inline"
        }
    }
</script>
</body>
</html>


</body>
</html>
