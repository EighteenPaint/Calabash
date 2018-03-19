package com.hbh.listeners;

import com.hbh.bean.BeansResolve;
import com.hbh.controller.HelloController;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
@WebListener
public class MvcInitListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("MvcInitListener -----> Launched");
        new BeansResolve(new String[]{"com.hbh.controller"});
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
