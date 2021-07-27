/**
 * @ClassName:Designer
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:00
 * @Version 1.0
 */
package domain;

import service.Equipment;
import service.Status;


public class Designer extends Programmer{
    private double bonus;

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary, int memberId, Status status, Equipment equipment, double bonus) {
        super(id, name, age, salary, memberId, status, equipment);
        this.bonus = bonus;
    }


    public double getBonus(double bonus) {
        return bonus;
    }


    public void setBonus(double bonus) {
        this.bonus = bonus;

    }
}
