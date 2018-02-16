package com.homework.common.exception;

/**
 * @author：ldy on 16/02/2018 09:02
 */
public class BaseRuntimeException extends RuntimeException {

    /**
     *@see ExceptionCodeEnum
     */
    private Integer code;

    public BaseRuntimeException() {
        super();
    }

    protected BaseRuntimeException(String message) {
        super(message);
    }

    protected BaseRuntimeException(String message, Throwable e) {
        super(message, e);
    }

    protected BaseRuntimeException(Throwable e) {
        super(e);
    }

    public Integer getCode() {
        return code;
    }

    protected void setCode(Integer code) {
        this.code = code;
    }
}
