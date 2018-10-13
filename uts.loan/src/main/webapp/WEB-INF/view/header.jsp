<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/29/18
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
  This is header.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <%--TODO:添加uts图标--%>
            <img  width="100" src="/static/img/uts.JPG" class="img-circle"/>
            <p class="navbar-brand">UTS Loan System</p>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${! empty sessionScope.type}">
                    <li><a href="/logout">Log out</a></li>
                </c:if>
                <c:if test="${empty sessionScope.type}">
                    <li><a href="/login">Log in</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

