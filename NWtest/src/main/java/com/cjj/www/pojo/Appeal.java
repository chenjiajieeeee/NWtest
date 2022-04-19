package com.cjj.www.pojo;

public class Appeal {
    private Integer id;
    private Integer noteId;
    private Integer managerId;

    public Appeal(Integer noteId, Integer managerId) {
        this.noteId = noteId;
        this.managerId = managerId;
    }

    public Appeal() {
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
