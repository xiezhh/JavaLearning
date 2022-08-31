/*
 *@Description:
 *@author:xiezhh
 *@create:2022-08-29 22:41
 *@Version 1.0
 */
package personal.jdbc.util;



import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    public static Connection getConnection() throws Exception {
        //1.提供数据库连接信息
        InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(is);

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        //2.获取Driver 实现类对象：使用反射
        // Class clazz =
        Class.forName(driverClass);
        // Driver driver = (Driver) clazz.newInstance();

        //3.获取连接
        Connection conn = DriverManager.getConnection(url,user,password);
        // System.out.println(conn);
        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement ps){
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
