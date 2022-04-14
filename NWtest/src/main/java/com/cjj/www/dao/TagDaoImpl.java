package com.cjj.www.dao;

import com.cjj.www.pojo.Tag;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TagDaoImpl implements TagDao{

    @Override
    public boolean addTag(Tag tag) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="insert into note_tag(note_id,tag_main) values(?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,tag.getNoteId());
            preparedStatement.setString(2,tag.getTagMain());
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }return result;
    }

    @Override
    public List<Tag> queryTagByTagMain(String Main) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Tag> tags=new ArrayList<>();
        Tag tag=new Tag();
        String sql="select * from note_tag where tag_main = '"+Main+"'";
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                tag.setNoteId(resultSet.getInt("note_id"));
                tag.setTagMain(resultSet.getNString("tag_main"));
                tag.setTagId(resultSet.getInt("id"));
                tags.add(tag);
                tag=new Tag();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return tags;
    }

    @Override
    public List<Tag> queryTagByNoteId(Integer noteId) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Tag> tags=new ArrayList<>();
        Tag tag=new Tag();
        String sql="select * from note_tag where note_id = "+noteId;
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                tag.setNoteId(resultSet.getInt("note_id"));
                tag.setTagMain(resultSet.getNString("tag_main"));
                tag.setTagId(resultSet.getInt("id"));
                tags.add(tag);
                tag=new Tag();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return tags;
    }

    @Override
    public List<Integer> queryNoteIdByTagMain(String tagMain ,Integer begin) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Integer> noteIds=new ArrayList<>();
        connection=JdbcUtil.getConnection();
        String sql="select * from note_tag where tag_main like '%"+tagMain+"%' limit "+begin+","+" 4";
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
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

    @Override
    public Integer queryNoteTotalPageByMain(String tagMain) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        Integer count=0;
        String sql="select count(*) from note_tag where tag_main like '%"+tagMain+"%'";
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                count=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return count;
    }
}
