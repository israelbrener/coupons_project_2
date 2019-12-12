package com.israel.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.israel.coupons.entities.User;

public interface IUserDao extends CrudRepository<User, Long>{

	//	HQL uses class name instead of table name, and property names instead of column name


	//	List<User> findByUserId(long userId);

	@Query("SELECT u FROM User u WHERE u.userId=:userIdQueryParam")
	User findByUserId(@Param("userIdQueryParam") Long userIdFormalParam);

	//	List<User> findByUserName(String userName);

	@Query("SELECT u FROM User u WHERE u.userName=:userNameQueryParam")
	User findByUserName(@Param("userNameQueryParam") String userNameFormalParam);


	//	List<User> findByPassword(String password);

	@Query("SELECT u FROM User u WHERE u.password=:passwordQueryParam")
	List<User> findByPassword(@Param("passwordQueryParam") String passwordFormalParam);

	@Query("SELECT u FROM User u WHERE u.userName=:userNameQueryParam"
			+ " AND u.password=:passwordQueryParam")
	List<User> findByUserNameAndPassword(@Param("userNameQueryParam") String userNameFormalParam,
			@Param("passwordQueryParam") String passwordFormalParam);


	//	@Query("SELECT u FROM User u WHERE u.name=:name and u.password=:password")
	//	List<User> fetchUsers(@Param("name") String name, @Param("password") String password);

}