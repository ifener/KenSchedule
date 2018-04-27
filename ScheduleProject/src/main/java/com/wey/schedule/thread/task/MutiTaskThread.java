package com.wey.schedule.thread.task;

import com.wey.schedule.sync.AbstractSyncManager;
import com.wey.schedule.thread.BaseSchedulerThread;
import com.wey.schedule.util.SpringUtil;

public class MutiTaskThread extends BaseSchedulerThread {
    
    @Override
    protected void doWakeUp() throws Exception {
        AbstractSyncManager taskSyncManager = (AbstractSyncManager) SpringUtil.getBean("taskSyncManager");
        taskSyncManager.execute();
    }
    
}
