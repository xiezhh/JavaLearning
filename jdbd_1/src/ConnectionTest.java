/*
 *@Description:
 *@author:xiezhh
 *@create:2022-08-28 17:31
 *@Version 1.0
 */

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    //数据库连接方式一
    @Test
    public void testConnection() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver() ;
        String url = "jdbc:mysql://localhost:3306/localhost";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");
        Connection conn = driver.connect(url,info);
        System.out.println(conn);
    }

    //方式二：对方式一的迭代，使代码具有更好的移植性
    @Test
    public  void testConnection1() throws Exception {
        //1.获取Driver 实现类对象：使用反射
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/localhost";

        //3.提供数据库账号密码
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");

        //4.获取连接
        Connection conn = driver.connect(url,info);
        System.out.println(conn);
    }

    //方式三：使用 driverManager 替换Driver
    @Test
    public  void testConnection3() throws Exception {
        //1.获取Driver 实现类对象：使用反射
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //2.提供数据库连接信息
        String url = "jdbc:mysql://localhost:3306/localhost";
        String user= "root";
        String password = "root";

        //3.注册Driver
        DriverManager.registerDriver(driver);

        //4.获取连接
        Connection  conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }

    //方式四：对方式三的优化
    @Test
    public  void testConnection4() throws Exception {
        //1.提供数据库连接信息
        String url = "jdbc:mysql://localhost:3306/localhost";
        String user= "root";
        String password = "root";

        //2.获取Driver 实现类对象：使用反射
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //3.注册Driver
        //省略第三步，这一步由mysql帮我们实现了
        //DriverManager.registerDriver(driver);

        //4.获取连接
        Connection  conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }

    //方式五：从配置文件中获取参数
    @Test
    public  void testConnection5() throws Exception {
        //1.提供数据库连接信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(is);

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        //2.获取Driver 实现类对象：使用反射
        Class clazz = Class.forName(driverClass);
        Driver driver = (Driver) clazz.newInstance();

        //3.获取连接
        Connection  conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }

}

