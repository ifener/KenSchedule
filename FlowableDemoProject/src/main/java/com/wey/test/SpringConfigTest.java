package com.wey.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-core.xml")
public class SpringConfigTest {

	@Value("${userName}")
	private String uname;

	@Value("${userPassword}")
	private String pwd;

	@Test
	public void test() {
		System.out.println("username:" + uname);
		System.out.println("password:" + pwd);
	}

}
