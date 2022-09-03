/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-03 22:23
 *@Version 1.0
 */
package personal.dao;

import Bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl  extends  BaseDAO implements CustomerDAO{
    @Override
    public void insert(Connection connection, Customer customer) {
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        update(connection,sql,customer.getName(),customer.getEmail(),customer.getBirth());
    }

    @Override
    public void update(Connection connection, Customer customer) {
        String sql = "update customers set name = ?,email =? ,birth=? where id=?";
        update(connection,sql,customer.getName(),customer.getEmail(),customer.getBirth(),customer.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int i) {
        String sql = "select id,name,email,birth from customers where id =?";
        return getInstance(connection,Customer.class,sql,i);
    }

    @Override
    public List<Customer> getAllCustomer(Connection connection) {
        String sql = "select id,name,email,birth from customers";
        return getForList(connection,Customer.class,sql);
    }

    @Override
    public long getCount(Connection connection)  {
        String sql = "select count(*) from customers";
        return getValue(connection,sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(birth) from customers";
        return getValue(connection,sql);
    }
}
