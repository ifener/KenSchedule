package com.wey.test;

import org.flowable.engine.RepositoryService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

//@ContextConfiguration("classpath:spring-core.xml")
public class FlowSpringContextTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring-core.xml");
		RepositoryService repositoryService = (RepositoryService)applicationContext.getBean("repositoryService");

		String id = repositoryService.createDeployment().addClasspathResource("holiday-request.bpmn20.xml").deploy().getId();
		System.out.println(id);
	}

}
