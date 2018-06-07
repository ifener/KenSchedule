package com.wey.schedule.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceB {
    
    @Transactional
    public void callB() {
        throw new RuntimeException("");
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void callC() {
        throw new RuntimeException("");
    }
}
