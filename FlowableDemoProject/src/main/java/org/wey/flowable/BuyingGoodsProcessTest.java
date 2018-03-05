package org.wey.flowable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BuyingGoodsProcessTest {    
    public static void main(String[] args) {
        deploy();
        //deploy2();
        //instance();  //215001 
        //findProcessInstance("65001");
        //findTask("65001");
        //managerTask();
        //jingliTask();
        //findProcessDefinition();
        //applicantTask();
        //findHistoricTask("215001");
    }
    
    /**
     * 布置流程
     */
    public static void deploy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
        
        String id = repositoryService.createDeployment().addClasspathResource("diagrams/BuyingGoodsProcess.bpmn")
                .deploy().getId();
        System.out.println(id);
    }
    
    public static void deploy2() {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
            RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
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
    
    public static void instance() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RuntimeService runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
        
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applicant", "ken");
        variables.put("manager", "andy");
        variables.put("jingli", "genie");
        variables.put("zongjiang", "michael");
        
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("BuyingGoodsProcess", variables);
        System.out.println(processInstance.getId());
    }
    
    public static void managerTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("andy").processDefinitionKey("BuyingGoodsProcess").list();
        for (Task task : tasks) {
            System.out.println("主管节点要审批的: " + task.getName());
            
            // claim it
            //taskService.claim(task.getId(), "andy");
            
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("transition", "N");
            variables.put("amount", 500);
            taskService.complete(task.getId(), variables);
        }
    }
    
    public static void jingliTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("genie").list();
        for (Task task : tasks) {
            System.out.println("总监节点要审批的: " + task.getName());
            
            // claim it
            //taskService.claim(task.getId(), "andy");
            
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("transition", "Y");
            taskService.complete(task.getId(), variables);
        }
    }
    
    public static void applicantTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("ken").processDefinitionKey("BuyingGoodsProcess").list();
        for (Task task : tasks) {
            System.out.println("申请人节点要审批的: " + task.getName());
            taskService.complete(task.getId());
        }
    }
    
    /**
     * 查找流程定义
     */
    public static void findProcessDefinition() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
        for(ProcessDefinition p:list) {
            System.out.println("id="+p.getId()+" deploymentId="+p.getDeploymentId()+" name="+p.getName());
        }
    }
    
    public static void findProcessInstance(String instanceId) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RuntimeService runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if (pi == null) {
            System.out.println("流程已经结束");
        } else {
            System.out.println("流程没有结束");
            System.out.println(pi.getProcessDefinitionId());
            System.out.println(pi.getProcessDefinitionName());
           
        }
    }
    
    public static void findHistoricTask(String instanceId) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        HistoryService historyService = (HistoryService) applicationContext.getBean("historyService");
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(instanceId)
                .orderByHistoricActivityInstanceId().asc().list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance hti : list) {
                System.out.println(hti.getId() + "    " +
                        hti.getAssignee() + "    " +
                        hti.getName() + "    " +
                        hti.getProcessInstanceId() + "   " +
                        hti.getStartTime() + "   " +
                        hti.getEndTime() + "   " +
                        hti.getDurationInMillis());
                System.out.println("################################");
            }
        }
    }

    
}
