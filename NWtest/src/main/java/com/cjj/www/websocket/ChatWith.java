package com.cjj.www.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatWith {
    //建立连接之后，记录下用户登录的session
    private Session session;
    public static Map<Session, MessageUtil> userSessions = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session){
        //
    }
    @OnMessage
    public void onMessage(String msg,Session session){
        System.out.println(msg);
    }
    @OnError
    public void onError(Throwable t){
       t.printStackTrace();
    }
    @OnClose
    public void onClose(Session session){
        userSessions.remove(session);
    }
}
