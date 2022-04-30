package com.cjj.www.service;

import com.cjj.www.pojo.Like;
import com.cjj.www.pojo.Note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LikeActService {
   void likeAct(HttpServletRequest request, HttpServletResponse response);
   boolean judgeLikeOrNot(Integer noteId,Integer userId);
   boolean LikeAct(Integer noteId,Integer userId);
   List<Note> queryLikeNoteByUserId(Integer userId);
}
