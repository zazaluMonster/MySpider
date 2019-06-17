package Util;

import java.io.File;
import java.io.IOException;

public class FIleUtil {
    public static File createEmptyFile(String directoryPath,String newFileName) throws IOException {
        String newfilePath = directoryPath + File.separator +newFileName;
        MyLogger.log("start create:" + newfilePath);

        File dir = new File(directoryPath);
        File newFile =  new File(newfilePath);
        if(dir.isDirectory()){
            if(newFile.exists()){
                newFile.delete();
            }
            if(newFile.createNewFile()){
                MyLogger.log("create:" + newFileName + "success");
                return newFile;
            }
        }else{
            MyLogger.warning(ErrorCode.FILE_ERROR.log(newFileName));
        }

        return null;
    }

    public static String getPrefix(String custom) throws Exception {
        if(StringUtil.isNull(custom)){
            throw new Exception("getPrefix error");
        }
        return "file://" + FIleUtil.class.getResource(File.separator).getPath() + File.separator + custom + File.separator;
    }
}
