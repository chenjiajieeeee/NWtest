package com.cjj.www.pojo;

public class areaAdministrator {
    private Integer id;
    private String account;
    private String password;
    private String zoomName;

    public areaAdministrator() {
    }

    public areaAdministrator(Integer id, String account, String password, String zoomName) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.zoomName = zoomName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZoomName() {
        return zoomName;
    }

    public void setZoomName(String zoomName) {
        this.zoomName = zoomName;
    }
}
