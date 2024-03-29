/**
 * @ClassName:Designer
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:00
 * @Version 1.0
 */
package domain;

import service.Equipment;


public class Designer extends Programmer{
    private double bonus;

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String getDetailsForTeam(){
        return getMemberDetails() + "\t设计师\t" + getBonus();
    }

    @Override
    public String toString() {
        return getDetails() + "\t设计师\t" + getStatus() + "\t" + getBonus() + "\t\t" + getEquipment().getDescription();
    }
}
