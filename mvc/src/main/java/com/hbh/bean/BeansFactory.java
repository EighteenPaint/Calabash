package com.hbh.bean;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 * 这里使用HashMap作为存储Bean的工具，是需要注意HashMap的效率
 */
public class BeansFactory {
    private static HashMap<String,Object> BeanMaps = new HashMap<String, Object>();
    private static HashMap<String,MethodInvokeBean> RequestMaps = new HashMap<String, MethodInvokeBean>();
    public static Object getBean(String bkey){
        return BeanMaps.get(bkey);
    }
    static void putBean(String className,Object obj){
        BeanMaps.put(className,obj);
    }
    static void putMapping(String mapping,MethodInvokeBean methodInvokeBean){
        RequestMaps.put(mapping,methodInvokeBean);
    }
    public static MethodInvokeBean getMapping(String mapping){
        return RequestMaps.get(mapping);
    }

}
