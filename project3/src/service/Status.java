/**
 * @ClassName:Status
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 22:49
 * @Version 1.0
 */
package service;

public class Status {
    private final String NAME;
    private Status(String name){
        this.NAME= name;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getNAME(){
        return NAME;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
