package com.hbh.Utils;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by chenbin@megaeyes.com on 2018/3/6 0006.
 */
public class ClassFileFilter implements FileFilter{
    @Override
    public boolean accept(File pathname) {
        return (pathname.isDirectory()) || (pathname.getName().endsWith(".class"));
    }
}
