package com.wey.schedule.lock.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    
    private String product;
    private int count;
    private volatile boolean flag = false;
    
    private Lock lock = new ReentrantLock();
    Condition conditionPro = lock.newCondition();
    Condition conditionCon = lock.newCondition();
    
    public void produce(String product) {
        lock.lock();
        
        try {
            while (flag) {
                conditionPro.await();
            }
            
            this.product = product + product + "----" + count++;
            System.out.println(Thread.currentThread().getName() + "————生產了————" + this.product);
            flag = true;
            conditionCon.signal(); // 喚醒消費者對象！！
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        
    }
    
    public void consume() {
        lock.lock();
        try {
            while (!flag) {
                
                conditionCon.await();
            }
            
            System.out.println(Thread.currentThread().getName() + "————消費了————" + this.product);
            flag = false;
            conditionPro.signal(); // 喚起生產者對象！！！
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
