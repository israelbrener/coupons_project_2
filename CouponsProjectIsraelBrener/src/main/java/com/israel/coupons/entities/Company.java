package com.israel.coupons.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="companies")
public class Company implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="COMPANY_ID")
	private long companyId; 
	
	@Column(name="COMPANY_NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name="ADDRESS", nullable = false)
	private String address;

	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="companyId")
	List<Coupon> coupons;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="companyId")
	List<User> users;
	
	// Default constructor (means NO parameters)
	public Company(){
	}
	// Full constructor 
	public Company(long companyId, String name, String address) {
		this.companyId = companyId;
		this.name = name;
		this.address = address;
	}
	// Full constructor without the 1st id field
	public Company(String name, String address) {
		this.name = name;
		this.address = address;
	}
	//getters & setters
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", address=" + address + "]";
	}
	
}
