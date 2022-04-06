<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
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
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<li class="active"><h3 style="color: #67b168">欢迎你！${requestScope.username}</h3></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post">
		<input type="submit" value="个人主页" name="action" formmethod="post" class="btn btn-success">
		<input type="hidden" value="${requestScope.username}" name="username" >
		<input type="hidden" value="${requestScope.password}" name="password" >
	</form>
	</li>
	&nbsp;&nbsp;
	<li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-success">退出登录</a></li>
</ul>
<ul class="breadcrumb " style="color: cornsilk;">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<li class="active">学习区</li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="动漫区" name="action">
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="科技区" name="action">
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="游戏区" name="action">
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
	<li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="美食区" name="action">
		<input type="hidden" value="${requestScope.username}" name="username">
		<input type="hidden" value="${requestScope.password}" name="password">
	</form></li>
</ul>
<div class="container">
	<div class="col" >
		<c:forEach items="${requestScope.notes}" var="notes">
			<div class="col-sm-2 col-md-3">
				<a href="http://localhost:8080/nw/NoteDetailServlet?noteId=${notes.id}&username=${requestScope.username}&password=${requestScope.password}
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
		</c:forEach>
	</div>
</div>
<div class=" container">
	<ul class="pagination">

		<c:if test="${requestScope.pageNo!=1}">
			<li><a href="http://localhost:8080/nw/PageServlet?username=${requestScope.username}&password=${requestScope.password}&pageNo=${requestScope.pageNo-1}&action=学习区">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${requestScope.pageNo}" end="${requestScope.pageNo+5}" var="i">
			<c:if test="${i==requestScope.pageNo}">
				<li class="active"><a href="#">${i}</a></li>
			</c:if>
			<c:if test="${i<=requestScope.pageTotal&&i!=requestScope.pageNo}">
				<li><a href="http://localhost:8080/nw/PageServlet?username=${requestScope.username}&password=${requestScope.password}&pageNo=${i}&action=学习区">${i}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${requestScope.pageNo!=requestScope.pageTotal}">
			<li><a href="http://localhost:8080/nw/PageServlet?username=${requestScope.username}&password=${requestScope.password}&pageNo=${requestScope.pageNo+1}&action=学习区">&raquo;</a></li>
		</c:if>
		<li class="h4" style="color: #5bc0de">当前第${requestScope.pageNo}页</li>
		<li class="h4" style="color: #5bc0de">共${requestScope.pageTotal}页</li>
		<li class="h4" style="color: #5cb85c">共${requestScope.record}条记录</li>
	</ul>
</div>
</body>
</html>
