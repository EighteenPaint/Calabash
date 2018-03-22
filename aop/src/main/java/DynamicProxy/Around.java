package DynamicProxy;

import java.lang.annotation.*;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented
public @interface Around {
}
