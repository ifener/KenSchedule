package org.wey.flowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProcess2AmountCondition3 {
    
    /**
     * 
     * SELECT * FROM ACT_RU_VARIABLE WHERE PROC_INST_ID_='122501';
SELECT * FROM ACT_RU_EXECUTION WHERE ID_='130001';
SELECT * FROM ACT_RU_TASK WHERE PROC_INST_ID_='130001';
SELECT * FROM ACT_RE_PROCDEF;

 select * from ACT_GE_PROPERTY where NAME_ = 'schema.version' ;
 SELECT * FROM ACT_RE_DEPLOYMENT;
 
 select distinct RES.* from ACT_RU_TASK RES WHERE RES.ASSIGNEE_ = 'andy' order by RES.ID_ asc;


select distinct RES.* , P.KEY_ as ProcessDefinitionKey, P.ID_ as ProcessDefinitionId, P.NAME_ as ProcessDefinitionName, P.VERSION_ as 
ProcessDefinitionVersion, P.DEPLOYMENT_ID_ as DeploymentId from ACT_RU_EXECUTION RES 
inner join ACT_RE_PROCDEF P on RES.PROC_DEF_ID_ = P.ID_ WHERE RES.PARENT_ID_ is null and RES.ID_ = '65001'
and RES.PROC_INST_ID_ = '65001' order by RES.ID_ asc 

     */
    
    public static void main(String[] args) {
        //TestProcess2AmountCondition3.deploy();
        //TestProcess2AmountCondition3.instance();  //160001 
        //TestProcess2AmountCondition3.findProcessInstance("95001");
        
        //TestProcess2AmountCondition3.managerTask();
        //TestProcess2AmountCondition3.cfoTask();
        //TestProcess2AmountCondition3.ceoTask();
        TestProcess2AmountCondition3.applicantTask();
        TestProcess2AmountCondition3.findTaskHistoric("160001");
    }
    
    /**
     * 布置流程
     */
    public static void deploy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
        
        String id = repositoryService.createDeployment().addClasspathResource("diagrams/TestProcess2AmountCondition3.bpmn")
                .deploy().getId();
        System.out.println(id);
    }
    
    public static void instance() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        RuntimeService runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
        
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applicant", "ken");
        variables.put("manager", "andy");
        variables.put("ceo", "ceo");
        variables.put("cfo", "cfo");
        
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("TestProcess2AmountCondition3", variables);
        System.out.println(processInstance.getId());
    }
    
    public static void managerTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("andy").processDefinitionKey("TestProcess2AmountCondition3").list();
        for (Task task : tasks) {
            System.out.println("主管节点要审批的: " + task.getName());
            
            // claim it
            //taskService.claim(task.getId(), "andy");
            
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("amount", 500);
            variables.put("cfo", "ming");
            variables.put("ceo", "chung");
            variables.put("approved", "Y");
            taskService.complete(task.getId(), variables);
        }
    }
    
    public static void cfoTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("ming").processDefinitionKey("TestProcess2AmountCondition3").list();
        for (Task task : tasks) {
            System.out.println("CFO节点要审批的: " + task.getName());
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("approved", "Y");
            taskService.complete(task.getId(),variables);
        }
    }
    
    public static void ceoTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("chung").processDefinitionKey("TestProcess2AmountCondition3").list();
        for (Task task : tasks) {
            System.out.println("CEO节点要审批的: " + task.getName());
            taskService.complete(task.getId());
        }
    }
    
    public static void applicantTask() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
        TaskService taskService = (TaskService) applicationContext.getBean("taskService");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("ken").processDefinitionKey("TestProcess2AmountCondition").list();
        for (Task task : tasks) {
            System.out.println("申请人: " + task.getName());
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
    
    public static void findTaskHistoric(String instanceId) {
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
