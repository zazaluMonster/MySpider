package Util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

/**
 * 基于java.util.logging包装的个人Log工具
 */
public class MyLogger {

    //加载log4j配置文件
    static {
        File file = new File(MyLogger.class.getResource(File.separator).getPath() + "log4j.properties");
        if(file.exists()){
            System.out.println("log4j: 默认位置存在配置文件,无需手动加载配置文件");
        }else{
            //配置文件不在默认位置,通过调用api告知log4j配置文件的位置
            PropertyConfigurator.configure(MyLogger.class.getResource(File.separator).getPath() + "properties/log4j.properties");
        }
    }

    private final static Logger logger = Logger.getLogger(MyLogger.class);

    private static Logger getLogger() {
        return logger;
    }

    public static void log(String log) {
        logger.info(log);
    }

    public static void warning(String log) {
        logger.warn(log);
    }

    public static void error(String log) {
        logger.error(log);
    }
}
