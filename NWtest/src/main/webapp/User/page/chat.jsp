<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">

    <meta charset="UTF-8">



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
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/loginpage" method="post">
        <input type="submit" value="首页" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
        <input type="hidden" value="${requestScope.root}" name="root">
    </form>
    </li>
    &nbsp;&nbsp;
    <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
</ul>


    <div class="container col">
        <div class="col-sm-2 col-md-2">
            <p class="h4" style="color: #4cae4c">关注列表</p>
            <br>
         <c:forEach items="${requestScope.users}" var="user">
             <p>${user.username}</p>
             <br>
         </c:forEach>
        </div>

        <div class="col-sm-2 col-md-2">
            <p class="h4" style="color: red">粉丝列表</p>
            <br>
            <c:forEach items="${requestScope.users1}" var="user">
                <p>${user.username}</p>
                <br>
            </c:forEach>
        </div>
    </div>

</body>
</html>