package com.cjj.www.dao;

public interface UserStatusDao {
    /**
     * 设置状态来表明该用户是否在这个区域被禁用了
     * 默认为不禁用
     *
     */
    boolean setUserStatus(String zoomName,String zoomStatus,Integer userId);
    String queryUserStatus(Integer userId,String zoomName);
    /**
     * 设置状态来表明用户发表的笔记是否能被看到其实就是审核有没有通过
     *
     */
    boolean setUserReleaseStatus(Integer noteId,String releaseStatus);

}
