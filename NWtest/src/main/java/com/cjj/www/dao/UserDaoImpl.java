package com.cjj.www.dao;

<<<<<<< HEAD
=======
import com.cjj.www.pojo.Note;
>>>>>>> 8d84cdf (eigth)
import com.cjj.www.pojo.User;
import com.cjj.www.util.JdbcUtil;

import java.sql.*;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 8d84cdf (eigth)

public class UserDaoImpl implements UserDao{
    @Override
    public boolean saveUser(User user) {
<<<<<<< HEAD
=======
        /*
        查看目前已经注册了多少个用户
         */
        UserDao userDao=new UserDaoImpl();
        int count = userDao.countUser()+1;
>>>>>>> 8d84cdf (eigth)
        boolean result=false;
        Connection connection;
        PreparedStatement preparedStatement = null;
        /*
        调用JDBCUtil中写好的连接数据库的方法
        sql语句编写
        插入数据
         */
        connection = JdbcUtil.getConnection();
<<<<<<< HEAD
        String sql = "insert into user(username, password) values(?,?)";
=======
        String sql = "insert into user(username, password,usernumber) values(?,?,?)";
>>>>>>> 8d84cdf (eigth)
        String sql1 = "insert into userstatus("+"动漫区"+ " ) "+"values('1')";
        /*
        同时将id添加到状态表
         */
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
<<<<<<< HEAD
=======
            preparedStatement.setInt(3,count);
>>>>>>> 8d84cdf (eigth)
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
<<<<<<< HEAD
=======
                user.setUserNumber(resultSet.getInt("usernumber"));
>>>>>>> 8d84cdf (eigth)
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
<<<<<<< HEAD
=======
                user.setUserNumber(resultSet.getInt("usernumber"));
>>>>>>> 8d84cdf (eigth)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet,statement,connection);
        }
        return user;

    }

<<<<<<< HEAD
=======
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

>>>>>>> 8d84cdf (eigth)
}
