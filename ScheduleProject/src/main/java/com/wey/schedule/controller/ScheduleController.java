package com.wey.schedule.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wey.schedule.pojo.SchedulerObject;
import com.wey.schedule.servlet.ScheduleServlet;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mv) {
        List<SchedulerObject> schedules = ScheduleServlet.getSchedulers();
        mv.addObject("schedules", schedules);
        mv.setViewName("schedule/list");
        return mv;
    }
    
    @RequestMapping("/doCommand")
    @ResponseBody
    public SchedulerObject doCommand(String command) {
        return ScheduleServlet.doCommand(command);
    }
}
