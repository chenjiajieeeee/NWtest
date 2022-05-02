package com.cjj.www.service;

import com.cjj.www.pojo.Note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CollectService {
    /**
     *
     * @param noteId 该笔记的id
     * @param userId 对该笔记执行操作的用户id
     * @return ture已经点过赞了，false则为没有点赞
     *
     */
    boolean judgeCollectOrNot(Integer noteId,Integer userId);

    /**
     *
     * @param request servlet的req
     * @param response servlet的resp
     * 将servlet转移到service层中
     */
    void collectAct(HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param noteId 需要执行收藏的笔记id
     * @param userId 执行收藏操作的用户id
     * @return ture 为成功， false 为失败
     */
    boolean collectAct(Integer noteId,Integer userId);

    /**
     *
     * @param userId 通过已经进行收藏的用户id查询收藏了的笔记的id
     * @return list数组：笔记的数组
     */
    List<Note> queryCollectNoteByUserId(Integer userId);
}
