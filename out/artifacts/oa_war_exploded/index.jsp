<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用OA系统</title>
</head>
<body>

<c:if test="${username != null}">
    <h3>你已登录账号 ${username}，请先<a href="${pageContext.request.contextPath}/user/logout">[退出登录]</a></h3>
</c:if>

<c:if test="${username == null}">
    <%--<a href="<%=request.getContextPath()%>/user/list">查看用户列表页</a>--%>
    <h1>User Login</h1>

    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <label>
            username: <input type="text" name="username" />
        </label>
        <br>
        <label>
            password:  <input type="password" name="password" />
        </label>
        <br>
        <label>
            十天免登陆 <input type="checkbox" name="remember" value="1">
        </label>
        <br>
        <input type="submit" value="login">
    </form>
</c:if>

</body>
</html>