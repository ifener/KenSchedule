package com.wey.demo;

public class SingleTon {
	private static SingleTon singleTon = new SingleTon();
	
	public static SingleTon getInstance() {
			return singleTon;
	}

}
