package com.israel.coupons.entities;
//package com.israel.coupons.beans;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.Iterator;
//
//import com.israel.coupons.api.UserApi;
//
//public class ReflectionExample {
//
//	public static void main(String[] args) throws Exception {
//		User user = (User) User.class.newInstance();
//
//		Method[] methods = user.getClass().getMethods();
//		for(Method method: methods) {
//			System.out.println(method.getName());
//		}
//		
//		UserApi userApi = new UserApi();
//		Class userApiClass = userApi.getClass();
//		Annotation[] annotations = userApiClass.getAnnotations();
//		for(Annotation annotation: annotations) {
//			System.out.println(annotation.toString());
//		}
//	}
//}
