package com.homework.common.exception;

public class SystemErrorRuntimeException extends BaseRuntimeException {
    private static final int ERROR_CODE = ExceptionCodeEnum.SYSTEM_ERROR.getCode();


    public SystemErrorRuntimeException() {
        super();
        setCode(ERROR_CODE);

    }

    public SystemErrorRuntimeException(String message) {
        super(message);
        setCode(ERROR_CODE);

    }

    public SystemErrorRuntimeException(String message, Throwable e) {
        super(message, e);
        setCode(ERROR_CODE);

    }

    public SystemErrorRuntimeException(Throwable e) {
        super(e);
        setCode(ERROR_CODE);
    }


}
