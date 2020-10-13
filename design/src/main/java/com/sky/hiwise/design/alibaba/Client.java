package com.sky.hiwise.design.alibaba;

public class Client {

	public static void main(String[] args) throws Throwable {
		Client client=new Client();
		try {
			String retString=client.test();
			Thread.sleep(500);
			System.out.println("return String : "+retString);
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	public String test() throws Throwable{
		String retString="start";
		try {
			System.out.println("Start String : "+retString);
			retString="try inner";
			throw new Exception("ExceptionDemo inner");
		} catch (Exception e) {
			retString="catch inner";
			System.out.println(1111);
			return retString;
			// TODO: handle exception
		}finally{
			retString="finally inner";
			System.out.println("finally String :"+retString);
			return retString;
		}
	}


}
