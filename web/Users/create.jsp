<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/7/2020
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>User Management</h1>
    <h2>
        <a href="list.jsp" style="text-decoration: none">List User All</a>
    </h2>
</div>
<center>
    <div>
        <form method="post">
            <table border="1" cellpadding="5">
                <caption>Add New User</caption>
                <tr>
                    <th>User Name :</th>
                    <td><input type="text" name="name" size="45"></td>
                </tr>

                <tr>
                    <th>User Email :</th>
                    <td><input type="email" name="email" size="45"></td>
                </tr>

                <tr>
                    <th>User Country :</th>
                    <td><input type="text" name="country" size="45"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="save"></td>
                </tr>
            </table>
        </form>
    </div>
</center>
</body>
</html>
