package com.hbh.annotations;

import java.lang.annotation.*;

/**
 * Created by chenbin@megaeyes.com on 2018/2/27 0027.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowire {
    Class clazz();
}
