package com.wey.schedule.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wey.schedule.thread.BaseSchedulerThread;

public class SyncTaskControlThread extends BaseSchedulerThread {
    
    private static Logger logger = LoggerFactory.getLogger(SyncTaskControlThread.class);
    
    @Override
    protected void doWakeUp() throws Exception {
        for (int i = 0; i < 1000000000; i++) {
            logger.info("SyncTaskControlThread " + i);
        }
        
    }
    
}
