package com.cjj.www.service;

import com.cjj.www.pojo.Note;

import java.util.List;

public interface CollectService {
    boolean judgeCollectOrNot(Integer noteId,Integer userId);
    boolean collectAct(Integer noteId,Integer userId);
    List<Note> queryCollectNoteByUserId(Integer userId);
}
