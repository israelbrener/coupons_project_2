package com.israel.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.israel.coupons.dao.IPurchaseDao;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.logic.CustomerController;
import com.israel.coupons.logic.CouponController;
import com.israel.coupons.entities.Customer;
import com.israel.coupons.entities.Purchase;
import com.israel.coupons.entities.User;
import com.israel.coupons.enums.ErrorType;
import com.israel.coupons.enums.UserType;
import com.israel.coupons.exceptions.ApplicationException;

@Controller
public class PurchaseController {

	@Autowired
	private IPurchaseDao purchaseDao;

	@Autowired
	private CustomerController customerController;

	@Autowired
	private CouponController couponController;

	public void createPurchase(Purchase purchase, LoggedInUserData userData) throws ApplicationException {
		validateAmountPurchased(purchase.getAmountPurchased());
		//Credentials: Only the ADMIN can manipulate any purchase
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= purchase.getCustomerId()) {
			System.out.println("Only the ADMIN can manipulate any purchase!");
			return;		
		}
		purchaseDao.save(purchase);
		System.out.println("The created purchase is: " + purchase);
	}

	public Purchase getPurchaseById(long id) throws ApplicationException {
		validatePurchaseId(id);	
		Purchase purchase = purchaseDao.findByPurchaseId(id);
		if(purchase==null) {
			throw new ApplicationException(ErrorType.INVALID_PURCHSE_ID, "PurchaseId doesn't exist");
		}
		return purchase;
	}

	public List<Purchase> getPurchaseByCustomerId(long customerId) throws ApplicationException {
		customerController.getCustomerById(customerId);
		List<Purchase>purchases = purchaseDao.findByCustomerId(customerId);
		if(purchases.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "No purchase for customerId " +customerId+ " do exist");
		}
		return purchases;
	}

	public List<Purchase> getPurchasesByCouponId(long couponId) throws ApplicationException {
		couponController.getCouponById(couponId);
		List<Purchase> purchases = (List<Purchase>) purchaseDao.findByCouponId(couponId);
		if(purchases.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "No purchase for couponId " +couponId+ " do exist");
		}
		return purchases;
	}

	public List<Purchase>getAllPurchases() throws ApplicationException {
		List<Purchase> purchases = (List<Purchase>) purchaseDao.findAll();
		if(purchases.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no purchases");
		}
		return purchases;
	}

	public void updatePurchase(Purchase purchase, LoggedInUserData userData) throws ApplicationException {
		isPurchadeExistsById(purchase.getPurchaseId());
		customerController.isCustomerExistsById(purchase.getCustomerId());
		couponController.isCouponExistsById(purchase.getCouponId());
		validateAmountPurchased(purchase.getAmountPurchased());	
		//Credentials: Only the ADMIN can manipulate any purchase
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= purchase.getCustomerId()) {
			System.out.println("Only the ADMIN can manipulate any purchase!");
			return;		
		}			
		purchaseDao.save(purchase);
		System.out.println("The updated purchase is: " + purchase);
	}

	public void deletePurchase(long purchaseId, LoggedInUserData userData) throws ApplicationException {
		isPurchadeExistsById(purchaseId);
		Purchase purchase = getPurchaseById(purchaseId);
		//Credentials: Only the ADMIN can manipulate any purchase
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= purchase.getCustomerId()) {
			System.out.println("Only the ADMIN can manipulate any purchase!");
			return;		
		}	
		purchaseDao.delete(purchase);
		System.out.println("Deleted purchase id is: " + purchaseId);
	}

	//	public void removeOldCouponPurchases(java.sql.Date todayDate) throws ApplicationException {
	//
	//		if (todayDate==null) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "null date");
	//		}
	//		if  (todayDate.equals(null)) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "empty date");
	//		}			
	//		this.purchaseDao.removeOldCouponPurchases(todayDate);
	//	}
	//
	//	public Long[] checkOldCouponPurchases(java.sql.Date todayDate) throws ApplicationException{
	//
	//		if (todayDate==null) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "null date");
	//		}
	//		if  (todayDate.equals(null)) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "empty date");
	//		}	
	//		return this.purchaseDao.checkOldCouponPurchases(todayDate);
	//	}	

	public void validatePurchaseId(long id) throws ApplicationException {
		//i have to add a validation for id is typed as an integer number only:
		if  (id+0 != id) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "PurchaseId must be an integer number");
		}
		if  (id == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "PurchaseId is null");
		}	
		if  (id < 0 ) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "PurchaseId must be positive");
		}	
	}

	public void validateAmountPurchased(int amountPurchased) throws ApplicationException {

		if  (amountPurchased == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null amountPurchased");		
		}
		if  (amountPurchased < 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "AmountPurchased must be positive");		
		}
	}

	public boolean isPurchadeExistsById(long id) throws ApplicationException { 
		validatePurchaseId(id);
		if (getPurchaseById(id) != null) {
			return true;	
		}
		return false;
	}	


}	