/**
 * @ClassName:NameListService
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:20
 * @Version 1.0
 */
package service;

import domain.*;
import static service.Data.*;


public class NameListService {
    private Employee[] employees;

    public NameListService(){
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < employees.length; i++) {
            //获取通用属性
            int type = Integer.parseInt(EMPLOYEES[i][0]);
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);

            //获取其他属性
            Equipment eq;
            double bonus ;
            int stock;

            switch(type){
                case EMPLOYEE:
                    employees[i] = new Employee(id,name,age,salary);
                    break;
                case PROGRAMMER:
                    eq = createEquipment(i);
                    employees[i] = new Programmer(id,name,age,salary,eq);
                    break;
                case DESIGNER:
                    eq = createEquipment(i);
                    bonus = Integer.parseInt(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id,name,age,salary,eq,bonus);
                    break;
                case ARCHITECT:
                    eq = createEquipment(i);
                    bonus = Integer.parseInt(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id,name,age,salary,eq,bonus,stock);
                    break;
            }
        }
    }

    private Equipment createEquipment(int index) {
        int type = Integer.parseInt(EQUIPMENTS[index][0]);
        switch(type){
            case PC:
                return new PC(EQUIPMENTS[index][1],EQUIPMENTS[index][2]);
            case NOTEBOOK:
                int price = Integer.parseInt(EQUIPMENTS[index][2]);
                return new Notebook(EQUIPMENTS[index][1],price);
            case PRINTER:
                return new Printer(EQUIPMENTS[index][1],EQUIPMENTS[index][2]);
        }
        return null;
    }


    /**
     * @description 返回全部员工信息
     * @author xiezhh snail915@qq.com
     * @date 2021/8/2 23:15
     * @return domain.Employee[]
     */
    public Employee[] getAllEmployees(){
        return employees;
    }

    /**
     * @description TODO
     * @author xiezhh snail915@qq.com
     * @date 2021/7/26 23:25
     * @param id
     * @return domain.Employee
     */
    public Employee getEmployee(int id) throws TeamException{
        for(Employee e : employees){
            if(e.getId() == id){
                return e;
            }
        }
        throw new TeamException("该员工不存在");
    }

}
