package Boot;

import Util.MyLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Boot的最小功能实现
 */
public abstract class AbstractBoot implements Boot{

    protected static URL bootPropertiesURL;
    private static String bootFileName = "boot.properties";


    @Override
    public void beforeBoot() {
        //do some work beforeBoot
    }

    @Override
    public void boot() {
        //Let's link start!
        try {
            Properties properties = loadBootProperties();

            boolean initFlag =  initConstants(properties);

            if(initFlag){
                MyLogger.log("boot success! ^_^");
            }

        } catch (Exception e) {
            MyLogger.log("boot启动出现了一些问题");
        }
    }

    @Override
    public void afterBoot() {

    }

    protected Properties loadBootProperties() throws IOException, URISyntaxException {
        String url = "file://" + AbstractBoot.class.getResource(File.separator).getPath() + File.separator + "properties" + File.separator + bootFileName;
        bootPropertiesURL = new URL(url);

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(bootPropertiesURL.toURI())));
        return properties;
    }

    protected boolean initConstants(Properties properties){
        //init your constans here
        return true;
    }
}
