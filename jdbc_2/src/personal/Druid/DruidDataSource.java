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
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDataSource {
    @Test
    public void getConnection() throws Exception {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.Druid.properties");
        pros.load(is);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);
        Connection connection= dataSource.getConnection();
        System.out.println(connection);



    }
}
