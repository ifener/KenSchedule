package org.wey.flowable;

import java.util.Collection;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.RepositoryService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReverseFlowableNodesTest {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
        BpmnModel bpmnModel = repositoryService.getBpmnModel("BuyingGoodsProcess:14:242504");
        org.flowable.bpmn.model.Process process = bpmnModel.getProcesses().get(0);
        Collection<UserTask> flowElements = process.findFlowElementsOfType(UserTask.class);
        for (UserTask userTask : flowElements) {
            System.out.println(userTask.getId());
        }
        
        System.out.println("以下是获取开始节点的");
        //以下是获取开始节点的
        Collection<StartEvent> startEvents = process.findFlowElementsOfType(StartEvent.class);
        for (StartEvent startEvent : startEvents) {
            System.out.println(startEvent.getId());
            
        }
        
    }
    
}
