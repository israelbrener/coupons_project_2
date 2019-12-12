package com.israel.coupons.logic;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.cache.ICacheController;
import com.israel.coupons.dao.IUserDao;
import com.israel.coupons.data.LoginResponseDataObject;
import com.israel.coupons.entities.User;
import com.israel.coupons.enums.ErrorType;
import com.israel.coupons.enums.UserType;
import com.israel.coupons.exceptions.ApplicationException;


@Controller
public class UserController {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private ICacheController cacheController;

	@Value("${maxFailedLogin}")
	private int maxOfFailedLogin;

	public void createUser(User user) throws ApplicationException {
		validateUserName(user.getUserName());
		validatePassword(user.getPassword());
		if(userDao.findByUserName(user.getUserName()) != null) {
			throw new ApplicationException(ErrorType.USER_NAME_ALREADY_EXISTS, "The user already exists by userName");
		}	
		else{
			userDao.save(user);
		}
	}

	public User getUserById(Long id) throws ApplicationException   {		
		validateUserId(id);	
		User user = userDao.findByUserId(id);
		if(user==null) {
			throw new ApplicationException(ErrorType.INVALID_ID, "UserId doesn't exist");
		}
		return user;
	}

	public User getUserByUserName(String userName) throws ApplicationException  {
		validateUserName(userName);
		User user = userDao.findByUserName(userName);
		if(user==null) {
			throw new ApplicationException(ErrorType.INVALID_USER_NAME, "UserName doesn't exist");
		}
		return user;
	}

	public User getUserByPassword(String password) throws ApplicationException {
		validatePassword(password);
		List<User> users = userDao.findByPassword(password);
		if(users.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Password doesn't exist");
		}
		return users.get(0);
	}

	public User getUserByUserNameAndPassword(String userName, String password) throws ApplicationException {
		validateUserName(userName);
		validatePassword(password);		
		List<User> users = userDao.findByUserNameAndPassword(userName, password);
		if(users.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserName/Password doesn't exist");
		}
		return users.get(0);
	}

	public List<User>getAllUsers() throws ApplicationException {
		List<User> users = (List<User>) userDao.findAll();
		if(users.size()==0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no users");
		}
		return users;
	}

	public void updateUser(User user, LoggedInUserData userData) throws ApplicationException {
		isUserExistsById(user.getUserId());
		validateUserName(user.getUserName());
		validateUserNameExists(user.getUserName(), user.getUserId());
		validatePassword(user.getPassword());
		//Credentials: Only the ADMIN can manipulate any user		
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= user.getUserId() ) {
			System.out.println("Only the ADMIN can manipulate any user!");
			return;		
		}	
		userDao.save(user);
		System.out.println("The updated user is: " + user);
	}

	public void deleteUser(long userId, LoggedInUserData userData) throws ApplicationException {
		isUserExistsById(userId);
		User user = getUserById(userId);
		//Credentials: Only the ADMIN can manipulate any user
		if(!userData.getUserType().equals(UserType.ADMIN) &&
				userData.getUserId()!= user.getUserId() ) {
			System.out.println("Only the ADMIN can manipulate any user!");
			return;		
		}				
		userDao.delete(user);
		System.out.println("Deleted user id is: " + userId);
	}

	public LoginResponseDataObject login(String userName, String password) throws ApplicationException {
		User user = getUserByUserNameAndPassword(userName, password);
		if(user == null) {
			throw new ApplicationException(ErrorType.LOGIN_FAILED, "Login failed. Please try again.");
		}
		UserType userType = user.getType();
		Long companyId = user.getCompanyId();
		long userId = user.getUserId();

		LoggedInUserData loggedInUserData; 

		if(companyId != null) {
			loggedInUserData = new LoggedInUserData(userType, companyId, userId);
		}
		else {
			loggedInUserData = new LoggedInUserData(userType, userId);
		}

		//		int token = generateToken(userName, loggedInUserData);
		int token = 123;
		loggedInUserData.setToken(token);

		// Converting the int into a String
		String strToken = String.valueOf(token); 
		// Save login user data in cache
		cacheController.put(strToken, loggedInUserData);
		LoginResponseDataObject loginResponseDataObject = new LoginResponseDataObject(token, loggedInUserData.getUserType());
		return loginResponseDataObject;
	}

	private int generateToken(String username, LoggedInUserData loggedInUserData) {
		Random rnd = new Random();
		String salt = "#####";
		return (username + rnd.nextInt(9999999) + salt + loggedInUserData.getUserId()).hashCode();
	}

	@PostConstruct
	public void postConstructExample() {
		System.out.println("This is the max number of login tries " + maxOfFailedLogin);
	}

	public void validateUserId(long id) throws ApplicationException {
		//i have to add a validation for id is typed as an integer number only:
		//		if  (id+0 != id) {
		//			throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserId must be an integer number");
		//		}
		if  (id == 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserId is null");
		}	
		if  (id < 0 ) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserId must be positive");
		}	
	}

	public void validateUserName(String userName) throws ApplicationException {
		if  (userName == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null userName");		
		}
		if  (userName.isEmpty()) {
			throw new ApplicationException(ErrorType.USER_NAME_IS_EMPTY, "An empty userName");		
		}
	}

	public void validateUserNameExists(String userName, long id) throws ApplicationException  { 
		validateUserName(userName);
		User user = userDao.findByUserName(userName);
		User user2 = userDao.findById(id).get();
		if((user != null) && (user != user2)) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserName already exists");
		}
	}	

	public void validatePassword(String password) throws ApplicationException {
		if  (password == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null password");		
		}
		if  (password.isEmpty()) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty password");		
		}
	}

	public boolean isUserExistsById(long id) throws ApplicationException { 
		validateUserId(id);
		if (getUserById(id) != null) {
			return true;	
		}
		return false;
	}	

	public boolean isUserExistsByUserName(String userName) throws ApplicationException { 
		validateUserName(userName);
		if (getUserByUserName(userName) != null) {
			return true;	
		}
		return false;
	}	

}



