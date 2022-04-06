<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TiNa
  Date: 2022/3/30
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/note/dist/css/bootstrap.min.css">
</head>
<body background="http://localhost:8080/nw/manager/img/manager.jpg">
    <ul class="breadcrumb " style="color: cornsilk;">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <p style="color: red">${deleteMsg}</p>
        <p style="color: red">${checkMsg}</p>
        <p style="color: red">${forbidMsg}</p>
        <li class="active"><h3 style="color: #67b168" align="center">欢迎你！${requestScope.zoomName} 的管理员</h3></li>
        &nbsp;&nbsp;
        <li><a href="http://localhost:8080/nw/manager/manager.jsp" class="btn btn-success">退出登录</a></li>
    </ul>
    <div class="container">
    <table class="table table-bordered " >
        <caption style="color: #4cae4c">你需要管理的笔记的详细信息：</caption>
        <thead style="color: white">
        <tr>
            <th>User_id</th>
            <th>主体内容</th>
            <th>笔记标题</th>
            <th>目前发布状态</th>
            <th>对笔记执行删除操作</th>
            <th>同意笔记发布？</th>
        </tr>
        </thead>
        <tbody style="color: white">
        <c:forEach items="${requestScope.notes}" var="notes">
        <tr>
            <td>${notes.userId}</td>
            <td>${notes.main}</td>
            <td>${notes.title}</td>
            <td>${notes.releaseStatus}</td>
          <form action="/nw/ManagerServlet" method="post">
            <td><input type="submit" name="action" value="删除笔记" class="btn btn-success" onclick="return confirm('确认删除？')"/></td>
            <td><input type="submit" name="action" value="同意" class="btn btn-warning"></td>
              <input type="hidden" name="noteId" value="${notes.id}">
              <input type="hidden" name="account" value="${requestScope.account}">
              <input type="hidden" name="password" value="${requestScope.password}">
              <input type="hidden" name="releaseStatus" value="${notes.releaseStatus}">
              <input type="hidden" name="notes" value="${requestScope.notes}">
              <input type="hidden" name="zoomName" value="${requestScope.zoomName}">
        </form >
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <div class="container">
    <table class="table table-bordered "  >
        <caption style="color: #4cae4c">你需要管理的用户的详细信息：</caption>
        <thead style="color: white">
        <tr>
            <th>User_id</th>
            <th>目前在该区域的禁用状态</th>
            <th>对用户执行操作</th>
        </tr>
        </thead>
        <tbody style="color: white">
        <c:forEach items="${requestScope.allUserZoomStatus}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
                <form action="/nw/ManagerServlet" method="post">
                    <td><input type="submit" name="action" value="禁用/解封该用户" class="btn btn-warning"></td>
                    <input type="hidden" name="account" value="${requestScope.account}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                    <input type="hidden" name="zoomName" value="${requestScope.zoomName}">
                    <input type="hidden" name="userId" value="${entry.key}">
                    <input type="hidden" name="userStatus" value="${entry.value}">
                </form >
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>

</body>
</html>
