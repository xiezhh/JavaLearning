package packaging;
//import JDBCUtil.*;

public class packaging {
    public static void main(String[] args){
        //JDBCUtil conection = new JDBCUtil();
        //JDBCUtil.getConnection();
        int n = JDBCUtil.update("update student set name = 'jack' where id =2");
        System.out.println("Hello world.");
    }
}
