package com.hlp.utils;

public class webutils {
    public static int paraInt(String strNum, int defaultval) {
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println("格式不对");
            ;
        }
        return defaultval;
    }
}