package com.hbh.Utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
public class UrlPatternUtils {
    // 2018/2/27 0027 处理Url工具，主要处理斜杠问题
    public String UrlPattern(String url){
        if(url.startsWith("/")){
            url = url.substring(1);
        }
        if(url.endsWith("/")){
            url = url.substring(0,url.length() - 1);
        }
        url.replace("//","/");
        return url;
    }
    public static String UrlPatternParse(String url){
        if(url.startsWith("/")){
            url = url.substring(1);
        }
        if(url.endsWith("/")){
            url = url.substring(0,url.length() - 1);
        }
        url.replace("//","/");
        return url;
    }
    public static String UrlMappingHandler(String requestURI,String contextURI){
        int index = contextURI.length();
        return requestURI.substring(index);
    }
    public static String responseUrl(String forwardUrl){
        if(!forwardUrl.startsWith("/")){
            return "/" + forwardUrl;
        }
        return forwardUrl;
    }
}
