package com.wey.schedule.callable.and.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureDemo {
    
    public static void main(String[] args) {
        
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            executorService.shutdown();
        }
        
    }
    
}
