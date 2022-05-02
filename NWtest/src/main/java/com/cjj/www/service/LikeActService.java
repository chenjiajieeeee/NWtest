package com.cjj.www.service;

import com.cjj.www.pojo.Note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LikeActService {
   /**
    *
    * @param request 对应servlet的req
    * @param response 对应servlet的resp
    */
   void likeAct(HttpServletRequest request, HttpServletResponse response);

   /**
    *
    * @param noteId 用于判断的该笔记的id
    * @param userId 进行判断的对象用户的id
    * @return true表示该用户已经点过赞了，false则表示还没有点过赞
    */
   boolean judgeLikeOrNot(Integer noteId,Integer userId);

   /**
    *
    * @param noteId 为执行点赞or取消点赞的笔记id
    * @param userId 为执行操作的用户id
    * @return ture 为表示点赞成功 ， false则表示点赞失败
    */
   boolean LikeAct(Integer noteId,Integer userId);

   /**
    *
    * @param userId 进行查询已经点赞的笔记操作的用户id
    * @return list集合，每一个元素都是note对象
    */
   List<Note> queryLikeNoteByUserId(Integer userId);
}
