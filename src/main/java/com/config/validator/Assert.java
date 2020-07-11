package com.config.validator;

import com.config.exception.AppException;
import com.result.ResultStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * @description： 验证断言 验证属性，如果属性不合要求，抛异常
 */
public abstract class Assert {

    public static void isBlank(String str,int code, String message) {
        if (StringUtils.isBlank(str)) {
            throw new AppException(code,message);
        }
    }

    public static void isBlank(String str, ResultStatus resultStatus) {
        if (StringUtils.isBlank(str)) {
            throw new AppException(resultStatus.getCode(),resultStatus.getMsg());
        }
    }

    public static void isNull(Object object, int code, String message) {
        if (object == null) {
            throw new AppException(code,message);
        }
    }
}
