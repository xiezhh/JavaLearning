/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-03 11:59
 *@Version 1.0
 */
package Connection;

import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

    @Test
    public void CommonUpdateTest1()  {
        Connection conn = null;
        try {
            //1.创建连接
            conn =JDBCUtil.getConnection();
            //2.设置不自动提交
            conn.setAutoCommit(false);

            String sql1 = "update user_account set balance = balance - 100 where name =?";
            CommonUpdate(conn,sql1,"AAA");

            int i = 10/0;

            String sql2 = "update user_account set balance = balance + 100 where name =?";
            CommonUpdate(conn,sql2,"BBB");

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtil.closeConnection(conn,null);
        }

    }

    /**
     * @description 使用事务的通用更新程序
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 16:41
     * @param conn
     * @param sql
     * @param args
     * @return void
     */
    public void CommonUpdate(Connection conn,String sql,Object ...args)  {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0 ;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(null,ps);
        }
    }

    @Test
    public void test1(){
        String sql1 = "update user_account set balance = balance - 100 where name =?";
        CommonUpdate(sql1,"AAA");

        //int i = 10/0;

        String sql2 = "update user_account set balance = balance + 100 where name =?";
        CommonUpdate(sql2,"BBB");
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

    public <T> List<T> getInstance(Class<T> clazz, String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i =0 ;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }

            rs = ps.executeQuery();

            ArrayList<T> list = new ArrayList<>();
            while(rs.next()){
                ResultSetMetaData rsmd = rs.getMetaData();

                T t = clazz.newInstance();

                int columnCount = rsmd.getColumnCount();
                for(int i = 0;i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps,rs);
        }
        return null;
    }
}
