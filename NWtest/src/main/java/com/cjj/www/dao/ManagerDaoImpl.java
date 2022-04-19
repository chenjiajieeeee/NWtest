package com.cjj.www.dao;

import com.cjj.www.pojo.*;

import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



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

        Integer count=0;
        String sql="select count(*) from note where zoom_name = '"+zoomName+"'"+"and release_status != '-2'";
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

    @Override
    public boolean backNoteReleaseStatus(Integer noteId) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="update note set release_status= '-1' where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,noteId);
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
    public boolean deleteNoteByNoteId(Integer noteId) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="update note set release_status= '-2' where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,noteId);
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
    public List<Report> queryReportedNoteMsg(String zoomName) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        String sql="select * from report where zoom = '"+zoomName+"'";
        connection=JdbcUtil.getConnection();
        List<Report> reports=new ArrayList<>();
        Report report=new Report();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                report.setId(resultSet.getInt("id"));
                report.setNoteId(resultSet.getInt("note_id"));
                report.setZoomName(resultSet.getString("zoom"));
                report.setUsername(resultSet.getString("username"));
                report.setMain(resultSet.getString("main"));
                reports.add(report);
                report=new Report();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }return reports;
    }

    @Override
    public boolean deleteReportMsg(Integer noteId, String username) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="delete from report where username = '"+username+"' and note_id = "+noteId;
        connection = JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }return result;
    }

    @Override
    public boolean deleteReportMsg(Integer noteId) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="delete from report where  note_id = "+noteId;
        connection = JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }return result;
    }

    @Override
    public Integer queryNoteTotalPage() {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        Integer count=0;
        String sql="select count(*) from note where release_status !='-2'";
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

    @Override
    public List<Note> queryNotePage(Integer begin, Integer end) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        String sql="select * from note  where release_status != '-2' limit "+begin+" , "+end;
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
    public List<User> queryUserByRoot(String root) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        User user=new User();
        List<User> users=new ArrayList<>();
        String sql="select * from user where root = '"+root+"'";
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                user.setRoot(resultSet.getString("root"));
                user.setAppealCount(resultSet.getInt("appeal_count"));
                users.add(user);
                user=new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }return users;
    }

    @Override
    public boolean saveOperation(Appeal appeal) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into appeal(manager_id,note_id) values(?,?)";
        connection=JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,appeal.getManagerId());
            preparedStatement.setInt(2,appeal.getNoteId());
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
    public boolean deleteOperation(Integer noteId) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="delete from appeal where note_id = "+noteId;
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }return result;
    }

    @Override
    public boolean addAppeal(String username) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="update user set appeal_count = appeal_count +1 where username = '"+username+"'";
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }return result;
    }

    @Override
    public boolean resetAppeal(String username) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="update user set appeal_count = 0 where username = '"+username+"'";
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }return result;
    }

    @Override
    public Integer findManager(Integer noteId) {
        Integer id=-1;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        String sql="select * from appeal where note_id = "+noteId;
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                id=resultSet.getInt("manager_id");
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }return id;
    }

    @Override
    public boolean resetUser(Integer userId) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="update user set root = 'N' where id = "+userId;
        connection=JdbcUtil.getConnection();
        try {
            statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }
            return result;
    }


}
