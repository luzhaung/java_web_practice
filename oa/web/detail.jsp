<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户详情</title>
</head>
<body>
    <h3>欢迎${username}</h3>
    <h1>用户详情</h1>
    <div>
        姓名: ${user.name}
    </div>
    <div>
        年龄: ${user.age}
    </div>
    <div>
        性别:
        <c:choose>
            <c:when test="${user.sex == 1}">
                男
            </c:when>
            <c:otherwise>
                女
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>