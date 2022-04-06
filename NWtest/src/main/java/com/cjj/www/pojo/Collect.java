package com.cjj.www.pojo;

public class Collect {
    private Integer noteId;
    private Integer userId;

    public Collect() {
    }

    public Collect(Integer noteId, Integer userId) {
        this.noteId = noteId;
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
