/*
 *@Description:
 *@author:xiezhh
 *@create:2022-08-30 23:42
 *@Version 1.0
 */
package PreparedStatementTest;

import bean.Order;
import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class OrderForQuery {

    @Test
    public void commonQueryForOrderTest(){
        String sql = "select order_id orderId,order_name orderName from `order` where order_id = ?";
        Order order = commonQueryForOrder(sql,1);
        System.out.println(order);
    }


    public Order commonQueryForOrder(String sql,Object ...args) {
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

            if(rs.next()){
                ResultSetMetaData rsmd = rs.getMetaData();

                Order order = new Order();

                int columnCount = rsmd.getColumnCount();
                for(int i = 0;i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);

                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order,columnValue);
                }
                return order;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps,rs);
        }
        return null;
    }

    @Test
    public void queryForOrderTest()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtil.getConnection();
            //2.预编译sql
            String sql = "select order_id,order_name from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);
            //3.填充sql
            ps.setObject(1,1);

            //4.执行sql 并处理数据
            rs = ps.executeQuery();

            if(rs.next()){
                int orderId = rs.getInt(1);
                String orderName = rs.getString(2);
                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderName(orderName);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭数据库连接
            JDBCUtil.closeConnection(conn,ps,rs);
        }
    }
}

