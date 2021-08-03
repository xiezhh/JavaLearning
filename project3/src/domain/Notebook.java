/**
 * @ClassName:Notebook
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:08
 * @Version 1.0
 */
package domain;

import service.Equipment;

public class Notebook implements Equipment {
    private String model;
    private double price;


    public Notebook() {
        super();
    }


    public Notebook(String model, double price) {
        super();
        this.model = model;
        this.price = price;
    }

    /**
     * @description TODO
     * @author xiezhh snail915@qq.com
     * @date 2021/7/26 23:23
     * @return java.lang.String
     */
    public String getModel() {
        return model;
    }

    /**
     * @description TODO
     * @author xiezhh snail915@qq.com
     * @date 2021/7/26 23:17
     * @param model
     * @return void
     */
    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return  model + "(" + price + ")";
    }
}
