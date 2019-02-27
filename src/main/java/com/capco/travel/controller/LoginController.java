package com.capco.travel.controller;

import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.service.EmployeeDetailService;
import com.capco.travel.service.LoginService;
import com.capco.travel.util.TokenGeneratorUtils;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.EmployeeDetailsVO;
import com.capco.travel.vo.LoginDetailsVO;

/**
 * This service is created to authenticate login user
 * 
 * @author e5542274
 *
 */
@RestController
public class LoginController {	

	private final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	EmployeeDetailService employeeDetailService;

	/**
	 * This method is written to validate login details from request parameters and
	 * send back response along with login details
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/portalLogin", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<EmployeeDetailsVO> login(@RequestBody LoginDetailsVO loginDetailsVo,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("LoginController : login: Started");
		HttpSession session = request.getSession();
		String userId = loginDetailsVo.getCapcoUserId();
		String password = loginDetailsVo.getPassword();
		Boolean userExists = false;
		EmployeeDetailsVO employeesDetailsVO = null;
		try {
			userExists = loginService.validateLoginUser(userId, password);
			if (userExists) {
				// Generate token id to call rest service
				EmployeeDetailsBO employeesDetailsBO = employeeDetailService.getEmployeeDetailsByCapcoUserId(userId);
				if(employeesDetailsBO != null) {
					loginDetailsVo.setEmployeeId(employeesDetailsBO.getEmployeeId());
					//String tokenId = JWTUtils.generateToken(loginDetailsVo);
					employeesDetailsVO  = TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(employeesDetailsBO);
					//session.setAttribute("tokenId", tokenId);
					TokenGeneratorUtils.generateRandomString(userId+(employeesDetailsBO.getEmployeeId()));
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(System.currentTimeMillis());
					cal.add(Calendar.MINUTE, 15);
					//session.setAttribute("tokenCreatedTime", cal);
					TokenGeneratorUtils.loginTokenTime.put("tokenCreatedTime", cal);
					session.setAttribute("userId", userId);
					session.setAttribute("employeeId", employeesDetailsVO.getEmployeeId()); 
				}
			} else {
				logger.info("LoginController : login: ended with no content");
				return new ResponseEntity<>(employeesDetailsVO, HttpStatus.NO_CONTENT);
			}
			logger.info("LoginController : login: Ended");
			return new ResponseEntity<>(employeesDetailsVO, HttpStatus.OK);

		} catch (TravelServiceException e) {
			logger.error("LoginController : login: exception caught" + e);
			return new ResponseEntity<>(employeesDetailsVO, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method is written to log out user
	 * 
	 * @return ResponseEntity
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/logout", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<EmployeeDetailsVO> logout(HttpServletRequest request, HttpServletResponse response)
			throws TravelServiceException {
		HttpSession session = request.getSession();
		EmployeeDetailsVO employeeDetailsVO = new EmployeeDetailsVO();
		logger.info("LoginController : logout: Started");
		String capcoUserId =(String) session.getAttribute("userId");
		//employeeDetailService.updateTokenId(capcoUserId);
		System.out.println("token Id before logout "+TokenGeneratorUtils.loginTokenTime.get("tokenId"));
		TokenGeneratorUtils.loginTokenTime.put("tokenId", 0);
			System.out.println("token id after logout "+TokenGeneratorUtils.loginTokenTime.get("tokenId"));
		System.out.println("capco user id before changed"+capcoUserId);
		capcoUserId=null;
		System.out.println("capco user id has been changed"+capcoUserId);
		logger.info("LoginController : logout: capcoUserId : capcoUserId changed to null");
		session.invalidate();
		logger.info("LoginController : logout: session : invalidate");
		logger.info("LoginController : logout: Ended");
		return new ResponseEntity<>(employeeDetailsVO, HttpStatus.OK);
	}

}
