package DownLoader;

import java.io.File;
import java.net.URL;

/**
 * 使用Java网络编程接口下载网络资源,存储至本地（这块）
 */
public interface Downloader {

    //everything will be reset!
    void reset(URL url);

    //start download!
    File run() throws Exception;

    String getName();

    //根据你的喜欢对网络连接进行一些系统参数调整
    default void webConfig(){

    };

    //根据你的喜欢来选择是否使用网络代理
    default void proxyConfig(){
        //http
        System.setProperty("http.proxyHost", "www.proxy.com");
        System.setProperty("http.proxyPort", "80");
        //https
        System.setProperty("https.proxyHost", "www.proxy.com");
        System.setProperty("https.proxyPort", "443");
    };

}
