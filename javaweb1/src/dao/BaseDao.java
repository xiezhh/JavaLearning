/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-09 9:52
 *@Version 1.0
 */
package dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {

    /**
     * @description 通用删除、更新操作；返回0，说明未执行操作
     * @author xiezhh snail915@qq.com
     * @date 2022/9/9 10:06
     * @param connection
     * @param sql
     * @param args
     * @return int
     */
    public int getInstance(Connection connection, String sql,Object ...args)  {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.CloseConnection(connection,ps);
        }
        return 0;
    }

    public <T>T getInstance(Connection connection,Class<T> clazz ,String sql,Object ...args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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

    public <T> List<T> getForList(Connection connection,Class<T> clazz,String sql,Object ...args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while(rs.next()){
                ResultSetMetaData rsmd = rs.getMetaData();
                T t = clazz.newInstance();
                int columnCount = rsmd.getColumnCount();
                for(int i =0;i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.CloseConnection(connection,ps,rs);
        }
        return null;
    }

}
