//package com.israel.coupons.timerTask;
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//public class Main {
//
//	public static void main(String args[]){
//
//		// Creating a task 
//		TimerTask timerTask = new MyTimerTask();
//		
//		// Creating a timer
//		Timer timer = new Timer();
//		
//		// Tell the timer to run the task every 10 seconds, starting of now
//		timer.scheduleAtFixedRate(timerTask, 0, 10000);
//		
//		System.out.println("TimerTask started");
//
//		try {
//			// 10 seconds delay before canceling the task
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// Removing the task
//		timer.cancel();
//		System.out.println("TimerTask cancelled");
//	}
//
//}
