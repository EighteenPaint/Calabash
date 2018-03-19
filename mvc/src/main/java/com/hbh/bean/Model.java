package com.hbh.bean;

/**
 * Created by chenbin@megaeyes.com on 2018/3/16 0016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来存放Request的atttrbuite值
 */
public class Model {
    private final Map<String,Object> attributes = new HashMap<>(20);


    public void addAttribute(String name, Object object){
        attributes.put(name,object);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
