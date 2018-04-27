package com.wey.schedule.control.thread;

import java.util.ArrayList;
import java.util.List;

public class TaskThreadControl {
    
    private final List<TaskThread> taskThreads = new ArrayList<TaskThread>();
    
    // 运行 任务线程
    public void excuteTaskThread(String taskName, String taskParameter, int priorityOfThread, String taskType) {
        // 设置线程优先级
        if (priorityOfThread > Thread.MAX_PRIORITY) {
            priorityOfThread = Thread.MAX_PRIORITY;
        }
        if (priorityOfThread < Thread.MIN_PRIORITY) {
            priorityOfThread = Thread.MIN_PRIORITY;
        }
        
        for (TaskThread thread : taskThreads) {
            System.out.println("##################线程状态 #########" + thread.getState());
            if (thread.getState() == Thread.State.TERMINATED) {
                taskThreads.remove(thread);
                continue;
            }
            
            if (thread.getState() == Thread.State.WAITING) {
                thread.setTaskName(taskName);
                thread.setTaskParameter(taskParameter);
                thread.setPriority(priorityOfThread);
                thread.setTaskType(taskType);
                try {
                    synchronized (thread) {
                        thread.notify();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                
                return;
            }
        }
        
        System.out.println("#####################开启一个新的线程###########################");
        TaskThread thread = new TaskThread(taskName, taskParameter, taskType);
        thread.setPriority(priorityOfThread);
        thread.setName(System.currentTimeMillis() + "");
        thread.start();
        taskThreads.add(thread);
        
    }
    
    public int getNumberOfThread() {
        return TaskThread.getNumberOfThread();
    }
    
    public List<TaskThread> getTaskThreads() {
        return taskThreads;
    }
    
}
