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
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">
</head>
<body>
<div id="head">
    <div class="logo_title">
        <h1>小红薯的个人空间&nbsp;&nbsp;&nbsp;欢迎！ <h4 style="color: #4cae4c; ">${requestScope.username}</h4></h1>
        <h2>标记我的生活</h2>
        <p style="color: red">${requestScope.addTagResult}</p>
    </div>
    <div class="navi">
        <ul>
            <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/login" method="post">
                <input type="submit" value="首页" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">
                <input type="hidden" value="${requestScope.password}" name="password">
            </form>
            <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/page/findPage" method="post">
                <input type="submit" value="个人主页" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">
                <input type="hidden" value="${requestScope.password}" name="password">
            </form>
            </li>
            </li>
            <li><a href = "/nw/User/page/login.jsp" class="btn btn-success">退出登录</a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>
<div class="container" >
    <div class="col" >
            <div class="col-sm-2 col-md-3 "  >
                <a href="#" class="thumbnail">
                        <img src="${requestScope.note.notePictureUrl}"
                             alt="通用的占位符缩略图">
                </a>
                <table class="table table-bordered">
                    <tr class="warning">
                        <td>
                            <h4 class="center-block">${requestScope.note.title}</h4>
                        </td>
                    </tr>
                    <tr class="success">
                        <td>
                                ${requestScope.note.main}
                        </td>
                    </tr>
                    <tr class="well">
                        <td>
                            所属区域：${requestScope.note.zoomName}
                        </td>
                    </tr>
                    <tr class="well">
                        <td>
                            已有标签：
                            <c:forEach items="${requestScope.tags}" var="tag">
                                <p style="color: #5cb85c">#${tag.tagMain}</p>
                            </c:forEach>
                        </td>
                    </tr>

                </table>
            </div>
    </div>
</div>

<form role="form" method="post" class="container" action="http://localhost:8080/nw/user/updateNote">
    <div class="form-group container">
        <p style="color: red">${requestScope.updateMsg}</p>
        <label for="head" >修改标题</label>
        <input type="text" class="form-control" placeholder="起一个新的标题~" name="newTitle">
        <br>
        <input type="submit" name="action" value="确认修改标题" class="btn btn-success">
        <input type="hidden" name="id" value="${requestScope.note.id}">
        <input type="hidden" name="username" value="${requestScope.username}">
        <input type="hidden" name="password" value="${requestScope.password}">
    </div>
</form>
<form role="form" method="post" class="container" action="http://localhost:8080/nw/user/updateNote">
    <div class="form-group " >
        <label for="head">修改笔记内容</label>
        <textarea class="form-control" rows="3" placeholder="不要什么都不写噢" name="newMain"></textarea>
        <br>
        <input type="submit" name="action" value="确认修改内容" class="btn btn-warning">
        <input type="hidden" name="id" value="${requestScope.note.id}">
        <input type="hidden" name="username" value="${requestScope.username}">
        <input type="hidden" name="password" value="${requestScope.password}">
    </div>
</form>
<form role="form" method="post" class="container" action="http://localhost:8080/nw/user/updateNote">
    <div class="form-group " >
        <label for="head">为笔记添加标签</label>
        <br>
        <label for="head">游戏区：</label>
        <div class="row">
        <div class="checkbox-inline">
            <label><input type="checkbox" name="tag" value="英雄联盟">英雄联盟</label>
        </div>
        <div class="checkbox-inline">
            <label><input type="checkbox"  name="tag" value="lpl比赛">lpl比赛</label>
        </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="明日方舟">明日方舟</label>
            </div>
        </div>
        <br>
        <label for="head">学习区：</label>
        <div class="row">
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="考研">考研</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="高考">高考</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="中考">中考</label>
            </div>
        </div>
        <br>
        <label for="head">美食区：</label>
        <div class="row">
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="甜品类">甜品类</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="厨师教学">厨师教学</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="美食图鉴">美食图鉴</label>
            </div>
        </div>
        <br>
        <label for="head">科技区：</label>
        <div class="row">
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="科教兴国">科教兴国</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="测评">测评</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="手机">手机</label>
            </div>
        </div>
        <br>
        <label for="head">动漫区：</label>
        <div class="row">
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="新番">新番</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="mad">mad</label>
            </div>
            <div class="checkbox-inline">
                <label><input type="checkbox" name="tag" value="混剪">混剪</label>
            </div>
        </div>

        <input type="hidden" name="id" value="${requestScope.note.id}">
        <input type="hidden" name="username" value="${requestScope.username}">
        <input type="hidden" name="password" value="${requestScope.password}">
        <input type="submit" name="action" value="确认添加" class="btn btn-success">
    </div>
</form>
<br>
<br>
<br>
<h4 style="color: #5cb85c" class="container">为笔记添加图片：</h4>
<form action="http://localhost:8080/nw/user/fileUpLoad" method="post" enctype="multipart/form-data" class="container" role="form">
    <input type="hidden" name="id" value="${requestScope.note.id}">
    <input type="hidden" name="username" value="${requestScope.username}">
    <input type="hidden" name="password" value="${requestScope.password}">
    <input type="file" name="notePicture" class="btn-warning">
    <br>
    <input type="submit" value="提交" class="btn btn-success">
</form>


</body>
</html>
