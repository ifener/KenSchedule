package com.wey.schedule.thread;

import java.util.Date;

import com.wey.schedule.util.StringUtil;

public abstract class BaseSchedulerThread extends Thread {
    
    private Object semaphore = null;
    private long timeslice = 15000L;
    private long timeoffset = 0L;
    private String contactMail = "";
    private String dbLog = "Y";
    private String param = null;
    private String lastStartTime = "";
    private String lastEndTime = "";
    private volatile boolean bSchedule = false;
    private volatile boolean needStop = false;
    private volatile boolean bWakeupOnce = false;
    
    public BaseSchedulerThread() {
        this(15, 0);
        System.out.println("current thread - " + Thread.currentThread());
    }
    
    public BaseSchedulerThread(int periodSec, int offsetSec) {
        this(periodSec, offsetSec, new StringBuffer());
    }
    
    public BaseSchedulerThread(int periodSec, int offsetSec, Object semaphore) {
        if (periodSec <= 0)
            periodSec = 15;
        if (offsetSec < 0)
            offsetSec = 0;
        offsetSec %= periodSec;
        this.timeslice = (periodSec * 1000L);
        this.timeoffset = (offsetSec * 1000L);
        this.semaphore = semaphore;
    }
    
    public void setSchedule(int periodSec, int offsetSec) {
        if (periodSec <= 0) {
            periodSec = 15;
        }
        
        if (offsetSec < 0) {
            offsetSec = 0;
        }
        
        offsetSec %= periodSec;
        this.timeslice = (periodSec * 1000L);
        this.timeoffset = (offsetSec * 1000L);
    }
    
    public void suspendSchedule() {
        System.out.println("current thread - " + Thread.currentThread());
        synchronized (this.semaphore) {
            this.bSchedule = false;
            interrupt();
        }
    }
    
    public void resumeSchedule() {
        System.out.println("current thread - " + Thread.currentThread());
        synchronized (this.semaphore) {
            this.bSchedule = true;
            interrupt();
        }
    }
    
    public void notifyWakeup() {
        System.out.println("current thread - " + Thread.currentThread());
        synchronized (this.semaphore) {
            this.bWakeupOnce = true;
            interrupt();
        }
    }
    
    public void notifyWakeup(String param) {
        System.out.println("current thread - " + Thread.currentThread());
        synchronized (this.semaphore) {
            this.bWakeupOnce = true;
            this.param = param;
            interrupt();
        }
    }
    
    public void notifyStop() {
        System.out.println("current thread - " + Thread.currentThread());
        synchronized (this.semaphore) {
            this.needStop = true;
            interrupt();
        }
    }
    
    @Override
    public void run() {
        System.out.println("当前线程：" + Thread.currentThread());
        long t0 = 0L;
        while (!this.needStop) {
            t0 = System.currentTimeMillis();
            if ((this.bSchedule) || (this.bWakeupOnce)) {
                synchronized (this.semaphore) {
                    interrupted();
                    this.lastStartTime = StringUtil.getDateWithFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    try {
                        doWakeUp();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.lastEndTime = StringUtil.getDateWithFormat("yyyy-MM-dd HH:mm:ss.SSS");
                }
                
            }
            
            this.bWakeupOnce = false;
            try {
                if (this.bSchedule) {
                    long t = (System.currentTimeMillis() - this.timeoffset) % this.timeslice;
                    long sleep = this.timeslice - t + 100L;
                    sleep(sleep);
                }
                else {
                    sleep(4294967295L);
                }
                
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(getClass().getName() + " wake up - " + new Date());
        }
        System.out.println("exit - current thread - " + Thread.currentThread());
    }
    
    protected abstract void doWakeUp() throws Exception;
    
    public long getTimeslice() {
        return timeslice;
    }
    
    public void setTimeslice(long timeslice) {
        this.timeslice = timeslice;
    }
    
    public long getTimeoffset() {
        return timeoffset;
    }
    
    public void setTimeoffset(long timeoffset) {
        this.timeoffset = timeoffset;
    }
    
    public String getContactMail() {
        return contactMail;
    }
    
    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }
    
    public String getDbLog() {
        return dbLog;
    }
    
    public void setDbLog(String dbLog) {
        this.dbLog = dbLog;
    }
    
    public Object getSemaphore() {
        return semaphore;
    }
    
    public void setSemaphore(Object semaphore) {
        this.semaphore = semaphore;
    }
    
    public boolean isbSchedule() {
        return bSchedule;
    }
    
    public void setbSchedule(boolean bSchedule) {
        this.bSchedule = bSchedule;
    }
    
    public boolean isNeedStop() {
        return needStop;
    }
    
    public void setNeedStop(boolean needStop) {
        this.needStop = needStop;
    }
    
    public String getLastStartTime() {
        return lastStartTime;
    }
    
    public void setLastStartTime(String lastStartTime) {
        this.lastStartTime = lastStartTime;
    }
    
    public String getLastEndTime() {
        return lastEndTime;
    }
    
    public void setLastEndTime(String lastEndTime) {
        this.lastEndTime = lastEndTime;
    }
    
}
