/**
 * @description:
 * @author xiezhh
 * @create 2021-04-14 22:48
 */
package CustomerDemo;
import java.util.*;

public class CMUtility {
    private static Scanner scan = new Scanner(System.in);

    public static char readMenuSelection(){
        char c ;
        for(;;){
            String str = readKeyBoard(1,false);
            System.out.println(str);
            c = str.charAt(0);
            if(c != '1' && c != '2' && c != '3' && c!= '4' && c!= '5'){
                System.out.println("输入错误，请重新输入");
            }else{
                break;
            }
        }
        return c;
    }

    public static char readChar(){
        return readKeyBoard(1,false).charAt(0);
    }
    public static char readChar(char defaultValue){
        if(defaultValue != 0){
            return defaultValue;
        }else {
            return readKeyBoard(1, false).charAt(0);
        }
    }
    public static int readInt(){
        return Integer.parseInt(readKeyBoard(10,false));}

    public static int readInt(int defaultValue){
        if(defaultValue != 0){
            return defaultValue;
        }else{
            return Integer.parseInt(readKeyBoard(10,false));
        }
    }
    /**
     * @title: readString
     * @description:
     * @author: xiezhh
     * @param: limit
     * @updateTime: 2021/4/19 20:25
     * @return: int
     * @throws:
     */
    public static String readString(int limit){
        return readKeyBoard(limit,false);
    }
//
//    public static String readString(int limit,String defaultValue){
//
//    }
//    public static char readConfirmSelection(){
//
//    }

    public static String readKeyBoard(int limit,boolean blankReturn){
        String line = "";
        while(scan.hasNext()){
            line = scan.next();
            if(line.length() < 1 || line.length() > limit){
                System.out.println("输入有误，最大不得超过" + limit);
                continue;
            }
            else{
                break;
            }
        }
        return line;
    }
}
