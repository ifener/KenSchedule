package com.wey.schedule.sync;

public abstract class AbstractSyncManager {
    
    public void execute() throws Exception {
        executeSync();
    }
    
    public abstract void executeSync() throws Exception;
}
