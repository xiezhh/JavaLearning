/**
 * @description:
 * @author xiezhh
 * @create 2021-04-14 23:02
 */
package CustomerDemo;


public class CustomerView{
   private CustomerList customerList = new CustomerList(10);

   public CustomerView(){

       Customer customer = new Customer("李磊",'男',21,"13444433332","1233412@qq.com");
       customerList.addCustomer(customer);
//       customerList.getTotal();

   }

    public void enterMainMenu(){
        System.out.println("欢迎登陆用户管理系统，请选择你要执行的操作");
        System.out.println("1. 新增用户");
        System.out.println("2. 修改客户信息");
        System.out.println("3. 删除用户");
        System.out.println("4. 查询用户");
        System.out.println("5. 退出系统");

    }
    private void addNewCustomer(){
        String name = CMUtility.readString(20);
        char sex = CMUtility.readChar();
        int age = CMUtility.readInt();
        String tel = CMUtility.readString(11);
        String email = CMUtility.readString(20);
        Customer customer = new Customer(name,sex,age,tel,email);
        customerList.addCustomer(customer);
    }
    private void modifyCustomer(){

    }
    private void deleteCustomer(){

    }
    private void listAllCustomer(){
        for (int i = 0;i< customerList.getTotal();i++){
            System.out.print(i+1);
            System.out.print("\t");
            System.out.println(customerList.getCustomer(i).getName());
        }
    }

    public static void main(String[] args){
       // CMUtility.readMenuSelection();
        System.out.println("Hello World.");
        CustomerView view = new CustomerView();
        view.enterMainMenu();
        char menu = CMUtility.readMenuSelection();
        switch (menu){
            case '1':
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            default:
                break;
        }




        }

//        char c;
//        System.out.println(c);

}
