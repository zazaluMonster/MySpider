package Processor.custom;


import DataObject.YouthNews;
import Processor.AbstractProcessor;
import Util.DateUtil;
import Util.FIleUtil;
import Util.MyLogger;
import Util.StringUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 中国青年网解析器，没有使用其他HTML解析工具，纯粹手写的一个简单的解析器。如果你喜欢快捷的解析html，那么Jsoup是你的一个选择
 */
public class YouthProcessor extends AbstractProcessor<YouthNews> {

    public YouthProcessor(String name){
        this.name = name;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public File parseToFile(File downloadFile) throws Exception {
        if(downloadFile == null){
            throw new Exception("parse file is null");
        }

        File destinationFile = FIleUtil.createEmptyFile(new URL(FIleUtil.getPrefix("ProcessorTmp")).getPath(),StringUtil.getRandomFileName() + "_" + name);
        try(RandomAccessFile randomAccessFile_read = new RandomAccessFile(downloadFile,"rw");
            RandomAccessFile randomAccessFile_write = new RandomAccessFile(destinationFile,"rw")){
            String curLine = "";
            while ( (curLine = randomAccessFile_read.readLine()) != null){
                if (StringUtil.isNull(curLine)){
                    continue;
                }
                if(curLine.contains("<li>")){
                    String time = curLine.substring(curLine.indexOf("<font>"),curLine.indexOf("</font>")).substring(6);
                    String content = curLine.split("</a></li>")[0].substring(curLine.lastIndexOf(".htm\">")).substring(6);

                    time = new String(time.getBytes(StandardCharsets.ISO_8859_1),"GBK");
                    content = new String(content.getBytes(StandardCharsets.ISO_8859_1),"GBK");

                    MyLogger.log(time + content);

                    randomAccessFile_write.write((time + "\t" +content).getBytes("GBK"));
                    randomAccessFile_write.write("\n".getBytes("GBK"));
                }
                if(curLine.contains("</ul>")){
                    break;
                }
            }
        }
        return destinationFile;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<YouthNews> parseToList(File downloadFile) throws Exception {
        if(downloadFile == null){
            throw new Exception("parse file is null");
        }

        List<YouthNews> result = new ArrayList<>();

        try(RandomAccessFile randomAccessFile_read = new RandomAccessFile(downloadFile,"rw")){
            String curLine = "";
            while ( (curLine = randomAccessFile_read.readLine()) != null){
                if (StringUtil.isNull(curLine)){
                    continue;
                }
                if(curLine.contains("<li>")){
                    String time = curLine.substring(curLine.indexOf("<font>"),curLine.indexOf("</font>")).substring(6);
                    String content = curLine.split("</a></li>")[0].substring(curLine.lastIndexOf(".htm\">")).substring(6);

                    time = new String(time.getBytes(StandardCharsets.ISO_8859_1),"GBK");
                    content = new String(content.getBytes(StandardCharsets.ISO_8859_1),"GBK");

                    MyLogger.log(time + content);

                    YouthNews youthNews = new YouthNews();
                    youthNews.setReleaseTime(DateUtil.getSqlDateByStringAndPattern(time,"yyyy-MM-dd HH:mm:ss"));
                    youthNews.setTitle(content);
                    youthNews.setCrawlTime(new Date());

                    result.add(youthNews);
                }
                if(curLine.contains("</ul>")){
                    break;
                }
            }
        }
        return result;
    }
}
