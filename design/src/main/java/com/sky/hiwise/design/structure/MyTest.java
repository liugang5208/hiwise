package com.sky.hiwise.design.structure;

public class MyTest implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*Thread t=new Thread(new MyTest());
		t.start();
		System.out.print("start");
		t.join();
		System.out.print("end");*/
		test();

	}
	
	public void run(){
		for(int i=0;i<4;i++){
			System.out.print(i);
		}
	}
	
	public static void test(){
		boolean x=true;
		boolean y=false;
		short z=42;
		if((z++==42)&&(y=true))
			z++;
		if((x=true)||(++z==45))
			z++;
		System.out.println(z);
		
	}

}
