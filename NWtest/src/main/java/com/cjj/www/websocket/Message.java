package com.cjj.www.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String msgSender;
    private String msgReceiver;
    private String info;
    private Date msgDate;

    public Message() {
    }

    public Message(String msgSender, String msgReceiver, String info, Date msgDate, String msgDateStr) {
        this.msgSender = msgSender;
        this.msgReceiver = msgReceiver;
        this.info = info;
        this.msgDate = msgDate;
        this.msgDateStr = msgDateStr;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    public String getMsgDateStr() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msgDateStr=simpleDateFormat.format(msgDate);
        return msgDateStr;
    }

    public void setMsgDateStr(String msgDateStr) {
        this.msgDateStr = msgDateStr;
    }

    private String msgDateStr;

}
