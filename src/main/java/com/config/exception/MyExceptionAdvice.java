package com.config.exception;

import com.result.Result;
import com.result.ResultStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 统一 异常 处理
 */
@RestControllerAdvice
public class MyExceptionAdvice {


    /**
     * 参数校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public Result validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();

        HashMap<String,String> errorInfo = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorInfo.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return Result.fail(errorInfo, ResultStatus.ERROR_Parameter);
    }

    /**
     * 应用程序异常
     */
    @ExceptionHandler(value = AppException.class)
    public Result appExceptionHandler(AppException e) {

        return Result.fail(e.getCode(),e.getMsg());
    }

    /**
     * 系统异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e){

        e.printStackTrace();

        return Result.fail(ResultStatus.ERROR_SYS);
    }

}