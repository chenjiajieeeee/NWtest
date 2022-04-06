package com.cjj.www.service;

import com.cjj.www.pojo.Like;
import com.cjj.www.pojo.Note;

import java.util.List;

public interface LikeActService {
   boolean LikeAct(Integer noteId,Integer userId);
   List<Note> queryLikeNoteByUserId(Integer userId);
}
