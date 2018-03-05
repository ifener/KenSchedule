package com.wey.demo;

public class SyncDemo implements Runnable {
	
	
	private static Integer num = 0;

	public static void main(String[] args) {
		SyncDemo s1 = new SyncDemo();
		SyncDemo s2 = new SyncDemo();
		
		Thread t1 = new Thread(s1);
		Thread t2 = new Thread(s2);
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		synchronized (num) {
			 num++;
			   System.out.println(num);
		}
				
	  
	}

}
