package com.cjj.www.dao;

import com.cjj.www.pojo.Like;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeActDaoImpl implements LikeActDao{
    @Override
    public boolean addLike(Like like) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="INSERT into `like`(user_id,like_note_id) values(?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,like.getUserId());
            preparedStatement.setInt(2,like.getNoteId());
            int row = preparedStatement.executeUpdate();
            if(row>0){
                String sql1="update note set likecount = likecount+1 where id = ?";
                preparedStatement=connection.prepareStatement(sql1);
                preparedStatement.setInt(1,like.getNoteId());
                row=preparedStatement.executeUpdate();
                if(row>0){
                    result=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }return result;
    }

    @Override
    public boolean cancelLike(Integer noteId, Integer userId) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="delete from `like` where like_note_id=? and user_id=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,noteId);
            preparedStatement.setInt(2,userId);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                String sql1="update note set likecount = likecount-1 where id=?";
                preparedStatement=connection.prepareStatement(sql1);
                preparedStatement.setInt(1,noteId);
                row=preparedStatement.executeUpdate();
                if(row>0){
                    result=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }return result;

    }

    @Override
    public boolean judgeLikeOrNot(Integer noteId, Integer UserId) {
        boolean result=true;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        String sql="select * from `like`where like_note_id = "+noteId+" and user_id = "+UserId;
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
    public List<Integer> queryLikeNoteByUserId(Integer userId) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet =null;
        connection= JdbcUtil.getConnection();
        List<Integer> likeNoteIds=new ArrayList<>();
        Integer likeNoteId;
        String sql="select * from `like` where user_id = "+userId;
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
               likeNoteId= resultSet.getInt("like_note_id");
               likeNoteIds.add(likeNoteId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return likeNoteIds;
    }

}
