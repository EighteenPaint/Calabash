package Cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
public abstract class CGlibProxyFactory implements MethodInterceptor{
    private Object targetObject; // 代理的目标对象
    public Object createProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer(); // 该类用于生成代理对象
        enhancer.setSuperclass(this.targetObject.getClass()); // 设置目标类为代理对象的父类
        enhancer.setCallback(this); // 设置回调用对象为本身
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        JavaServiceBean bean = (JavaServiceBean)this.targetObject;
        Object result = null;
        before(objects);
        result = methodProxy.invoke(targetObject, objects); // 把方法调用委派给目标对象
        after(objects);
        return result;
    }
    abstract Object before(Object ...args);
    abstract Object after(Object ...args);
}
