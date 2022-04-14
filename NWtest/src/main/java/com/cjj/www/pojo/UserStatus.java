package com.cjj.www.pojo;

public class UserStatus {
    private Integer userId;
    private String gameZoomStatus;
    private String foodStatus;
    private String itStatus;

    public UserStatus() {
    }

    public UserStatus(Integer userId, String gameZoomStatus, String foodStatus, String itStatus, String cartoonStatus, String studyStatus) {
        this.userId = userId;
        this.gameZoomStatus = gameZoomStatus;
        this.foodStatus = foodStatus;
        this.itStatus = itStatus;
        this.cartoonStatus = cartoonStatus;
        this.studyStatus = studyStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGameZoomStatus() {
        return gameZoomStatus;
    }

    public void setGameZoomStatus(String gameZoomStatus) {
        this.gameZoomStatus = gameZoomStatus;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }

    public String getItStatus() {
        return itStatus;
    }

    public void setItStatus(String itStatus) {
        this.itStatus = itStatus;
    }

    public String getCartoonStatus() {
        return cartoonStatus;
    }

    public void setCartoonStatus(String cartoonStatus) {
        this.cartoonStatus = cartoonStatus;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    private String cartoonStatus;
    private String studyStatus;
}
