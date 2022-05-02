package com.cjj.www.dao;

public interface UserStatusDao {
    /**
     * 设置用户状态
     * @param zoomName 设置的区域名称
     * @param zoomStatus 新的状态
     * @param userId 需要设置的用户的id
     * @return ture为成功，false为失败
     */
    boolean setUserStatus(String zoomName,String zoomStatus,Integer userId);

    /**
     * 查询用户状态
     * @param userId 被查询的用户
     * @param zoomName 分区名称
     * @return “被禁用” “用户状态正常”
     */
    String queryUserStatus(Integer userId,String zoomName);


}
