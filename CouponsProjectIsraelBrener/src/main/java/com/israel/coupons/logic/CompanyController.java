package com.israel.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.israel.coupons.dao.ICompanyDao;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.entities.Company;
import com.israel.coupons.enums.ErrorType;
import com.israel.coupons.enums.UserType;
import com.israel.coupons.exceptions.ApplicationException;

@Controller
public class CompanyController {

	@Autowired
	private ICompanyDao companyDao;

	public void createCompany(Company company, LoggedInUserData userData) throws ApplicationException {
		validateNameExists(company.getName());
		validateAddress(company.getAddress());
		//Credentials: Only the ADMIN & COMPANY can manipulate a company
		if(userData.getUserType().equals(UserType.CUSTOMER)) {
			System.out.println("Only the ADMIN & COMPANY can manipulate a company!");
			return;		
		}
		companyDao.save(company);
		System.out.println("The created company is: " + company);
	}

	public Company getCompanyById(long id) throws ApplicationException {
		validateId(id);	
		Company company = companyDao.findByCompanyId(id);
		if(company == null) {
			throw new ApplicationException(ErrorType.INVALID_COMPANY_ID, "CompanyId doesn't exist");
		}
		return company;
	}

	public Company getCompanyByName(String name) throws ApplicationException  {
		validateName(name);
		Company company = companyDao.findByName(name);
		if(company==null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Name doesn't exist");
		}
		return company;
	}

	public List<Company>getAllCompanies() throws ApplicationException {
		List<Company> companies = (List<Company>) companyDao.findAll();
		if(companies.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no companies");
		}
		return companies;
	}

	public void updateCompany(Company company, LoggedInUserData userData) throws ApplicationException {
		validateIdNotExists(company.getCompanyId());
		validateNameExists(company.getName());
		validateAddress(company.getAddress());
		/*Credentials: Only the ADMIN & owning COMPANY can manipulate a company
		 *  & since COMPANY_ID is a FK in users & coupons,
		 *  only the ADMIN can delete a company
		 *  Whether ther's no user/coupon related to the company or by cascade*/
		if((userData.getUserType().equals(UserType.CUSTOMER)) ||
				(userData.getUserType().equals(UserType.COMPANY) && 
						!userData.getCompanyId().equals(company.getCompanyId()))) {	
			System.out.println("Only the ADMIN & owning COMPANY can manipulate a company!");
			return;		
		}		
		companyDao.save(company);
		System.out.println("The updated company is: " + company);
	}

	public void deleteCompany(long companyId, LoggedInUserData userData) throws ApplicationException {
		validateIdNotExists(companyId);
		Company company = getCompanyById(companyId);
		//Credentials: Only the ADMIN & owning COMPANY can manipulate a company
		if((userData.getUserType().equals(UserType.CUSTOMER)) ||
				(userData.getUserType().equals(UserType.COMPANY) && 
						!userData.getCompanyId().equals(company.getCompanyId()))) {	
			System.out.println("Only the ADMIN & owning COMPANY can manipulate a company!");
			return;		
		}		
		companyDao.delete(company);
		System.out.println("Deleted company id is: " + companyId);
	}

	public boolean isCompanyExistsById(long id) throws ApplicationException { 
		validateId(id);
		if (getCompanyById(id) != null) {
			return true;	
		}
		return false;
	}	
	public void validateIdNotExists(long id) throws ApplicationException  { 
		validateId( id);
		Company company = companyDao.findByCompanyId(id);
		if(company==null) {
			throw new ApplicationException(ErrorType.INVALID_COMPANY_ID, "Company id does not exist");
		}
	}	

	public void validateId(long id) throws ApplicationException {
		//i have to add a validation for id is typed as an integer number only:
		//		if  (id+0 != id) {
		//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CompanyId must be an integer number");
		//		}
		if  (id == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CompanyId is null");
		}	
		if  (id < 0 ) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "CompanyId must be positive");
		}	
	}

	public boolean isCompanyExistsByName(String name) throws ApplicationException { 
		validateName(name);
		if (getCompanyByName(name) != null) {
			return true;	
		}
		return false;
	}

	public void validateNameExists(String name) throws ApplicationException  { 
		validateName(name);
		Company company = companyDao.findByName(name);
		if(company!=null) {
			throw new ApplicationException(ErrorType.COMPANY_NAME_ALREADY_EXISTS, "Company name does exist");
		}
	}	

	public void validateName(String name) throws ApplicationException {
		if  (name == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null name");		
		}
		if  (name.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty name");
		}
	}

	public void validateAddress(String address) throws ApplicationException {
		if  (address == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null address");		
		}
		if  (address.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty address");
		}
	}

}




