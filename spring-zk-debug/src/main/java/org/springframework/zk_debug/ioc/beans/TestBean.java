package org.springframework.zk_debug.ioc.beans;

public class TestBean {
	public void say(){
		System.out.println(this.getClass().getName()+" hello");
	}
}
