<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="overflow-y" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">
    <script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="UTF-8">

    <style type="text/css">
        .div2 {
            width: 540px;
            height: 500px;
            padding: 50px;
            border: 1px solid green;
            overflow: auto;
        }
    </style>
    <script>
        let user;
        let ws;
        const ws_url = "ws://localhost:8080/nw/chat?username='${requestScope.username}'";
            //建立连接
          ws=new WebSocket(ws_url);
          ws.onopen=function () {
              console.log("已连接");
          };
          ws.onclose=function () {
              console.log("已关闭");
          };
          ws.onmessage=function (event) {
              console.log("有新的消息！");
              let data = event.data;
              if(data==="该用户未上线，暂时不支持发送离线消息！"){
                  alert(data);
              }else {
                  console.log(data);
                  let obj = JSON.parse(data);
                  if(obj.msgSender==='${requestScope.username}'){
                      $("#msgZoom").append('<div class="text-right">'+'<h4 style="color: #4cae4c">'+obj.msgDateStr+'</h4>'+'<p style="color: #2aabd2">'+obj.msgSender+'</p>'+'<p>'+obj.info+'</p>');
                  }else if(obj.msgSender===user){
                      $("#msgZoom").append('<div class="text-left">'+'<h4 style="color: #4cae4c">'+obj.msgDateStr+'</h4>'+'<p style="color: #761c19">'+obj.msgSender+'</p>'+'<p>'+obj.info+'</p>');
                  }else {
                      alert(obj.msgSender+"给你发送一条信息！");
                      user=obj.msgSender;
                      document.getElementById("user").innerHTML=obj.msgSender;
                      $("#msgZoom").append('<div class="text-left">'+'<h4 style="color: #4cae4c">'+obj.msgDateStr+'</h4>'+'<p style="color: #761c19">'+obj.msgSender+'</p>'+'<p>'+obj.info+'</p>');
                  }
              }
          };
        function ws_sendMsg(){
            let context = document.getElementById("context").value;
            if(user===undefined){
                alert("还未选择用户聊天！");
            }else if(context===''){
                alert("信息不能为空！")
            }else {
                ws.send(user+'\n'+'${requestScope.username}'+'\n'+context);
                $("#context").val("");
            }
        }
        function getName(a) {
             user = document.getElementById(a).value;
            document.getElementById("user").innerHTML=user;
        }
    </script>


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
             <input type="button" onclick="getName('${user.username}')" id="${user.username}" value="${user.username}" class="btn btn-success">
             <br>
             <br>
         </c:forEach>
        </div>

        <div class="col-sm-2 col-md-2">
            <p class="h4" style="color: red">粉丝列表</p>
            <br>
            <c:forEach items="${requestScope.users1}" var="user">
                <input type="button" onclick="getName('${user.username}')" id="${user.username}" value="${user.username}" class="btn btn-danger">
                <br>
            </c:forEach>
        </div>
        <div class="col-sm-6 col-md-6 col-md-offset-4">
        <div class="div2">
            <h4 style="color: black" class="text-center" id="user">还未选择聊天对象</h4>
            <hr style="color: red">
            <div id="msgZoom" class="text-center" >

            </div>

        </div>
            <label for="context">回复</label>
            <input type="text" class="form-control" id="context"
                   placeholder="请输入内容..">
            <br>
            <input type="button" value="发送" class="btn btn-success right" onclick="ws_sendMsg()">
        </div>
    </div>

</body>
</html>