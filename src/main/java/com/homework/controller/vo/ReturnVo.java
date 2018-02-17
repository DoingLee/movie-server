package com.homework.controller.vo;

import java.io.Serializable;

public class ReturnVo implements Serializable {

    private int code;
    private String message;
    private Long systemTime = System.currentTimeMillis();
    private Object results;

    public ReturnVo() {
        this.code = ErrorCode.OK.getCode();
        this.message = ErrorCode.OK.getMessage();
    }

    public ReturnVo(Object results) {
        this();
        this.results = results;
    }

    public ReturnVo(Object results, Long systemTime) {
        this();
        this.results = results;
        this.systemTime = systemTime;
    }

    public ReturnVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnVo(ErrorCode codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public ReturnVo(BusinessCode codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public ReturnVo(int code, String message, Object results) {
        this(code, message);
        this.results = results;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public Long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Long systemTime) {
        this.systemTime = systemTime;
    }

    @Override
    public String toString() {
        return "ReturnVo{" +
                       "code=" + code +
                       ", message='" + message + '\'' +
                       ", results=" + results +
                       '}';
    }

    public enum BusinessCode {
        PARAMETER_NULL("参数不能为空", 1),
        USER_ALREADY_EXIST("用户已经存在", 100),
        USER_NOT_EXIST("用户不存在", 101),
        PASSWORD_INCORRECT("密码错误", 102);

        private final int    code;
        private final String message;

        BusinessCode(String message, int code) {
            this.message = message;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum ErrorCode {
        OK("处理完成", 0),
        TOAST_ERROR("请求出错, 请检查后重试", -1),
        SYSTEM_ERROR("系统异常，请重试", -2),
        PARAM_INVALID("参数有误, 请检查后重试", -3);

        private final int    code;
        private final String message;

        ErrorCode(String message, int code) {
            this.message = message;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }


    public static class ParamInvalidVo extends ReturnVo {

        public ParamInvalidVo() {
            super(ErrorCode.TOAST_ERROR.getCode(), "参数有误");
        }

        public ParamInvalidVo(String message) {
            super(ErrorCode.TOAST_ERROR.getCode(), message);
        }
    }


    public static class OKVo extends ReturnVo {

        public OKVo() {
            super(0, "请求成功!");
        }

        public OKVo(String message) {
            super(0, message);
        }

    }
}
