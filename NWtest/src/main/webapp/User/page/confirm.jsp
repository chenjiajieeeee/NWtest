<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">

</head>

<body  background="http://localhost:8080/nw/User/page/background.jpg"
       style="background-repeat:no-repeat;
               background-attachment:fixed;
               background-size:90% 100%;
               background-position: center;"
       >
<br>
<br>
<br>


<br>
    <div class="container">
    <form action="http://localhost:8080/nw/user/confirm" method="post" role="form">
                <p class="h3" style="color:red;">${requestScope.error}</p>
                <div class="text-center">
                    <p class="h3" style="color: white">${requestScope.username},你好！一封邮件已经发送到您的邮箱，请输入其中的验证码</p>
                </div>
                <div class="form-group">
                    <label for="name" style="color: white">输入您的验证码</label>
                    <input type="text" class="form-control" id="name"
                           placeholder="请输入验证码" name="code">
                    <input type="hidden" name="username" value="${requestScope.username}">
                    <br>
                    <input type="submit" value="进行验证" class="btn btn-success center-block">
                </div>


    </form>
    </div>
</body>
</html>
