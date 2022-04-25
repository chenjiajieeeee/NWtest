package com.cjj.www.pojo;

public class Friend {
    private Integer id;
    private Integer userId;
    private Integer friendId;

    public Friend(Integer id, Integer userId, Integer friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    public Friend() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
