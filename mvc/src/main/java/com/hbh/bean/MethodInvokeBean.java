package com.hbh.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
public class MethodInvokeBean {
    private String className;
    private Method method;
    private Type returnType;

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public MethodInvokeBean(String className, Method method) {
        this.className = className;
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
