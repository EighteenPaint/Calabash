package com.hbh.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbh.TestPO;
import com.hbh.Utils.MethodASM;
import com.hbh.Utils.TypeConvert;
import com.hbh.Utils.UrlPatternUtils;
import com.hbh.bean.*;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.beanutils.BeanUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
@WebServlet(urlPatterns = {"/"})
public class DispatcherServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);
        //Create Template Engine
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        //Write the response headers
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
//        //Create Servlet context
//        WebContext ctx = new WebContext(req, resp, this.getServletContext(), req.getLocale());
//        ctx.setVariable("helloword","hello thymeleaf,wellcome!");
//        //Executing template engine
//        templateEngine.process("mvc", ctx, resp.getWriter());
        doDispatch(req,resp,templateEngine);
    }
    protected void doDispatch(HttpServletRequest req, HttpServletResponse resp,TemplateEngine templateEngine){
        String urlhandle = UrlPatternUtils.UrlMappingHandler(req.getRequestURI(),req.getContextPath());
        String mapping = UrlPatternUtils.UrlPatternParse(urlhandle);
        MethodInvokeBean methodInvokeBean = BeansFactory.getMapping(mapping);
        if(methodInvokeBean != null) {
            String className = methodInvokeBean.getClassName();
            Method method = methodInvokeBean.getMethod();
            Object object = BeansFactory.getBean(className);
            Object obj = mappingMethodParamterHandler(req,resp,method,object); //得到传递过来的表单数据并处理
            doResponse(obj,method,req,resp,templateEngine);
        }else {
            resp.setStatus(404);
        }

    }
    protected Object mappingMethodParamterHandler(HttpServletRequest req,HttpServletResponse response,Method method,Object object){
        Map<String,String[]> map = req.getParameterMap();//这里为什么是<String,String[]>
        MethodParameterBean[] methodParamNames = new MethodParameterBean[]{};
        try {
            methodParamNames = MethodASM.getMethodParamNames(method);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int paramterCount = methodParamNames.length;

        if(paramterCount != 0 && map.size() != 0) {//说明有参数
            //将参数封装为数组
            Object[] objects = new Object[methodParamNames.length];
            for (int i = 0; i < methodParamNames.length; i++) {
                Type type = methodParamNames[i].getParaType();
                if(type.equals(Type.getType(String[].class))){//判断类型是否相等，因为参数有可能是字符数组类型的
                    objects[i] = TypeConvert.convert(methodParamNames[i].getClazz(),map.get(methodParamNames[i].getParaName()));
                }else if(type.equals(Type.getType(ModelAndView.class))||type.equals(Type.getType(Model.class))){//判断是不是Model或者ModelAndView
                    objects[i] = TypeConvert.convert(methodParamNames[i].getClazz(),req);
                }
                else {
                    if(map.get(methodParamNames[i].getParaName()) == null){//说明有这个参数但是没有表单，即为实体类
                        try {
                            Object newObj = methodParamNames[i].getClazz().newInstance();
                            BeanUtils.copyProperties(newObj,map);
                            objects[i] = newObj;
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }else {
                        objects[i] = TypeConvert.convert(methodParamNames[i].getClazz(),map.get(methodParamNames[i].getParaName())[0]);
                    }
                }
            }
            return doMethodInvoke(method,object,paramterCount,objects);
        }else {//说明没有参数
            return doMethodInvoke(method,object,paramterCount);
        }
    }
    protected Object doMethodInvoke(Method method,Object bean,int paramterCount,Object... args){
        try {
            if (0 == args.length) {
                return method.invoke(bean, new Object[paramterCount]);
            } else {
                return method.invoke(bean, args);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "error";
    }
    protected void doResponse(Object object,Method method,HttpServletRequest request,HttpServletResponse response,TemplateEngine templateEngine){
        //根据方法返回值采取不同的策略，需要封装
        WebContext ctx = new WebContext(request, response, this.getServletContext(), request.getLocale());
        ctx.setVariable("helloword","hello thymeleaf,wellcome!");
        //Executing template engine
        Class returnClass = method.getReturnType();
        String viewName ;//默认error视图
        if(returnClass == String.class){
            viewName = UrlPatternUtils.responseUrl((String)object);
            try {
                templateEngine.process(viewName,ctx,response.getWriter());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if( returnClass == ModelAndView.class){
            ModelAndView modelAndView = (ModelAndView)object;
            viewName = UrlPatternUtils.responseUrl(modelAndView.getViewName());
            ctx.setVariables(modelAndView.getModel().getAttributes());
            try {
                templateEngine.process(viewName,ctx,response.getWriter());
            }catch (IOException e) {
                e.printStackTrace();
            }

        }else {//实体类型就转化为json数据
            ObjectMapper objectMapper = new ObjectMapper();
            String json = "";
            try {
                json = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setContentType("text/json; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println(json);
        }
    }
}
