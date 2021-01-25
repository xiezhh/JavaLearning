package Demo;
import java.sql.*;

public class Demo {
    //加载驱动
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        System.out.println("Hello world.");

        Connection conn = null;
        try{
            //获得链接对象，数据库名称
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/localhost","root","root");
            System.out.println("连接成功");

            //查询所有学生信息
            PreparedStatement cmd = conn.prepareStatement("select * from student");

            ResultSet set = cmd.executeQuery();

            while(set.next()){
                System.out.println(set.getInt("id")+"\t");
                System.out.println(set.getString("code")+"\t");
                System.out.println(set.getString("name")+"\t");
            }

            //新增数据
           /* cmd = conn.prepareStatement("insert into student(code,name) values('002','Tom')");

            int n = cmd.executeUpdate();
            System.out.println("共插入"+n+"条数据");*/

            //更新数据
            cmd = conn.prepareStatement("update student set name = 'Jimmy' where code = '002'");
            int n = cmd.executeUpdate();
            System.out.println("更新一行数据");

            //删除数据
            cmd = conn.prepareStatement("delete from student where id = 3");
            n = cmd.executeUpdate();
            System.out.println("删除一行数据");

            //关闭链接
            set.close();

            //查询
        }catch(Exception e){e.printStackTrace();}finally {
            try{
                conn.close();
                System.out.println("关闭链接");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
