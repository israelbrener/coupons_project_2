package com.israel.coupons.api;

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
import com.israel.coupons.entities.Purchase;
import com.israel.coupons.exceptions.ApplicationException;
import com.israel.coupons.logic.PurchaseController;

@RestController
@RequestMapping("/purchase")
public class PurchaseApi {

	@Autowired
	private PurchaseController purchaseController;

	//method=POST   url=http://localhost:8080/purchase
	@PostMapping
	public void createPurchase(@RequestBody Purchase purchase, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		purchaseController.createPurchase(purchase, userData);
		System.out.println("The userdata is: " + userData);
	}

	//method=GET   url=http://localhost:8080/purchase/444
	@GetMapping("/{purchaseId}")
	public Purchase getPurchaseById(@PathVariable("purchaseId") long id) throws ApplicationException {
		Purchase purchase = purchaseController.getPurchaseById(id);
		System.out.println("The purchase by id is: " +"\n"+ purchase);
		return purchase;
	}

	//method=GET   url=http://localhost:8080/purchase/byCustomerId?customerId=444
	@GetMapping("/byCustomerId")
	public List<Purchase> getPurchaseByCustomerId(@RequestParam("customerId") long customerId) throws ApplicationException {
		System.out.println("We have to get all purchases list by customerId " +customerId+ " on a web page");
		return purchaseController.getPurchaseByCustomerId(customerId);
	}

	//method=GET   url=http://localhost:8080/purchase/byCouponId?couponId=42
	@GetMapping("/byCouponId")
	public List<Purchase> getPurchasesByCouponId(@RequestParam("couponId") long couponId) throws ApplicationException {
		System.out.println("We have to get all purchases list by couponId " +couponId+ " on a web page");
		return purchaseController.getPurchasesByCouponId(couponId);
	}

	//method=GET   url=http://localhost:8080/purchase
	@GetMapping
	public List<Purchase> getAllPurchases() throws ApplicationException {
		System.out.println("We have to get all purchases list on a web page");
		return purchaseController.getAllPurchases();
	}

	//method=PUT   url=http://localhost:8080/purchase
	@PutMapping
	public void updatePurchase(@RequestBody Purchase purchase, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		purchaseController.updatePurchase(purchase, userData);
		System.out.println("The userdata is: " + userData);	
	}

	//Delete purchase from purchases
	//method=DELETE   url=http://localhost:8080/purchase/444
	@DeleteMapping("/{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") long id, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		purchaseController.deletePurchase(id, userData);
		System.out.println("The userdata is: " + userData);	
	}

}

