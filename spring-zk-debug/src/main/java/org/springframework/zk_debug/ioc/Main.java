package org.springframework.zk_debug.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.zk_debug.ioc.beans.TestBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		testApplicationContext();
	}

	public static void testApplicationContext(){
		ApplicationContext context = new
				ClassPathXmlApplicationContext("applicationContext.xml");
		TestBean testBean = context.getBean("fbtb", TestBean.class);
		testBean.say();
	}

	public static void testStreamAPI(){
		Random random = new Random();
		List<Integer> integerList = new ArrayList<>(1000);
		for(int j = 0;j<10;integerList.add(random.nextInt(1000)),++j);
		System.out.println(integerList);
		List<Integer> collect = integerList.parallelStream().sorted().map(t -> t+1).collect(Collectors.toList());
		System.out.println(collect);
	}

	public void test(){

	}
}
