package ScheduleQueue.custom;

import ScheduleQueue.AbstractScheduleQueue;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class DemoScheduleQueue extends AbstractScheduleQueue {

    public DemoScheduleQueue(){
        this.queue = new LinkedList<>();
    }

    public DemoScheduleQueue(Queue<URL> queue){
        this.queue = queue;
    }

    /**
     * @param capacity -1 if you want to use default capacity which is 10;
     */
    public DemoScheduleQueue(Queue<URL> queue, int capacity){
        this.queue = queue;
        if(capacity > 0){
            this.maxCapacity = capacity;
        }
    }
}
