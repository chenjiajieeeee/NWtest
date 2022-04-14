package com.cjj.www.pojo;

public class Tag {
    Integer tagId;
    Integer noteId;
    String tagMain;

    public Tag() {
    }

    public Tag(Integer tagId, Integer noteId, String tagMain) {
        this.tagId = tagId;
        this.noteId = noteId;
        this.tagMain = tagMain;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getTagMain() {
        return tagMain;
    }

    public void setTagMain(String tagMain) {
        this.tagMain = tagMain;
    }
}
