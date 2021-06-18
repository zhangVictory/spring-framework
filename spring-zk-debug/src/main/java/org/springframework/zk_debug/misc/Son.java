package org.springframework.zk_debug.misc;

public class Son extends Father{

	private static int c = 20;

	private int d = 5;

	{
		System.out.println("S instance code");
	}

	static {
		System.out.println("S static code");
	}

	Son(){
		System.out.println("S constructor");
	}
}
