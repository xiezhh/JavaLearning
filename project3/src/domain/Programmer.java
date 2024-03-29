/**
 * @ClassName:Programmer
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 22:47
 * @Version 1.0
 */
package domain;

import service.Equipment;
import service.Status;

public class Programmer extends Employee{
    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.memberId = memberId;
        this.status = status;
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getDetailsForTeam(){
        return getMemberDetails() + "\t程序员";
    }

    protected String getMemberDetails() {
        return getMemberId() + "\t" + getDetails();
    }

    @Override
    public String toString() {
        return getDetails() + "\t程序员\t" + status  + "\t\t\t" + equipment.getDescription();
    }
}
