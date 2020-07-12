package com.common.annotation;

import java.lang.annotation.*;

/**
 * 方法入参 前、后处理 注解
 * @author yc
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExeForm {
	// 执行的方法名称
	String[] value() default {};
}
