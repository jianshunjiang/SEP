<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 8/19/18
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UTS Loan System</title>
</head>
<body>
    <div class="container">
        <h1>Welcome</h1>
        <hr/>

        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <td>${student.id}</td>
            </tr>
            <tr>
                <th>Name</th>
                <td>${student.name}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${student.email}</td>
            </tr>
            <tr>
                <th>Bank Account</th>
                <td>${student.bankaccount}</td>
            </tr>
            <tr>
                <th>Phone</th>
                <td>${student.phone}</td>
            </tr>
        </table>
    </div>

</body>
</html>
