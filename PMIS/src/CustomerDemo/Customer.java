/**
 * @description:Customer 类，用于存储客户信息
 * @author xiezhh
 * @create 2021-04-13 23:38
 */
package CustomerDemo;

public class Customer {
    private String name;
    private char  sex;
    private int age;
    private String tel;
    private String email;

    public Customer(String name,char sex,int age,String tel,String email){
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
