<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NW一轮考核</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/nw/User/css/login&register.css"/>
  <script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="login">
  <h1>小红书</h1>
  <form action="http://localhost:8080/nw/user/register" method="post">
    <p style="color: red">${requestScope.msg}</p>
    <span>
    <input type="text" name="username" placeholder="起一个好听的名字" required="required" onblur="check()" id="username">
    </span>
    <p style="color: red" id="error"></p>
    <p style="color: green" id="correct"></p>
    <input type="password" name="password" placeholder="输入密码" required="required">

    <input type="text" name="mail" placeholder="输入你的邮箱" required="required">
  <button type="submit">注册</button>
  <a href="http://localhost:8080/nw/User/page/login.jsp">已有账号？点击登录</a>
  </form>
</div>
<script type="text/javascript">
  function check() {
    $.post("http://localhost:8080/nw/user/registerCheck",{username:document.getElementById("username").value},function (data){
       if(data==="用户名已存在"){
         document.getElementById("error").innerHTML=data;
         document.getElementById("correct").innerHTML="";
       }else{
         document.getElementById("correct").innerHTML=data;
         document.getElementById("error").innerHTML="";
       }
    });
  }
</script>
</body>
</html>