package com.hbh.Utils;

import com.hbh.bean.Model;
import com.hbh.bean.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenbin@megaeyes.com on 2018/3/13 0013.
 * TypeConvert实现方式主要是泛型方法的使用,加上相应类型valueof或者什么方法
 */
public class TypeConvert {
    public static Object convert(Class targetType,String sourceStrValue){
        if(targetType == Integer.class){
            return Integer.valueOf(sourceStrValue);
        }else if( targetType == Long.class){
            return Long.valueOf(sourceStrValue);
        }else if( targetType == String.class){
            return sourceStrValue;
        }
        return null;
    }
    public static Object[] convert(Class targetType,String[] sourceStrValue){
        final int length = sourceStrValue.length;
        if(targetType == Integer[].class){
            Integer[] integers = new Integer[length];
            for(int i = 0 ; i < length ;i++){
                integers[i] = Integer.valueOf(sourceStrValue[i]);
            }
            return integers;
        }else if( targetType == Long[].class){
            Long[] longs = new Long[length];
            for(int i = 0 ; i < length ;i++){
                longs[i] = Long.valueOf(sourceStrValue[i]);
            }
            return longs;
        }else if( targetType == String[].class){
            return sourceStrValue;
        }
        return null;
    }
    public static Object convert(Class targetType, HttpServletRequest request){
        if( targetType == ModelAndView.class){
            ModelAndView modelAndView = new ModelAndView();
            Enumeration<String> e = request.getAttributeNames();
            while (e.hasMoreElements()){
                String key = e.nextElement();
                modelAndView.addAttribute(key,request.getAttribute(key));
            }
            return modelAndView;
        }else if( targetType == Model.class){
            Enumeration<String> e = request.getAttributeNames();
            Model model = new Model();
            while (e.hasMoreElements()){
                String key = e.nextElement();
                model.addAttribute(key,request.getAttribute(key));
            }
            return model;
        }
        return null;
    }
}
