package com.wey.demo;

public class TestSingleTon implements Runnable{

	public static void main(String[] args) {
		TestSingleTon t1 = new TestSingleTon();
		TestSingleTon t2 = new TestSingleTon();
		
		Thread thread1 = new Thread(t1);
		Thread thread2 = new Thread(t2);
		thread1.start();
		thread2.start();
	}

	@Override
	public void run() {
		System.out.println(SingleTon.getInstance());
		
	}

}
