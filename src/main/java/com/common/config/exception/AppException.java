package com.common.config.exception;

import com.common.result.ResultStatus;

/**
 * 自定义应用程序异常
 */
public class AppException extends RuntimeException{

    private int code;
    private String msg;

    public AppException(){}

    public AppException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public AppException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
    }

    public AppException(ResultStatus resultStatus, String msg) {
        super(resultStatus.getMsg());
        this.code = resultStatus.getCode();
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
