package com.israel.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.israel.coupons.entities.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Long>{
	
//	HQL uses class name instead of table name, and property names instead of column name
	
	
//	List<Customer> findByCustomerId(long customerId);
	
	@Query("SELECT c FROM Customer c WHERE c.customerId=:customerIdQueryParam")
	Customer findByCustomerId(@Param("customerIdQueryParam") Long customerIdFormalParam);
	
	public List<Customer> findCustomerByFirstName(String firstName);
	

}