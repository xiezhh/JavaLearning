/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-09 9:52
 *@Version 1.0
 */
package dao;

import util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {

    public int update(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.CloseConnection(connection,ps);
        }
        return 0;
    }

    /**
     * @description 通用删除、更新操作；返回0，说明未执行操作
     * @author xiezhh snail915@qq.com
     * @date 2022/9/9 10:06
     * @param
     * @param sql
     * @param args
     * @return int
     */
    public int getInstance(String sql,Object ...args)  {
        Connection connection =null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.CloseConnection(connection,ps);

        }
        return 0;
    }

    public <T>T getInstance(Class<T> clazz ,String sql,Object ...args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
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
            JDBCUtil.CloseConnection(connection,ps,rs);
        }
        return null;
    }

    public <T> List<T> getForList(Class<T> clazz,String sql,Object ...args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            List<T> list = new ArrayList<T>();
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                T t = clazz.newInstance();

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
            JDBCUtil.CloseConnection(connection,ps,rs);
        }
        return null;
    }

    /**
     * @description TODO
     * @author xiezhh snail915@qq.com
     * @date 2022/9/18 22:52
     * @param connection
     * @param sql
     * @param args
     * @return E
     */
    public <E> E getValue(String sql,Object ...args)  {
        PreparedStatement ps= null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                E e = (E) rs.getObject(1);
                return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.CloseConnection(connection,ps);
        }
        return null;
    }

}
