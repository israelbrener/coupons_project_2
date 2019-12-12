package com.israel.coupons.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="customers")
public class Customer implements Serializable {

//	@Transient
	@OneToOne(cascade=CascadeType.REMOVE)
	private User user;

	@Id
	@Column(name="CUSTOMER_ID")
	private long customerId;

	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name="LAST_NAME", nullable = false)
	private String lastName;

	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="customerId") //, fetch=FetchType.EAGER)
	List<Purchase> purchases;

	// Default constructor (means NO parameters)
	public Customer() {
	}
	// Full constructor w/o customerId 
	public Customer(User user, String firstName, String lastName) {
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// Full constructor w/o User, with customerId
	public Customer(long  customerId, String firstName, String lastName) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//  Full constructor without the 1st id field 
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	//	getters & setters
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Customer [user=" + user + ", customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
