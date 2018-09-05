<%@ page import="static com.loan.uts.controller.LoginController.USER_TYPE" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/29/18
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
  This is header.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <%--TODO:添加uts图标--%>
            <%--<img src="/resources/static/image/IMG_0514.JPG" class="img-circle"/>--%>
            <p class="navbar-brand">UTS Loan System</p>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
<<<<<<< HEAD
                <li><a href="/logout">Log out</a></li>
                <%--<li><a href="#">SVN</a></li>--%>
=======
                <% HttpSession httpSession = request.getSession(false);
                    String user_type = "";
                    if (httpSession != null) {
                         user_type = (String) httpSession.getAttribute(USER_TYPE);
                        if (user_type != null && !user_type.equals("")) {
                %>
                <li><a href="/logout">Log out</a></li>
                <%

                } else {
                %>
                <li><a href="/login">Log in</a></li>
                <%
                    }}
                %>
            </ul>
        </div>
    </div>
</nav>

