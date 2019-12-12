package com.israel.coupons.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import com.israel.coupons.enums.UserType;


@Entity
@Table(name="users")
public class User implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private long userId;

	@Column(name="USER_NAME", unique=true, nullable=false)
	private String userName;

	@Column(name="EMAIL", nullable=false)
	private String email;

	@Column(name="PASSWORD", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name="TYPE", nullable = false)
	private UserType type;

	@Column(name="COMPANY_ID")
	private Long companyId; // is nullable

	@OneToOne
	private Customer customer;
	
	@ManyToOne
	private Company company;
	
	// Default constructor (means NO parameters)
	public User() {
	}

	//  constructor with only userId (for createCustomer())
	public User(long userId) {
		this.userId = userId;
	}

	// Full constructor
	public User(long userId, String userName, String email, String password, UserType type, Long companyId) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.companyId = companyId;
	}

	// Full constructor without the 1st id field
	public User(String userName, String email, String password, UserType type, Long companyId) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.companyId = companyId;
	}

	// getters & setters
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", type=" + type + ", companyId=" + companyId + "]";
	}
}
