package com.hbh.bean;

/**
 * Created by chenbin@megaeyes.com on 2018/3/16 0016.
 */

/**
 * 用来存放视图信息如视图名等等
 */
public class View {
    private String viewName;

    public View(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
