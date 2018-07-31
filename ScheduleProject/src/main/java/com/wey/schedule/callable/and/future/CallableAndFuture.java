package com.wey.schedule.callable.and.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableAndFuture {
    
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return new Random().nextInt();
            }
        };
        
        /*FutureTask实现了两个接口，Runnable和Future，所以它既可以作为Runnable被线程执行，
         * 又可以作为Future得到Callable的返回值，那么这个组合的使用有什么好处呢？
         * 假设有一个很耗时的返回值需要计算，并且这个返回值不是立刻需要的话，那么就可以使用这个组合，
         * 用另一个线程去计算返回值，而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，
         * 再通过Future得到，岂不美哉！
         * */
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
            System.out.println("DONE");
            
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
