/**
 * @ClassName:Architect
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:02
 * @Version 1.0
 */
package domain;

import service.Equipment;
import service.Status;

public class Architect extends Designer{
    private int stock;

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, int memberId, Status status, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, memberId, status, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
