package ClassLoadAOP;

import javassist.*;
import org.omg.PortableInterceptor.Interceptor;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
public class JavaAssitProxy {
    private static final String PROXY_CLASS_NAME = ".Gproxy$";
    private static int proxyIndex = 1;
    protected Interceptor interceptor;

    /**
     * Prohibit instantiation
     * 利用私有构造函数阻止该类实例化
     */
    private JavaAssitProxy() {
    }

    protected JavaAssitProxy(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public static Object createProxy(Class<?> targetClass, Interceptor interceptor) {
        int index = 0;
/*获得运行时类的上下文*/
        ClassPool pool = ClassPool.getDefault();
/*动态创建代理类*/
        CtClass proxy = pool.makeClass(targetClass.getPackage().getName() + PROXY_CLASS_NAME + proxyIndex++);

        try {
/*获得DProxy类作为代理类的父类*/
            CtClass superclass = pool.get("ClassLoadAOP.JavaAssitProxy");
            proxy.setSuperclass(superclass);
/*获得被代理类的所有接口*/
            CtClass[] interfaces = pool.get(targetClass.getName()).getInterfaces();
            for (CtClass i : interfaces) {
/*动态代理实现这些接口*/
                proxy.addInterface(i);
/*获得结构中的所有方法*/
                CtMethod[] methods = i.getDeclaredMethods();
                for (int n = 0; n < methods.length; n++) {
                    CtMethod m = methods[n];
/*构造这些Method参数 以便传递给拦截器的interceptor方法*/
                    StringBuilder fields = new StringBuilder();
                    fields.append("private static java.lang.reflect.Method method" + index);
                    fields.append("=Class.forName(\"");
                    fields.append(i.getName());
                    fields.append("\").getDeclaredMethods()[");
                    fields.append(n);
                    fields.append("];");
/*动态编译之*/
                    CtField cf = CtField.make(fields.toString(), proxy);
                    proxy.addField(cf);
                    GenerateMethods(pool, proxy, m, index);
                    index++;
                }
            }
/*创建构造方法以便注入拦截器*/
            CtConstructor cc = new CtConstructor(new CtClass[]{pool.get("com.lifewool.Interceptor")}, proxy);
            cc.setBody("{super($1);}");
            proxy.addConstructor(cc);
//proxy.writeFile();
            return proxy.toClass().getConstructor(Interceptor.class).newInstance(interceptor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 动态生成生成方法实现（内部调用）
     */
    private static void GenerateMethods(ClassPool pool, CtClass proxy, CtMethod method, int index) {

        try {
            CtMethod cm = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), proxy);
/*构造方法体*/
            StringBuilder mbody = new StringBuilder();
            mbody.append("{super.interceptor.intercept(this,method");
            mbody.append(index);
            mbody.append(",$args);}");
            cm.setBody(mbody.toString());
            proxy.addMethod(cm);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
