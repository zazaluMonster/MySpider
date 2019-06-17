package Test;

import Util.FIleUtil;
import Util.MyLogger;
import Util.PathUtil;
import Util.StringUtil;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TestGetHtml {

    @Test
    public void testUseJavaToGetHtmlAndCrawl() throws IOException {
        URL target = new URL("http://news.youth.cn/gn/");
        URL context = new URL("file://" + PathUtil.getProjectRootPath() +  "/src/main/resources");
        File dataFile =  FIleUtil.createEmptyFile(context.getPath(),"htmlData");
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(target.openStream());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dataFile)) ){
            int c;
            while( (c=bufferedInputStream.read()) != -1 ){
                bufferedOutputStream.write(c);
                bufferedOutputStream.flush();
            }
        }

        //parse
        File destinationFile = FIleUtil.createEmptyFile(context.getPath(),"result.txt");
        try(RandomAccessFile randomAccessFile_read = new RandomAccessFile(dataFile,"rw");
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

    }

    @Test
    public void testFileParse() {
        String name = "Iam君山";
        String name2 = "Iam¾ýÉ½";
        try {
            byte[] iso8859 = name.getBytes("ISO-8859-1");
            System.out.println("iso8859");
            look(iso8859);
            System.out.println(new String(iso8859,"GBK"));
            byte[] gb2312 = name.getBytes("GB2312");
            System.out.println("gb2312");
            look(gb2312);
            byte[] gbk = name.getBytes("GBK");
            System.out.println("gbk");
            look(gbk);
            byte[] utf16 = name.getBytes("UTF-16");
            System.out.println("utf16");
            look(utf16);
            byte[] utf8 = name.getBytes("UTF-8");
            System.out.println("utf8");
            look(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void look(byte[] arr){
        for (byte b:
                arr) {
            System.out.print(getBit(b));
            System.out.print(" ");
        }
        System.out.println("");
    }

    private static String getBit(byte by){
        StringBuffer sb = new StringBuffer();
        sb.append((by>>7)&0x1)
                .append((by>>6)&0x1)
                .append((by>>5)&0x1)
                .append((by>>4)&0x1)
                .append((by>>3)&0x1)
                .append((by>>2)&0x1)
                .append((by>>1)&0x1)
                .append((by>>0)&0x1);
        return sb.toString();
    }

    @Test
    public void testReadGBKByRandomAccessFile() throws IOException {
        String name = "Iam君山";
        byte[] gbk = name.getBytes("GBK");
        System.out.println("gbk");
        look(gbk);
        byte[] iso8859 = name.getBytes("ISO-8859-1");
        System.out.println("iso8859");
        look(iso8859);

        URL context = new URL("file://" + PathUtil.getProjectRootPath() +  "/src/main/resources");
        File file = new File(context.getPath() + "/gbktest.txt");
        try(RandomAccessFile randomAccessFile_read = new RandomAccessFile(file,"rw")){
            int c;
            String s = "";
            while ( (c = randomAccessFile_read.read()) != -1 ){
                System.out.print(Integer.toBinaryString(c) + " ");
                System.out.print((char)c + " ");
                s+=(char)c;
            }
            System.out.println();
            System.out.println(s);
            System.out.println("s");
            System.out.println(new String(s.getBytes(StandardCharsets.ISO_8859_1),"GBK"));
            look(s.getBytes(StandardCharsets.ISO_8859_1));
            look(s.getBytes("GBK"));
        }
    }

    @Test
    public void testReadGBKByBufferedReader() throws IOException {
        String name = "Iam君山";
        byte[] gbk = name.getBytes("GBK");
        System.out.println("gbk");
        look(gbk);

        URL context = new URL("file://" + PathUtil.getProjectRootPath() +  "/src/main/resources");
        File file = new File(context.getPath() + "/gbktest.txt");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String s = "";
            while ( (s = bufferedReader.readLine()) != null ){
                System.out.println(s);
                System.out.println(new String(s.getBytes("UTF-8"),"GBK"));
                look(s.getBytes("UTF-8"));
            }
        }
    }

    @Test
    public void testContextPath(){
        System.out.println(System.getProperty("user.dir"));
        System.out.println(StringUtil.getRandomFileName());
        System.out.println("wdadwad".hashCode());
    }

    @Test
    public void testHTTP() throws IOException {
        URL target = new URL("https://www.baidu.com/");

        HttpURLConnection urlConnection = (HttpURLConnection) target.openConnection();

        Map<String, List<String>> map = urlConnection.getHeaderFields();

        System.out.println(map);
    }
}
