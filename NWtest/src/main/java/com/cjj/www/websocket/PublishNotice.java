package com.cjj.www.websocket;

import com.cjj.www.dao.ManagerDao;
import com.cjj.www.dao.ManagerDaoImpl;
import com.cjj.www.pojo.Notice;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.List;

@ServerEndpoint("/notice")
public class PublishNotice {

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        //获取公告
        ManagerDao managerDao=new ManagerDaoImpl();
        List<Notice> notices = managerDao.queryNotice();
        int i=1;
        String s="";
        for (Notice notice:notices){
            s=s+"公告"+i+"：";
            s=s+notice.getTitle();
            s=s+"\n";
            s=s+"公告内容：";
            s=s+notice.getMain();
            s=s+"\n\n\n";
            i++;
        }
        session.getBasicRemote().sendText(s);
    }

    @OnError
    public void onError(Throwable t) {
        //以下代码省略...
        System.out.println("出错了");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        //以下代码省略...
        System.out.println("关闭了");
    }

}

