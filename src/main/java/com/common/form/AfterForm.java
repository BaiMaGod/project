package com.common.form;


/**
 * 入参接收 后置处理 接口
 * 实现该接口后，拦截器将会在方法执行后，调动本接口方法
 * @author yc
 */
public interface AfterForm {

    /**
     * 对入参进行预处理（去掉前密码等敏感字符）
     */
    default void afterFilter(){

    }

}
