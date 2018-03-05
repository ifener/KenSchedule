package com.wey.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.RepositoryService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerTest {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        //applicationContext.getBean("freemarkerEngine");
        Configuration freemarkerEngine = (Configuration)applicationContext.getBean("freemarkerEngine");
        Map<String, Object> model = new HashMap<String,Object>();
        User user = new User();
        user.setUsername("ken");
        user.setCity("东莞");
        user.setProvince("广东");
        model.put("user",user);
       
        try {
            String result = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerEngine.getTemplate("freemarkerTest.ftl"), model);
            System.out.println(result);
        }
        catch (TemplateNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedTemplateNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
}
