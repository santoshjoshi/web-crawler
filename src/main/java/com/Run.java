package com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String... args) {
		
		new ClassPathXmlApplicationContext("application-context.xml");
	}
}
