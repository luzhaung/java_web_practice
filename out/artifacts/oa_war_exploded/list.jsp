<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>用户列表页</title>
</head>

<style>
  table {
    border: 0;
    width: 50%;
    border-collapse: collapse;
  }
  th {
    font-weight: bold;
  }
  th,td {
    padding: 10px 20px;
    text-align: center;
    border: 1px solid #dee2e6;
  }
  .addUser {
    margin-bottom: 20px;
  }
</style>
<script>
  function del(id) {
    if (window.confirm("确定删除吗?")) {
      window.location.href="${pageContext.request.contextPath}/user/delete?id=" + id;
    }
  }
</script>
<body>
  <h3>欢迎 ${username}，当前在线人数${onlineUserCount}。<a href="${pageContext.request.contextPath}/user/logout">[退出登录]</a></h3>
  <h1>用户列表</h1>
  <div class="addUser"><a href="${pageContext.request.contextPath}/add.jsp">新增用户</a></div>

  <table>
    <tr>
      <th>编号</th>
      <th>姓名</th>
      <th>年龄</th>
      <th>性别</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${userList}" var="user" varStatus="userStatus">
      <tr>
        <td>${userStatus.count}</td>
        <td>${user.name}</td>
        <td>${user.age}</td>
        <c:choose>
          <c:when test="${user.sex == 1}">
            <td>男</td>
          </c:when>
          <c:otherwise>
            <td>女</td>
          </c:otherwise>
        </c:choose>
        <td>
          <a href="javascript:void(0);" onclick="del(${user.id})">删除</a>
          <a href="${pageContext.request.contextPath}/user/detail?id=${user.id}">查看</a>
          <a href="${pageContext.request.contextPath}/user/edit?id=${user.id}">编辑</a>
        </td>
      </tr>
    </c:forEach>

  </table>
</body>
</html>