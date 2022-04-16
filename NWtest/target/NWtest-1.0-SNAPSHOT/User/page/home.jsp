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
            <p style="color: red">${requestScope.sendMsg}</p>
            <p style="color: red">${requestScope.deleteMsg}</p>
            <p style="color: red">${requestScope.updateSuccessMsg}</p>
            <p style="color: red">${requestScope.uploadMsg}</p>
        </div>
        <div class="navi">
           <ul>
               <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/login" method="post">
                   <input type="submit" value="首页" name="action" class="btn btn-success">
                   <input type="hidden" value="${requestScope.username}" name="username">
                   <input type="hidden" value="${requestScope.password}" name="password">
               </form>
               </li>
               <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/UserRoleServlet" method="post">
                   <input type="submit" value="发布笔记" name="action" class="btn btn-success">
                   <input type="hidden" value="${requestScope.username}" name="username">
                   <input type="hidden" value="${requestScope.password}" name="password">
               </form>
               </li>
               <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/updateUserInformation" method="post">
                   <input type="submit" value="修改个人信息" name="action" class="btn btn-success">
                   <input type="hidden" value="${requestScope.username}" name="username">
                   <input type="hidden" value="${requestScope.password}" name="password">
               </form>
               </li>
               <li><a href = "/nw/User/page/login.jsp" class="btn btn-success">退出登录</a></li>
           </ul>
        </div>
        <div class="clear"></div>
    </div>
    <hr>
    <h4 style="color: #5cb85c" class="container">审核中的笔记</h4>
    <div class="container">
        <div class="col" >
            <c:forEach items="${requestScope.notes4}" var="notes">
                <div class="col-sm-2 col-md-3">
                    <a href="#" class="thumbnail"
                    >
                        <img src="${notes.notePictureUrl}"
                             alt="通用的占位符缩略图">
                    </a>
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
    <hr>
    <h4 style="color: #5cb85c" class="container">被驳回的笔记</h4>
    <div class="container">
        <div class="col" >
            <c:forEach items="${requestScope.notes5}" var="notes">
                <div class="col-sm-2 col-md-3">
                    <a href="#" class="thumbnail"
                    >
                        <img src="${notes.notePictureUrl}"
                             alt="通用的占位符缩略图">
                    </a>
                    <form action="http://localhost:8080/nw/user/updateNote" method="post" >
                        <input type="submit" value="修改" name="action" class="btn btn-warning">
                        <input type="hidden" name="username" value="${requestScope.username}">
                        <input type="hidden" name="password" value="${requestScope.password}">
                        <input type="hidden" name="id" value="${notes.id}">
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
    <hr>
    <h4 style="color: #5cb85c" class="container">我发布的笔记</h4>
    <div class="container">
        <div class="col" >
            <c:forEach items="${requestScope.notes6}" var="notes">
                <div class="col-sm-2 col-md-3">
                    <a href="http://localhost:8080/nw/note/detail?noteId=${notes.id}&username=${requestScope.username}&password=${requestScope.password}
	                 " class="thumbnail"
                    >
                        <img src="${notes.notePictureUrl}"
                             alt="通用的占位符缩略图">
                    </a>
                    <form action="http://localhost:8080/nw/user/updateNote" method="post" >
                    <input type="submit" value="修改" name="action" class="btn btn-warning">
                    <input type="submit" value="删除"  name="action" class="btn btn-warning" onclick="return confirm('确认删除吗？')">
                        <input type="hidden" name="username" value="${requestScope.username}">
                        <input type="hidden" name="password" value="${requestScope.password}">
                        <input type="hidden" name="id" value="${notes.id}">
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
    <hr>
    <h4 style="color: #2aabd2" class="container">我点赞过的笔记</h4>
    <div class="container">
        <div class="col" >
            <c:forEach items="${requestScope.notes1}" var="notes">
                <c:if test="${notes.releaseStatus.equals('1')}">
                <div class="col-sm-2 col-md-3">
                    <a href="http://localhost:8080/nw/note/detail?noteId=${notes.id}&username=${requestScope.username}&password=${requestScope.password}
	                  " class="thumbnail"
                    >
                        <img src="${notes.notePictureUrl}"
                             alt="通用的占位符缩略图">
                    </a>
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
                </c:if>
                <c:if test="${!notes.releaseStatus.equals('1')}">
                    <div class="col-sm-2 col-md-3">
                        <a href="#" class="thumbnail">
                            <img src="http://localhost:8080/nw/notebook/check.jpeg"
                                 alt="通用的占位符缩略图">
                        </a>
                        <p>${notes.title}</p>
                        <p>该笔记主人修改了该笔记，审核后即可查看~</p>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <hr>
    <h4 style="color: #8a6d3b" class="container">我收藏的笔记</h4>
    <div class="container">
        <div class="col" >
            <c:forEach items="${requestScope.notes2}" var="notes">
                <c:if test="${notes.releaseStatus.equals('1')}">
                <div class="col-sm-2 col-md-3">
                        <a href="http://localhost:8080/nw/note/detail?noteId=${notes.id}&username=${requestScope.username}&password=${requestScope.password}
	                  " class="thumbnail"
                        >
                            <img src="${notes.notePictureUrl}"
                                 alt="通用的占位符缩略图">
                        </a>
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
                </c:if>
                <c:if test="${!notes.releaseStatus.equals('1')}">
                    <div class="col-sm-2 col-md-3">
                    <a href="#" class="thumbnail">
                        <img src="http://localhost:8080/nw/notebook/check.jpeg"
                             alt="通用的占位符缩略图">
                    </a>
                        <p>${notes.title}</p>
                        <p>该笔记主人修改了该笔记，审核后即可查看~</p>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
<hr>
    <h4 style="color: red" class="container">我评论的笔记</h4>
    <div class="container">
        <div class="col" >
            <c:forEach items="${requestScope.notes3}" var="notes">
                <c:if test="${notes.releaseStatus.equals('1')}">
                <div class="col-sm-2 col-md-3">
                    <a href="http://localhost:8080/nw/note/detail?noteId=${notes.id}&username=${requestScope.username}&password=${requestScope.password}
	                  " class="thumbnail"
                    >
                        <img src="${notes.notePictureUrl}"
                             alt="通用的占位符缩略图">
                    </a>
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
                </c:if>
                <c:if test="${!notes.releaseStatus.equals('1')}">
                    <div class="col-sm-2 col-md-3">
                    <a href="#" class="thumbnail">
                        <img src="http://localhost:8080/nw/notebook/check.jpeg"
                             alt="通用的占位符缩略图">
                    </a>
                        <p>${notes.title}</p>
                        <p>该笔记主人修改了该笔记，审核后即可查看~</p>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>

</body>
</html>