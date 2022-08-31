
package PreparedStatementTest;

import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Properties;

/**
 * @Description:
 * @author XieZhh
 * @create:2022-08-28 23:22:00
 * @Version 1.0
 */
public class PreparedStatementTest {




    @Test
    public void CommonUpdateTest(){
        String sql ="update customers set email = ? where id =?";
        CommonUpdate(sql,"snail915@qq.com",1);
    }

    public void CommonUpdate(String sql,Object ...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0 ;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps);
        }
    }

    @Test
    public void PreparedStatementUpdateTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update customers set name = ? where id =?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"李商隐");
            ps.setObject(2,1);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps);
        }
    }

    @Test
    public void testInsert()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.提供数据库连接信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(is);

            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String url = prop.getProperty("url");
            String driverClass = prop.getProperty("driverClass");

            //2.获取Driver 实现类对象：使用反射
            Class.forName(driverClass);
            // Driver driver = (Driver) clazz.newInstance();

            // 3.获取连接
            conn = DriverManager.getConnection(url,user,password);
            System.out.println(conn);

            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"李宁");
            ps.setString(2,"lining@163.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1980-09-13");
            ps.setDate(3, new Date(date.getTime()));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }
}
