/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-03 21:42
 *@Version 1.0
 */
package personal.dao;

import personal.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO {

    /**
     * @description 通用更新操作
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 21:44
     * @param sql
     * @param args
     * @return void
     */
    public int update(Connection conn,String sql,Object ...args){
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for(int i = 0 ;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //3.执行并返回
           return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(null,ps);
        }
        return 0;
    }

    //查询多条结果
    public <T> List<T> getForList(Connection conn,Class<T> clazz, String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtil.closeConnection(null,ps,rs);
        }
        return null;
    }

    /**
     * @description 用于查询一行结果
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 21:44
     * @param clazz
     * @param sql
     * @param args
     * @return T
     */
    public <T>T getInstance(Connection conn,Class<T> clazz,String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.预编译sql
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for(int i =0 ;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //3.执行
            rs = ps.executeQuery();
            //4.结果处理
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
            JDBCUtil.closeConnection(null,ps,rs);
        }
        return null;
    }

    /**
     * @description 查询具体的数值
     * @author xiezhh snail915@qq.com
     * @date 2022/9/3 22:01
     * @param conn
     * @param sql
     * @param args
     * @return E
     */
    public <E> E getValue(Connection conn,String sql,Object ...args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i =0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
             return (E) rs.getObject(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(null,ps,rs);
        }
        return null;
    }
}
