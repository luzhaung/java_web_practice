<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增用户</title>
</head>
<body>
    <h3>欢迎${username}</h3>
    <h1>新增用户</h1>
    <form action="${pageContext.request.contextPath}/user/save" method="post">
        <label>
            姓名: <input type="text" name="name" />
        </label><br>
        <label>
            年龄: <input type="number" name="age" />
        </label><br>
        <label>
            男: <input type="radio" name="sex" value="1" />&nbsp;&nbsp;
            女: <input type="radio" name="sex" value="0" />
        </label><br>
        <input type="submit" value="新增">
    </form>
</body>
</html>