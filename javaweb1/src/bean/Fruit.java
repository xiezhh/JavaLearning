/*
 *@Description:
 *@author:xiezhh
 *@create:2022-09-07 23:34
 *@Version 1.0
 */
package bean;

public class Fruit {
    private long id;
    private String name;
    public double price;
    public double fCount;
    public String remark;


    public Fruit(long id, String name, double price, double fCount, String remark) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fCount = fCount;
        this.remark = remark;
    }

    public Fruit() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getfCount() {
        return fCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setfCount(double fCount) {
        this.fCount = fCount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", fCount=" + fCount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
