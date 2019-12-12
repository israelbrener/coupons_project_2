//
//
////import java.util.Date;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.Arrays;
////import com.israel.coupons.utils.DateUtils;
//import java.util.Calendar;
//import java.util.List;
//
//import com.israel.coupons.utils.JdbcUtils;
//import com.israel.coupons.dao.PurchaseDao;
//import com.israel.coupons.entities.Company;
//import com.israel.coupons.entities.Coupon;
//import com.israel.coupons.entities.Customer;
//import com.israel.coupons.entities.Purchase;
//import com.israel.coupons.entities.User;
//import com.israel.coupons.dao.IUserDao;
//import com.israel.coupons.enums.UserType;
//import com.israel.coupons.exceptions.ApplicationException;
//import com.israel.coupons.logic.CompanyController;
//import com.israel.coupons.logic.CouponController;
//import com.israel.coupons.logic.CustomerController;
//import com.israel.coupons.logic.PurchaseController;
//import com.israel.coupons.logic.UserController;
//import com.israel.coupons.logic.CompanyController;
//import com.israel.coupons.dao.CompanyDao;
//import com.israel.coupons.dao.CouponDao;
//import com.israel.coupons.dao.ICustomerDao;
//
//public class Tester {
//
//	public static void main(String[] args) throws ApplicationException {
//		//company:
////											  testCreateCompany();
//		//									   testReadCompany();
////												testReadAllCompanies();
//		//							  		  testUpdateCompany();
//		//        							  testDeleteCompany();
//		//									  testCheckIfCompanyExistsById();
//		//									  testCheckIfCompanyExistsByName();
//
//		//coupon:
//		//										        testCreateCoupon();
//		//									   testRead();
//		//												testReadAllCoupons();
//		//							        	testUpdatecCoupon();
//		//												testDeleteCoupon();
//		//										testRemoveOldCoupons();
//		//		                                testCheckOldCoupons();
//		//										testCheckIfCouponExisitsById();
//		//										testCheckIfCouponExistsByCompanyId();
//
//		//customer: 
//		//									    testCreateCustomer();
//		//								        testReadCustomer();
//		//										        testReadAllCustomers();
//		//										testUpdatecCustomer();
//		//												testDeleteCustomer();
//		//										testCheckIfCustomerExistsByFirstName();
//		//										testCheckIfCustomerExistsByUserId();
//
//
//		//purchase: 
//		//									    testCreatePurchase();
//		//											    testReadPurchase();
//		//											    testReadAllPurchases();	
//		//										testUpdatePurchase();
//		//												testDeletePurchase();
//		//						                testRemoveOldCouponPurchases();
//		//						                 testCheckOldCouponPurchases();
//		//						                        testCheckIfPurchaseExistsById();
//		//										testCheckIfPurchaseExistsByUserId();
//		//										testCheckIfPurchaseExistsByCouponId();
//
//		//user: 
////												testCreateUser();
////												testReadUser();
////												testReadAllUsers();
////												testUpdateUser();
////												testDeleteUser();
////												testCheckIfUserExistsByName();
////		 	     	  		   		            testCheckIfUserExistsById();
////												testCheckUserClientTypeLogin();
//	}		
//
//	//	____________________________________________________________________
//	public static void testCreateCompany() throws ApplicationException {
//
//		//insert company to companies (COMPANY_ID is AI), name is unique
//		Company company = new Company("kfkk",  "68 address");
//		CompanyController companyDao = new CompanyController();
//		long id = companyDao.createCompany(company);
//
//		System.out.println("The id of the created company is: " + id);
//	}
//
//	private static void testReadCompany()  throws ApplicationException {
//
//		//Select company from companies by id
//		CompanyController companyDao = new CompanyController();
//		long id = 69;
//		System.out.println(companyDao.getCompanyById(id));
//		System.out.println("The id of the selected company is: " + id);
//	}
//
//
//	private static void testReadAllCompanies() throws ApplicationException {
//
//		CompanyController companyrDow = new CompanyController();
//		List<Company> companies= companyrDow.getAllCompanies();
//
//		System.out.println("The companies list is: " + companies);
//	}
//
//
//	private static void testUpdateCompany() throws ApplicationException  {
//
//		//Update company in companies
//		Company company = new Company(69, "b", "bb update address");
//		CompanyController companyDao = new CompanyController();
//		companyDao.updateCompany(company);
//
//		long id = company.getCompanyId();
//		System.out.println(companyDao.getCompanyById(id));
//		System.out.println("The id of the updated company is: " + id);
//	}
//
//	private static void testDeleteCompany() throws ApplicationException {
//
//		//Delete company from companies (COMPANY_ID is a FK in users & coupons)
//		CompanyController companyDao = new CompanyController();
//		long id = 71;
//		companyDao.deleteCompany(id);
//
//		System.out.println("The id of the deleted company is: " + id);
//	}
//
//	private static void testCheckIfCompanyExistsById() throws ApplicationException {
//
//		CompanyController companyDao = new CompanyController();
//		long id = 67;
//		boolean b = companyDao.isCompanyExistsById(id);
//		System.out.println("The result of the check is: " + b);
//	}
//
//	private static void testCheckIfCompanyExistsByName() throws ApplicationException {
//
//		CompanyController companyDao = new CompanyController();
//		String name = "com";
//		boolean b = companyDao.isCompanyExistsByName(name);
//		System.out.println("The result of the check is: " + b);
//	}
//	//	____________________________________________________________________
//	@SuppressWarnings("deprecation")
//	private static void testCreateCoupon() throws ApplicationException {
//
//		//insert coupon to coupons (COUPON_ID is AI & COMPAMY_ID is a FK) 
//		//set the start date (d1)
//		int year1= 2017;	
//		int month1 = 9;	
//		int day1 = 5;
//		Date d1 = new Date(year1-1900, month1-1,day1);
//		System.out.println("Start date (d1) is: " + day1 + "/" + month1 +"/" + year1 );
//		//set the end date (d2)
//		int year2 = 2019;	
//		int month2 = 4;	
//		int day2 = 1;
//		Date d2 = new Date(year2-1900, month2-1,day2);
//		System.out.println("End date (d2) is: " + day2 + "/" + month2 +"/" + year2 );
//
//		Coupon coupon =new Coupon(67, 29, "kk", "jj", d1, d2, 4, 9.8F, "kk");
//		CouponController couponDao = new CouponController();
//		long id = couponDao.createCoupon(coupon);
//
//		System.out.println("The id of the created coupon is: " + id);
//	}
//
//	private static void testReadCoupon()  throws ApplicationException {
//		//Select coupon from coupons by id
//		CouponController couponDao = new CouponController();
//		long id = 42;
//		System.out.println(couponDao.getCouponById(id));
//		System.out.println("The id of the selected coupon is: " + id);
//	}
//
//	private static void testReadAllCoupons()  throws ApplicationException {
//
//		CouponController couponDow = new CouponController();
//		List<Coupon> coupons= couponDow.getAllCoupons();
//
//		System.out.println("The coupons list is: " + coupons);
//	}
//
//	private static void testUpdatecCoupon() throws ApplicationException  {
//
//		//Update coupon in coupons (COMPAMY_ID is a FK)
//		//set the start date (d1)
//		int year1= 1976;	
//		int month1 = 9;	
//		int day1 = 5;
//		Date d1 = new Date(year1-1900, month1-1,day1);
//		System.out.println("Start date (d1) is: " + day1 + "/" + month1 +"/" + year1 );
//		//set the end date (d2)
//		int year2 = 2019;	
//		int month2 = 4;	
//		int day2 = 1;
//		Date d2 = new Date(year2-1900, month2-1,day2);
//		System.out.println("End date (d2) is: " + day2 + "/" + month2 +"/" + year2 );
//
//		Coupon coupon = new Coupon(34, 69, 100, "ud", "ud", d1, d2, 15, 6.8F, "img");
//		CouponController couponDao = new CouponController();
//		couponDao.updateCoupon(coupon);
//
//		long id = coupon.getCouponId();
//		System.out.println(couponDao.getCouponById(id));
//		System.out.println("The id of the updated coupon is: " + id);
//	}
//
//	private static void testDeleteCoupon() throws ApplicationException {
//		//Delete coupon from coupons (COUPON_ID is a FK in purchases)
//		CouponController couponDao = new CouponController();
//		long id = 320;
//		couponDao.deleteCoupon(id);
//
//		System.out.println("The id of the deleted coupon is: " + id);
//	}
//
//	public static void testRemoveOldCoupons()throws ApplicationException {
//		//Delete old coupon from coupons (old: endDate < today)
//		long now = Calendar.getInstance().getTimeInMillis();
//		Date todayDate = new Date(now);
//		//select old coupon from coupons (old: endDate < today)
//		CouponController couponDao = new CouponController();
//		Long [] arrCouponId = 	couponDao.checkOldCoupons(todayDate);
//		System.out.println("The checked old coupon/s Id/'s to delete: " + Arrays.toString(arrCouponId));
//
//		couponDao.removeOldCoupons(todayDate);
//	}
//
//	public static void testCheckOldCoupons()throws ApplicationException {
//		//select old coupon from coupons (old: endDate < today)
//		long now = Calendar.getInstance().getTimeInMillis();
//		Date todayDate = new Date(now);
//		CouponController couponDao = new CouponController();
//		Long [] arrCouponId = couponDao.checkOldCoupons(todayDate);
//
//		System.out.println("The checked old coupon/s  Id/'s: " + Arrays.toString(arrCouponId));
//	}
//
//	private static void testCheckIfCouponExisitsById() throws ApplicationException {
//
//		CouponController couponDao = new CouponController();
//		long couponId = 34; 
//		boolean b = couponDao.isCouponExistsById(couponId);
//		System.out.println("The result of the check whether a coupon exists by id is: " + b);
//	}
//
//	private static void testCheckIfCouponExistsByCompanyId() throws ApplicationException {
//
//		CouponController couponDao = new CouponController();
//		long companyId = 69; 
//		boolean b = couponDao.isCouponExistsByCompanyId(companyId);
//		System.out.println("The result of the check whether a coupon exists by company id is: " + b);
//	}
//	//	____________________________________________________________________
//	private static void testCreateCustomer() throws ApplicationException {
//		//insert user to users (USER_ID is AI & COMPANY_ID, which is nullable, is a FK)
//		User user = new User( "skkllk", "60email", "password", UserType.valueOf("CUSTOMER"), (long) 67);
//		IUserDao userDao = new IUserDao();
//		long id = userDao.createUser(user);
//		System.out.println("The id of the created user is: " + id);
//		System.out.println(userDao.getUserById(id));
//
//		//insert customer to customers (USER_ID is a FK)
//		Customer customer = new Customer(id, "lll","ll");
//		//		Customer customer = new Customer(userDao.getUserById(id), "59firstName", "59lastName");
//		CustomerController  customerDao = new CustomerController();
//		customerDao.createCustomer(customer, id);
//		System.out.println("The id of the created customer is: " + id);
//		System.out.println(customer);
//	}
//
//	private static void testReadCustomer()  throws ApplicationException {
//
//		//Select customer from customers by id
//		CustomerController customerDao = new CustomerController();
//		long id = 121;
//		System.out.println(customerDao.getCustomerByCustomerID(id));
//		System.out.println("The id of the selected customer is: " + id);
//	}
//
//	private static void testReadAllCustomers()  throws ApplicationException {
//
//		CustomerController customerDow = new CustomerController();
//		List<Customer> customers= customerDow.getAllCustomers();
//
//		System.out.println("The customers list is: " + customers);
//	}
//
//	private static void testUpdatecCustomer() throws ApplicationException  {
//
//		//Update customer in customer (USER_ID is a FK)
//		Customer customer = new Customer("d", "fff");
//		long userId = 90;
//		CustomerController customerDao = new CustomerController();
//		customerDao.updateCustomer(customer, userId);
//
//		//		long id = customer.getUserId();
//		System.out.println(customerDao.getCustomerByCustomerID(userId));
//		System.out.println("The id of the updated customer is: " + userId);
//		//		System.out.println((companyDao.getPurchase(company.getPurchaseId()))));
//		//		System.out.println(companyDao.getPurchase(52).toString());
//	}
//
//	private static void testDeleteCustomer() throws ApplicationException {
//		//Delete customer from customers
//		CustomerController customerDao = new CustomerController();
//		long id = 91;
//		customerDao.deleteCustomer(id);
//
//		System.out.println("The id of the deleted customer is: " + id);
//	}
//
//	private static void testCheckIfCustomerExistsByUserId() throws ApplicationException {
//
//		CustomerController customerDao = new CustomerController();
//		long id = 90; 
//		boolean b = customerDao.isCustomerExistsByUserId(id);
//		System.out.println("The result of the check whether a customer exists by user Id is: " + b);
//	}
//
//	private static void testCheckIfCustomerExistsByFirstName() throws ApplicationException {
//
//		CustomerController customerDao = new CustomerController();
//		String firstName = "a";
//		boolean b = customerDao.isCustomerExistsByFirstName(firstName);
//		System.out.println("The result of the check is: " + b);
//	}
//	//	_______________________________________________________________________
//	public static void testCreatePurchase() throws ApplicationException {
//
//		//insert purchase to purchases (PURCHASE_ID is AI & COUPON_ID & USER_ID are FK's)
//		Purchase purchase = new Purchase(96,33,3);
//		PurchaseController purchaseDao = new PurchaseController();
//		long id = purchaseDao.createPurchase(purchase);
//
//		System.out.println("The id of the created purchase is: " + id);
//	}
//
//	private static void testReadPurchase()  throws ApplicationException {
//
//		//Select purchase from purchases by id
//		PurchaseController purchaseDao = new PurchaseController();
//		long id = 21;
//		System.out.println(purchaseDao.getPurchaseById(id));
//		System.out.println("The id of the selected purchase is: " + id);
//	}
//
//	private static void testReadAllPurchases() throws ApplicationException {
//
//		PurchaseController purchaseDow = new PurchaseController();
//		List<Purchase> purchases= purchaseDow.getAllPurchases();
//
//		System.out.println("The purchases list is: " + purchases);
//	}
//
//	private static void testUpdatePurchase() throws ApplicationException  {
//
//		//Update purchase in purchases (COUPON_ID & USER_ID are FK's)
//		Purchase purchase = new Purchase(10, 91, 30, 5);
//		PurchaseController purchaseDao = new PurchaseController();
//		purchaseDao.updatePurchase(purchase);
//
//		long id = purchase.getPurchaseId();
//		System.out.println(purchaseDao.getPurchaseById(id));
//		System.out.println("The id of the updated purchase is: " + id);
//	}
//
//	private static void testDeletePurchase() throws ApplicationException {
//
//		//Delete purchase from purchases (COMPANY_ID is a FK in users)
//		PurchaseController purchaseDao = new PurchaseController();
//		long id = 16;
//		purchaseDao.deletePurchase(id);
//
//		System.out.println("The id of the deleted purchase is: " + id);
//	}
//
//	public static void testRemoveOldCouponPurchases()throws ApplicationException {
//		//Delete old coupon from coupons (old: endDate < today)
//		long now = Calendar.getInstance().getTimeInMillis();
//		Date todayDate = new Date(now);
//		//select old coupon from coupons (old: endDate < today)
//		PurchaseController purchaseDao = new PurchaseController();
//		Long [] arrPurchaseId = 	purchaseDao.checkOldCouponPurchases(todayDate);
//		System.out.println("The checked purchases Id/'s with old coupon to delete: " + Arrays.toString(arrPurchaseId));
//
//		purchaseDao.removeOldCouponPurchases(todayDate);
//	}
//
//	public static void testCheckOldCouponPurchases() throws ApplicationException {
//		//select purchases with old coupon from purchases (old: endDate < today)
//		long now = Calendar.getInstance().getTimeInMillis();
//		Date todayDate = new Date(now);
//		PurchaseController purchaseDao = new PurchaseController();
//		Long [] arrPurchaseId = purchaseDao.checkOldCouponPurchases(todayDate);
//
//		System.out.println("The checked purchases Id/'s with old coupon:  " + Arrays.toString(arrPurchaseId));
//	}
//
//	private static void testCheckIfPurchaseExistsById() throws ApplicationException {
//
//		PurchaseController purchaseDao = new PurchaseController();
//		long purchaseId = 22; 
//		boolean b = purchaseDao.isPurchaseExistsById(purchaseId);
//		System.out.println("The result of the check whether purchase exists by purchase Id is: " + b);
//	}
//
//	private static void testCheckIfPurchaseExistsByUserId() throws ApplicationException {
//
//		PurchaseController purchaseDao = new PurchaseController();
//		long userId = -5; 
//		boolean b = purchaseDao.isPurchaseExistsByUserId(userId);
//		System.out.println("The result of the check whether user exists by user Id is: " + b);
//	}
//
//	private static void testCheckIfPurchaseExistsByCouponId() throws ApplicationException {
//
//		PurchaseController purchaseDao = new PurchaseController();
//		long couponId = 300; 
//		boolean b = purchaseDao.isPurchaseExistsByCouponId(couponId);
//		System.out.println("The result of the check whether user exists by coupon Id is: " + b);
//	}
//	//	____________________________________________________________________
//	public static void testCreateUser() throws ApplicationException {
//
//		//insert user to users (USER_ID is AI & COMPANY_ID, which is nullable, is a FK)
//		User user = new User("oppo137", "dds", "DDSxx", UserType.valueOf("CUSTOMER"), (long) 67);	
//		UserController userDow = new UserController();
//		long id = userDow.createUser(user);
//
//		System.out.println("The id of the created user is: " + id);
//	}
//
//	private static void testReadUser()  throws ApplicationException {
//
//		//Select user from users by id
//		UserController userDow = new UserController();
//		long id  = 142;
//		System.out.println(userDow.getUserById(id));
//		System.out.println("The id of the selected user is: " + id);
//	}
//
//	private static void testReadAllUsers() throws ApplicationException {
//
//		UserController userDow = new UserController();
//		List<User> users= userDow.getAllUsers();
//
//		System.out.println("The users list is: " + users);
//	}
//
//	private static void testUpdateUser() throws ApplicationException  {
//
//		//Update user in users (COMPANY_ID, which is nullable, is a FK)
//		//		User user = new User(23,"un","e","p","t", (long)67);
//		User user = new User((long)146, "dn146", "b", "p", UserType.valueOf("CUSTOMER"), (long) 67);
//
//		UserController userDow = new UserController();
//		userDow.updateUser(user);
//
//		long id = user.getUserId();
//		System.out.println(userDow.getUserById(id));
//		System.out.println("The id of the updated user is: " + id);
//	}
//
//	private static void testDeleteUser() throws ApplicationException {
//
//		//Delete user from users (USER_ID is a FK in customers $ purchases)
//		UserController userDao = new UserController();
//		long id = 146;
//		userDao.deleteUser(id);
//
//		System.out.println("The id of the deleted user is: " + id);
//	}
//
//	private static void testCheckIfUserExistsByName() throws ApplicationException {
//
//		UserController userDao = new UserController();
//		String userName = "test142";
//		boolean b = userDao.isUserExistsByName(userName);
//		System.out.println("The result of the check whether user exists by username is: " + b);
//	}
//
//	private static void testCheckIfUserExistsById() throws ApplicationException {
//
//		UserController userDao = new UserController();
//		long userId = 142;
//		boolean b = userDao.isUserExistsById(userId);
//		System.out.println("The result of the check whether user exists by user Id is: " + b);
//	}
//
//	private static void testCheckUserClientTypeLogin() throws ApplicationException {
//
//		UserController userDao = new UserController();
//		String userName ="hkkjk";
//		String password = "password";
//
//		UserType  type = userDao.userClientTypeLogin(userName, password);
//		System.out.println("The client type is: " + type);	
//	}
//	//	____________________________________________________________________
//
//}
//
//
//
//
