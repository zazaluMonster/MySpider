package Constants;

import Util.PathUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class Constants {
    public static int theadPoolSize;
    public static int executeTimes;
    public static String filePath;
    public static String spiderPrefixName;
    public static String dataFileName;
    public static String resultFileName;
    public static final String DEFAULT_CONFIG_DIR = Constants.class.getResource("/").getPath() + "/";

    public static void init() throws IOException, URISyntaxException {
        URL context = new URL("file://" + PathUtil.getProjectRootPath() +  "/src/main/resources/MySpiderStart.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(context.toURI())));
        theadPoolSize = Integer.parseInt(properties.getProperty("ThreadPoolSize"));
        executeTimes = Integer.parseInt(properties.getProperty("executeTimes"));
        filePath = properties.getProperty("filePath");
        spiderPrefixName = properties.getProperty("spiderPrefixName");
        dataFileName = properties.getProperty("dataFileName");
        resultFileName = properties.getProperty("resultFileName");
    }
}
