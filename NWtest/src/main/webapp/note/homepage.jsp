<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>NW一轮考核</title>
	<link rel="stylesheet" href="http://localhost:8080/nw/note/dist/css/bootstrap.min.css" type="text/css">
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
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post">
		<input type="submit" value="个人主页" name="action" class="btn btn-success">
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form>
	</li>
	&nbsp;&nbsp;
	<li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
</ul>
<ul class="breadcrumb " style="color: cornsilk;">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="动漫区" name="action" >
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="学习区" name="action" >
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="科技区" name="action" >
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="游戏区" name="action" >
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="美食区" name="action" >
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
</ul>
<form class="form-inline container" role="form" action="http://localhost:8080/nw/SearchServlet" method="post">
	<div class="form-group">
		<label class="sr-only" for="name">搜索框</label>
		<input type="text" class="form-control" id="name" name="tag"
			   placeholder="明日方舟" value="明日方舟">
	</div>
	<button type="submit" class="btn btn-default" name="action">搜索</button>
	<input type="hidden" value="${requestScope.username}" name="username">
	<input type="hidden" value="${requestScope.password}" name="password">
</form>
<br>
	<div class="container">
	<div class="col" >
		<c:forEach items="${requestScope.note}" var="note">
	    <div class="col-sm-2 col-md-3">
	        <a href="http://localhost:8080/nw/NoteDetailServlet?noteId=${note.id}&username=${requestScope.username}&password=${requestScope.password}
	        " class="thumbnail"  >
				<img src="${note.notePictureUrl}"
					 alt="通用的占位符缩略图">
			</a>

			<table class="table table-bordered">
				<tr class="warning">
					<td>
						<h4 class="center-block">${note.title}</h4>
					</td>
				</tr>
				<tr class="success">
					<td>
							${note.main}
					</td>
				</tr>
				<tr class="well">
					<td>
						所属区域：${note.zoomName}
					</td>
				</tr>
			</table>
	    </div>
		</c:forEach>
	</div>
	</div>
<div class=" container">
	<ul class="pagination">

		<c:if test="${requestScope.pageNo!=1}">
			<li><a href="http://localhost:8080/nw/UserLoginServlet?username=${requestScope.username}&password=${requestScope.password}&pageNo=${requestScope.pageNo-1}">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${requestScope.pageNo}" end="${requestScope.pageNo+5}" var="i">
			<c:if test="${i==requestScope.pageNo}">
			   <li class="active"><a href="#">${i}</a></li>
			</c:if>
			<c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
				<li><a href="http://localhost:8080/nw/UserLoginServlet?username=${requestScope.username}&password=${requestScope.password}&pageNo=${i}">${i}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${requestScope.pageNo!=requestScope.pageTotal}">
			<li><a href="http://localhost:8080/nw/UserLoginServlet?username=${requestScope.username}&password=${requestScope.password}&pageNo=${requestScope.pageNo+1}">&raquo;</a></li>
		</c:if>
		<li class="h4" style="color: #5bc0de">当前第${requestScope.pageNo}页</li>
		<li class="h4" style="color: #5bc0de">共${requestScope.pageTotal}页</li>
		<li class="h4" style="color: #5cb85c">共${requestScope.record}条记录</li>
	</ul>
</div>
</body>
</html>