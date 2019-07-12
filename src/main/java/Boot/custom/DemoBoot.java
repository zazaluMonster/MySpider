package Boot.custom;

import Boot.AbstractBoot;
import Constants.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * 继承AbstractBoot来实现符合你实际场景的的Boot类
 */
public class DemoBoot extends AbstractBoot {

    private static String bootFileName = "DemoBoot.properties";

    protected Properties loadBootProperties() throws IOException, URISyntaxException {
        String url = "file://" + AbstractBoot.class.getResource(File.separator).getPath() + File.separator + "properties" + File.separator + "custom" + File.separator + bootFileName;
        bootPropertiesURL = new URL(url);

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(bootPropertiesURL.toURI())));
        return properties;
    }

    protected boolean initConstants(Properties properties) {
        //init your constans here
        Constants.theadPoolSize = Integer.parseInt(properties.getProperty("ThreadPoolSize"));
        Constants.executeTimes = Integer.parseInt(properties.getProperty("executeTimes"));
        Constants.filePath = properties.getProperty("filePath");
        Constants.spiderPrefixName = properties.getProperty("spiderPrefixName");
        Constants.dataFileName = properties.getProperty("dataFileName");
        Constants.resultFileName = properties.getProperty("resultFileName");

        return true;
    }
}
