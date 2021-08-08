/*
 *@Description:
 *@author:xiezhh
 *@create:2021-08-02 23:20
 *@Version 1.0
 */
package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

public class TeamService {
    //用于自动生成团队成员id
    private static int counter = 1;
    //记录成员上限
    private final int MAX_MEMBER = 5;
    //保存当前团队成员
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0 ;

    public TeamService() {
    }
    /**
     * @description 返回所有成员构成的数组
     * @author xiezhh snail915@qq.com
     * @date 2021/8/2 23:26
     * @return domain.Programmer[]
     */
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e)throws TeamException{
        if(total >= MAX_MEMBER){
            throw new TeamException("团队成员已满，无法添加");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加进组");
        }
        Programmer p = (Programmer)e;

        //判断是否已经存在项目中
        if(isExit(p)){
            throw new TeamException("该成员已经存在项目中，不允许重复添加");
        }
        //判断用户状态
        if(p.getStatus().getNAME().equals("BUSY")){
            throw new TeamException("成员忙，无法添加");
        }else if(p.getStatus().getNAME().equals("VOCATION")){
            throw new TeamException("成员正在修改，无法添加");
        }

        int numOfArch = 0,numOfDsgn = 0 ,numOfPrg = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect){
                numOfArch++;
            }else if(team[i] instanceof Designer){
                numOfDsgn++;
            }else if(team[i] instanceof Programmer){
                numOfPrg++;
            }
        }
        if(p instanceof Architect){
            if(numOfArch >= 1){
                throw new TeamException("团队中只允许有一个架构师");
            }
        }else if(p instanceof Designer){
            if(numOfDsgn >= 2){
                throw new TeamException("团队中只允许有两个设计师");
            }
        }else if(p instanceof Programmer){
            if(numOfPrg >= 3){
                throw new TeamException("团队中只允许有三个程序员");
            }
        }

        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;
    }

    /**
     * @description 删除团队成员
     * @author xiezhh snail915@qq.com
     * @date 2021/8/3 22:30
     * @param memberId
     * @return void
     */
    public void removeMember(int memberId)throws TeamException{
        int n = 0;
        for(;n < total;n++){
            if(team[n].getMemberId() == memberId ){
                team[n].setStatus(Status.FREE);
                break;
            }
        }
        if(n == total){
            throw new TeamException("没有找到该成员，无法删除");
        }

        for (int i = n+1; i < total; i++) {
            team[i - 1] = team[i];
            team[--total] = null;
        }
    }
    /**
     * @description 判断 p是否已经存在于 team 中
     * @author xiezhh snail915@qq.com
     * @date 2021/8/3 22:12
     * @param p
     * @return boolean
     */
    private boolean isExit(Programmer p) {
        for (int i = 0; i < total; i++) {
            if(team[i].getId() == p.getId()){
                return true;
            }
        }
        return false;
    }
}
