package com.cjj.www.pojo;

public class UserStatusAndUsername {
    private String username;
    private Integer userId;
    private String userStatus;

    public UserStatusAndUsername() {
    }

    public UserStatusAndUsername(String username, Integer userId, String userStatus) {
        this.username = username;
        this.userId = userId;
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
