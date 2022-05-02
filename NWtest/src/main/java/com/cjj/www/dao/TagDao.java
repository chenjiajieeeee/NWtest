package com.cjj.www.dao;

import com.cjj.www.pojo.Tag;

import java.util.List;

public interface TagDao {
    /**
     * 为特定笔记添加标签
     * @param tag tag标签对象
     */
    void addTag(Tag tag);

    /**
     * 根据用户输入的搜索内容查询tag
     * @param Main 搜索内容
     * @return list对象，tag集合
     */
    List<Tag> queryTagByTagMain(String Main);

    /**
     * 显示该笔记拥有的tag
     * @param noteId 笔记的id
     * @return list集合，该笔记tag对象的集合
     */
    List<Tag> queryTagByNoteId(Integer noteId);

    /**
     * 查询拥有该标签的笔记的id
     * @param tagMain 标签内容
     * @param begin 查询起始点
     * @return list集合 笔记id的集合
     */
    List<Integer> queryNoteIdByTagMain(String tagMain,Integer begin);

    /**
     * 查询笔记对象总页数
     * @param tagMain 标签内容
     * @return count总记录数
     */
    Integer queryNoteTotalPageByMain(String tagMain);
}
