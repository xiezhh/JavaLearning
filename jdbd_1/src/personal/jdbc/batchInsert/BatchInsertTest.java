/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-02 0:23
 *@Version 1.0
 */
package personal.jdbc.batchInsert;


import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchInsertTest {
    @Test
    public void batchInsert() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtil.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 0;i<20000;i++){
                ps.setObject(1,"name" + Integer.toString(i));
                ps.execute();
            }
            long end = System.currentTimeMillis();
            //插入2w条数据花费的时长是63069
            System.out.println("插入2w条数据花费的时长是"+ (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps);
        }
    }

    @Test
    public void batchInsert1(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtil.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 0;i<=1000000;i++){
                ps.setObject(1,"name" + Integer.toString(i));
                ps.addBatch();
                if(i%500 == 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }

            }
            long end = System.currentTimeMillis();
            //插入100w条数据花费的时长是16438
            System.out.println("插入100w条数据花费的时长是"+ (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps);
        }
    }

    @Test
    public void batchInsert2(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 0;i<=1000000;i++){
                ps.setObject(1,"name" + i);
                ps.addBatch();
                if(i%500 == 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            conn.commit();
            long end = System.currentTimeMillis();
            //插入100w条数据花费的时长是9704
            System.out.println("插入100w条数据花费的时长是"+ (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps);
        }
    }
}
