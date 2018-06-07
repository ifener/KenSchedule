package com.wey.schedule.lock.test;

public class LockTest {
    
    public static void main(String[] args) {
        Resource resource = new Resource();
        
        Produce produce = new Produce(resource);
        Consume consume = new Consume(resource);
        
        Thread p1 = new Thread(produce, "生产者1");
        Thread p2 = new Thread(produce, "生产者2");
        
        Thread c1 = new Thread(consume, "消费者1");
        Thread c2 = new Thread(consume, "消费者2");
        
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        
    }
    
}
