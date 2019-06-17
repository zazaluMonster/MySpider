package DownLoader.custom;

import DownLoader.AbstractDownloader;
import Util.MyLogger;

import java.io.*;
import java.net.URL;

/**
 * 使用Java流进行URL资源的下载
 */
public class StreamDownloader extends AbstractDownloader {

    protected InputStream inputStream;
    protected OutputStream outputStream;

    public StreamDownloader(){
        this.name = getName();
    }

    private StreamDownloader(URL url) throws IOException {
        this.name = getName();
        this.url = url;
    }

    private StreamDownloader(String name,URL url){
        this.url = url;
        this.name = name + "_" +name.hashCode();
    }

    @Override
    public void reset(URL url) {
        super.reset(url);
    }

    @Override
    public File run() throws Exception {
        File file = super.run();
        inputStream = url.openStream();
        outputStream = new FileOutputStream(file);

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream) ){
            int c;
            while( (c=bufferedInputStream.read()) != -1 ){
                bufferedOutputStream.write(c);
                bufferedOutputStream.flush();
            }
        }

        MyLogger.log("Download complete.");
        return file;
    }



}
