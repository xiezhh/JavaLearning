/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-01 23:22
 *@Version 1.0
 */
package personal.jdbc.blob;

import bean.Customer;
import org.junit.Test;
import personal.jdbc.util.JDBCUtil;

import java.io.*;
import java.sql.*;

public class BlobTest {
    @Test
    public void insertBlobTest()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"钟楚红");
            ps.setObject(2,"zhongchuhong@qq.com");
            ps.setObject(3,"1962-02-16");
            FileInputStream fis = new FileInputStream(new File("zhongchuhong.jpg"));
            ps.setObject(4,fis);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn,ps);
        }
    }

    @Test
    public void getBlobTest(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,3);
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer customer = new Customer(id,name,email,birth);

                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("zhongchuhong1.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer,0,len);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            try{
                if(fos != null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            JDBCUtil.closeConnection(conn,ps,rs);
        }


    }
}
