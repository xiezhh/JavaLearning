/**
 * @ClassName:Architect
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:02
 * @Version 1.0
 */
package domain;

import service.Equipment;

public class Architect extends Designer{
    private int stock;

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary,  Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String getDetailsForTeam(){
        return getMemberDetails() + "\t架构师\t" + getStock();
    }

    @Override
    public String toString() {
        return getDetails() + "\t设计师\t" + getStatus() + "\t" + getBonus() + "\t" + getStock() + "\t" + getEquipment().getDescription();
    }
}
