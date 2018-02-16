package com.homework.common.text;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * @author：ldy on 12/02/2018 15:20
 */
public class GsonUtils {

    private static ThreadLocal<Gson> disableEscape = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new GsonBuilder().disableHtmlEscaping().setDateFormat(
                    "yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        }
    };

    public static String toJsonWithDisableEscape(Object o) {
        return disableEscape.get().toJson(o);
    }

    public static <T extends Object> T fromJson(String json, Type type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return (T) disableEscape.get().fromJson(json, type);
    }
}
