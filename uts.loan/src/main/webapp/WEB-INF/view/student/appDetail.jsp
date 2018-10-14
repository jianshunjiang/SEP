<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/19/18
  Time: 2:33 PM
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
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>
<div class="col-md-9">
    <table class="table table-bordered">
        <caption style="align-content: center; text-align: center"><h2>Application Detail</h2></caption>
        <tbody>
        <tr>
            <td><strong>Application ID</strong></td>
            <td>${application.id}</td>
        </tr>
        <tr>
            <td><strong>Application Title</strong></td>
            <td>${application.title}</td>
        </tr>
        <tr>
            <td><strong>Student Name</strong></td>
            <td>${application.student.firstname} ${application.student.lastname}</td>
        </tr>
        <tr>
            <td><strong>Email</strong></td>
            <td>${application.student.email}</td>
        </tr>
        <tr>
            <td><strong>Phone</strong></td>
            <td>${application.student.phone}</td>
        </tr>
        <tr>
            <td><strong>Nationality</strong></td>
            <td>${application.student.nationality}</td>
        </tr>
        <tr>
            <td>Start year</td>
            <td>${application.student.startYear}</td>
        </tr>
        <tr>
            <td><strong>Date of birth</strong></td>
            <td>${application.student.dob}</td>
        </tr>
        <tr>
            <td><strong>Course</strong></td>
            <td>${application.student.course}</td>
        </tr>
        <tr>
            <td><strong>Gender</strong></td>
            <td>${application.student.gender}</td>
        </tr>
        <tr>
            <td><strong>Faculty</strong></td>
            <td>${application.student.faculty}</td>
        </tr>
        <tr>
            <td><strong>Bank Account</strong></td>
            <td>${application.student.bankaccount}</td>
        </tr>
        <tr>
            <td><strong>Application Content</strong></td>
            <td>${application.content}</td>
        </tr>
        <tr>
            <td><strong>Amount($)</strong></td>
            <td>${application.amount}</td>
        </tr>
        <tr>
            <td><strong>Years to pay back</strong></td>
            <td>${application.payBackYears}</td>
        </tr>
        <tr>
            <td><strong>Amount($)</strong></td>
            <td>${application.amount}</td>
        </tr>
        <tr>
            <td><strong>Status</strong></td>
            <td>${application.status}</td>
        </tr>
        <tr>
            <td><strong>Manager comment</strong></td>
            <td>${application.comment}</td>
        </tr>
        <tr>
            <td><strong>Total pay back amount</strong></td>
            <td>${application.sum}</td>
        </tr>
        <c:if test="${not empty attachments}">
            <tr>
                <td colspan="2"><strong>Attachments</strong></td>
            </tr>
        </c:if>
        <c:forEach items="${attachments}" var="attachment">
            <tr>
                <td><strong><a href="/attachment/download?filename=${attachment.name}">Download</a></strong></td>
                <td>${attachment.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${application.status.equals('Replied')}">
        <a role="button" class="btn btn-primary btn-lg" href="/student/applications/response?id=${application.id}"><span
                class="glyphicon glyphicon-primary"></span> Response</a>
    </c:if>
    <c:if test="${application.status.equals('Accepted')}">
        <a role="button" class="btn btn-primary btn-lg" href="/student/applications/contract?id=${application.id}"><span
                class="glyphicon glyphicon-primary"></span> Download Contract</a>
    </c:if>

</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>

</script>
</body>
</html>
