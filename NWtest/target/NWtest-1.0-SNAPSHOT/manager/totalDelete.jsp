<%--
  Created by IntelliJ IDEA.
  User: TiNa
  Date: 2022/3/31
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="This is a testing demo page">
    <meta name="keywords" content="testing,html,demo">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>NW一轮考核</title>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/nw/User/css/home.css">
    <link rel="stylesheet" href="http://localhost:8080/nw/note/dist/css/bootstrap.min.css" type="text/css">
</head>
<body>
<div id="head">
    <div class="logo_title">
        <h1>&nbsp;&nbsp;&nbsp;欢迎！总管理员</h1>
        <p style="color: red">${requestScope.deleteMsg}</p>
    </div>
    <div class="navi">
        <ul>
            <li><a href = "/nw/manager/totalAdministrator.jsp" class="btn btn-success">退出登录</a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>
<div class="container">
    <div class="col" >
        <c:forEach items="${requestScope.notes}" var="notes">
            <div class="col-sm-2 col-md-3">
                <a href="#" class="thumbnail">
                    <img src="http://localhost:8080/nw/note/sample.jpg"
                         alt="通用的占位符缩略图">
                </a>
                <form action="http://localhost:8080/nw/ManagerServlet" method="post" >
                    <input type="submit" value="删除"  name="action" class="btn btn-warning" onclick="return confirm('确认删除吗？')">
                    <input type="hidden" name="noteId" value="${notes.id}">
                    <input type="hidden" name="account" value="${requestScope.account}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                </form>
                <table class="table table-bordered">
                    <tr class="warning">
                        <td>
                            <h4 class="center-block">${notes.title}</h4>
                        </td>
                    </tr>
                    <tr class="success">
                        <td>
                                ${notes.main}
                        </td>
                    </tr>
                    <tr class="well">
                        <td>
                            所属区域：${notes.zoomName}
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>
</div>



<script src="lib/jquery-2.1.1.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>