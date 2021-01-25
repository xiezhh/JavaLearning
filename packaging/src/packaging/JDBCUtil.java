package packaging;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JDBCUtil  {
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/localhost?useUnicode=true&characterEncoding=UTF-8";
    public static String USER_NAME = "root";
    public static String PASSWORD = "root";

    //加载驱动
    static {
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public JDBCUtil (){

    }

    //获得连接
    public static  Connection getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }

    //关闭连接
    public static void close(ResultSet rs,Statement st,Connection con){
        try{
            try{
                if(rs != null){
                    rs.close();
                }
            }finally{
                try{
                    if(st != null){
                        rs.close();
                    }
                }finally {
                    if(st != null){
                        rs.close();
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //关闭连接
//    参数 ResultSet rs
    public static void close(ResultSet rs){
        Statement st = null;
        Connection con = null;
        try{
            try{
                if(rs != null){
                    rs.close();
                }
            }finally {
                try{
                    if(st != null){
                        con = st.getConnection();
                        st.close();
                    }
                }finally {
                    if(con != null){
                        con.close();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

/**
    * 关闭连接
    * @para:
    */
    public static void close(Statement st,Connection con){
        try{
            try{
                if(st != null){
                    st.close();
                }
            }finally {
                if(con != null){
                    con.close();
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

//    增加/更新/删除
    public static int update(String sql,Object...args){
        int result = 0;
        Connection con = getConnection();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            if(args != null){
                for(int i =0;i<args.length;i++){
                    ps.setObject((i+1),args[i]);
                }
            }
            result = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(ps,con);
        }
        return result;
    }
    /*
     * 查询
     * @Param sql
     * @Param args
     * @return Result
     * */
    public static ResultSet query(String sql,Object...args){
        ResultSet result = null;
        Connection con = getConnection();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            if(args != null){
                for(int i = 1;i < args.length;i++){
                    ps.setObject((i+1),args[i]);
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(ps,con);
        }
        return result;
    }

    /**
    * 查询单个记录
    * @Param sql
    * @Param args
    * @Param Map<String Object>
    * */
    public static Map<String,Object> queryForMap(String sql,Object...args){
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> list = queryForList(sql,args);
        if(list.size() > 0){
            result = list.get(0);
        }
        return result;
    }

    /**
    * 查询单个记录返回强类型
    * @Param sql
    * @Param args
    * @return <T> 泛型
    * */
    public static <T> T queryForObject(String sql,Class<T> clz, Object...args){
        T result = null;
        List<T> list = queryForList(sql,clz,args);
        if(list.size() > 0){
            result = list.get(0);
        }
        return result;
    }

    /**
    * 查询单个记录
    * @Param sql
    * @Param args
     * @return List<Map<String,Object>>
    * */
    public static List<Map<String,Object>> queryForList(String sql,Object...args){
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(sql);
            if(args != null){
                for(int i = 1;i < args.length;i++){
                    ps.setObject((i+1),args[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                Map<String,Object> map = new HashMap<String ,Object>();
                for(int i = 1;i <= columnCount;i++){
                    map.put(rsmd.getColumnLabel(i),rs.getObject(i));
                }
                result.add(map);
                }
            }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs,ps,con);
        }
        return result;
    }

    /**
    * 查询多个对象，返回强类型集合
    * @Param sql
    * @Param args
    * @return List<T>
    * */
    public static <T>List<T> queryForList(String sql,Class<T> clz,Object...args){
        List<T> result = new ArrayList<T>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(sql);
            if(args != null){
                for(int i = 1;i < args.length;i++){
                    ps.setObject((i+1),args[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                T obj = clz.newInstance();
                for(int i = 1;i <= columnCount;i++){
                    String columnName = rsmd.getColumnName(i);
                    String methodName = "set" + columnName.substring(0,1).toUpperCase() + columnName.substring(1,columnName.length());
                    Method method[] = clz.getMethods();
                    for(Method meth:method){
                        if(methodName.equals(meth.getName())){
                            meth.invoke(obj,rs.getObject(i));
                        }
                    }
                }result.add(obj);
            }
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }finally{
            close(rs,ps,con);
        }
        return result;
    }







//    public static void main(String[] args){
//        System.out.println("Hello world.");
//    }
}
