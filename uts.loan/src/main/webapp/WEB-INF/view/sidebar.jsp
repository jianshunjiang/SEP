<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 31/08/18
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-2">
    <ul class="nav nav-list">
        <c:if test="${not empty sessionScope.student}">
            <li class="nav-header">Welcome ${sessionScope.student.firstname} ${sessionScope.student.lastname}</li>
            <li class="active"><a href="/student"><i class="icon-white icon-home"></i> Home</a></li>
            <li><a href="/student/applications"><span class="glyphicon glyphicon-file"></span> Applications</a></li>
            <li><a href="/student/history"><span class="glyphicon glyphicon-dashboard"></span> History</a></li>
            <li><a href="/student/studentProfile"><span class="glyphicon glyphicon-user"></span> Student Profile</a> </li>
        </c:if>

        <c:if test="${not empty sessionScope.manager}">
            <li class="nav-header">Welcome ${sessionScope.manager.firstname} ${sessionScope.manager.lastname}</li>
            <li class="active"><a href="/loanManager"><i class="icon-white icon-home"></i> Home</a></li>
            <li><a href="/loanManager/applications"><span class="glyphicon glyphicon-file"></span> Applications</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
        </c:if>

        <c:if test="${not empty sessionScope.admin}">
            <li class="nav-header">Welcome ${sessionScope.admin.username}</li>
            <li class="active"><a href="/admin"><i class="icon-white icon-home"></i> Home</a></li>
            <li><a href="/admin/managers"><span class="glyphicon glyphicon-user"></span> Staffs</a></li>
            <li><a href="/admin/profile"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
        </c:if>
    </ul>
</div>
