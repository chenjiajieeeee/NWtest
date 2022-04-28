package com.cjj.www.websocket;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatWith {
    int i=0;
    //建立连接之后，记录下用户登录的session
    public static Map<Session,String > userSessions = new ConcurrentHashMap<>();
    //封装信息
    private Message message;
    @OnOpen
    public void onOpen(Session session) throws UnsupportedEncodingException {
        String loginUser = session.getQueryString();
        loginUser= URLDecoder.decode(loginUser,"utf-8");
        String[] user = loginUser.split("=");
        String username=user[1];
        username=username.substring(1,username.length()-1);
        userSessions.put(session,username);
    }
    @OnMessage
    public void onMessage(String msg,Session session) throws IOException {
        //将数据封装
        message=new Message();
        message.setMsgDate(new Date());
        String[] MS = msg.split("\n");
        message.setMsgDateStr(message.getMsgDateStr());
        message.setMsgReceiver(MS[0]);
        message.setMsgSender(MS[1]);
        message.setInfo(MS[2]);
        String Msg = JSONObject.toJSONString(message);
        //发送信息
        boolean flag=false;
        for (Session session1:userSessions.keySet()){
            if(userSessions.get(session1).equals(message.getMsgReceiver())){
                session1.getBasicRemote().sendText(Msg);
                session.getBasicRemote().sendText(Msg);
                flag=true;
                break;
            }
        }
        if (flag==false){
            session.getBasicRemote().sendText("该用户未上线，暂时不支持发送离线消息！");
        }
    }
    @OnError
    public void onError(Throwable t){
       t.printStackTrace();
    }
    @OnClose
    public void onClose(Session session){
        userSessions.remove(session);
        System.out.println("连接已关闭");
    }
}
