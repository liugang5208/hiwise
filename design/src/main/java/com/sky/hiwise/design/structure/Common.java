package com.sky.hiwise.design.structure;

import java.util.Scanner;

public class Common {
//String
//StringBuffer
	//StringBuilder
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Common test = new Common();
		// System.out.println(test.rabbit(24));
		// test.sushu();
		// test.shuixianhua();
		// test.fenjie();
		// test.wanshu();
		// test.qiu();
		// test.timu11();
		// test.timu13();
		// test.timu20();
		// System.out.println(test.timu22(5));
		test.timu();
	}

	public int rabbit(int yue) {
		/*
		 * int f1=1,f2=1,f; for(int i=3;i<yue;i++){ f=f2; f2=f1+f2; f1=f;
		 * System.out.println("第"+i+"个月的兔子总数是:"+f2); }
		 */
		int result = 0;
		for (int i = 1; i < yue; i++) {
			if (i == 1 || i == 2) {
				result = 1;
			} else {
				result = rabbit(i - 1) + rabbit(i);
			}
		}
		return result;
	}

	public void sushu() {
		int count = 0;
		for (int i = 101; i < 200; i += 2) {
			boolean flag = false;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
			if (flag) {
				count++;
				System.out.println(i);
			}
		}
		System.out.println("素数个数为：" + count);
	}

	public void shuixianhua() {
		int b1, b2, b3;
		for (int i = 101; i < 1000; i++) {
			b3 = i / 100;
			b2 = i % 100 / 10;
			b1 = i % 10;
			if ((b1 * b1 * b1 + b2 * b2 * b2 + b3 * b3 * b3) == i) {
				System.out.println(i + "是水仙花数");
			}
		}
	}

	public void fenjie() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一个正整数：");
		int n = scan.nextInt();
		System.out.println();
		int k = 2;
		System.out.print(n + "=");
		while (k <= n) {
			if (k == n) {
				System.out.println(n);
				break;
			} else if (n % k == 0) {
				System.out.print(k + "*");
				n = n / k;
			} else {
				k++;
			}
		}
	}

	public void wanshu() {
		System.out.println("1000以内的完数有：");
		for (int i = 0; i < 1000; i++) {
			int t = 0;
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					t = t + j;
				}
			}
			if (t == i) {
				System.out.println(i);
			}
		}
	}

	public void qiu() {
		double h = 100, s = 100;
		for (int i = 0; i < 10; i++) {
			s = s + h;
			h = h / 2;
		}
		System.out.println("路程：" + s);
		System.out.println("高度：" + h / 2);
	}

	public void timu11() {
		int count = 0;
		for (int x = 1; x < 5; x++) {
			for (int y = 1; y < 5; y++) {
				for (int z = 1; z < 5; z++) {
					if (x != y && y != z && x != z) {
						count++;
						System.out.println(x * 100 + y * 10 + z);
					}
				}
			}
		}
		System.out.println("共有" + count + "个三位数");
	}

	public void timu13() {
		/*
		 * for(int i=1;i<100000;i++){ if(Math.sqrt(i+100)%1==0){
		 * if(Math.sqrt(i+268)%1==0){ System.out.println(i); } } }
		 */
		int x = 1;
		for (int i = 2; i < 10; i++) {
			x = (x + 1) * 2;
		}
		System.out.println(x);
	}

	public void timu20() {
		int x = 2, y = 1, t;
		double sum = 0;
		for (int i = 1; i < 20; i++) {
			sum = sum + (double) x / y;
			t = y;
			y = x;
			x = t + y;
		}
		System.out.println(sum);
	}

	public void timu21() {
		int n = 20;
		long sum = 0;
		long fac = 1;
		for (int i = 1; i <= n; i++) {
			fac = fac * i;
			sum += fac;
		}
		System.out.println(sum);
	}

	public long timu22(int n) {
		long result = 0;
		if (n == 1) {
			result = 1;
		} else {
			result = n * timu22(n - 1);
		}
		return result;
	}

	public void timu30() {
		int[] a = { 1, 3, 6, 9, 15, 16, 19, 28, 29, 35, 39, 59, 68, 78, 95, 98 };
		int[] b = new int[a.length + 1];
		 int i =0;
		Scanner s = new Scanner(System.in);
		System.out.print("请输入一个整数：");
		int num = s.nextInt();
		if(num >= a[a.length-1]){
			b[b.length-1] = num;
			for( i=0;i<a.length;i++){
				b[i]=a[i];
			}
		}else{
			for(i=0;i<a.length;i++){
				if(num>a[i]){
					b[i]=a[i];
				}else{
					b[i]=num;
					break;
				}
			}
			for(int j=i+1;j<b.length;j++){
				b[j]=a[j-1];
			}
		}
		for(i=0;i<b.length;i++){
			System.out.println(b[i]);
		}
	}

	public void timutenxun(){
		
		for(int i=0;i<1000;i++){
			int count=0;
			int temp=0;
			temp=i;
			while(count<9){
				if(temp%2==0){
					count++;
					temp=temp/2;
				}else{
					count++;
					temp=temp+1;
				}
				if(temp==1)
					break;
			}
			if(temp==1&&count==9){
				System.out.println(i);
			}
		}
	}
	
	public void timu(){
		System.out.println("输入所求数：");
		Scanner r = new Scanner(System.in);
		long n = r.nextLong();
		int count=0;
		for(int i=2;i<=n;i++){
			if(n%i==0){
				count++;
				if(count==1){
					System.out.print(n+"="+i);
				}else{
					System.out.print("*"+i);
				}
				n=n/i;	
				i--;
			}
		}
		if(count==0){
			System.out.println(n+"为质数");
		}else{
			System.out.println("有"+count+"个质因数");
		}
	}
}
