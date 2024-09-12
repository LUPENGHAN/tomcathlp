package com.hlp.tomcat.Utils;

public class WebUtils {
    public static int parseInt(String str, int defaultValue) {
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException e){
            System.out.println("不能转为数字");
        }
        return defaultValue;
    }
}
