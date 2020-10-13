package com.sky.hiwise.design.singleton;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EagerSingleton s1 = EagerSingleton.getInstance();
		EagerSingleton s2 = EagerSingleton.getInstance();
		System.out.println(s1==s2);
		LazySingleton s3=LazySingleton.getInstance();
		LazySingleton s4=LazySingleton.getInstance();
		System.out.println(s3==s4);
		
	}

}
