package Util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 基于java.util.logging包装的个人Log工具
 */
public class MyLogger {

    //log4j自动检索classpath下的log4j.properties,但由于我是将log4j.properties统一放在properties文件夹下，所以需手动指定classpath路径
    static {
        PropertyConfigurator.configure("target/classes/properties/log4j.properties");
    }

    private final static Logger logger = Logger.getLogger(MyLogger.class);

    private static Logger getLogger(){
        return logger;
    }

    public static void log(String log){
        logger.info(log);
    }

    public static void warning(String log){
        logger.warn(log);
    }

    public static void error(String log){
        logger.error(log);
    }
}
