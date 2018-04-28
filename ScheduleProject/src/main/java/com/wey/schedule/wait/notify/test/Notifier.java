package com.wey.schedule.wait.notify.test;

public class Notifier implements Runnable {
    
    private Temp temp;
    
    public Notifier(Temp t) {
        this.temp = t;
    }
    
    public void run() {
        try {
            temp.notifier();
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
