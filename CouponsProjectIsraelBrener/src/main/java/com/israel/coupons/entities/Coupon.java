package com.israel.coupons.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="coupons")
public class Coupon  implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="COUPON_ID")
	private long couponId;

	@Column(name="COMPANY_ID", nullable = false)
	private long companyId;

	@Column(name="CATEGORY_ID", nullable = false)
	private long categoryId;

	@Column(name="TITLE", nullable = false)
	private String title;

	@Column(name="DESCRIPTION", nullable = false)
	private String description;

	@Column(name="START_DATE", nullable = false)
	private Date startDate;

	@Column(name="END_DATE", nullable = false)
	private Date endDate;

	@Column(name="AMOUNT", nullable = false)
	private int amount;

	@Column(name="PRICE", nullable = false)
	private float price;

	@Column(name="IMAGE", nullable = false)
	private String image;

	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="couponId")
	List<Purchase> purchases;

	@ManyToOne
	private Company company;

	// Default constructor (means NO parameters)
	public Coupon() {
	}
	// Full constructor
	public Coupon(long couponId, long companyId, long categoryId, String title, String description, Date startDate,
			Date endDate, int amount, float price, String image) {
		this.couponId = couponId;
		this.companyId = companyId;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	// Full constructor without the couponId (AI)
	public Coupon(long companyId, long categoryId, String title, String description, Date startDate, Date endDate,
			int amount, float price, String image) {
		this.companyId = companyId;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	//getters & setters
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return  startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", companyId=" + companyId + ", categoryId=" + categoryId + ", title="
				+ title + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", price=" + price + ", image=" + image + "]";
	}
}



