package com.hbh.bean;

import com.hbh.Utils.PackageScanUtils;
import com.hbh.Utils.StringUtils;
import com.hbh.Utils.UrlPatternUtils;
import com.hbh.annotations.Autowire;
import com.hbh.annotations.Controller;
import com.hbh.annotations.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
public class BeansResolve {

    private void resolveFileds(Class clazz){
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(Autowire.class)){
                Autowire butowire = field.getAnnotation(Autowire.class);
                try {
                    Class clacc = butowire.clazz();
                    field.setAccessible(true);
                    field.set(obj,clacc.newInstance());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        BeansFactory.putBean(clazz.getName(),obj);

    }
    private void resolveMethods(Class clazz){
        Method[] methods  = clazz.getDeclaredMethods();
        for(Method method : methods){
           //todo:spring使用asm解析方法参数名，我们也可以使用这种方式
            //todo：最难的部分应该就是ModelAndView的实现
            //todo:看一下并发的HashMap有何不同
            if(method.isAnnotationPresent(RequestMapping.class)){
                RequestMapping route = method.getAnnotation(RequestMapping.class);
                if(!StringUtils.isEmpty(route.value())){
                    String methodMapping = UrlPatternUtils.UrlPatternParse(route.value());
                    BeansFactory.putMapping(methodMapping,new MethodInvokeBean(clazz.getName(),method));
                }
                String[] values = route.values();
                if(0 != values.length){
                    for(String value : values){
                        if(StringUtils.isEmpty(value)){
                            continue;
                        }
                        String methodMapping = UrlPatternUtils.UrlPatternParse(value);
                        BeansFactory.putMapping(methodMapping,new MethodInvokeBean(clazz.getName(),method));
                    }
                }
            }
        }
    }
    private void resolveClasses(Class clazz){
        if(clazz.isAnnotationPresent(Controller.class)){
            resolveFileds(clazz);
            resolveMethods(clazz);
        }
    }
    public BeansResolve(Class clazz) {
        resolveClasses(clazz);
    }
    public BeansResolve(String[] packageName) {
        PackageScanUtils packageScanUtils = new PackageScanUtils();
        packageScanUtils.doPackageScan(packageName);
        Set classes =packageScanUtils.getClasses();
        Iterator<Class> iterator = classes.iterator();
        while (iterator.hasNext()){
            resolveClasses(iterator.next());
        }
    }
}
