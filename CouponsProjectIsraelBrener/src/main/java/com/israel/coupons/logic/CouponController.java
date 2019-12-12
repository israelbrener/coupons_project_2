package com.israel.coupons.logic;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.israel.coupons.dao.ICouponDao;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.logic.CompanyController;
import com.israel.coupons.entities.Coupon;
import com.israel.coupons.enums.ErrorType;
import com.israel.coupons.enums.UserType;
import com.israel.coupons.exceptions.ApplicationException;

@Controller
public class CouponController {

	@Autowired
	private ICouponDao couponDao;

	@Autowired
	private CompanyController companyController;


	public void createCoupon(Coupon coupon, LoggedInUserData userData) throws ApplicationException {
		validateTitle(coupon.getTitle());
		validateDescription(coupon.getDescription());
		companyController.isCompanyExistsById(coupon.getCompanyId());
		validateAmount(coupon.getAmount());
		validatePrice(coupon.getPrice());
		//Credentials: Only the ADMIN can manipulate any coupon
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getCompanyId()!= coupon.getCompanyId()) {
			System.out.println("Only the ADMIN can manipulate any coupon!");
			return;		
		}
		couponDao.save(coupon);
		System.out.println("The created coupon is: " + coupon);
	}

	public Coupon getCouponById(Long id) throws ApplicationException {
		validateCouponId(id);
		Coupon coupon = couponDao.findByCouponId(id);
		if(coupon==null) {
			throw new ApplicationException(ErrorType.INVALID_COUPON_ID, "CouponId doesn't exist");
		}
		return coupon;
	}

	public List<Coupon> getCouponByStartDateOnward(Date startDate) throws ApplicationException  {
		List<Coupon> coupons = (List<Coupon>) couponDao.findByStartDateOnward(startDate);
		if(coupons.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no coupons");
		}
		return coupons;
	}

	public List<Coupon>getAllCoupons() throws ApplicationException {
		List<Coupon> coupons = (List<Coupon>) couponDao.findAll();
		if(coupons.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no coupons");
		}
		return coupons;

	}

	public void updateCoupon(Coupon coupon, LoggedInUserData userData) throws ApplicationException {
		isCouponExistsById(coupon.getCouponId());
		validateTitle(coupon.getTitle());
		validateDescription(coupon.getDescription());
		validateAmount(coupon.getAmount());
		validatePrice(coupon.getPrice());
		//Credentials: Only the ADMIN can manipulate any coupon
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getCompanyId()!= coupon.getCompanyId()) {
			System.out.println("Only the ADMIN can manipulate any coupon!");
			return;		
		}
		couponDao.save(coupon);
		System.out.println("The updated coupon is: " + coupon);
	}

	public void deleteCoupon(long couponId, LoggedInUserData userData) throws ApplicationException {
		isCouponExistsById(couponId);
		Coupon coupon = getCouponById(couponId);
		//Credentials: Only the ADMIN can manipulate any coupon
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getCompanyId()!= coupon.getCompanyId()) {
			System.out.println("Only the ADMIN can manipulate any coupon!");
			return;		
		}
		couponDao.delete(coupon);
		System.out.println("Deleted coupon id is: " + couponId);

	}

	//	public void removeOldCoupons(java.sql.Date todayDate) throws ApplicationException {
	//
	//		if (todayDate==null) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "null date");
	//		}
	//		if  (todayDate.equals(null)) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "empty date");
	//		}		
	//		 this.couponDao.removeOldCoupons(todayDate);
	//	}

	//	public Long[] checkOldCoupons(java.sql.Date todayDate) throws ApplicationException{
	//		
	//		if (todayDate==null) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "null date");
	//		}
	//		if  (todayDate.equals(null)) {
	//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "empty date");
	//		}	
	//		 return this.couponDao.checkOldCoupons(todayDate);
	//	}

	public void validateCouponId(long id) throws ApplicationException {
		//i have to add a validation for id is typed as an integer number only:
		//		if  (id+0 != id) {
		//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CouponId must be an integer number");
		//		}
		if  (id == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CouponId is null");
		}	
		if  (id < 0 ) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CouponId must be positive");
		}	
	}

	public void validateTitle(String title) throws ApplicationException {
		if  (title == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null title");		
		}
		if  (title.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty title");		
		}
	}

	public void validateDescription(String description) throws ApplicationException {
		if  (description == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null description");		
		}
		if  (description.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty description");		
		}
	}

	public void validateAmount(int amount) throws ApplicationException {

		if  (amount == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null amount");		
		}
		if  (amount < 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Amount must be positive");		
		}
	}

	public void validatePrice(float price) throws ApplicationException {

		if  (price == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null price");		
		}
		if  (price < 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Price must be positive");		
		}
	}

	public boolean isCouponExistsById(long id) throws ApplicationException { 
		validateCouponId(id);
		if (getCouponById(id) != null) {
			return true;	
		}
		return false;
	}


}
