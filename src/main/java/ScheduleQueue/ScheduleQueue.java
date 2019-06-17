package ScheduleQueue;


import java.net.URL;

/**
 * 任务队列，在爬虫应用中，通常被调度的就是下一个需爬取的是哪个URL地址
 *
 * 由于接口无法控制构造器，故在此说明ScheduledQueue实现类必须拥有的构造器：
 * 1. 无参构造器,使用默认的Queue实现类LinkedList
 * 2. 传入一个Queue实现类参数作为调度器使用的队列数据结构
 */
public interface ScheduleQueue {

    int size();

    boolean addNewURL(URL url);

    //just look don't remove
    URL lookNextURL();

    //return URL and this URL will be remove in Queue
    URL nextURL();

    boolean removeHead();
}
