package com.israel.coupons.data;

public class UserLoginDetailsDataObject {

	private String userName;
	private String password;

	public UserLoginDetailsDataObject(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public UserLoginDetailsDataObject() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLoginDetailsDataObject [userName=" + userName + ", password=" + password + "]";
	}	
}
