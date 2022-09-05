/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-04 23:51
 *@Version 1.0
 */
package personal.Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidDataSource {

    @Test
    public void getConnection(){
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("Druid.properties");
            pros.load(is);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DataSource source;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemResourceAsStream("Druid.properties");
            pros.load(is);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 使用 Druid 获取连接池
     * @author xiezhh snail915@qq.com
     * @date 2022/9/5 23:35
     * @return java.sql.Connection
     */
    public static Connection getConnection1() throws SQLException {
        Connection connection = source.getConnection();
        return connection;
    }
}
