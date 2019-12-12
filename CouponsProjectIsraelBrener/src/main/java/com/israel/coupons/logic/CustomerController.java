package com.israel.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.israel.coupons.dao.ICustomerDao;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.entities.Customer;
import com.israel.coupons.entities.User;
import com.israel.coupons.enums.ErrorType;
import com.israel.coupons.enums.UserType;
import com.israel.coupons.exceptions.ApplicationException;
import com.israel.coupons.logic.UserController;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private UserController userController;

	public void createCustomer(Customer customer) throws ApplicationException {
		validateFirstName(customer.getFirstName());
		validateLastName(customer.getLastName());
		userController.createUser(customer.getUser());
		Long userId = customer.getUser().getUserId();
		customer.setCustomerId(userId);		
		customerDao.save(customer);
	}

	public Customer getCustomerById(long id) throws ApplicationException {
		validateCustomerId(id);	
		Customer customer = customerDao.findByCustomerId(id);
		if(customer==null) {
			throw new ApplicationException(ErrorType.INVALID_CUSTOMER_ID, "CustomerId doesn't exist");
		}
		return customer;
	}

	public List<Customer>getAllCustomers() throws ApplicationException {
		List<Customer> customers = (List<Customer>) customerDao.findAll();
		if(customers.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no customers");
		}
		return customers;
	}

	public void updateCustomer(Customer customer, LoggedInUserData userData) throws ApplicationException {
		isCustomerExistsById(customer.getCustomerId());
		validateFirstName(customer.getFirstName());
		validateLastName(customer.getLastName());
		//Credentials: Only the ADMIN can manipulate any customer		
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= customer.getCustomerId()) {
			System.out.println("Only the ADMIN can manipulate any customer!");
			return;		
		}
		userController.updateUser(customer.getUser(), userData);
		customerDao.save(customer);
		System.out.println("The updated customer is: " +"\n"+ customer);
	}		

	public void deleteCustomer(long id, LoggedInUserData userData) throws ApplicationException {
		isCustomerExistsById(id);
		Customer customer = getCustomerById(id);
		//Credentials: Only the ADMIN can manipulate any customer		
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= customer.getCustomerId()) {
			System.out.println("Only the ADMIN can manipulate any customer!");
			return;		
		}
		customerDao.delete(customer);
		System.out.println("DeleteD customer id is: " + id);
	}

	public void validateCustomerId(long id) throws ApplicationException {
		//		//i have to add a validation for id typed as an integer number only:
		//		if (id !=(long)id) {
		//						System.out.println("CustomerId must be an integer number");
		//					}
		if  (id == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CustomerId is null");
		}	
		if  (id < 0 ) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CustomerId must be positive");
		}
	}

	public void validateFirstName(String firstName) throws ApplicationException {
		if  (firstName == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null firstName");		
		}
		if  (firstName.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty firstName");		
		}
		//		if  (firstName != (String)firstName) {
		//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "firstName must be a String");
		//		}

	}

	public void validateLastName(String lastName) throws ApplicationException {
		if  (lastName == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null lastName");		
		}
		if  (lastName.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty lastName");		
		}
	}

	public boolean isCustomerExistsById(long id) throws ApplicationException { 
		validateCustomerId(id);
		if (getCustomerById(id) != null) {
			return true;	
		}
		return false;
	}	
}
