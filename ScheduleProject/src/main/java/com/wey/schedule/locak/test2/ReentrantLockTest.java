package com.wey.schedule.locak.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    
    static class NumberWrapper {
        
        public int value = 1;
    }
    
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        final Condition reachThreeCondition = lock.newCondition();
        final Condition reachSixCondition = lock.newCondition();
        
        final NumberWrapper num = new NumberWrapper();
        Thread threadA = new Thread(new Runnable() {
            
            public void run() {
                lock.lock();
                try {
                    System.out.println("Thread A " + lock.isHeldByCurrentThread());
                    System.out.println("Thread A Hold Count " + lock.getHoldCount());
                    System.out.println("Thread A start wirte");
                    while (num.value <= 3) {
                        System.out.println(num.value);
                        num.value++;
                    }
                    reachThreeCondition.signal(); // 唤醒等待的线程
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                
                lock.lock();
                try {
                    reachSixCondition.await();// 线程进入等待状态
                    System.out.println("Thread A start write again");
                    System.out.println("Thread A Hold Count " + lock.getHoldCount());
                    while (num.value <= 9) {
                        System.out.println(num.value);
                        num.value++;
                    }
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                
                System.out.println("Thread A is over");
                
            }
        });
        
        Thread threadB = new Thread(new Runnable() {
            
            public void run() {
                lock.lock();
                try {
                    System.out.println("Thread B " + lock.isHeldByCurrentThread());
                    System.out.println("Thread B Hold Count " + lock.getHoldCount());
                    while (num.value <= 3) {
                        System.out.println("##############");
                        reachThreeCondition.await(); // 进行等待状态
                    }
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                
                try {
                    lock.lock();
                    System.out.println("Thread B start write");
                    while (num.value <= 6) {
                        System.out.println(num.value);
                        num.value++;
                    }
                    reachSixCondition.signal(); // 唤醒等待的线程
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
                System.out.println("Thread B is over");
            }
            
        });
        threadB.start();
        threadA.start();
        
    }
}
