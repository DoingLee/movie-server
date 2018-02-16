package com.homework.common.exception;

public class FrontNotifiableRuntimeException extends BaseRuntimeException{
    private static final int FRONT_NOTIFIED_ERROR_CODE = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();

    public FrontNotifiableRuntimeException() {
        super();
        setCode(FRONT_NOTIFIED_ERROR_CODE);

    }

    public FrontNotifiableRuntimeException(String message) {
        super(message);
        setCode(FRONT_NOTIFIED_ERROR_CODE);

    }

    public FrontNotifiableRuntimeException(ExceptionCodeEnum code, String message) {
        super(message);
        setCode(code.getCode());
    }

    public FrontNotifiableRuntimeException(String message, Throwable e) {
        super(message, e);
        setCode(FRONT_NOTIFIED_ERROR_CODE);

    }

    public FrontNotifiableRuntimeException(Throwable e) {
        super(e);
        setCode(FRONT_NOTIFIED_ERROR_CODE);
    }

}
