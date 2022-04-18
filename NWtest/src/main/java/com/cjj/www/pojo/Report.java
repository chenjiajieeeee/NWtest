package com.cjj.www.pojo;

public class Report {
    private Integer id;
    private Integer noteId;
    private String main;
    private String zoomName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public String getZoomName() {
        return zoomName;
    }

    public void setZoomName(String zoomName) {
        this.zoomName = zoomName;
    }

    public Report() {
    }

    public Report(Integer id, Integer noteId, String main) {
        this.id = id;
        this.noteId = noteId;
        this.main = main;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
