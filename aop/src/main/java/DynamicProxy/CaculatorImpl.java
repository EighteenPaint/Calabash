package DynamicProxy;

/**
 * Created by chenbin@megaeyes.com on 2018/3/21 0021.
 */
public class CaculatorImpl  extends DynamicProxyAspect implements Caculator{


    @Override
    public int calculate(int a, int b) {
        return a / b;
    }

    public Object before(Object... args) {
        for(Object o : args){
            System.out.println(o);
        }

        return null;
    }

    public Object after(Object... args) {
        for(Object o : args){
            System.out.println(o);
        }
        return null;
    }

}
