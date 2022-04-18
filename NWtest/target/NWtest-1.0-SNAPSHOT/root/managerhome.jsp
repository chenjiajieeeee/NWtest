<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">
</head>
<body>

<ul class="breadcrumb " style="color: cornsilk;">
    <li class="active "><h2 style="color: crimson;">小红书</h2></li>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <li class="active "><h4 style="color: black;">标记我的生活</h4></li>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <li class="active"><h3 style="color: #67b168">欢迎你！${requestScope.username}</h3></li>
    &nbsp;&nbsp;
    <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
    <li><a href="http://localhost:8080/nw/user/login?username=${requestScope.username}&password=${requestScope.password}&root=${requestScope.root}" class="btn btn-warning">返回首页</a></li>
    <li><form  action="http://localhost:8080/nw/manager/chargeNoteBatch" method="post">
        <input type="submit" value="批量操作" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
        <input type="hidden" value="${requestScope.root}" name="root">
    </form>
    </li>
    <li><form  action="http://localhost:8080/nw/manager/dealReportedNote" method="post">
        <input type="submit" value="处理被举报笔记" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
        <input type="hidden" value="${requestScope.root}" name="root">
    </form>
    </li>
</ul>
    <p style="color: red" class="container">${requestScope.deleteNoteMsg}</p>
<p style="color: red" class="container">${requestScope.agreeMsg}</p>
<p style="color: red" class="container">${requestScope.backMsg}</p>
<br>
<div class="container">
    <div class="col" >
        <c:forEach items="${requestScope.notes}" var="notes">
            <c:if test="${!notes.releaseStatus.equals('-2')}">
            <div class="col-sm-2 col-md-3">
                <a href="#" class="thumbnail">
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
                    <tr class="well">
                        <td>
                            <form role="form" method="post" action="http://localhost:8080/nw/manager/reNote">
                                发布状态：<c:if test="${notes.releaseStatus.equals('0')}">
                                未审核
                                <input type="submit" value="同意" class="btn btn-success" name="action">
                                <input type="submit" value="驳回" class="btn btn-success" name="action">
                            </c:if>
                                <c:if test="${notes.releaseStatus.equals('1')}">
                                    已发布
                                    <input type="submit" value="删除" name="action" class="btn btn-success" onclick="return confirm('确定删除笔记吗')">
                                </c:if>
                                <c:if test="${notes.releaseStatus.equals('-1')}">
                                    已驳回
                                </c:if>
                                    <input type="hidden" value="${requestScope.username}" name="username">
                                    <input type="hidden" value="${requestScope.password}" name="password">
                                    <input type="hidden" value="${requestScope.root}" name="root">
                                    <input type="hidden" value="${notes.id}" name="noteId">
                                    <input type="hidden" value="${requestScope.pageNo}" name="pageNo">
                                    <input type="hidden" value="${notes.releaseStatus}" name="releaseStatus">

                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
        </c:forEach>
    </div>
</div>

<div class=" container ">
    <ul class="pagination">

        <c:if test="${requestScope.pageNo!=1}">
            <li><a href="http://localhost:8080/nw/manager/chargeNote?username=${requestScope.username}&password=${requestScope.password}&pageNo=${requestScope.pageNo-1}&root=${requestScope.root}">&laquo;</a></li>
        </c:if>
        <c:if test="${requestScope.pageNo-3<=0&&requestScope.pageTotal>=6}">
            <c:forEach begin="${1}" end="${6}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeNote?username=${requestScope.username}&password=${requestScope.password}&pageNo=${i}&root=${requestScope.root}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageNo-3>0&&requestScope.pageNo+2<=requestScope.pageTotal&&requestScope.pageTotal>=6}">
            <c:forEach begin="${requestScope.pageNo-3}" end="${requestScope.pageNo+2}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeNote?username=${requestScope.username}&password=${requestScope.password}&pageNo=${i}&root=${requestScope.root}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageNo+2>requestScope.pageTotal&&requestScope.pageTotal>=6}">
            <c:forEach begin="${requestScope.pageNo-(5-(requestScope.pageTotal-requestScope.pageNo))}" end="${requestScope.pageTotal}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeNote?username=${requestScope.username}&password=${requestScope.password}&pageNo=${i}&root=${requestScope.root}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageTotal<6}">
            <c:forEach begin="${1}" end="${requestScope.pageTotal}" var="i">
                <c:if test="${i==requestScope.pageNo}">
                    <li class="active"><a href="#">${i}</a></li>
                </c:if>
                <c:if test="${i!=requestScope.pageNo}">
                    <li><a href="http://localhost:8080/nw/manager/chargeNote?username=${requestScope.username}&password=${requestScope.password}&pageNo=${i}&root=${requestScope.root}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.pageNo!=requestScope.pageTotal}">
            <li><a href="http://localhost:8080/nw/manager/chargeNote?username=${requestScope.username}&password=${requestScope.password}&pageNo=${requestScope.pageNo+1}&root=${requestScope.root}">&raquo;</a></li>
        </c:if>
        <li class="h4" style="color: #5bc0de">当前第${requestScope.pageNo}页</li>
        <li class="h4" style="color: #5bc0de">共${requestScope.pageTotal}页</li>
        <li class="h4" style="color: #5cb85c">共${requestScope.record}条记录</li>
    </ul>
</div>


<form class="form-inline container" role="form" method="post" action="http://localhost:8080/nw/manager/chargeNote">
    <p style="color: red">${requestScope.jumpMsg}</p>
    <input type="text" class="form-control"  placeholder="输入页码" name="pageNo">
    <input type="hidden" name="username" value="${requestScope.username}">
    <input type="hidden" name="password" value="${requestScope.password}">
    <input type="hidden" name="root" value="${requestScope.root}">
    <input type="submit" value="跳转至" class="btn btn-success">
</form>
<br>
<form class="form-inline container" role="form" method="post" action="http://localhost:8080/nw/manager/chargeNote">
    <input type="hidden" name="username" value="${requestScope.username}">
    <input type="hidden" name="password" value="${requestScope.password}">
    <input type="hidden" name="pageNo" value="1">
    <input type="hidden" name="root" value="${requestScope.root}">
    <input type="submit" value="首页" class="btn btn-success">
</form>
<br>
<form class="form-inline container" role="form" method="post" action="http://localhost:8080/nw/manager/chargeNote">
    <input type="hidden" name="username" value="${requestScope.username}">
    <input type="hidden" name="password" value="${requestScope.password}">
    <input type="hidden" name="pageNo" value="${requestScope.pageTotal}">
    <input type="hidden" name="root" value="${requestScope.root}">
    <input type="submit" value="尾页" class="btn btn-success">
</form>
</body>
</html>
