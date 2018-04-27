package com.wey.schedule.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
    
    private static ApplicationContext ctx = null;
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
        
    }
    
    public static Object getBean(String beanName) {
        Object object = null;
        
        if (ctx != null) {
            try {
                object = ctx.getBean(beanName);
            }
            catch (Exception e) {
                object = null;
            }
        }
        
        return object;
    }
    
}
