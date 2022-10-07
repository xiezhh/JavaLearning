/*
 *@Description:
 *@author:xiezhh
 *@create:2022-08-31 22:12
 *@Version 1.0
 */
package PreparedStatementTest;

import bean.Customer;
import bean.Order;
import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CommonQueryForAnyTable {
    @Test
    public void getInstanceTest(){
        String sql = "select order_id orderId,order_name orderName from `order` where order_id < ?";
        List<Order> instance = getInstance(Order.class, sql, 3);
        // 下面这句没学过
        instance.forEach(System.out::println);
        //添加了git之后上传一次代码
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

    @Test
    public void commonQueryTest(){
        String sql = "select order_id orderId,order_name orderName from `order` where order_id = ?";
        Order order = commonQuery(Order.class,sql,1);
        System.out.println(order);

        sql = "select name,email from customers where id = ?";
        Customer customer = commonQuery(Customer.class,sql,1);
        System.out.println(customer);
    }

    public <T>T commonQuery(Class<T> clazz,String sql, Object ...args) {
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

                T t = clazz.newInstance();

                int columnCount = rsmd.getColumnCount();
                for(int i = 0;i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps,rs);
        }
        return null;
    }

}
