package com.wey.schedule.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.digester3.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.wey.schedule.pojo.SchedulerObject;
import com.wey.schedule.thread.BaseSchedulerThread;

public class ScheduleServlet extends HttpServlet {
    
    private static Logger logger = LoggerFactory.getLogger(ScheduleServlet.class);
    private static List<SchedulerObject> schedulers = null;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        logger.info("ScheduleServlet初始化");
        schedulers = new ArrayList<SchedulerObject>();
        
        Digester digester = new Digester();
        digester.push(this);
        digester.setValidating(false);
        digester.addObjectCreate("kenschedule/schedulers/scheduler", "com.wey.schedule.pojo.SchedulerObject",
                                 "className");
        digester.addSetNext("kenschedule/schedulers/scheduler", "addScheduler",
                            "com.wey.schedule.pojo.SchedulerObject");
        digester.addCallMethod("kenschedule/schedulers/scheduler/name", "setName", 0);
        digester.addCallMethod("kenschedule/schedulers/scheduler/type", "setType", 0);
        digester.addCallMethod("kenschedule/schedulers/scheduler/period", "setPeriod", 0);
        digester.addCallMethod("kenschedule/schedulers/scheduler/offset", "setOffset", 0);
        digester.addCallMethod("kenschedule/schedulers/scheduler/suspend", "setSuspend", 0);
        digester.addCallMethod("kenschedule/schedulers/scheduler/contactMail", "setContactMail", 0);
        digester.addCallMethod("kenschedule/schedulers/scheduler/desc", "setDesc", 0);
        
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/schedule/schedule.xml");
        try {
            digester.parse(inputStream);
            inputStream.close();
        }
        catch (SAXException e) {
            throw new ServletException(e.getMessage());
        }
        catch (IOException e) {
            throw new ServletException(e.getMessage());
        }
        
        boolean suspend = false;
        String suspendStr = getServletContext().getInitParameter("suspend");
        if (suspendStr != null) {
            if (suspendStr.equalsIgnoreCase("true")) {
                suspend = true;
            }
        }
        
        for (int i = 0, j = schedulers.size(); i < j; i++) {
            SchedulerObject so = schedulers.get(i);
            try {
                Class threadClass = Class.forName(so.getType());
                BaseSchedulerThread bst = (BaseSchedulerThread) threadClass.newInstance();
                so.setThread(bst);
                bst.setSchedule(so.getPeriod(), so.getOffset());
                bst.setContactMail(so.getContactMail());
                bst.setDbLog(so.getDbLog());
                
                if (suspend || so.getSuspend()) {
                    bst.suspendSchedule();
                }
                
                bst.start();
            }
            catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info("线程：" + getClass().getName() + so.toString());
        }
    }
    
    public void addScheduler(SchedulerObject so) {
        schedulers.add(so);
    }
    
    public static synchronized SchedulerObject doCommand(String command) {
        if (command == null) {
            return null;
        }
        
        StringTokenizer st = new StringTokenizer(command);
        String cmd = null;
        String name = null;
        if (st.hasMoreTokens()) {
            cmd = st.nextToken();
        }
        if (cmd == null) {
            return null;
        }
        if (st.hasMoreTokens()) {
            name = st.nextToken();
        }
        
        if (name == null) {
            return null;
        }
        
        SchedulerObject scheduler = null;
        for (int i = 0, j = schedulers.size(); i < j; i++) {
            SchedulerObject so = (SchedulerObject) schedulers.get(i);
            if (!name.equalsIgnoreCase(so.getName())) {
                continue;
            }
            
            scheduler = so;
            break;
        }
        
        if (scheduler == null) {
            return null;
        }
        
        BaseSchedulerThread bst = scheduler.getThread();
        if (bst == null) {
            return null;
        }
        
        if (cmd.equalsIgnoreCase("suspend")) {
            bst.suspendSchedule();
        }
        else if (cmd.equalsIgnoreCase("resume")) {
            bst.resumeSchedule();
        }
        else if (cmd.equalsIgnoreCase("wakeup")) {
            String param = null;
            if (st.hasMoreTokens())
                param = st.nextToken();
            if (param == null)
                bst.notifyWakeup();
            else
                bst.notifyWakeup(param);
        }
        else if (cmd.equalsIgnoreCase("restart")) {
            bst.notifyStop();
            try {
                Class threadClass = Class.forName(scheduler.getType());
                bst = (BaseSchedulerThread) threadClass.newInstance();
                bst.start();
                scheduler.setThread(bst);
            }
            catch (Exception e) {
                scheduler.setThread(null);
            }
        }
        
        return scheduler;
        
    }
    
    public void destroy() {
        for (int i = 0; i < schedulers.size(); i++) {
            SchedulerObject so = (SchedulerObject) schedulers.get(i);
            BaseSchedulerThread bst = so.getThread();
            if (bst != null)
                bst.notifyStop();
            so.setThread(null);
        }
    }
    
    public static List<SchedulerObject> getSchedulers() {
        return schedulers;
    }
    
}
