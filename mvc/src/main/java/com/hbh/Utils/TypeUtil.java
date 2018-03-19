package com.hbh.Utils;

/**
 * Created by chenbin@megaeyes.com on 2018/3/7 0007.
 */
public class TypeUtil {
    public static boolean isLang(String typeStr){
        return typeStr.contains("java.lang");
    }
    public static boolean isUtil(String typeStr){
        return typeStr.contains("java.util");
    }
}
