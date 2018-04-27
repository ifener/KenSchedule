package com.wey.schedule.control.thread;

public class TaskThread extends Thread {
    
    public TaskThread() {
    }
    
    public TaskThread(String taskName, String taskParameter, String taskType) {
        this.taskName = taskName;
        this.taskParameter = taskParameter;
        this.taskType = taskType;
    }
    
    private static volatile int numberOfThread;
    
    private String taskName;
    private String taskParameter;
    private String taskType;
    
    @Override
    public void run() {
        while (true) {
            numberOfThread++;
            if (Constant.TASK_TYPE_CODE.equals(taskType)) {
                for (int i = 0; i < 6; i++) {
                    System.out.println(Thread.currentThread().getName() + "-JAVA 代码任务：" + taskName + "--" + i);
                }
            }
            else if (Constant.TASK_TYPE_PROCEDURE.equals(taskType)) {
                for (int i = 0; i < 6; i++) {
                    System.out.println(Thread.currentThread().getName() + "-存储过程任务：" + taskName + "--" + i);
                }
            }
            
            /*  try {
                Thread.sleep(60000);
            }
            catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            */
            numberOfThread--;
            
            try {
                synchronized (this) {
                    this.wait();
                }
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public String getTaskParameter() {
        return taskParameter;
    }
    
    public void setTaskParameter(String taskParameter) {
        this.taskParameter = taskParameter;
    }
    
    public String getTaskType() {
        return taskType;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    public static int getNumberOfThread() {
        return numberOfThread;
    }
    
    public void setNumberOfThread(int numberOfThread) {
        this.numberOfThread = numberOfThread;
    }
    
}
