package com.wey.schedule.concurrent.LinkedQueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并发队列链表
 * @author weisunqing
 *
 */
public class TestConcurrentLinkedQueue {
    
    public static void main(String[] args) throws InterruptedException {
        int peopleNum = 100000;
        int tableNum = 10;
        
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        // 计数器
        CountDownLatch count = new CountDownLatch(tableNum);
        for (int i = 1; i <= peopleNum; i++) {
            queue.offer("饭客" + i);
        }
        // 队列不能为空值
        // queue.offer(null);
        
        System.out.println("-----------------------------------开饭了-----------------------------------");
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(tableNum);
        for (int i = 1; i <= tableNum; i++) {
            executorService.submit(new Dinner(i + "", queue, count));
        }
        
        // 计数器等待，知道队列为空（所有人吃完）,只有值为0的时候不阻塞否则主线程被阻塞
        count.await();
        long time = System.currentTimeMillis() - start;
        System.out.println("-----------------------------------所有人已经吃完-----------------------------------");
        System.out.println("共耗时：" + time);
        // 停止线程池
        executorService.shutdown();
    }
    
}
