<%@ page import="com.loan.uts.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 31/08/18
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Student student = (Student) session.getAttribute("Student");%>
<%--<div class="container navigation">--%>
    <%--<div class="row">--%>
    <div class="col-md-2">
        <ul class="nav nav-list">
            <li class="nav-header">Welcome <%=student.getFirstname()%>  <%=student.getLastname()%></li>
            <li class="active"><a href="/student"><i class="icon-white icon-home"></i> Home</a></li>
            <li><a href="/student/applications"><span class="glyphicon glyphicon-file"></span> Applications</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-dashboard"></span> History</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
        </ul>
    </div>
    <%--</div>--%>
</div>