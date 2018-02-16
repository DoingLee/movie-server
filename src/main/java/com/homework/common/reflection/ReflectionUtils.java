package com.homework.common.reflection;



import com.homework.common.exception.SystemErrorRuntimeException;
import com.homework.common.text.StringCommonUtils;

import java.lang.reflect.Method;


/*
 * @author hzfjd@corp.netease.com
 * @date 2012-5-8
 */
public class ReflectionUtils {

    public static Method getPropertyMethod(Class clz, String propertyName) {
        Method mth = null;
        propertyName = StringCommonUtils.upperFirstChar(propertyName);
        try {

            mth = clz.getMethod("get" + propertyName);
        } catch (Exception e) {
            try {
                mth = clz.getMethod("is" + propertyName);
            } catch (Exception e2) {
                throw new SystemErrorRuntimeException(e2);
            }
        }
        return mth;

    }


    public static Method getSetPropertyMethod(Class clz, String propertyName, Class propertyClz) {
        Method mth = null;
        propertyName = StringCommonUtils.upperFirstChar(propertyName);
        try {

            mth = clz.getMethod("set" + propertyName, propertyClz);
        } catch (Exception e) {
            throw new SystemErrorRuntimeException(e);
        }
        return mth;

    }

    public static Method getSetPropertyMethod(Class clz, String propertyName) {
        Method mth = null;
        propertyName = StringCommonUtils.upperFirstChar(propertyName);
        try {

            mth = clz.getMethod("set" + propertyName);
        } catch (Exception e) {
            throw new SystemErrorRuntimeException(e);
        }
        return mth;

    }


}
