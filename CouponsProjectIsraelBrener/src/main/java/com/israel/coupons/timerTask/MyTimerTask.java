//package com.israel.coupons.timerTask;
//import java.sql.Date;
//import java.util.Arrays;
//import java.util.Calendar;
////import java.util.Date;
//import java.util.TimerTask;
//import com.israel.coupons.dao.CouponDao;
//import com.israel.coupons.logic.CouponController;
//import com.israel.coupons.logic.PurchaseController;
//
//public class MyTimerTask extends TimerTask {
//
////    @Override
////    public void run() {
////    	System.out.println("The task is running");
////    }
//	
//	
//	@Override
//    public void run() {
//		long now = Calendar.getInstance().getTimeInMillis();
//		Date todayDate = new Date(now);
//		PurchaseController purchaseDao = new PurchaseController();
//		CouponController couponDao = new CouponController();
//		try {
//			//delete purchases with old coupon
//			Long [] arrPurchaseId = 	purchaseDao.checkOldCouponPurchases(todayDate);
//			System.out.println("The checked purchases Id/'s with old coupon to delete: " + Arrays.toString(arrPurchaseId));
//			purchaseDao.removeOldCouponPurchases(todayDate);
//			//delete old coupons
//			Long [] arrCouponId = 	couponDao.checkOldCoupons(todayDate);
//			System.out.println("The checked old coupon/s Id/'s to delete: " + Arrays.toString(arrCouponId));
//			couponDao.removeOldCoupons(todayDate);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    }
//    
//    
//}