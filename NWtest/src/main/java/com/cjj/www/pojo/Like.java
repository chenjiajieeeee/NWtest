package com.cjj.www.pojo;

public class Like {
    Integer userId;
    Integer noteId;

    public Like() {
    }

    public Like(Integer userId, Integer noteId) {
        this.userId = userId;
        this.noteId = noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }
}
