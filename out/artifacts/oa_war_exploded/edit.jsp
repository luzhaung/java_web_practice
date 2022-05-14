<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑用户</title>
</head>
<body>
<h3>欢迎${username}</h3>
<h1>编辑用户</h1>
<form action="${pageContext.request.contextPath}/user/update" method="post">
    <label>
        姓名
        <input type="text" name="name" value="${user.name}" />
    </label><br>
    <label>
        年龄
        <input type="number" name="age" value="${user.age}" />
    </label><br>
    <label>
        性别
        男: <input type="radio" name="sex" value="1" <c:if test="${user.sex == 1}">checked</c:if> />
        女: <input type="radio" name="sex" value="0" <c:if test="${user.sex == 0}">checked</c:if> />
    </label><br>
    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="编辑">
</form>
</body>
</html>