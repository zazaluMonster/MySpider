package DownLoader;

import Util.FIleUtil;
import Util.MyLogger;
import Util.StringUtil;

import java.io.File;
import java.net.URL;

public abstract class AbstractDownloader implements Downloader {

    //download name will be use while define the data file name
    protected String name;
    protected URL url;


    @Override
    public void reset(URL url) {
        if(url == null && this.url == null){
            MyLogger.error("url is null. Downloader stopped.");
        }
        this.url = url;
        MyLogger.log("Downloader reset start.");
        MyLogger.log("new URL is : " + url.getPath());
    }

    @Override
    public File run() throws Exception {
        if(url == null || name == null){
            throw new Exception("Downloader can't run without url or name");
        }
        MyLogger.log("Downloader start run!");
        MyLogger.log("URL is : " + url.getPath());
        MyLogger.log("name is : " + this.name);
        return FIleUtil.createEmptyFile(new URL(getPrefixPath()).getPath() ,getName());
    }

    @Override
    public String getName() {
        if(StringUtil.isNull(name)){
            MyLogger.warning("Downloader should have its own name");
            name = StringUtil.getRandomFileName();
        }
        return name;
    }

    protected String getSavePath() throws Exception {
        return FIleUtil.getPrefix("download") + getName();
    }

    protected String getPrefixPath() throws Exception {
        return FIleUtil.getPrefix("download");
    }


}
