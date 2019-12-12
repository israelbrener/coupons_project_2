package com.israel.coupons.cache;


public interface ICacheController {

	public Object get(String key);

	public void put(String key, Object value);

}
