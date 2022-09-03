package personal.dao;

import Bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomerDAO {
    /**
     * @description 添加一行数据
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:17
     * @param connection
     * @param customer
     * @return void
     */
    void insert(Connection connection, Customer customer);

    /**
     * @description 更新一个数据
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:21
     * @param connection
     * @param customer
     * @return void
     */
    void update(Connection connection,Customer customer);

    /**
     * @description 查询一个对象
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:22
     * @param connection
     * @param i
     * @return Bean.Customer
     */
    Customer getCustomerById(Connection connection,int i);

    /**
     * @description 查询全部对象
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:22
     * @param connection
     * @return java.util.List<Bean.Customer>
     */
    List<Customer> getAllCustomer(Connection connection);

    /**
     * @description 查询条目数
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:22
     * @param connection
     * @return long
     */
    long getCount(Connection connection);

    /**
     * @description 查询最大生日值
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:22
     * @param connection
     * @return java.sql.Date
     */
    Date getMaxBirth(Connection connection);

}
