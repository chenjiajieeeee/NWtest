package com.cjj.www.websocket;

public class MessageUtil {
    private String SendUser;
    private String receiveUser;
    private String msg;

    public MessageUtil() {
    }

    public String getSendUser() {
        return SendUser;
    }

    public void setSendUser(String sendUser) {
        SendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
