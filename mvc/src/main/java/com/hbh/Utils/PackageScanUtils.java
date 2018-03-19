package com.hbh.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
public class PackageScanUtils {
    // todo: 2018/2/27 0027  完成包扫描工具
    private Set<Class> classes = new HashSet<>();
    /**
     * default current package
     */
    public PackageScanUtils() {
    }

    public void doPackageScan(String[] packageNames){
        Enumeration<URL> dirs;
       for(String packageName:packageNames){
           String path = packageName.replace(".","/");
           try {
               dirs = Thread.currentThread().getContextClassLoader().getResources(path);
               while (dirs.hasMoreElements()){
                   URL url = dirs.nextElement();
                   String protocol = url.getProtocol();
                   if("file".equals(protocol)){
                       System.err.println("file scan");
                       File file = new File(url.getPath());
                       if (!file.exists() || !file.isDirectory()) {
                           return;
                       }
                       File[] dirfiles = file.listFiles(new ClassFileFilter());
                       for (File f : dirfiles){
                           // 如果是目录 则继续扫描
                           if (f.isDirectory()) {
                               doPackageScan(new String[]{packageName + "." + f.getName()});
                           }else{
                               // 如果是java类文件 去掉后面的.class 只留下类名
                               String className = f.getName().substring(0, f.getName().length() - 6);
                               try{
                                   // 添加到集合中去
                                   // classes.add(Class.forName(packageName + '.' +
                                   // className));
                                   // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                                   classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                                   System.out.println(classes);
                               }catch (ClassNotFoundException e){
                                   // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                                   e.printStackTrace();
                               }
                           }
                       }
                   }else if ("jar".equals(protocol)){
                       System.out.println("jar scan");
                   }else {
                       System.err.println("Please check package and class file");
                   }
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
    public Set<Class> getClasses(){
        return classes;
    }
}
