package com.sun.playcat.common;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sunlin on 2017/7/6.
 */

public class Log {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void trace(String msg){
        logger.trace(msg);
    }
    public static void debug(String msg){
        logger.debug(msg);
    }
    public static void info(String msg){
        logger.info(msg);
    }
    public static void warn(String msg){
        logger.warn(msg);
    }
    public static void error(String msg){
        logger.error(msg);
    }
    public static void fatal(String msg){
        logger.fatal(msg);
    }
}