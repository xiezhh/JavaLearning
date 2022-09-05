/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-03 11:51
 *@Version 1.0
 */
package Connection;

import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.sql.Connection;

public class ConnectionTest {
    @Test
    public void getConnectionTest() throws Exception {
        Connection conn = JDBCUtil.getConnection();
        System.out.println(conn);
    }


}
