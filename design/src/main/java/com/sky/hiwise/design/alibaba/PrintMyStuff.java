package com.sky.hiwise.design.alibaba;

public class PrintMyStuff {
	
	public static void main(String[] args) {
		try {
			Thread t=new Counter();
			t.start();
			System.out.println(" Started ");
		}
		finally{
			System.out.println("Finally");
		}
		
	}

}

class Counter extends Thread implements Runnable{
	
	public void run(){
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Running ");
			// TODO: handle exception
		}
		
	}
	
}