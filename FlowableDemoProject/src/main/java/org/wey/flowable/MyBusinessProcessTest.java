package org.wey.flowable;

import static org.junit.Assert.assertEquals;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableRule;
import org.flowable.task.api.Task;
import org.junit.Test;

public class MyBusinessProcessTest {

	public FlowableRule FlowableRule = new FlowableRule();
	
	@Test
	  @Deployment
	  public void ruleUsageExample() {
	    RuntimeService runtimeService = FlowableRule.getRuntimeService();
	    runtimeService.startProcessInstanceByKey("ruleUsage");

	    TaskService taskService = FlowableRule.getTaskService();
	    Task task = taskService.createTaskQuery().singleResult();
	    assertEquals("My Task", task.getName());

	    taskService.complete(task.getId());
	    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	  }
}
