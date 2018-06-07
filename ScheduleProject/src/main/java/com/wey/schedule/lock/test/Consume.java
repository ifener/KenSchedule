package com.wey.schedule.lock.test;

public class Consume implements Runnable {
    
    Resource resource;
    
    public Consume(Resource r) {
        this.resource = r;
    }
    
    public void run() {
        while (true) {
            resource.consume();
        }
        
    }
    
}
