package com.cjj.www.dao;

import com.cjj.www.pojo.User;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao{
    @Override
    public void updateUserName(String oldUsername, String newUsername) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection= JdbcUtil.getConnection();
        String sql="update user set username=? where username=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,oldUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
    }

    @Override
    public boolean updatePassword(String username,String oldPassword, String newPassword,String newSalt) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection= JdbcUtil.getConnection();
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUserName(username);
        if(user.getPassword().equals(oldPassword)&&newPassword!=null){
                result=true;
            String sql="update `user` set password = ? where username = ?";
            String sql1="update `user` set password_salt = ? where username = ?";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,newPassword);
                preparedStatement.setString(2,username);
                int i = preparedStatement.executeUpdate();
                preparedStatement=connection.prepareStatement(sql1);
                preparedStatement.setString(1,newSalt);
                preparedStatement.setString(2,username);
                int row=preparedStatement.executeUpdate();
                if(row>0&&i>0){
                    result=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JdbcUtil.close(preparedStatement,connection);
            }
        }else {
            JdbcUtil.close(preparedStatement,connection);
        }
        return result;
    }

    @Override
    public List<Integer> queryReportNoteByUsername(String username) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Integer> noteIds = new ArrayList<>();
        String sql="select * from report where username = '"+username+"'";
        connection=JdbcUtil.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                noteIds.add(resultSet.getInt("note_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return noteIds;
    }
}
