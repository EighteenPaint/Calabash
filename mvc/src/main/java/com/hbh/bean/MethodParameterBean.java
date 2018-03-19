package com.hbh.bean;


import com.sun.xml.internal.ws.org.objectweb.asm.Type;

/**
 * Created by chenbin@megaeyes.com on 2018/3/7 0007.
 */
public class MethodParameterBean {
    private String paraName;
    private Type paraType;
    private Class clazz;

    public MethodParameterBean(Type paraType, Class clazz) {
        this.paraType = paraType;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public MethodParameterBean(Type paraType) {
        this.paraType = paraType;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public Type getParaType() {
        return paraType;
    }

    public void setParaType(Type paraType) {
        this.paraType = paraType;
    }
}
