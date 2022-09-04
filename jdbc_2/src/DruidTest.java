/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-05 0:32
 *@Version 1.0
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {

    @Test
    public void getConnection1()  {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemResourceAsStream("Druid.properties");
            pros.load(is);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
