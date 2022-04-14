package com.cjj.www.dao;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.pojo.UserStatus;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ManagerDaoImpl implements ManagerDao{


    @Override
    public List<Note> queryNoteByAreaPaging(Integer begin,Integer end,String zoomName) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        String sql="select * from note where zoom_name = '"+zoomName+"'"+ "limit "+begin+" , "+end;
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            Note note=new Note();
            while (resultSet.next()){
                note.setMain(resultSet.getString("main"));
                note.setTitle(resultSet.getString("title"));
                note.setZoomName(resultSet.getString("zoom_name"));
                note.setUserId(resultSet.getInt("user_id"));
                note.setId(resultSet.getInt("id"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setLikeCount(resultSet.getInt("likecount"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                notes.add(note);
                note=new Note();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return notes;
    }

    @Override
    public boolean ChangeUserStatus(Integer userId, String userStatus, String zoomName) {
        UserStatusDao userStatusDao=new UserStatusDaoImpl();
        return userStatusDao.setUserStatus(zoomName, userStatus, userId);
    }

    @Override
    public boolean deleteNoteByManager(Integer id) {
        NoteDao noteDao=new NoteDaoImpl();
        return noteDao.deleteNote(id);
    }

    @Override
    public boolean setNoteReleaseStatus(Integer noteId, String releaseStatus) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="update note set release_status= ? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,releaseStatus);
            preparedStatement.setInt(2,noteId);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result= true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
        return result;
    }



    @Override
    public List<User> queryAllUser() {
       Connection connection=null;
       Statement statement=null;
       ResultSet resultSet=null;
       connection=JdbcUtil.getConnection();
       String sql="select * from user";
       User user=new User();
       List<User> users=new ArrayList<>();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
                user=new User();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return users;
    }

    @Override
    public List<UserStatus> queryZoomStatus() {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        String sql="select * from userstatus ";
        List<UserStatus> userStatuses=new ArrayList<>();
        UserStatus userStatus=new UserStatus();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                userStatus.setUserId(resultSet.getInt("user_id"));
                userStatus.setItStatus(resultSet.getString("科技区"));
                userStatus.setGameZoomStatus(resultSet.getString("游戏区"));
                userStatus.setFoodStatus(resultSet.getString("美食区"));
                userStatus.setCartoonStatus(resultSet.getString("动漫区"));
                userStatus.setStudyStatus(resultSet.getString("学习区"));
                userStatuses.add(userStatus);
                userStatus=new UserStatus();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return userStatuses;
    }

    @Override
    public Integer queryNoteTotalPage(String zoomName) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        Integer count=0;
        String sql="select count(*) from note where zoom_name = '"+zoomName+"'";
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
