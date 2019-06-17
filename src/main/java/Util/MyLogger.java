package Util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 基于java.util.logging包装的个人Log工具
 */
public class MyLogger {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static Logger getLogger(){
        return logger;
    }

    public static void log(String log){
        logger.log(Level.INFO,log);
    }

    public static void warning(String log){
        logger.log(Level.WARNING,log);
    }

    public static void error(String log){
        logger.log(Level.SEVERE ,log);
    }
}
