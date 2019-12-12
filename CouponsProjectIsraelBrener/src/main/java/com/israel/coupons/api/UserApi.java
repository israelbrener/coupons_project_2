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

import com.israel.coupons.logic.UserController;
import com.israel.coupons.consts.Constants;
import com.israel.coupons.data.LoggedInUserData;
import com.israel.coupons.data.LoginResponseDataObject;
import com.israel.coupons.data.UserLoginDetailsDataObject;
import com.israel.coupons.entities.User;
import com.israel.coupons.exceptions.ApplicationException;

@RestController
@RequestMapping("/user")
public class UserApi {

	@Autowired
	private UserController userController;

	//method=POST   url=http://localhost:8080/user
	@PostMapping
	public void createUser(@RequestBody User user) throws ApplicationException  {
		userController.createUser(user);
		long id = user.getUserId();
		System.out.println("The id of the created user is: " + id);
	}

	//method=GET   url=http://localhost:8080/user/444
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") long id) throws ApplicationException {
		User user = userController.getUserById(id);
		System.out.println("The user by id is: " +"\n"+ user);
		return user;
	}

	//method=GET   url=http://localhost:8080/user/byUserName?userName=un
	@GetMapping("/byUserName")
	public User getUserByUserName(@RequestParam("userName") String userName) throws ApplicationException {
		User user = userController.getUserByUserName(userName);
		System.out.println("The user by userName is: " +"\n"+ user);
		return user;
	}

	//method=GET   url=http://localhost:8080/user/byPassword?password=pw
	@GetMapping("/byPassword")
	public User getUserByPassword(@RequestParam("password") String password) throws ApplicationException {
		User user = userController.getUserByPassword(password);
		System.out.println("The user by password is: " +"\n"+ user);
		return user;
	}

	//method=GET   url=http://localhost:8080/user/byUserNameAndPassword?userName=un&password=pw
	@GetMapping("/byUserNameAndPassword")
	public User getUserByUserNameAndPassword(@RequestParam("userName") String userName, @RequestParam("password") String password) throws ApplicationException {
		User user =  userController.getUserByUserNameAndPassword(userName, password);
		System.out.println("The user by userName & password is: " +"\n"+ user);

		return user;
	}

	//method=GET   url=http://localhost:8080/user
	@GetMapping
	public List<User> getAllUsers() throws ApplicationException {
		System.out.println("We have to get all users list on a web page");
		return userController.getAllUsers();
	}	

	//method=PUT   url=http://localhost:8080/user
	@PutMapping
	public void updateUser(@RequestBody User user, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		userController.updateUser(user, userData);
		System.out.println("The userdata is: " + userData);
	}

	//Delete user from users (USER_ID is a FK in customers $ purchases)
	//method=DELETE   url=http://localhost:8080/user/444
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long id, HttpServletRequest request) throws ApplicationException {
		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		userController.deleteUser(id, userData);
		System.out.println("The userdata is: " + userData);
	}

	//method=POST   url=http://localhost:8080/user/login
	@PostMapping("/login")
	public LoginResponseDataObject login(@RequestBody UserLoginDetailsDataObject userLoginDetailsDataObject) throws ApplicationException {
		String userName = userLoginDetailsDataObject.getUserName();
		String password = userLoginDetailsDataObject.getPassword();
		LoginResponseDataObject loginResponseDataObject = userController.login(userName, password);
		System.out.println("loginResponseDataObject: " + loginResponseDataObject);
		return loginResponseDataObject;	
	}

}
