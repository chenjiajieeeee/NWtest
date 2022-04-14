package com.cjj.www.dao;

import com.cjj.www.pojo.Tag;

import java.util.List;

public interface TagDao {
    /*
    为特定的笔记添加笔记
     */
    boolean addTag(Tag tag);
    /*
    模糊搜索
     */
    List<Tag> queryTagByTagMain(String Main);
    /*
    打开笔记详细页时要显示他的tag
     */
    List<Tag> queryTagByNoteId(Integer noteId);
    /*
    使用sql语句中的模糊搜索
     */
    List<Integer> queryNoteIdByTagMain(String tagMain,Integer begin);
    Integer queryNoteTotalPageByMain(String tagMain);
}
