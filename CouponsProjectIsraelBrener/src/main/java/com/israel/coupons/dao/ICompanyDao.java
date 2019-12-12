package com.israel.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.israel.coupons.entities.Company;


public interface ICompanyDao extends CrudRepository<Company, Long> {
	
//	HQL uses class name instead of table name, and property names instead of column name

	@Query("SELECT c FROM Company c WHERE c.companyId=:companyIdQueryParam")
	Company findByCompanyId(@Param("companyIdQueryParam") Long companyIdFormalParam);
	
	public List<Company> findByAddress(String address);

	@Query("SELECT c FROM Company c WHERE c.name=:nameQueryParam")
	Company findByName(@Param("nameQueryParam") String nameFormalParam);

	

}