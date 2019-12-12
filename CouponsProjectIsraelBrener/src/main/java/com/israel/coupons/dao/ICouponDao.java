package com.israel.coupons.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.israel.coupons.entities.Coupon;


public interface ICouponDao extends CrudRepository<Coupon, Long> {

	//	HQL uses class name instead of table name, and property names instead of column name

	@Query("SELECT c FROM Coupon c WHERE c.couponId=:couponIdQueryParam")
	Coupon findByCouponId(@Param("couponIdQueryParam") Long couponIdFormalParam);

	@Query("SELECT c FROM Coupon c WHERE c.startDate=:startDateQueryParam")
	List<Coupon> findByStartDateOnward(@Param("startDateQueryParam") Date startDateFormalParam);

	public List<Coupon> findByTitle(String title);


}


