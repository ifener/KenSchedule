package com.wey.schedule.locak.test2;

public class DinnerThread {
	
	public static void main(String[] args){
		DinnerThread thread = new DinnerThread();
		Dinner fatherThread = thread.new Dinner();
		Thread myThread = new Thread(fatherThread);
		myThread.start();
		
	}

	public class Dinner implements Runnable
	{

		public void run() {
			System.out.println("去饭店吃饭");
			System.out.println("点完菜让饭店做菜：");
			Thread restaurantThread = new Thread(new RestaurantThread());
			restaurantThread.start();
			try {
				restaurantThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("开始吃饭");	
		}
		
	}
	
	public class RestaurantThread implements Runnable{

		public void run() {
			System.out.println("饭店开始做菜");
			for(int i=0;i<10;i++){
				System.out.println("饭店做菜("+(i+1)+")...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("饭店上菜");
			
		}
		
	}
}
