package util;/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-07 23:51
 *@Version 1.0
 */

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    public static Connection getConnection() throws Exception {

        String file = Thread.currentThread().getContextClassLoader().getResource("JDBC.Properties").getFile();
        //InputStream is = ClassLoader.getSystemResourceAsStream("JDBC.Properties");
        InputStream is = new FileInputStream(file);
        Properties pros = new Properties();
        pros.load(is);
        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String driverClass = pros.getProperty("driverClass");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url,user,password);
        return  connection;
    }

    public static void CloseConnection(Connection connection, PreparedStatement ps){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CloseConnection(Connection connection, PreparedStatement ps, ResultSet rs){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
