package com.israel.coupons.data;

import com.israel.coupons.enums.UserType;

public class LoggedInUserData {
	private int token;
	private UserType userType;
	private Long companyId;
	private Long userId;

	public LoggedInUserData() {
	}

	public LoggedInUserData(UserType userType, Long companyId, Long userId) {
		this(userType, userId);
		this.companyId = companyId;
	}

	public LoggedInUserData(UserType userType, Long userId) {
		this.userType = userType;
		this.userId = userId;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LoggedInUserData [token=" + token + ", userType=" + userType + ", companyId=" + companyId + ", userId="
				+ userId + "]";
	}
	
	
}