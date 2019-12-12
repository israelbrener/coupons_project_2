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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.israel.coupons.consts.Constants;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.entities.Company;
import com.israel.coupons.entities.User;
import com.israel.coupons.enums.ErrorType;
import com.israel.coupons.exceptions.ApplicationException;
import com.israel.coupons.logic.CompanyController;

@RestController
@RequestMapping("/company")
public class CompanyApi {

	//@RequestMapping(method="get", url="/login")

	@Autowired
	private CompanyController companyController;

	//method=POST   url=http://localhost:8080/company
	@PostMapping
	public void createCompany(@RequestBody Company company, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		companyController.createCompany(company, userData);
		System.out.println("The userdata is: " + userData);
	}

	//method=GET   url=http://localhost:8080/company/444
	@GetMapping("/{companyId}")
	public Company getCompanyById(@PathVariable("companyId") long id) throws ApplicationException {
		Company company = companyController.getCompanyById(id);
		System.out.println("The company by id is: " +"\n"+ company);
		return company;
	}

	//method=GET   url=http://localhost:8080/company/byName?name=nm
	@GetMapping("/byName")
	public Company getCompanyByName(@RequestParam("name") String name) throws ApplicationException {
		Company company = companyController.getCompanyByName(name);
		System.out.println("The company by name is: " +"\n"+ company);
		return company;
	}

	//method=GET   url=http://localhost:8080/company
	@GetMapping
	public List<Company> getAllCompanies() throws ApplicationException {
		System.out.println("we have to get all conpanies list on a web page");
		return companyController.getAllCompanies();
	}

	//method=PUT   url=http://localhost:8080/company
	@PutMapping
	public void updateCompany(@RequestBody Company company, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		companyController.updateCompany(company, userData);
		System.out.println("The userdata is: " + userData);
	}

	//Delete company from companies (COMPANY_ID is a FK in users & coupons)
	//method=DELETE   url=http://localhost:8080/company/444
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long id, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		companyController.deleteCompany(id, userData);
		System.out.println("The userdata is: " + userData);
	}

}
