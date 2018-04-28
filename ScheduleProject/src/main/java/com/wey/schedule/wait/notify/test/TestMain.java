package com.wey.schedule.wait.notify.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {
    
    public static void main(String[] args) {
        Temp temp = new Temp();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Waiter(temp));
        executorService.execute(new Notifier(temp));
        executorService.shutdown();
    }
    
}
