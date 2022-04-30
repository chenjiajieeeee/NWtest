package com.cjj.www.service;

import com.cjj.www.pojo.Note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CollectService {
    boolean judgeCollectOrNot(Integer noteId,Integer userId);
    void collectAct(HttpServletRequest request, HttpServletResponse response);
    boolean collectAct(Integer noteId,Integer userId);
    List<Note> queryCollectNoteByUserId(Integer userId);
}
