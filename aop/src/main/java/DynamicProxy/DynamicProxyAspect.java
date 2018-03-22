package DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;

/**
 * Created by chenbin@megaeyes.com on 2018/3/21 0021.
 */
public class DynamicProxyAspect implements InvocationHandler{
    private Object target;

    /**
     *绑定委托对象并返回一个【代理占位】
     * @param target 真实对象
     * @return 代理对象占位
     */
    public  Object bind(Object target) {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        String methodName = method.getName();
        //反射方法前调用
        before(methodName,args);
        //反射执行方法  相当于调用target.sayHelllo;
        result=method.invoke(target, args);
        //反射方法后调用.
        after(methodName,args);
        return result;
    }

    private  Object before(String methodName , Object... args){
        PointCut pointCut = AOPBeanMap.aopBeans.get(methodName);
        return pointCut.invokeBefore(methodName,args);
    }

    private  Object after(String methodName,Object... args){
        PointCut pointCut = AOPBeanMap.aopBeans.get(methodName);
        return pointCut.invokeAfter(methodName,args);
    }
}
