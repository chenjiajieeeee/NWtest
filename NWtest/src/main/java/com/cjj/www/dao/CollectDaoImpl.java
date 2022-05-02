package com.cjj.www.dao;

import com.cjj.www.pojo.Collect;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectDaoImpl implements CollectDao{
    @Override
    public void addCollect(Collect collect) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection= JdbcUtil.getConnection();
        String sql="INSERT into collect(note_id,user_id) values(?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,collect.getNoteId());
            preparedStatement.setInt(2,collect.getUserId());
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
    }

    @Override
    public void cancelCollect(Integer noteId, Integer userId) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="delete from collect where note_id=? and user_id=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,noteId);
            preparedStatement.setInt(2,userId);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
    }

    @Override
    public boolean judgeCollectOrNot(Integer noteId, Integer userId) {
        boolean result=true;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        String sql="select * from collect where note_id = "+noteId+" and user_id = "+userId;
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                result=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return result;
    }

    @Override
    public List<Integer> queryCollectNoteByUserId(Integer userId) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet =null;
        connection= JdbcUtil.getConnection();
        List<Integer> CollectNoteIds=new ArrayList<>();
        Integer CollectNoteId;
        String sql="select * from collect where user_id = "+userId;
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                CollectNoteId= resultSet.getInt("note_id");
                CollectNoteIds.add(CollectNoteId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return CollectNoteIds;
    }
}
