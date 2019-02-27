package com.capco.travel.interceptor;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.capco.travel.controller.LoginController;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.util.TokenGeneratorUtils;
import com.capco.travel.vo.ErrorCodeHandlerVO;

import javassist.NotFoundException;

public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger
			.getLogger(RequestHandlerInterceptor.class);
	@Autowired
	private MainRequestService mainRequestService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*long startTime = System.currentTimeMillis();*/
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Start Time=" + System.currentTimeMillis());
		HttpSession session = request.getSession();		
		/*Integer tokenId = (Integer) session.getAttribute("tokenId");*/
		String userId = (String) session.getAttribute("userId");
		String tokenId = (String) TokenGeneratorUtils.loginTokenTime.get("tokenId");
		
		
		if(tokenId != null && validateKeepAliveTime()) {
			logger.info("RequestHandlerInterceptor : preHandle: validatingRequestUrl");
			return true;

		} else {
			logger.error("RequestHandlerInterceptor : preHandle: validatingRequestUrlFailed");
			ErrorCodeHandlerVO errorVO = new ErrorCodeHandlerVO();
			errorVO.setErrorCode(HttpStatus.BAD_REQUEST.value());
			throw new NotFoundException("Hi");
		}
	}

	/**
	 * @param session
	 */
	private boolean validateKeepAliveTime() {
		Calendar cal = (Calendar) TokenGeneratorUtils.loginTokenTime.get("tokenCreatedTime"); /*session.getAttribute("tokenCreatedTime");*/
		if(cal.getTimeInMillis() > System.currentTimeMillis()) {
			cal.setTimeInMillis((System.currentTimeMillis()));
			cal.add(Calendar.MINUTE, 15);	
			TokenGeneratorUtils.loginTokenTime.put("tokenCreatedTime", cal);
			//session.setAttribute("tokenCreatedTime", cal);
			logger.info("System updated time: " + TokenGeneratorUtils.loginTokenTime.get("tokenCreatedTime"));
			return true;
		} else {
			return false;
		}
	}

	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {
		logger.info("RequestHandlerInterceptor: afterCompletion: completion");
	}


}
