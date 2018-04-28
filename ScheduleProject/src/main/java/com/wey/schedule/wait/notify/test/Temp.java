package com.wey.schedule.wait.notify.test;

import java.util.concurrent.TimeUnit;

public class Temp {
    
    int count = 0;
    
    public void waiter() throws InterruptedException {
        synchronized (this) {
            System.out.println("等待");
            
            for (int i = 1; i <= 30; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("等待" + i + "秒");
            }
            
            this.wait();
            
            for (int i = 1; i <= 30; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("等待后" + i + "秒");
            }
            System.out.println(this.count);
        }
    }
    
    public void notifier() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(100);
        synchronized (this) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("唤醒");
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + "notifyer:" + i);
                count += i;
            }
            notify();
        }
    }
}
