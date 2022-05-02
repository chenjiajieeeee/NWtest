package com.cjj.www.dao;

import com.cjj.www.pojo.Note;
import com.cjj.www.pojo.User;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public boolean saveUser(User user) {
        /*
        查看目前已经注册了多少个用户
         */
        UserDao userDao=new UserDaoImpl();
        int count = userDao.countUser()+1;
        boolean result=false;
        Connection connection;
        PreparedStatement preparedStatement = null;
        /*
        调用JDBCUtil中写好的连接数据库的方法
        sql语句编写
        插入数据
         */
        connection = JdbcUtil.getConnection();
        String sql = "insert into user(username, password,usernumber,mail,code,password_salt) values(?,?,?,?,?,?)";
        String sql1 = "insert into userstatus("+"动漫区"+ " ) "+"values('1')";
        /*
        同时将id添加到状态表
         */
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3,count);
            preparedStatement.setString(4,user.getMail());
            preparedStatement.setString(5,user.getCode());
            preparedStatement.setString(6,user.getSalt());
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql1);
            int row = preparedStatement.executeUpdate();
            if(row>0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(preparedStatement, connection);
        }
        return result;
    }

    @Override
    public boolean check(String username, String password) {
        boolean result = false;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        /*
        创建Connnection, Statement, ResultSet对象
        调用验证登录的方法
        先查询用户名是否存在
        再验证用户名所对应的密码是否相等
        */
        String sql ="select * from user where username = '"+ username +"'";
        connection = JdbcUtil.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            /*
            遍历结果集
             */
            while (resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(resultSet, statement, connection);
        }
        return result;
    }


    @Override
    public User queryUserByUserName(String username) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user=new User();
        String sql="select * from user where username = '"+username+"'" ;
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //处理结果集
            while (resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                user.setRoot(resultSet.getString("root"));
                user.setAppealCount(resultSet.getInt("appeal_count"));
                user.setUserNumber(resultSet.getInt("usernumber"));
                user.setCode(resultSet.getString("code"));
                user.setMail(resultSet.getString("mail"));
                user.setActivateStatus(resultSet.getString("activate_status"));
                user.setSalt(resultSet.getString("password_salt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return user;

    }

    @Override
    public User queryUserByUserId(Integer userId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user=new User();
        String sql="select * from user where id = "+userId;
        connection=JdbcUtil.getConnection();
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //处理结果集
            while (resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                user.setRoot(resultSet.getString("root"));
                user.setAppealCount(resultSet.getInt("appeal_count"));
                user.setUserNumber(resultSet.getInt("usernumber"));
                user.setCode(resultSet.getString("code"));
                user.setMail(resultSet.getString("mail"));
                user.setActivateStatus(resultSet.getString("activate_status"));
                user.setSalt(resultSet.getString("password_salt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return user;

    }

    @Override
    public Integer countUser() {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        connection=JdbcUtil.getConnection();
        List<Note> notes=new ArrayList<>();
        Integer count=0;
        String sql="select count(*) from `user`";
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
    public boolean addFriend(Integer userId, Integer friendId) {
        boolean result=false;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into friend(user_id,friend_id) values(?,?)";
        connection=JdbcUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,friendId);
            if(preparedStatement.executeUpdate()>0){
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
    public List<Integer> queryFriend(Integer userId) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        String sql="select * from friend where user_id = "+userId;
        connection=JdbcUtil.getConnection();
        List<Integer> friendsId=new ArrayList<>();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                friendsId.add(resultSet.getInt("friend_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return friendsId;
    }

    @Override
    public List<Integer> queryFans(Integer friendId) {

            Connection connection=null;
            Statement statement=null;
            ResultSet resultSet=null;
            String sql="select * from friend where friend_id = "+friendId;
            connection=JdbcUtil.getConnection();
            List<Integer> fansId=new ArrayList<>();
            try {
                statement=connection.createStatement();
                resultSet=statement.executeQuery(sql);
                while (resultSet.next()){
                    fansId.add(resultSet.getInt("user_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JdbcUtil.close(resultSet,statement,connection);
            }
            return fansId;

    }

    @Override
    public boolean activateUser(String username) {
        boolean result=false;
        Connection connection=null;
        Statement statement=null;
        String sql="update `user` set activate_status = '1' where username = '"+username+"'";
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


}
