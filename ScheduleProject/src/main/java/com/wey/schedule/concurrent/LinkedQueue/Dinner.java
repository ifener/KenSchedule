package com.wey.schedule.concurrent.LinkedQueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class Dinner implements Runnable {
    
    private String name;
    private ConcurrentLinkedQueue<String> queue;
    private CountDownLatch count;
    
    public Dinner(String name, ConcurrentLinkedQueue<String> queue, CountDownLatch count) {
        this.name = name;
        this.queue = queue;
        this.count = count;
    }
    
    // https://www.cnblogs.com/yangzhenlong/p/8359875.html
    public void run() {
        // while (queue.size() > 0) { //判断队列是否为空时用isEmpty()方法，size()方法效率不高其需要遍历链表
        while (!queue.isEmpty()) {
            System.out.println("[" + queue.poll() + "]已经吃完饭了，饭桌编号：" + name);
        }
        // 计数器-1
        count.countDown();
    }
    
}
