
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
        <h1>&nbsp;&nbsp;&nbsp;欢迎！超级管理员</h1>
        <p style="color: red">${requestScope.deleteMsg}</p>
    </div>
    <div class="navi">
        <ul>
            <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/manager/batchDeleteNote" method="post">
                <input type="submit" value="批量删除" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">

                <input type="hidden" value="${requestScope.root}" name="root">
            </form>
            </li>
            <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/page/findPage" method="post">
                <input type="submit" value="个人主页" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">

                <input type="hidden" value="${requestScope.root}" name="root">
            </form>
            </li>
            <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/loginpage" method="post">
                <input type="submit" value="首页" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">

                <input type="hidden" value="${requestScope.root}" name="root">
            </form>
            </li>
            <li><a href = "http://localhost:8080/nw/User/page/login.jsp" class="btn btn-success">退出登录</a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>
<div class="container">
    <div class="col" >
        <c:forEach items="${requestScope.notes}" var="notes">
            <div class="col-sm-2 col-md-3">
                <a href="#" class="thumbnail">
                    <img src="${notes.notePictureUrl}"
                         alt="通用的占位符缩略图">
                </a>
                <form action="http://localhost:8080/nw/manager/deleteNote" method="post" >
                    <input type="submit" value="删除"  name="action" class="btn btn-warning" onclick="return confirm('确认删除吗？')">
                    <input type="hidden" name="noteId" value="${notes.id}">
                    <input type="hidden" name="username" value="${requestScope.username}">

                    <input type="hidden" name="pageNo" value="${requestScope.pageNo}">
                    <input type="hidden" value="${requestScope.root}" name="root">
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


<div class=" container ">
    <ul class="pagination">

        <c:if test="${requestScope.pageNo!=1}">
            <li><a href="http://localhost:8080/nw/manager/chargeAllNote?username=${requestScope.username}&pageNo=${requestScope.pageNo-1}">&laquo;</a></li>
        </c:if>
        <c:if test="${requestScope.pageNo-3<=0&&requestScope.pageTotal>=6}">
            <c:forEach begin="${1}" end="${6}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeAllNote?username=${requestScope.username}&pageNo=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageNo-3>0&&requestScope.pageNo+2<=requestScope.pageTotal&&requestScope.pageTotal>=6}">
            <c:forEach begin="${requestScope.pageNo-3}" end="${requestScope.pageNo+2}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeAllNote?username=${requestScope.username}&pageNo=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageNo+2>requestScope.pageTotal&&requestScope.pageTotal>=6}">
            <c:forEach begin="${requestScope.pageNo-(5-(requestScope.pageTotal-requestScope.pageNo))}" end="${requestScope.pageTotal}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeAllNote?username=${requestScope.username}&pageNo=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageTotal<6}">
            <c:forEach begin="${1}" end="${requestScope.pageTotal}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeAllNote?username=${requestScope.username}&pageNo=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageNo!=requestScope.pageTotal}">
            <li><a href="http://localhost:8080/nw/manager/chargeAllNote?username=${requestScope.username}&pageNo=${requestScope.pageNo+1}">&raquo;</a></li>
        </c:if>
        <li class="h4" style="color: #5bc0de">当前第${requestScope.pageNo}页</li>
        <li class="h4" style="color: #5bc0de">共${requestScope.pageTotal}页</li>
        <li class="h4" style="color: #5cb85c">共${requestScope.record}条记录</li>
    </ul>
</div>


<form class="form-inline container" role="form" method="post" action="http://localhost:8080/nw/manager/chargeAllNote">
    <p style="color: red">${requestScope.jumpMsg}</p>
    <input type="text" class="form-control"  placeholder="输入页码" name="pageNo">
    <input type="hidden" name="username" value="${requestScope.username}">

    <input type="hidden" value="${requestScope.root}" name="root">
    <input type="submit" value="跳转至" class="btn btn-success">
</form>
<br>
<form class="form-inline container" role="form" method="post" action="http://localhost:8080/nw/manager/chargeAllNote">
    <input type="hidden" name="username" value="${requestScope.username}">

    <input type="hidden" name="pageNo" value="1">
    <input type="hidden" value="${requestScope.root}" name="root">
    <input type="submit" value="首页" class="btn btn-success">
</form>
<br>
<form class="form-inline container" role="form" method="post" action="http://localhost:8080/nw/manager/chargeAllNote">
    <input type="hidden" name="username" value="${requestScope.username}">

    <input type="hidden" name="pageNo" value="${requestScope.pageTotal}">
    <input type="hidden" value="${requestScope.root}" name="root">
    <input type="submit" value="尾页" class="btn btn-success">
</form>

</body>
</html>