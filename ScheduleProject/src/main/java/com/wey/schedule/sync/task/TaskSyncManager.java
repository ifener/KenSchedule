package com.wey.schedule.sync.task;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wey.schedule.control.thread.Constant;
import com.wey.schedule.control.thread.TaskThread;
import com.wey.schedule.control.thread.TaskThreadControl;
import com.wey.schedule.sync.AbstractSyncManager;

@Component("taskSyncManager")
public class TaskSyncManager extends AbstractSyncManager {
    
    TaskThreadControl taskThreadControl;
    
    static List<TaskThread> threads;
    
    static {
        threads = new LinkedList<TaskThread>();
        
        for (int i = 1; i <= 50; i++) {
            TaskThread thread = new TaskThread("JAVA-TaskName-" + i, "", Constant.TASK_TYPE_CODE);
            threads.add(thread);
        }
        
        for (int i = 1; i <= 50; i++) {
            TaskThread thread = new TaskThread("PROCEDURE-TaskName-" + i, "", Constant.TASK_TYPE_PROCEDURE);
            threads.add(thread);
        }
    }
    
    @Override
    public void executeSync() throws Exception {
        if (taskThreadControl == null) {
            taskThreadControl = new TaskThreadControl();
        }
        
        int maxThreadNumber = 3;
        System.out.println("当前线程数：" + taskThreadControl.getNumberOfThread());
        
        if (taskThreadControl.getNumberOfThread() < maxThreadNumber) {
            if (threads.size() > 0) {
                TaskThread thread = threads.remove(0);
                taskThreadControl.excuteTaskThread(thread.getTaskName(), thread.getTaskParameter(), 0,
                                                   thread.getTaskType());
                
            }
            
        }
        else {
            for (TaskThread thread : taskThreadControl.getTaskThreads()) {
                System.out.println("线程状态-----" + thread.getState());
            }
        }
    }
    
}
