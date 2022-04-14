package com.cjj.www.util;

import java.sql.*;

public class JdbcUtil {
    //从properties文件中获取数据，防止把代码写死
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/xiaohongshu";
    private static final String user="root";
    private static final String password="a1216978720.";
    //注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //连接对象
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //使用完后关闭流(对于没有sql注入的)
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //针对PrepareStatement的
    public static void close(PreparedStatement preparedStatement,Connection connection){
        close(null,preparedStatement,connection);
    }

}
