package com.israel.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.israel.coupons.entities.Purchase;


public interface IPurchaseDao extends CrudRepository<Purchase, Long>{
	
//	HQL uses class name instead of table name, and property names instead of column name

	@Query("SELECT p FROM Purchase p WHERE p.purchaseId=:purchaseIdQueryParam")
	Purchase findByPurchaseId(@Param("purchaseIdQueryParam") Long purchaseIdFormalParam);
	
	List<Purchase> findByCustomerId(long customerId);
	
	List<Purchase> findByCouponId(long couponId);
	
}

