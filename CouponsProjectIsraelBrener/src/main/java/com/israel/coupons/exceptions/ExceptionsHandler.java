package com.israel.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.israel.coupons.entities.ErrorBean;
import com.israel.coupons.enums.ErrorType;

// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	//	Response - Object in Spring
	@ExceptionHandler
	@ResponseBody
	// Variable name is throwable in order to remember that it handles Exception and Error
	//	Spring do injection to a familiar variable - like HttpServletResponse
	public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {

		//	ErrorBean errorBean;
		if(throwable instanceof ApplicationException) {
			ApplicationException appException = (ApplicationException) throwable;

			ErrorType errorType = appException.getErrorType(); 
			String errorName = errorType.name();
			int errorNumber = errorType.getInternalErrorCode();
			String errorMessage = errorType.getInternalMessage();
//			String errorName = errorType.getErrorName();
			

			ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage, errorName); 
			if(appException.getErrorType().isShowStackTrace()) {
				appException.printStackTrace();
			}

			response.setStatus(errorNumber);
			return errorBean;
		}

		String errorMessage = throwable.getMessage();
		ErrorBean errorBean = new ErrorBean(601, errorMessage, "General error");
		throwable.printStackTrace();


		response.setStatus(errorBean.getErrorNumber());
		return errorBean;
	}

}



