package com.sky.hiwise.design.singleton;

public class LazySingleton {
	private static LazySingleton m_instance=null;
	
	private LazySingleton(){}
	
	 public static synchronized LazySingleton getInstance(){
		if(m_instance==null){
			m_instance=new LazySingleton();
		}
		
		return m_instance;
	}

}
