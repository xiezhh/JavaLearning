/*
 *@Description:
 *@author: xiezhh
 *@create:2021-07-26 22:00
 *@Version 1.0
 */
package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu(){
        boolean loopFlag = true;
        char key = 0;
        do{
            if(key != '1'){
                listAllEmployees();
            }
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            key = TSUtility.readMenuSelection();
            System.out.println();
            switch(key){
                case '1':
                    listTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("确认退出吗");
                    char yn = TSUtility.readConfirmSelection();
                    if(yn == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }
        }while(loopFlag);

    }

    private void addMember() {
        System.out.println("---------------------添加成员---------------------");
        System.out.print("请输入要添加的员工ID:");
        int id = TSUtility.readInt();
        try{
            Employee e = listSvc.getEmployee(id);
            teamSvc.addMember(e);
            System.out.print("添加成功");
            listTeam();
        } catch (TeamException e) {
            System.out.println("添加失败");
        }
        TSUtility.readReturn();
    }

    private void deleteMember() {
        System.out.println("---------------------删除成员---------------------");
        System.out.print("请输入需要删除员工的成员id");
        int id = TSUtility.readInt();
        System.out.print("确定删除吗（Y/N）:");
        char yn = TSUtility.readConfirmSelection();
        if(yn == 'N'){
            return;
        }
        try{
            teamSvc.removeMember(id);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }

    private void listAllEmployees() {
        System.out.println("\n-------------------------------开发团队调度软件--------------------------------\n");
//        Programmer[] team = teamSvc.getTeam();
        Employee[] employees = listSvc.getAllEmployees();
        if(employees.length == 0){
            System.out.println("开发团队目前没有成员" );
        }else{
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        }
        for(Employee e : employees){
            System.out.println("" + e);
        }
        System.out.println("-----------------------------------------------------");
    }
    private void listTeam() {
        System.out
                .println("\n--------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if (team.length == 0) {
            System.out.println("开发团队目前没有成员！");
        } else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        }

        for (Programmer p : team) {
            System.out.println(" " + p.getDetailsForTeam());
        }
        System.out
                .println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
