package com.sky.hiwise.design.structure;

import java.util.Scanner;

public class Test<Z> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*System.out.println("输入所求数：");
		Scanner r = new Scanner(System.in);
		long n = r.nextLong();

		int flag = 0;
		for (long i = 2; i <= n; i++) {
			if (n % i == 0) {
				flag++;
				if (flag == 1) {
					System.out.print(n + "=" + i);
				} else {
					System.out.print("×" + i);
				}
				n = n / i;
				i--;
			}
		}
		if (flag == 0) {
			System.out.println(n + "为质数");
		} else {
			System.out.println("共有" + flag + "个质因数");
		}*/
		/*Test test=new Test();
		test.Z();*/
		/*int k=5,n=0;
		do {
			switch (k) {
			case 1:
			case 2:	
			case 3:n+=1;k--	;break;
			case 4:n+=2;k--;	break;

			default: n=0;k--;
			}
			System.out.println(n);
		} while (k>0&&n<5);*/
	}

	private void Z(){
		Test<Z> al1=this;
		Test<Z> al2=this;
		synchronized (al1) {
			try {
				al2.wait();
				System.out.println("1111111");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("222222");
			}
			finally{
				System.out.println("32333");
			}
		}
		System.out.println("44444");
	}
	public static int f(int n){
		int result=0;
		if(n==1||n==2){
			result=1;
		}else{
	     result=f(n-1)+f(n-2)*2;
		}
		return result;
	}
	
}
