package org.springframework.zk_debug.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.zk_debug.ioc.beans.TestBean;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new
				ClassPathXmlApplicationContext("applicationContext.xml");
		TestBean tb = context.getBean("tb", TestBean.class);
		tb.say();
	}
}
