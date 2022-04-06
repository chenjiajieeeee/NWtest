package com.cjj.www.pojo;

public class Note {
    private Integer id;
    private Integer userId;
    private String main;
    private String zoomName;
    private String title;
    private String releaseStatus;
    private Integer likeCount;
    private String notePictureUrl;

    public String getNotePictureUrl() {
        return notePictureUrl;
    }

    public void setNotePictureUrl(String notePictureUrl) {
        this.notePictureUrl = notePictureUrl;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Note() {
    }

    public String getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(String releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Note(Integer id, Integer userId, String main, String zoomName, String title, String releaseStatus,Integer likeCount,String notePictureUrl) {
        this.id = id;
        this.userId = userId;
        this.main = main;
        this.zoomName = zoomName;
        this.title = title;
        this.releaseStatus=releaseStatus;
        this.likeCount=likeCount;
        this.notePictureUrl=notePictureUrl;
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

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getZoomName() {
        return zoomName;
    }

    public void setZoomName(String zoomName) {
        this.zoomName = zoomName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Note{" +
                "main='" + main + '\'' +
                ", zoomName='" + zoomName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
