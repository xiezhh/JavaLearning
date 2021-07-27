/**
 * @description:
 * @author xiezhh
 * @create 2021-04-15 21:02
 */
package CustomerDemo;

public class CustomerList {
    Customer[] customers;
    int total = 0;

    public CustomerList(int totalCustomer){
        customers = new Customer[totalCustomer];
    }

    /**
     * @title: addCustomer
     * @description: 添加客户信息
     * @author: xiezhh
     * @param: customer
     * @updateTime: 2021/4/25 22:51
     * @return: boolean false:添加失败  true:添加成功
     * @throws:
     */
    public boolean addCustomer(Customer customer){
        if(total > customers.length){
            return false;
        }
        customers[total++] = customer;
        return true;
    }

    /**
     * @title:
     * @description: 向指定位置插入客户信息
     * @author: xiezhh
     * @updateTime: 2021/4/25 22:54
     * @throws:
     */
    public boolean replaceCustomer(int index,Customer customer){
        if(index < 0 || index >= total){
            return false;
        }else{
            customers[index] = customer;
            return true;
        }
    }

    /**
     * @title:
     * @description: 删除指定位置客户信息
     * @author: xiezhh
     * @updateTime: 2021/4/25 22:58
     * @throws:
     */
    public boolean deleteCustomer(int index){
        if(index <0 || index >= total){
            return false;
        }else{
            for(int i=index;i<total-1;i++){
                customers[i] = customers[i+1];
            }
            customers[total-1] = null;
            total--;
            return true;
        }
    }
    public Customer[] getAllCustomers(){
        Customer[] custs = new Customer[total];
        for(int i=0;i<total;i++){
            custs[i] = customers[i];
        }
        return custs;
    }
    public Customer getCustomer(int index){
        if(index <0 || index >=total){
            return null;
        }
        return customers[index];
    }

    public int getTotal(){
        return total;
    }
}
