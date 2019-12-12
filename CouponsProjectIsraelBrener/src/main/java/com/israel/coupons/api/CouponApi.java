package com.israel.coupons.api;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.israel.coupons.consts.Constants;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.entities.Coupon;
import com.israel.coupons.exceptions.ApplicationException;
import com.israel.coupons.logic.CouponController;

@RestController
@RequestMapping("/coupon")
public class CouponApi {

	//@RequestMapping(method="get", url="/login")

	@Autowired
	private CouponController couponController;

	//method=POST   url=http://localhost:8080/coupon
	@PostMapping
	public void createCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		couponController.createCoupon(coupon, userData);
		System.out.println("The userdata is: " + userData);
	}

	//method=GET   url=http://localhost:8080/coupon/444
	@GetMapping("/{couponId}")
	public Coupon getCouponById(@PathVariable("couponId") long id) throws ApplicationException {
		Coupon coupon =couponController.getCouponById(id);
		System.out.println("The coupon by id is: " +"\n"+ coupon);
		return coupon;

	}

	//method=GET   url=http://localhost:8080/coupon/byStartDateOnward?startDate=2019-10-01
	@GetMapping("/byStartDateOnward")
	public List<Coupon> getCouponByStartDateOnward(@RequestParam("startDate") Date startDate) throws ApplicationException {
		List<Coupon> coupon =couponController.getCouponByStartDateOnward(startDate);
		System.out.println("The coupon by startDate Onward is: " +"\n"+ coupon);
		return coupon;

	}

	//method=GET   url=http://localhost:8080/coupon
	@GetMapping
	public List<Coupon> getAllCoupons() throws ApplicationException {
		System.out.println("we have to get all coupons list on a web page");
		return couponController.getAllCoupons();
	}

	//method=PUT   url=http://localhost:8080/coupon
	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		couponController.updateCoupon(coupon, userData);
		System.out.println("The userdata is: " + userData);
	}

	//Delete coupon from coupons (COUPON_ID is a FK in purchases)
	//method=DELETE   url=http://localhost:8080/coupon/444
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long id, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		couponController.deleteCoupon(id, userData);
		System.out.println("The userdata is: " + userData);
	}

}