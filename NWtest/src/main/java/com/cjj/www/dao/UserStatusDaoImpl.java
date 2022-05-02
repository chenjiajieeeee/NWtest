package com.cjj.www.dao;

import com.cjj.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusDaoImpl implements UserStatusDao{
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    @Override
    public boolean setUserStatus(String zoomName, String zoomStatus,Integer userId)
    {
        connection=JdbcUtil.getConnection();
        String sql="update userstatus set "+zoomName+" =? where user_id=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,zoomStatus);
            preparedStatement.setInt(2,userId);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                return true;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
        return false;
    }

    @Override
    public String queryUserStatus(Integer userId, String zoomName) {
        ResultSet resultSet=null;
        String sql="select "+zoomName+" from userstatus where user_id=?";
        connection=JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
             resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                 return resultSet.getString(zoomName);
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,preparedStatement,connection);
        }
        return null;
    }


}
