/**
 * @ClassName:PC
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:03
 * @Version 1.0
 */
package domain;

import service.Equipment;

public class PC implements Equipment {
    private String model;
    private String display;

    public PC() {
        super();
    }

    public PC(String model, String display) {
        super();
        this.model = model;
        this.display = display;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String getDescription() {
        return model + "（" + display + ")";
    }
}
