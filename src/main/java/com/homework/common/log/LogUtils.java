package com.homework.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：ldy on 16/02/2018 11:40
 */
public class LogUtils {
    private final static Logger logger = LoggerFactory.getLogger("root");

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void debug(String msg, Throwable e) {
        logger.debug(msg, e);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void info(String msg, Throwable e) {
        logger.info(msg, e);
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void warn(String msg, Throwable e) {
        logger.warn(msg, e);
    }

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void error(String msg, Throwable e) {
        logger.error(msg, e);
    }

}
