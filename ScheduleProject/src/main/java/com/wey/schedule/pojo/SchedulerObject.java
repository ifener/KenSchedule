package com.wey.schedule.pojo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wey.schedule.thread.BaseSchedulerThread;

public class SchedulerObject implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(SchedulerObject.class);
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String name = "";
    private String desc = "";
    private String type = null;
    private String lastStartTime;
    private String lastEndTime;
    private String result;
    private int period = 15;
    private int offset = 0;
    private boolean suspend = true;
    private String status = "";
    private String enableFlag = "";
    private String contactMail = "";
    private String dbLog = "";
    @JsonIgnore
    private BaseSchedulerThread thread = null;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
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
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public void setOffset(String offset) {
        this.offset = Integer.parseInt(offset);
    }
    
    public boolean getSuspend() {
        return suspend;
    }
    
    public void setSuspend(boolean suspend) {
        this.suspend = suspend;
    }
    
    public void setSuspend(String suspend) {
        this.suspend = Boolean.valueOf(suspend);
    }
    
    public String getStatus() {
        logger.debug(this.thread.getState().toString());
        if (this.thread.getState().equals(Thread.State.RUNNABLE)) {
            this.status = "运行中";
        }
        else {
            this.status = "等待";
        }
        return this.status;
    }
    
    public String getEnableFlag() {
        if (this.thread.isbSchedule()) {
            this.enableFlag = "启用";
        }
        else {
            this.enableFlag = "挂起";
        }
        
        return this.enableFlag;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
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
    
    public int getPeriod() {
        return period;
    }
    
    public void setPeriod(int period) {
        this.period = period;
    }
    
    public void setPeriod(String strPeriod) {
        this.period = Integer.parseInt(strPeriod);
    }
    
    public BaseSchedulerThread getThread() {
        return thread;
    }
    
    public void setThread(BaseSchedulerThread thread) {
        this.thread = thread;
    }
    
}
