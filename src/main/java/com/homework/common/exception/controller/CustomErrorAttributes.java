package com.homework.common.exception.controller;

import com.homework.controller.vo.ReturnVo;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：ldy on 16/02/2018 09:19
 */
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final String FRONT_NOTIFIABLE_RUNTIME_EXCEPTION = "com.homework.common.exception.FrontNotifiableRuntimeException";
    private static final String EXCEPTION = "exception";
    private static final String MESSAGE = "message";

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        String exception = (String) errorAttributes.get(EXCEPTION);
        String message = (String) errorAttributes.get(MESSAGE);

        Map<String, Object> result = new HashMap<String, Object>(4);
        result.put("code", "500");
        if (FRONT_NOTIFIABLE_RUNTIME_EXCEPTION.equals(exception)) {
            result.put(MESSAGE, message);
        } else {
            result.put(MESSAGE, ReturnVo.ErrorCode.SYSTEM_ERROR.getMessage());
        }
        return result;
    }
}
