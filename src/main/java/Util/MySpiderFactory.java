package Util;

import Boot.custom.DemoBoot;
import DataService.mybatis.YouthNewsService;
import DownLoader.custom.StreamDownloader;
import MySpiderStart.MySpider;
import Processor.custom.YouthProcessor;
import ScheduleQueue.custom.DemoScheduleQueue;

import java.net.URL;

/**
 * MySpider工厂类，可以生产固定种类的爬虫
 */
public class MySpiderFactory {



    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,单线程服务，存储数据至本地临时文件）
     * @return
     */
    public static MySpider getYouthNewsSpiderNoDataService(URL[] urls) throws Exception {
        MySpider mySpider = new MySpider(urls)
                .addBoot(new DemoBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zazalu"))
                .addScheduleQueue(new DemoScheduleQueue());
        return mySpider;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,多线程服务，存储数据至本地临时文件）
     * @return
     */
    public static MySpider getYouthNewsSpiderNoDataServiceForTheads(URL url) throws Exception {
        MySpider spider = new MySpider(new URL[]{url})
                .addBoot(new DemoBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zazalu"))
                .addScheduleQueue(new DemoScheduleQueue())
                .addDataService(new YouthNewsService());
        return spider;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,单线程服务，存储数据至本地mysql数据库）
     * @return
     */
    public static MySpider getYouthNewsSpider(URL[] urls) throws Exception {
        MySpider mySpider = new MySpider(urls)
                .addBoot(new DemoBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zazalu"))
                .addScheduleQueue(new DemoScheduleQueue())
                .addDataService(new YouthNewsService());
        return mySpider;
    }

    /**
     * 返回一个“中国青年网”的网络爬虫实例（非单例,多线程服务，存储数据至本地mysql数据库）
     * @return
     */
    public static MySpider getYouthNewsSpiderForTheads(URL url) throws Exception {
        MySpider spider = new MySpider(new URL[]{url})
                .addBoot(new DemoBoot())
                .addDownloader(new StreamDownloader())
                .addProcessor(new YouthProcessor("zazalu"))
                .addScheduleQueue(new DemoScheduleQueue())
                .addDataService(new YouthNewsService());
        return spider;
    }

}
