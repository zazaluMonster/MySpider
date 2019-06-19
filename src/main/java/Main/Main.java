package Main;

import Boot.custom.DemoBoot;
import DataService.mybatis.YouthNewsService;
import DownLoader.custom.StreamDownloader;
import MySpiderStart.MySpider;
import Processor.custom.YouthProcessor;
import ScheduleQueue.custom.DemoScheduleQueue;
import Util.MyLogger;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 网络爬虫入口程序
 */
public class Main {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static URL[] urls;
    public static boolean useThreads = true;


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
            MySpider mySpider = new MySpider(urls)
                    .addBoot(new DemoBoot())
                    .addDownloader(new StreamDownloader())
                    .addProcessor(new YouthProcessor("zazalu"))
                    .addScheduleQueue(new DemoScheduleQueue())
                    .addDataService(new YouthNewsService());
            mySpider.start();
        }
        //多线程启动demo
        else{
            for (URL url:
                    urls) {
                executorService.submit(() -> {
                    try {
                        MySpider spider = new MySpider(new URL[]{url})
                                .addBoot(new DemoBoot())
                                .addDownloader(new StreamDownloader())
                                .addProcessor(new YouthProcessor("zazalu"))
                                .addScheduleQueue(new DemoScheduleQueue())
                                .addDataService(new YouthNewsService());
                        spider.start();
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
