package com.cjj.www.service;

import com.cjj.www.pojo.Note;

import java.util.List;

public interface CollectService {
    boolean collectAct(Integer noteId,Integer userId);
    List<Note> queryCollectNoteByUserId(Integer userId);
}
