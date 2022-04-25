package com.cjj.www.websocket;



import com.cjj.www.dao.ManagerDao;
import com.cjj.www.dao.ManagerDaoImpl;
import com.cjj.www.pojo.Notice;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/notice")
public class PublishNotice {

    private String username;


    public static Map<Session, String> userSessions = new ConcurrentHashMap<>();


        ManagerDao managerDao=new ManagerDaoImpl();
        Notice notice = managerDao.queryNotice();


    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {

            //获取公告
            session.getBasicRemote().sendText(notice.getTitle());
            session.getBasicRemote().sendText(notice.getMain());
    }
    @OnMessage
    public void onMessage(String msg,Session session) throws IOException {
        //谁进入了个人主页还没退出的，记录下来，要是更新了公告就实时推送。
        if(session!=null) {
            this.username = msg;
            userSessions.put(session, username);
        }
        if(msg.equals("小孩子不懂，传着玩的")){
            for (Session key:userSessions.keySet()){
                key.getBasicRemote().sendText(notice.getTitle());
                key.getBasicRemote().sendText(notice.getMain());
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println(t);
    }

    @OnClose
    public void onClose(Session session) {
        userSessions.remove(session);
    }

}

