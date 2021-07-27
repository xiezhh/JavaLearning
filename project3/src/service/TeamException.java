/**
 * @ClassName:TeamException
 * @description:
 * @author:xiezhh
 * @create:2021-07-19 23:18
 * @Version 1.0
 */
package service;

public class TeamException extends Exception{
    static final long serialVersionUID  = -33875169124229948L;

    public TeamException(){
    }

    public TeamException(String message){
        super(message);
    }
}
