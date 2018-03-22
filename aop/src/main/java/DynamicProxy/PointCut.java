package DynamicProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
public class PointCut {
    private static final Map<String,Method> beforeMap = new HashMap<>();
    private static final Map<String,Method> afterMap = new HashMap<>();;
    private Object aopObject;
    private String methodName;//这个其实也可以用注解
    private String beforeMethodName;
    private Class[] beforeMethodParameters;
    private String afterMethodName;
    private Class[] afterMethodParameters;
    private void init(){
        try {
            beforeMap.put(methodName,aopObject.getClass().getDeclaredMethod(beforeMethodName,beforeMethodParameters));
            afterMap.put(methodName,aopObject.getClass().getDeclaredMethod(afterMethodName,afterMethodParameters));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public void adviceHandler() throws Exception {
       Class clazz = aopObject.getClass();
       if(!clazz.isAnnotationPresent(AspectX.class))
           throw new Exception("this class is not aspect");
       Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            if(method.isAnnotationPresent(Before.class)){
                this.beforeMethodName = method.getName();
                this.beforeMethodParameters = method.getParameterTypes();
            }else if( method.isAnnotationPresent(After.class)){
                this.afterMethodName = method.getName();
                this.afterMethodParameters = method.getParameterTypes();
            }else if( method.isAnnotationPresent(Around.class)){
                this.afterMethodName = this.beforeMethodName = method.getName();
                this.afterMethodParameters = this.beforeMethodParameters = method.getParameterTypes();
            }else if( method.isAnnotationPresent(Point.class)){
                Point point = method.getAnnotation(Point.class);
                String value = point.value();
                if(value == null || value.equals(""))
                    throw new Exception("point is empty string");
                methodName = value;
            }
        }
    }
    public PointCut(Object aopObject) {
        this.aopObject = aopObject;
        try {
            adviceHandler();
            init();
            AOPBeanMap.aopBeans.put(methodName,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Object getAopObject() {
        return aopObject;
    }

    public void setAopObject(Object aopObject) {
        this.aopObject = aopObject;
    }

    public Map<String, Method> getBeforeMap() {
        return beforeMap;
    }

    public Map<String, Method> getAfterMap() {
        return afterMap;
    }

    public Object invokeBefore(String functionName,Object ...args){
        try {
            Method method = this.beforeMap.get(functionName);
            return  method.getParameterTypes().length == 0 ? method.invoke(aopObject):method.invoke(aopObject,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object invokeAfter(String functionName,Object ...args){
        try {
            Method method = this.afterMap.get(functionName);
            return  method.getParameterTypes().length == 0 ? method.invoke(aopObject):method.invoke(aopObject,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
