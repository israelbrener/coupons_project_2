package com.israel.coupons.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CacheController implements ICacheController{

	// This class serves as a basis for a clustered cache mechanism
	private Map<String, Object> cacheMap;

	public CacheController() {
		this.cacheMap = new HashMap<String, Object>();
	}

	public Object get(String key) {
		return this.cacheMap.get(key);
	}

	public void put(String key, Object value) {
		this.cacheMap.put(key, value);
	}
	

}