package com.common.form;


/**
 * 入参接收 前置处理 接口
 * 实现该接口后，拦截器将会在方法执行前，调动本接口方法
 * @author yc
 */
public interface BeforeForm {



    /**
     * 用于检查参数是否合法（编码是否正确、字符串正则校验），如果不合法，抛出自定义运行时异常。
     */
    default void check(){

    }

    /**
     * 对入参进行预处理（去掉前后空格、编码转换、模糊匹配字符串拼接）
     */
    default void filter(){

    }

}
