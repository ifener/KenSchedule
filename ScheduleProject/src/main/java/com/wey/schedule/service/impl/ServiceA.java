package com.wey.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceA {
    
    @Autowired
    private ServiceB serviceB;
    
    @Transactional
    public void callA() {
        
        try {
            serviceB.callB();
        }
        catch (Exception e) {
            // callB 报RuntimeException了，此方法不处理会报Transaction rolled back because it has been marked as rollback-only
            // 因为callB方法回滚，此方法捕获异常不处理就会commit导致冲突报UnexpectedRollbackException异常
            e.printStackTrace();
        }
    }
    
    @Transactional
    public void callD() {
        
        try {
            serviceB.callC();
        }
        catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
