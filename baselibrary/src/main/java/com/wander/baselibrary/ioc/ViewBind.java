package com.wander.baselibrary.ioc;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: view注解Annotation
 * @Author: liuhao
 * @CreateDate: 2020-04-28 21:57
 */
// @Target(ElementType.FIELD)  代表Annotation的位置  FIELD：属性  TYPE:类  CONSTRUCTOR:构造函数
@Target(ElementType.FIELD)
// @Retention(RetentionPolicy.RUNTIME) 代表什么时候生效  RUNTIME：运行时  CLASS:编译时  SOURCE:源码
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewBind {
    @IdRes int[] value();
}
