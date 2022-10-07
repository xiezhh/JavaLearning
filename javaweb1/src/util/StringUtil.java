/*
 *@Description:
 *@author:xiezhh
 *@create:2022-10-07 23:12
 *@Version 1.0
 */
package util;

public class StringUtil {
    public static  boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
