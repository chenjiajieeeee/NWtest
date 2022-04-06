package com.cjj.www.pojo;

public class Comment {
    private Integer noteId;
    private Integer id;
    private String main;
    private Integer userId;

    public Comment() {
    }

    public Comment(Integer noteId, Integer id, String main, Integer userId) {
        this.noteId = noteId;
        this.id = id;
        this.main = main;
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
