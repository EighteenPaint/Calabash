package DynamicProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
public class AOPBeanMap {
    public static final Map<String,PointCut> aopBeans = new HashMap<String,PointCut>(1000);
}
