package com.israel.coupons.enums;

public enum ErrorType {
	UNAUTHORIZED_ACTION(401,"Unauthorized action was done", true),
	GENERAL_ERROR(600, "General error", true),
	USER_NAME_ALREADY_EXISTS(601, "The userName you chose already exists. Please pick another userName", true),
	USER_NAME_IS_EMPTY(602, "The userName is empty. Please fill the value of the userName", true),
	INVALID_USER_NAME(603, "The userName you've entered is invalid", true),
	INVALID_ID(604, "The ID you've entered is invalid. Please pick another ID", true),
	INVALID_CUSTOMER_ID(605, "The customer ID you've entered is invalid. Please pick another ID", true),
	INVALID_PURCHSE_ID(606, "The purchase ID you've entered is invalid. Please pick another ID", true),
	INVALID_COUPON_ID(607, "The coupon ID you've entered is invalid. Please pick another ID", true),
	INVALID_COMPANY_ID(608, "The company ID you've entered is invalid. Please pick another ID", true),
	INVALID_AMOUNT(609, "The amount you've entered is invalid", true),
	INVALID_PRICE(610, "The price you've entered is invalid", true),
	INVALID_EMAIL(611, "The email you've entered is invalid. Please try again.", true),
	INVALID_PASSWORD(612, "The password you've entered is invalid. Please try again.", true),
	INVALID_DATES(613, "The dates you've entered is invalid. Please try again.", true),	
	COUPON_IS_OUT_OF_ORDER(614, "Coupon is out of order", true),
	LOGIN_FAILED(615, "Login failed.Wrong userName/password. Please try again.", true),
	THE_LIST_IS_EMPTY(616, "The list is empty", true),
	COMPANY_NAME_ALREADY_EXISTS(617, "The name you chose already exists. Please pick another name", true),
	FIELD_IS_IRREPLACEABLE(618, "You can't change this field.", false),
	NAME_IS_IRREPLACEABLE(619, "You can't change your name.", false);
	

	private int internalErrorCode;
	private String internalErrorMessage;
	private boolean isShowStackTrace;

	private ErrorType(int internalErrorCode, String internalMessage, boolean isShowStackTrace) {
		this.internalErrorCode=internalErrorCode;
		this.internalErrorMessage=internalMessage;
		this.isShowStackTrace=isShowStackTrace;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getInternalMessage() {
		return internalErrorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}


} 
