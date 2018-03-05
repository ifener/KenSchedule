package org.wey.flowable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenerateFlowableImage {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
        BpmnModel bpmnModel = repositoryService.getBpmnModel("BuyingGoodsProcess:14:242504");
        
        DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
        List<String> highLightedActivities=new ArrayList<String>();
        List<String> highLightedFlows=new ArrayList<String>();
        InputStream inputStream = generator.generateDiagram(bpmnModel, "jpg", highLightedActivities,highLightedFlows,"宋体","宋体","宋体",null,1.0);
        
        try {
            OutputStream outputStream = new FileOutputStream("BuyingGoodsProcess.jpg");
            int index = 0;
            byte[] buffer = new byte[1024];
            while((index=inputStream.read(buffer, 0, 1024))!=-1) {
                outputStream.write(buffer);
            }
            
            outputStream.close();
            inputStream.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
}
