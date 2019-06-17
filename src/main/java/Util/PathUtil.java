package Util;

public class PathUtil {
    public static String getProjectClassesLocatePath(){
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

    public static String getProjectRootPath(){
        return System.getProperty("user.dir");
    }
}
