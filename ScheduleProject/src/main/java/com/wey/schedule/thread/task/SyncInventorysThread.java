package com.wey.schedule.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wey.schedule.thread.BaseSchedulerThread;

public class SyncInventorysThread extends BaseSchedulerThread {
    
    private static Logger logger = LoggerFactory.getLogger(SyncInventorysThread.class);
    
    @Override
    protected void doWakeUp() throws Exception {
        for (int i = 0; i < 100; i++) {
            logger.info("SyncInventorysThread " + i);
        }
        
    }
    
}
