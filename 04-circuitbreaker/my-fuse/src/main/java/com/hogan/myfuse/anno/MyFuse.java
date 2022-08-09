package com.hogan.myfuse.anno;

import java.lang.annotation.*;

/**
 * 熔断器切面注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyFuse {

}
