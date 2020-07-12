package com.common.config.validator;

import com.common.config.exception.AppException;
import com.common.result.ResultStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @description：hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * @modified By：
 * @version:
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws AppException  校验不通过，则报AppException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws AppException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
        	ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
            throw new AppException(ResultStatus.ERROR_Parameter,constraint.getMessage());
        }
    }

    /**
     * 验证 对象参数是否有错
     * @param object        待校验对象
     * @param groups        待校验的组
     * @return true: 有错，false: 无措
     */
    public static boolean hasError(Object object, Class<?>... groups){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);

        if (!constraintViolations.isEmpty()) {
            return true;
        }

        return false;
    }


}
