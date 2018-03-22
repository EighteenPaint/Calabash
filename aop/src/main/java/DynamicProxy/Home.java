package DynamicProxy;

import java.util.Map;

/**
 * Created by chenbin@megaeyes.com on 2018/3/21 0021.
 */
public class Home {
    public static void main(String[] args) {
        AdviceCaculator adviceCaculator = new AdviceCaculator();
        PointCut pointCut = new PointCut(adviceCaculator);
        Caculator caculator = new CaculatorImpl();
        DynamicProxyAspect dynamicProxyAspect = new DynamicProxyAspect();
        caculator = (Caculator) dynamicProxyAspect.bind(caculator);
        caculator.calculate(5, 2);
    }
}
