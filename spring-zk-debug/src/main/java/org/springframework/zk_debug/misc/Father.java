package org.springframework.zk_debug.misc;

public class Father {

	private static int a = 10;

	private int b = 2;

	{
		System.out.println("F instance code");
	}

	static {
		System.out.println("F static code");
	}

	Father(){
		System.out.println("F constructor");
	}
}
