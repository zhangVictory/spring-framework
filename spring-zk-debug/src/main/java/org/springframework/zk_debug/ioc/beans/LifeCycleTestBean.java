package org.springframework.zk_debug.ioc.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class LifeCycleTestBean implements InitializingBean, DisposableBean {

	private final String name;

	private final int age;

	private TestBean testBean;

	public void setTestBean(TestBean testBean) {
		this.testBean = testBean;
	}

	public LifeCycleTestBean(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("all necessary properties are all set---->name:"+name+"--->age:"+age+"--->testBean:"+testBean);
	}

	public void initialMethod(){
		System.out.println("init-method invoked");
	}

	public void destroyMethod(){
		System.out.println("destroy-method invoked");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy invoked");
	}
}
