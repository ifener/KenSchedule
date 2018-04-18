package com.wey.schedule.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.wey.schedule.servlet.ScheduleServlet;

public class WebInitializer implements WebApplicationInitializer {
    
    private static Logger logger = LoggerFactory.getLogger(WebInitializer.class);
    
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("start Web");
        
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfig.class);
        ctx.register(WebConfig.class);
        ctx.setServletContext(servletContext);
        
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        // 开启异步方法支持
        servlet.setAsyncSupported(true);
        
        Dynamic scheduleServlet = servletContext.addServlet("schedule", new ScheduleServlet());
        scheduleServlet.setInitParameter("suspend", "true");
        scheduleServlet.addMapping("/schedule");
        scheduleServlet.setLoadOnStartup(5);
    }
    
}
