/**
 * @ClassName:Printer
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:06
 * @Version 1.0
 */
package domain;


import service.Equipment;

public class Printer implements Equipment {
    private String name;
    private String type;

    public Printer() {
        super();
    }

    public Printer(String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return name + "(" + type + ")";
    }
}
