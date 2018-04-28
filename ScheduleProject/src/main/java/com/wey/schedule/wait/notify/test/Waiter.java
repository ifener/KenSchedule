package com.wey.schedule.wait.notify.test;

public class Waiter implements Runnable {
    
    private Temp temp;
    
    public Waiter(Temp t) {
        this.temp = t;
    }
    
    public void run() {
        try {
            temp.waiter();
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
