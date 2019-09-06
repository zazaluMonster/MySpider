package Main;

import MySpider.MySpider;
import Util.MyLogger;
import Util.MySpiderFactory;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MySpider运行测试demo, 对中国青年网的部分数据进行爬取, 可以单线程, 也可以多线程启动同时爬取
 */
public class Main {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static URL[] urls;
    public static boolean useThreads = false;


    public static void main(String[] args) throws Exception {

        //URL数据demo
        urls = new URL[]{
                new URL("http://news.youth.cn/gn/index.htm"),
                new URL("http://news.youth.cn/gn/index_1.htm"),
                new URL("http://news.youth.cn/gn/index_2.htm"),
                new URL("http://news.youth.cn/gn/index_3.htm"),
                new URL("http://news.youth.cn/gn/index_4.htm")
        };

        //单线程启动demo
        if(!useThreads){
            //从工厂类中获得一个爬虫实例
            MySpider mySpider =  MySpiderFactory.getYouthNewsSpiderNoDataService(urls);
            mySpider.start();
        }
        //多线程启动demo
        else{
            for (URL url:
                    urls) {
                executorService.submit(() -> {
                    try {
                        MySpider mySpider =  MySpiderFactory.getYouthNewsSpiderNoDataServiceForTheads(url);
                        mySpider.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            executorService.shutdown();
        }

        MyLogger.log("Main ended");
    }

}
