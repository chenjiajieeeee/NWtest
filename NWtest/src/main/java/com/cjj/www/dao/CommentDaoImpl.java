package com.cjj.www.dao;

import com.cjj.www.pojo.Comment;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommentDaoImpl implements CommentDao {
    /*
    连接数据库
    编写sql语句
    关闭流
     */
    @Override
    public boolean saveComment(Comment comment) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="insert into comment(note_id,user_id,main) values(?,?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,comment.getNoteId());
            preparedStatement.setInt(2,comment.getUserId());
            preparedStatement.setString(3,comment.getMain());
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
        return result;
    }

    @Override
    public List<Comment> queryCommentByNoteId(Integer noteId) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        String sql="select * from comment where note_id='"+noteId+"'";
        List<Comment> comments=new ArrayList<>();
        Comment comment=new Comment();
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                comment.setNoteId(resultSet.getInt("note_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setId(resultSet.getInt("id"));
                comment.setMain(resultSet.getString("main"));
                comments.add(comment);
                comment=new Comment();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return comments;
    }


    @Override
    public Set<Integer> queryCommentUser(Integer userId) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        Set<Integer> likeUser=new HashSet<>();
        Integer id;
        String sql="select * from comment where user_id = "+userId;
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                id = resultSet.getInt("note_id");
                likeUser.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return likeUser;
    }

}
