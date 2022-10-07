package personal.junit;

import bean.Customer;
import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import personal.dao.CustomerDAOImpl;

public class CustomerDAOImplTest {
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void testInsert()  {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Customer customer = new Customer();
            customer.setId(1);
            customer.setName("刘德华");
            customer.setEmail("liudehua@qq.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1950-10-10");
            customer.setBirth(new Date(date.getTime()));
            dao.insert(connection,customer);
            System.out.printf("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection,null);
        }
    }

    @Test
    public void testUpdate() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Customer customer = new Customer();
            customer.setId(3);
            customer.setName("李克勤");
            customer.setEmail("likeqing@qq.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1950-10-10");
            customer.setBirth(new Date(date.getTime()));
            dao.update(connection,customer);
            System.out.printf("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection,null);
        }
    }

    @Test
    public void getCustomerById() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Customer customer =  dao.getCustomerById(connection,3);
            System.out.printf("获取成功"+" "+customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection,null);
        }
    }

    @Test
    public void getAllCustomer() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            List<Customer> customer =  dao.getAllCustomer(connection);
            customer.forEach(System.out::println);
            //System.out.printf("获取成功"+" "+customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection,null);
        }
    }

    @Test
    public void getCount() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            long i = dao.getCount(connection);
            System.out.printf("查询成功"+" "+i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection,null);
        }
    }

    @Test
    public void getMaxBirth() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Date date =  dao.getMaxBirth(connection);
            System.out.printf("查询成功"+" "+ date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection,null);
        }
    }
}