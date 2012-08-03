package com.javataskforce;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Santosh Joshi
 *
 */
public class Run {

	public static void main(String... args) {
		
		new ClassPathXmlApplicationContext("application-context.xml");
	}
}
