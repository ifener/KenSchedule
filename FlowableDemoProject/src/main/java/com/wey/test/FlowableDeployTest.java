package com.wey.test;

import java.io.IOException;
import java.io.InputStream;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wey.flowable.BuyingGoodsProcessTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-core.xml")
public class FlowableDeployTest {
    
    @Autowired //自动注入  
    private RepositoryService repositoryService;

    
    @Test
    public void deploy2() {
        try {
            InputStream inputStream = BuyingGoodsProcessTest.class.getClassLoader().getResource("diagrams/BuyingGoodsProcess.bpmn").openStream();
            String category = "KenFlow";
            Deployment deploy = repositoryService.createDeployment().category(category).addInputStream("diagrams/BuyingGoodsProcess.bpmn", inputStream).deploy();
            System.out.println(deploy);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
