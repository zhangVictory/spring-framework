package org.springframework.zk_debug.ioc.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryBeanTestBean implements FactoryBean<TestBean> {

	@Autowired
	private TestBean testBean;

	@Override
	public TestBean getObject() throws Exception {
		System.out.println("other logic code");
		return testBean;
	}

	@Override
	public Class<?> getObjectType() {
		return TestBean.class;
	}
}
