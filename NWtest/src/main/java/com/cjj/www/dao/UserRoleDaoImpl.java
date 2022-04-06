package com.cjj.www.dao;

import com.cjj.www.pojo.User;
import com.cjj.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRoleDaoImpl implements UserRoleDao{
    @Override
    public boolean updateUserName(String oldUsername, String newUsername) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection= JdbcUtil.getConnection();
        String sql="update user set username=? where username=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,oldUsername);
            int row=preparedStatement.executeUpdate();
            if(row>0){
                result=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
        return result;
    }

    @Override
    public boolean updatePassword(String username,String oldPassword, String newPassword) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection= JdbcUtil.getConnection();
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        if(user.getPassword().equals(oldPassword)&&newPassword!=null){
                result=true;
            String sql="update user set password=? where password=?";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,newPassword);
                preparedStatement.setString(2,oldPassword);
                int row=preparedStatement.executeUpdate();
                if(row>0){
                    result=true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                JdbcUtil.close(preparedStatement,connection);
                return result;
            }
        }else {
            JdbcUtil.close(preparedStatement,connection);
            return result;
        }
    }
}
