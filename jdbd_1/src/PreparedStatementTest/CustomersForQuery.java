/*
 *@Description:
 *@author:xiezhh
 *@create:2022-08-29 23:44
 *@Version 1.0
 */
package PreparedStatementTest;

import bean.Customer;
import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;

public class CustomersForQuery {

    @Test
    public void CustomerForCommonQueryTest(){
        String sql = "select name,email from customers where id = ?";
        Customer customer =   CustomerForCommonQuery(sql,2);
        System.out.println(customer);
    }

    /**
     * @description 针对customer类的一个通用查询
     * @author xiezhh snail915@qq.com
     * @date 2022/8/30 23:28
     * @param sql
     * @param args
     * @return Bean.Customer
     */
    public Customer CustomerForCommonQuery(String sql,Object ...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtil.getConnection();
            //2.预编译sql
            ps = conn.prepareStatement(sql);
            //3.填充sql
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //4.执行sql
            rs =ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                //如果数据集获取不为空
                //创建一个 Customer 空对象，用于接收数据集
                Customer customer = new Customer();

                for(int i = 0;i < columnCount;i++){
                    Object columnValue = rs.getObject(i+1);

                    //获取每列的列名
                    String columnName = rsmd.getColumnName(i+1);

                    //给customer对象赋值
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer,columnValue);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps,rs);
        }
        return null;
    }

    @Test
    public void CustomerForQueryTest()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtil.getConnection();
            //2.预编译sql语句
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            //3.sql 填充
            ps.setObject(1,2);
            //4.执行查询语句
            rs = ps.executeQuery();
            //5.对查询结果进行处理
            if(rs.next()){
                //next()有查询结果的时候开始处理
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);

                Customer customer = new Customer(id,name,email,birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭数据库连接及其他资源
            JDBCUtil.closeConnection(conn,ps,rs);
        }
    }
}
