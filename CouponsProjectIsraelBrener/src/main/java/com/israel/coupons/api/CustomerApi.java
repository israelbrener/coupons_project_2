package com.israel.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.coupons.consts.Constants;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.entities.Customer;
import com.israel.coupons.exceptions.ApplicationException;
import com.israel.coupons.logic.CustomerController;

@RestController
@RequestMapping("/customer")
public class CustomerApi {

	@Autowired
	private CustomerController customerController;

	//method=POST   url=http://localhost:8080/customer
	@PostMapping
	public void createCustomer(@RequestBody Customer customer) throws ApplicationException {
		customerController.createCustomer(customer);
		long id  = customer.getCustomerId();
		System.out.println("The id of the created customer is: " +id);
	}

	//method=GET   url=http://localhost:8080/customer/444
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long id) throws ApplicationException {
		Customer customer = customerController.getCustomerById(id);
		System.out.println("The customer by id is: " +"\n"+ customer);
		return customer;
	}
	
	//method=GET   url=http://localhost:8080/customer
	@GetMapping
	public List<Customer> getAllCustomers() throws ApplicationException {
		System.out.println("We have to get all customers list on a webpage");
		return customerController.getAllCustomers();
	}	

	//method=PUT   url=http://localhost:8080/customer
	@PutMapping
	public void updateCustomer(@RequestBody Customer customer, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		customerController.updateCustomer(customer, userData);
		System.out.println("The userdata is: " + userData);
	}

	//method=DELETE   url=http://localhost:8080/customer/444
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") long id, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		customerController.deleteCustomer(id, userData);
		System.out.println("The userdata is: " + userData);
	}

}



