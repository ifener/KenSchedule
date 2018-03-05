package org.wey.flowable;

import java.util.List;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FinancialReportProcessTest {

	public static void main(String[] args) {
		//FinancialReportProcessTest.instance();
		FinancialReportProcessTest.task();
	}

	public static void deploy() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
		RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");

		String id = repositoryService.createDeployment().addClasspathResource("diagrams/FinancialReportProcess.bpmn")
				.deploy().getId();
		System.out.println(id);
	}

	public static void instance() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
		RuntimeService runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");

		System.out.println(processInstance.getId());

	}

	public static void task() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-core.xml");
		TaskService taskService = (TaskService) applicationContext.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		for (Task task : tasks) {
			System.out.println("Following task is available for accountancy group: " + task.getName());

			// claim it
			taskService.claim(task.getId(), "fozzieaaa");
		}
		
		tasks = taskService.createTaskQuery().taskAssignee("fozzieaaa").list();
	    for (Task task : tasks) {
	      System.out.println("Task for fozzie: " + task.getName());

	      // Complete the task
	      taskService.complete(task.getId());
	    }
	    
	    
	 // Retrieve and claim the second task
	    tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
	    for (Task task : tasks) {
	      System.out.println("Following task is available for management group: " + task.getName());
	      taskService.claim(task.getId(), "kermit");
	    }
	    
	    // Completing the second task ends the process
	    tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
	    for (Task task : tasks) {
	      System.out.println("Task for kermit: " + task.getName());

	      // Complete the task
	      taskService.complete(task.getId());
	    }

	}

}
