package com.quanmin.djdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: ate
 * @Description: 自定义返回结果集结构
 * @CreateDate: 2019-11-11 16:53
 * @ClassName: com.quanmin.djdata.aspect.AllResultAspect
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface OtherReturn {
}
