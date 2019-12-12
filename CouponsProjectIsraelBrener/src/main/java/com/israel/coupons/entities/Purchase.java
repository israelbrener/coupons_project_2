package com.israel.coupons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="purchases")
public class Purchase implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="PURCHASE_ID")
	private long purchaseId; 

	@Column(name="CUSTOMER_ID", nullable = false)
	private long customerId;

	@Column(name="COUPON_ID", nullable = false)
	private long couponId;

	@Column(name="AMOUNT_PURCHASED", nullable = false)
	private int amountPurchased;

	@ManyToOne
	private Coupon coupon;
	
	@ManyToOne
	private Customer customer;

	
	// Default constructor (means NO parameters)
	public Purchase() {
	}
	// Full constructor
	public Purchase(long purchaseId, long customerId, long couponId, int amountPurchased) {
		this.purchaseId = purchaseId;
		this.customerId = customerId;
		this.couponId = couponId;
		this.amountPurchased = amountPurchased;
	}
	//  Full constructor without the 1st id field
	public Purchase(long customerId, long couponId, int amountPurchased) {
		this.customerId = customerId;
		this.couponId = couponId;
		this.amountPurchased = amountPurchased;
	}
	//getters & setters
	public long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public int getAmountPurchased() {
		return amountPurchased;
	}
	public void setAmountPurchased(int amountPurchased) {
		this.amountPurchased = amountPurchased;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", customerId=" + customerId + ", couponId=" + couponId
				+ ", amountPurchased=" + amountPurchased + "]";
	}
}
