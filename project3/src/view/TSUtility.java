/**
 *@ClassName:TSUtility
 *@Description:
 *@author:xiezhh
 *@create:2021-07-15 21:46
 *@Version 1.0
 **/
package view;

import java.util.Scanner;


public class TSUtility {
    private static Scanner scanner = new Scanner(System.in);

    public static char readMenuSelection(){
        char c;
       for(;;){
           String str = readKeyBoard(1,false);
           c = str.charAt(0);
           if(c != '1' && c != '2' && c != '3' && c != '4'){
                System.out.print("输入有误，请重新输入");
           }else {
               break;
           }
       }
       return c;
    }



    public static void readReturn(){
        System.out.print("按回车键继续");
        readKeyBoard(100,false);
    }


    public static int readInt(){
        int n;
        for(;;){
            String str = readKeyBoard(2,false);
            try{
                n =  Integer.parseInt(str);
                break;
            }catch (NumberFormatException e){
                System.out.print("输入错误，不是数值");
            }
        }
        return n;
    }

    public static char readConfirmSelection(){
        char c;
        String str = readKeyBoard(1,false).toUpperCase();
        c = str.charAt(0);
        if(c == 'Y' || c == 'N'){
            return c;
        }else{
            System.out.print("你的输入有误，请重新输入");
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn){
        String line = "";
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.length() == 0){
                if(blankReturn){
                    return line;
                }else {
                    continue;
                }
            }

            if(line.length() > limit || line.length() < 1){
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }
        return line;
    }
}
