package com.homework.common.exception;

public enum ExceptionCodeEnum {


    //通用处理异常
    SYSTEM_ERROR(-1, "系统异常，前端不处理"),

    FRONT_NOTIFIABLE(-2, "前端弹窗异常"),
    FRONT_TOAST_NOTIFIABLE(-3, "前端toast异常"),

    //框架自动处理的异常
    NOT_LOGIN(1, "未登录"),
    NO_PRIVILEGE(2, "无权限");

    private Integer code;
    private String msg;

    private ExceptionCodeEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static boolean isCodeValid(Integer code) {
        for (ExceptionCodeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static ExceptionCodeEnum getByCode(Integer code) {
        for (ExceptionCodeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static ExceptionCodeEnum getByMsg(String msg) {
        for (ExceptionCodeEnum e : values()) {
            if (e.getMsg().equals(msg)) {
                return e;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
