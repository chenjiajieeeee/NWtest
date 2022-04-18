package com.cjj.www.dao;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.Report;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.*;

public class NoteDaoImpl implements NoteDao{

    @Override
    public List<Note> queryNoteByUserId(Integer id) {
        ResultSet resultSet=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Note note=new Note();
        List<Note> notes=new ArrayList<>();
        /*
        sql语句查询笔记
         */
        String sql="select * from note where user_id = ?";
        connection= JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                note.setId(resultSet.getInt("id"));
                note.setUserId(resultSet.getInt("user_id"));
                note.setZoomName(resultSet.getString("zoom_name"));
                note.setMain(resultSet.getString("main"));
                note.setTitle(resultSet.getString("title"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                note.setBrowse(resultSet.getInt("browse"));
                note.setLikeCount(resultSet.getInt("likecount"));
                /*
                将得到的数据封装成arrays集合来遍历
                 */
                notes.add(note);
                note=new Note();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,preparedStatement,connection);
        }
        return notes;
    }

    @Override
    public List<Note> queryNote() {
        /**
         * 同上一个方法一样，只不过不需要参数，全部显示出来
         */
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Note note=new Note();
        List<Note> notes=new ArrayList<>();
        /*
        sql语句查询笔记
         */
        String sql="select * from note";
        connection= JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                note.setId(resultSet.getInt("id"));
                note.setUserId(resultSet.getInt("user_id"));
                note.setZoomName(resultSet.getString("zoom_name"));
                note.setMain(resultSet.getString("main"));
                note.setTitle(resultSet.getString("title"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                note.setBrowse(resultSet.getInt("browse"));
                note.setLikeCount(resultSet.getInt("likecount"));
                /*
                将得到的数据封装成arrays集合来遍历
                 */
                notes.add(note);
                note=new Note();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,preparedStatement,connection);
        }
        return notes;
    }

    @Override
    public List<Note> queryNoteByZoom(String zoomName) {
        /**
         * 同上一个方法一样，只不过不需要参数，全部显示出来
         */
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Note note=new Note();
        List<Note> notes=new ArrayList<>();
        /*
        sql语句查询笔记
         */
        String sql="select * from note where zoom_name = '"+zoomName+"'";
        connection= JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                note.setId(resultSet.getInt("id"));
                note.setUserId(resultSet.getInt("user_id"));
                note.setZoomName(resultSet.getString("zoom_name"));
                note.setMain(resultSet.getString("main"));
                note.setTitle(resultSet.getString("title"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                note.setBrowse(resultSet.getInt("browse"));
                note.setLikeCount(resultSet.getInt("likecount"));
                /*
                将得到的数据封装成arrays集合来遍历
                 */
                notes.add(note);
                note=new Note();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,preparedStatement,connection);
        }
        return notes;
    }


    @Override
    public boolean saveNote(Note note) {
        /*
        同保存用户信息一样的操作，只不过这次不需要判断是否重复。
         */
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into note(user_id,main,zoom_name,title) values(?,?,?,?)";
        /*
        操作差不多
         */
        connection=JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,note.getUserId());
            preparedStatement.setString(2,note.getMain());
            preparedStatement.setString(3,note.getZoomName());
            preparedStatement.setString(4,note.getTitle());
            int row = preparedStatement.executeUpdate();
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
    public boolean updateNoteMain(String main,Integer id) {
        /**
         * 更新笔记是用户的特权，所以我们直接用笔记的id作为where的筛选条件进行修改
         *
         */
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update note set main=? where id=?";
        connection = JdbcUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, main);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(preparedStatement, connection);
        }
        return result;

    }

    @Override
    public boolean updateNoteTitle(String title, Integer id) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update note set title=? where id=?";
        connection = JdbcUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(preparedStatement, connection);
        }
        return result;
    }

    @Override
    public boolean deleteNote(Integer id) {
        /**
         * 因为用户和管理员都可以对笔记执行删除操作，所以他们直接有关联的就是笔记的id了
         * 大体流程和前面一直，如果有时间的话应该把他们提取出来优化一下代码
         */
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="delete from note where id=?";
        connection=JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
        return result;
    }

    @Override
    public Note queryNoteByNoteId(Integer noteId) {
        /*
        当进入评论界面，就只显示这个笔记的内容
         */
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        Note note=new Note();
        String sql="select * from note where id= '"+noteId+"'";
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                note.setId(resultSet.getInt("id"));
                note.setUserId(resultSet.getInt("user_id"));
                note.setMain(resultSet.getString("main"));
                note.setTitle(resultSet.getString("title"));
                note.setZoomName(resultSet.getString("zoom_name"));
                note.setLikeCount(resultSet.getInt("likeCount"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setBrowse(resultSet.getInt("browse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return note;
    }

    @Override
    public List<Note> queryNotePaging(Integer begin, Integer end) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        String sql="select * from note where release_status = '1' limit "+begin+" , "+end;
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
                note.setLikeCount(resultSet.getInt("likecount"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setBrowse(resultSet.getInt("browse"));
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
    public List<Note> queryNotePaging(Integer begin, Integer end, String zoomName) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        String sql="select * from note where release_status = '1' and zoom_name = '"+zoomName+"'"+ "limit "+begin+" , "+end;
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
                note.setLikeCount(resultSet.getInt("likecount"));
                note.setNotePictureUrl(resultSet.getString("picture_url"));
                note.setReleaseStatus(resultSet.getString("release_status"));
                note.setBrowse(resultSet.getInt("browse"));
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
    public Integer queryNoteTotalPage() {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        Integer count=0;
        String sql="select count(*) from note where release_status = '1' ";
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
    public Integer queryNoteTotalPage(String zoomName) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        Integer count=0;
        String sql="select count(*) from note where release_status = '1' and zoom_name =  '"+zoomName+"'";
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
    public boolean insertPictureUrl(String url,Integer noteId) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=JdbcUtil.getConnection();
        String sql="update note set picture_url = ? where id = "+noteId;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,url);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
        return result;
    }

    @Override
    public boolean addBrowse(Integer noteId)  {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="update note set browse = browse +1 where id = "+noteId;
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            int row = statement.executeUpdate(sql);
            if (row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(null,statement,connection);
        }
        return result;
    }

    @Override
    public boolean insertReportMsg(Report report) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into report(note_id,main,username,zoom) values(?,?,?,?)";
        connection=JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,report.getNoteId());
            preparedStatement.setString(2,report.getMain());
            preparedStatement.setString(3,report.getUsername());
            preparedStatement.setString(4,report.getZoomName());
            int row = preparedStatement.executeUpdate();
            if (row>0){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement, connection);
        }
        return result;
    }

    @Override
    public boolean queryReported(String username, Integer noteId) {
        boolean result=false;
        Connection connection=null;
        ResultSet resultSet=null;
        Statement statement=null;
        String sql="select * from report where username = '"+username+"' and note_id = "+noteId;
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                result=true;
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return result;
    }

}
