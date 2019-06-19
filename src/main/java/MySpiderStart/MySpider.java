package MySpiderStart;

import Boot.Boot;
import Boot.custom.DemoBoot;
import DataService.DataService;
import DownLoader.Downloader;
import DownLoader.custom.StreamDownloader;
import Processor.Processor;
import ScheduleQueue.ScheduleQueue;
import ScheduleQueue.custom.DemoScheduleQueue;
import Util.MyLogger;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * 网络爬虫入口程序
 */
public class MySpider {

    private Boot boot;
    private ScheduleQueue scheduleQueue;
    private Downloader downloader;
    private Processor processor;
    private DataService dataService;
    private static URL[] urls;

    public MySpider(URL[] urls){
        this.urls = urls;
    }

    private void init() throws Exception {
        //boot
        if(boot == null){
            boot = new DemoBoot();
        }
        boot.beforeBoot();
        boot.boot();
        boot.afterBoot();

        //scheduleQueue
        if(scheduleQueue == null){
            scheduleQueue = new DemoScheduleQueue();
        }
        for (URL url:
             urls) {
            scheduleQueue.addNewURL(url);
        }

        //downloader
        if(downloader == null){
            downloader = new StreamDownloader();
        }

        //processor
        if(processor == null){
            throw new Exception("please add your own processor");
        }

        //dataService
        if(dataService == null){
            MyLogger.log("there's no dataService,so data will don't save into your database.");
        }else{
            dataService.init();
        }

    }

    public void start() throws Exception {
        MyLogger.log("Main start work!");

        init();

        while(scheduleQueue.size() > 0){
            downloader.reset(scheduleQueue.nextURL());
            File downloadFile = downloader.run();
            if(dataService == null){
                File tempFile = processor.parseToFile(downloadFile);
            }else{
                List list = processor.parseToList(downloadFile);
                dataService.adds(list);
            }

            if(scheduleQueue.size() >= 1){
                Thread.sleep(1000*60);//一分钟爬一次
            }
        }

        MyLogger.log("just do it! boy");
    }

    public MySpider addBoot(Boot boot){
        this.boot = boot;
        return this;
    }

    public MySpider addScheduleQueue(ScheduleQueue scheduleQueue){
        this.scheduleQueue = scheduleQueue;
        return this;
    }

    public MySpider addDownloader(Downloader downloader){
        this.downloader = downloader;
        return this;
    }

    public MySpider addProcessor(Processor processor) throws Exception {
        this.processor = processor;
        return this;
    }

    public MySpider addDataService(DataService dataService){
        this.dataService = dataService;
        return this;
    }


}
