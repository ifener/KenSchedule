package com.wey.schedule.lock.test;

public class Produce implements Runnable {
    
    Resource resource;
    
    public Produce(Resource r) {
        this.resource = r;
    }
    
    public void run() {
        while (true) {
            resource.produce("Coolpad ");
        }
        
    }
    
}
