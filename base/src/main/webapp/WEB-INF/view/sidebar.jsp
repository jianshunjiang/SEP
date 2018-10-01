<%@ page import="com.loan.uts.model.Student" %>
<%@ page import="com.loan.uts.model.Manager" %>
<%@ page import="static com.loan.uts.controller.LoginController.*" %>
<%@ page import="com.loan.uts.model.Administrator" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 31/08/18
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userType = (String) session.getAttribute(USER_TYPE);
    Student student = null;
    Manager manager = null;
    Administrator admin = null;
    if (userType.equals(STUDENT)) student = (Student) session.getAttribute(STUDENT);
    if (userType.equals(LOAN_MANAGER)) manager = (Manager) session.getAttribute(LOAN_MANAGER);
    if(userType.equals(SYSTEM_ADMIN)) admin = (Administrator) session.getAttribute(SYSTEM_ADMIN);
%>
<%--<div class="container navigation">--%>
<%--<div class="row">--%>
<div class="col-md-2">
    <ul class="nav nav-list">
        <%
            if(student != null){
        %>
        <li class="nav-header">Welcome <%=student.getFirstname()%>  <%=student.getLastname()%>
        </li>
        <li class="active"><a href="/student"><i class="icon-white icon-home"></i> Home</a></li>
        <li><a href="/student/applications"><span class="glyphicon glyphicon-file"></span> Applications</a></li>
        <li><a href="/student/history"><span class="glyphicon glyphicon-dashboard"></span> History</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
        <%
            }else if(manager != null){
        %>
        <li class="nav-header">Welcome <%=manager.getFirstname()%>  <%=manager.getLastname()%>
        </li>
        <li class="active"><a href="/loanManager"><i class="icon-white icon-home"></i> Home</a></li>
        <li><a href="/loanManager/applications"><span class="glyphicon glyphicon-file"></span> Applications</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
        <%
            }
        %>
    </ul>
</div>
<%--</div>--%>
</div>