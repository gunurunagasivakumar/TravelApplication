package com.capco.travel.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import com.capco.travel.beans.TravelRequestWrapper;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.EmployeeDAO;
import com.capco.travel.dao.MainRequestDAO;
import com.capco.travel.model.AccomodationDetailsBO;
import com.capco.travel.model.CabDetailsBO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.model.FlightDetailsBO;
import com.capco.travel.model.ForexDetailsBO;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.model.VisaRequestBO;
import com.capco.travel.service.EmailNotificationService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.validator.AccomodationDetailsValidator;
import com.capco.travel.validator.CabDetailsValidator;
import com.capco.travel.validator.FlightDetailsValidator;
import com.capco.travel.validator.ForexDetailsValidator;
import com.capco.travel.validator.VisaDetailsValidator;
import com.capco.travel.vo.AccomodationDetailsVO;
import com.capco.travel.vo.CabDetailsVO;
import com.capco.travel.vo.EmployeeDetailsVO;
import com.capco.travel.vo.FlightDetailsVO;
import com.capco.travel.vo.ForexDetailsVO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.RequestHistoryLogVO;
import com.capco.travel.vo.RequestListVO;
import com.capco.travel.vo.VisaRequestVO;

/**
 * @author e5542274
 *
 */

@Service
public class MainRequestServiceImpl implements MainRequestService {

	private final Logger logger = Logger.getLogger(MainRequestServiceImpl.class);

	// validation errors
	private List<FieldError> allErrors = null;

	@Autowired
	private MainRequestDAO mainRequestDAO;

	@Autowired
	private EmployeeDAO empDao;

	@Autowired
	private EmailNotificationService emailNotificationService;

	/**
	 * @param MainRequestVO
	 *            object
	 * @return Integer
	 */
	public Integer insertMainRequest(MainRequestVO newRequest) throws TravelServiceException {
		Integer travelRequestId = 0;
		try {
			logger.info("MainRequestServiceImpl : insertMainReuqest: Started");
			MainRequestBO mainRequestBO = createMainRequestBO(newRequest);
			travelRequestId = mainRequestDAO.insertMainReuqest(mainRequestBO);
			final int finalRequestId = travelRequestId;

			// saving in request_history
			RequestHistoryLogBO requestHistoryLogBO = createRequestHistoryLogNew(mainRequestBO);
			mainRequestDAO.insertRequestHistoryLog(requestHistoryLogBO);

			//sending the email notification
			if (finalRequestId != 0) {

				sendEmailNotification(finalRequestId,TravelConstants.EMAIL_NEW);

			}

			logger.info("MainRequestServiceImpl : insertMainReuqest: ended");
		} catch (DAOException e) {
			logger.error("MainRequestServiceImpl : insertMainReuqest: Exception Caught: " + e);
			throw new TravelServiceException(e);
		} catch (ParseException e) {
			logger.error("MainRequestServiceImpl : insertMainReuqest: Exception Caught: " + e);
			throw new TravelServiceException(e);
		}
		return travelRequestId;
	}

	/**
	 * @param MainRequestBO
	 *            and MainRequestVO object
	 * @return MainRequestVO object
	 */
	public MainRequestVO updateMainRequest(MainRequestBO dbData, MainRequestVO updateRequest)
			throws TravelServiceException {
		MainRequestVO mainRequestVO = null;
		MainRequestBO mainRequestBO = null;
		try {
			logger.info("MainRequestServiceImpl : updateMainRequest: Started");
			mainRequestBO = updateMainRequestBO(dbData, updateRequest);
			mainRequestBO = mainRequestDAO.updateMainRequest(mainRequestBO);
			mainRequestVO = getRequestDetailsByRequestId(mainRequestBO.getRequestId());

			// saving update in request_history
			RequestHistoryLogBO requestHistoryLogBO = createRequestHistoryLogNew(mainRequestBO);
			mainRequestDAO.insertRequestHistoryLog(requestHistoryLogBO);
			
			//sending the email notification
			if (mainRequestBO.getRequestId() != 0) {

				sendEmailNotification(mainRequestBO.getRequestId(),TravelConstants.EMAIL_UPDATE);

			}

			// setting the comment list
			List<RequestHistoryLogVO> commentList = getRequestHistoryList(mainRequestBO.getRequestId());
			mainRequestVO.setCommentList(commentList);

			logger.info("MainRequestServiceImpl : updateMainRequest: ended");
		} catch (DAOException e) {
			logger.error("MainRequestServiceImpl : updateMainRequest: Exception Caught: " + e);
		}
		return mainRequestVO;
	}

	/**
	 * @methodName deleteRequestByRequestId
	 * @param MainRequestBO
	 *            object
	 * @return boolean
	 * @throws TravelServiceException
	 */
	public Boolean deleteRequestByRequestId(MainRequestBO mainRequestBO) throws TravelServiceException {

		Boolean requestDeleted = false;
		try {
			logger.info("MainRequestServiceImpl : deleteRequestByRequestId: Started");
			// MainRequestBO mainRequestBO
			// =TravelBeanUtils.mainRequestVO2MainRequestBO(mainRequestVO);
			mainRequestBO.setCurrentStatus(TravelConstants.CANCELLED);
			/*
			 * AccomodationDetailsBO bo = mainRequestBO.getAccomoDetailsBO();
			 * bo.setBudget("2345"); mainRequestBO.setAccomoDetailsBO(bo);
			 */
			requestDeleted = mainRequestDAO.deleteRequestByRequestId(mainRequestBO);
			
			if (mainRequestBO.getRequestId() != 0) {

				sendEmailNotification(mainRequestBO.getRequestId(),"");

			}

			// insert entry in request_history table
			RequestHistoryLogBO requestHistoryLogBO = createRequestHistoryLogNew(mainRequestBO);
			insertRequestHistoryLog(requestHistoryLogBO);

		} catch (DAOException e) {
			logger.error("MainRequestServiceImpl : getRequestDetailsByRequestId: Exception Caught: " + e);
		}
		return requestDeleted;
	}

	/**
	 * @methodName getRequestDetailsByRequestId
	 * @param RequestID
	 * @return MainRequestVO object
	 * @throws TravelServiceException
	 */
	public MainRequestVO getRequestDetailsByRequestId(Integer requestId) throws TravelServiceException {
		MainRequestBO mainRequestBO = null;
		MainRequestVO mainRequestVO = new MainRequestVO();
		
		try {
			logger.info("MainRequestServiceImpl : getRequestDetailsByRequestId: Started");
			mainRequestBO = mainRequestDAO.getRequestDetailsByRequestId(requestId);
			if (mainRequestBO == null) {
				return null;
			}
			mainRequestVO = TravelBeanUtils.mainRequestBO2MainRequestVO(mainRequestBO);

			// set the comment list
			//List<String> commentList = mainRequestDAO.getRequestHistoryCommentsByRequestId(requestId);
			//mainRequestVO.setCommentList(commentList);
			
			List<RequestHistoryLogVO> commentList = getRequestHistoryList(requestId);
			mainRequestVO.setCommentList(commentList);

			if (mainRequestBO.getAccomoDetailsBO() != null && mainRequestBO.getAccomoDetailsBO().getIsActive()) {
				mainRequestVO.setAccomodationRequest(TravelBeanUtils
						.AccomodationDetailsBO2AccomodationDetailsVO(mainRequestBO.getAccomoDetailsBO()));
			}

			if (mainRequestBO.getCabDetailsBO() != null && mainRequestBO.getCabDetailsBO().getIsActive()) {
				mainRequestVO.setCabRequest(TravelBeanUtils.CabDetailsBO2CabDetailsVO(mainRequestBO.getCabDetailsBO()));
			}

			if (mainRequestBO.getForexDetailsBO() != null && mainRequestBO.getForexDetailsBO().getIsActive()) {
				mainRequestVO.setForexRequest(
						TravelBeanUtils.ForexDetailsBO2ForexDetailsVO(mainRequestBO.getForexDetailsBO()));
			}

			if (mainRequestBO.getFlightDetailsBO() != null && mainRequestBO.getFlightDetailsBO().getIsActive()) {
				mainRequestVO.setFlightRequest(
						TravelBeanUtils.FlightDetailsBO2FlightDetailsVO(mainRequestBO.getFlightDetailsBO()));
			}

			if (mainRequestBO.getVisaRequestBO() != null && mainRequestBO.getVisaRequestBO().getIsActive()) {
				mainRequestVO.setVisaRequestVO(
						TravelBeanUtils.VisaRequestBO2VisaRequestVO(mainRequestBO.getVisaRequestBO()));
			}
				// to get employee details
			if(mainRequestBO.getRequestedBy()!=null) {
			mainRequestVO.getEmpData().add(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(empDao.getEmployeeDetails(mainRequestBO.getRequestedBy())));
						//mainRequestVO.setEmpData(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(empDao.getEmployeeDetails(mainRequestBO.getRequestedBy())));
				} 
			if(mainRequestBO.getApproverId()!=null) {
				mainRequestVO.getEmpData().add(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(empDao.getEmployeeDetails(mainRequestBO.getApproverId())));
							//mainRequestVO.setEmpData(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(empDao.getEmployeeDetails(mainRequestBO.getRequestedBy())));
					} 
				 


			// mainRequestVO.setEmpData(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(mainRequestBO.getEmployeeDetailsBO()));
		} catch (DAOException e) {
			logger.error("MainRequestServiceImpl : getRequestDetailsByRequestId: Exception Caught: " + e);
		}
		return mainRequestVO;
	}

	/**
	 * @methodName getMainRequestBOByRequestId
	 * @param RequestID
	 * @return MainRequestBO object
	 * @throws TravelServiceException
	 */
	public MainRequestBO getMainRequestBOByRequestId(Integer requestId) throws TravelServiceException {
		MainRequestBO mainRequestBO = null;

		try {
			logger.info("MainRequestServiceImpl : getMainRequestBOByRequestId: Started");
			mainRequestBO = mainRequestDAO.getRequestDetailsByRequestId(requestId);
			// mainRequestVO = TravelBeanUtils.mainRequestBO2MainRequestVO(mainRequestBO);
			logger.info("MainRequestServiceImpl : getMainRequestBOByRequestId: Ended");
		} catch (DAOException e) {
			logger.error("MainRequestServiceImpl : getMainRequestBOByRequestId: Exception Caught: " + e);
		}
		return mainRequestBO;
	}

	/**
	 * @methodName updateMainRequestBO
	 * @param MainRequestBO
	 *            and MainRequestVO object
	 * @return MainRequestBO object
	 */
	public MainRequestBO updateMainRequestBO(MainRequestBO mainRequestBO, MainRequestVO updateRequest) {
		logger.info("MainRequestServiceImpl : updateMainRequestBO: Started");
		List<EmployeeDetailsVO> employeeDetailsVO = updateRequest.getEmpData();
		AccomodationDetailsVO accomoDetailsVO = updateRequest.getAccomodationRequest();
		FlightDetailsVO flightDetailsVO = updateRequest.getFlightRequest();
		CabDetailsVO cabDetailsVO = updateRequest.getCabRequest();
		ForexDetailsVO forexDetailsVO = updateRequest.getForexRequest();
		VisaRequestVO visaRequestVO = updateRequest.getVisaRequestVO();
		// TODO : Do validation - Validate All request data
		/*
		 * mainRequestBO =
		 * EmployeeDetailsValidator.validateEmployeeDetails(mainRequestBO,
		 * empDetailsVO); mainRequestBO =
		 * CabDetailsValidator.validateCabDetails(mainRequestBO, cabDetailsVO);
		 * mainRequestBO = FlightDetailsValidator.validateFlightDetails(mainRequestBO,
		 * flightDetailsVO); mainRequestBO =
		 * ForexDetailsValidator.validateForexDetails(mainRequestBO, forexDetailsVO);
		 * mainRequestBO =
		 * AccomodationDetailsValidator.validateAccomodationDetails(mainRequestBO,
		 * accomoDetailsVO);
		 */

		if (null != updateRequest.getRequestedBy()) {
			// mainRequestBO.setRequestedBy(updateRequest.getRequestedBy());
		}
		if (null != updateRequest.getRequestedFor() && !updateRequest.getRequestedFor().isEmpty()) {
			mainRequestBO.setRequestedFor(updateRequest.getRequestedFor());
		}

		if (null != updateRequest.getApproverId()) {
			mainRequestBO.setApproverId(updateRequest.getApproverId());
		}
		if (null != updateRequest.getProjectCode()) {
			mainRequestBO.setProjectCode(updateRequest.getProjectCode());
		}
		if (null != updateRequest.getPurposeOfTravel()) {
			mainRequestBO.setPurposeOfTravel(updateRequest.getPurposeOfTravel());
		}
		if (null != updateRequest.getIsos()) {
			mainRequestBO.setIsos(updateRequest.getIsos());
		}
		if (null != updateRequest.getTypeOfProject()) {
			mainRequestBO.setTypeOfProject(updateRequest.getTypeOfProject());
		}
		if (null != updateRequest.getEmployeeLocation()) {
			mainRequestBO.setEmployeeLocation(updateRequest.getEmployeeLocation());
		}
		if (null != updateRequest.getMobileNumber()) {
			mainRequestBO.setMobileNumber(updateRequest.getMobileNumber());
		}
		if (null != updateRequest.getCurrentStatus()) {
			mainRequestBO.setCurrentStatus(updateRequest.getCurrentStatus());
		}
		
			
		
		mainRequestBO.setModifiedOn(new Date());
		//mainRequestBO.setCurrentStatus(TravelConstants.L1_PENDING);
		

		// EmployeeDetailsBO employeeDetailsBO =
		// TravelBeanUtils.EmployeeDetailsVO2EmployeeDetailsBO(empDetailsVO);
		/*
		 * FlightDetailsBO flightDetailsBO =
		 * TravelBeanUtils.FlightDetailsVO2FlightDetailsBO(flightDetailsVO);
		 * CabDetailsBO cabDetailsBO =
		 * TravelBeanUtils.CabDetailsVO2CabDetailsBO(cabDetailsVO); ForexDetailsBO
		 * forexDetailsBO =
		 * TravelBeanUtils.ForexDetailsVO2ForexDetailsBO(forexDetailsVO);
		 * AccomodationDetailsBO accomodationDetailsBO =
		 * TravelBeanUtils.AccomodationDetailsVO2AccomodationDetailsBO(accomoDetailsVO);
		 */

		if (flightDetailsVO != null) {
			
			if (mainRequestBO.getFlightDetailsBO() == null) {
				mainRequestBO.setFlightDetailsBO(new FlightDetailsBO());
				mainRequestBO.getFlightDetailsBO().setIsActive(true);
				mainRequestBO.getFlightDetailsBO().setCreatedOn(new Date());
			}
			flightDetailsVO.setCreatedOn(mainRequestBO.getFlightDetailsBO().getCreatedOn());
			flightDetailsVO.setModifiedOn(new Date());
			flightDetailsVO.setIsActive(true);
			BeanUtils.copyProperties(flightDetailsVO, mainRequestBO.getFlightDetailsBO());
		}
		else{
			if(mainRequestBO.getFlightDetailsBO()!=null){
				mainRequestBO.getFlightDetailsBO().setIsActive(false);
			}
			
		}

		if (cabDetailsVO != null) {
			
			if (mainRequestBO.getCabDetailsBO() == null) {
				mainRequestBO.setCabDetailsBO(new CabDetailsBO());
				mainRequestBO.getCabDetailsBO().setCreatedOn(new Date());
				mainRequestBO.getCabDetailsBO().setIsActive(true);
			}
			cabDetailsVO.setCreatedOn(mainRequestBO.getCabDetailsBO().getCreatedOn());
			cabDetailsVO.setModifiedOn(new Date());
			cabDetailsVO.setIsActive(true);
			BeanUtils.copyProperties(cabDetailsVO, mainRequestBO.getCabDetailsBO());
		}else{
			if(mainRequestBO.getCabDetailsBO()!=null){
				mainRequestBO.getCabDetailsBO().setIsActive(false);
			}
		}

		if (forexDetailsVO != null) {
			
			if (mainRequestBO.getForexDetailsBO() == null) {
				mainRequestBO.setForexDetailsBO(new ForexDetailsBO());
				mainRequestBO.getForexDetailsBO().setCreatedOn(new Date());
				mainRequestBO.getForexDetailsBO().setIsActive(true);
			}
			forexDetailsVO.setCreatedOn(mainRequestBO.getForexDetailsBO().getCreatedOn());
			forexDetailsVO.setModifiedOn(new Date());
			forexDetailsVO.setIsActive(true);
			BeanUtils.copyProperties(forexDetailsVO, mainRequestBO.getForexDetailsBO());
		}else{
			if(mainRequestBO.getForexDetailsBO()!=null){
			mainRequestBO.getForexDetailsBO().setIsActive(false);
			}
		}

		if (accomoDetailsVO != null) {
			
			if (mainRequestBO.getAccomoDetailsBO() == null) {
				mainRequestBO.setAccomoDetailsBO(new AccomodationDetailsBO());
				mainRequestBO.getAccomoDetailsBO().setCreatedOn(new Date());
				mainRequestBO.getAccomoDetailsBO().setIsActive(true);
			}
			accomoDetailsVO.setCreatedOn(mainRequestBO.getAccomoDetailsBO().getCreatedOn());
			accomoDetailsVO.setModifiedOn(new Date());
			accomoDetailsVO.setIsActive(true);
			BeanUtils.copyProperties(accomoDetailsVO, mainRequestBO.getAccomoDetailsBO());
		}
		else{
			if(mainRequestBO.getAccomoDetailsBO()!=null){
				mainRequestBO.getAccomoDetailsBO().setIsActive(false);
			}
		}

		if (visaRequestVO != null) {
			
			if (mainRequestBO.getVisaRequestBO() == null) {
				mainRequestBO.setVisaRequestBO(new VisaRequestBO());
				mainRequestBO.getVisaRequestBO().setCreatedOn(new Date());
				mainRequestBO.getVisaRequestBO().setIsActive(true);
			}
			visaRequestVO.setCreatedOn(mainRequestBO.getVisaRequestBO().getCreatedOn());
			visaRequestVO.setModifiedOn(new Date());
			visaRequestVO.setIsActive(true);
			BeanUtils.copyProperties(visaRequestVO, mainRequestBO.getVisaRequestBO());
		}
		else{
			if(mainRequestBO.getVisaRequestBO()!=null){
				mainRequestBO.getVisaRequestBO().setIsActive(false);
			}
		}

		/*
		 * mainRequestBO.setAccomoDetailsBO(accomodationDetailsBO);
		 * mainRequestBO.setCabDetailsBO(cabDetailsBO);
		 * mainRequestBO.setForexDetailsBO(forexDetailsBO);
		 * mainRequestBO.setFlightDetailsBO(flightDetailsBO);
		 */
		// mainRequestBO.setEmployeeDetailsBO(employeeDetailsBO);
		logger.info("MainRequestServiceImpl : updateMainRequestBO: Ended");
		return mainRequestBO;
	}

	/**
	 * This method will create main travel request object that will return
	 * 
	 * @param newRequest
	 * @return MainRequestBO object
	 * @throws ParseException
	 */
	public MainRequestBO createMainRequestBO(MainRequestVO newRequest) throws ParseException {
		logger.info("MainRequestServiceImpl : createMainRequestBO: Started");
		MainRequestBO mainRequestBO = new MainRequestBO();
		AccomodationDetailsVO accomoDetailsVO = newRequest.getAccomodationRequest();
		FlightDetailsVO flightDetailsVO = newRequest.getFlightRequest();
		CabDetailsVO cabDetailsVO = newRequest.getCabRequest();
		ForexDetailsVO forexDetailsVO = newRequest.getForexRequest();
		VisaRequestVO visaRequestVO = newRequest.getVisaRequestVO();

		// TODO : Do validation - Validate All request data
		/*
		 * mainRequestBO =
		 * EmployeeDetailsValidator.validateEmployeeDetails(mainRequestBO,
		 * empDetailsVO); mainRequestBO =
		 * CabDetailsValidator.validateCabDetails(mainRequestBO, cabDetailsVO);
		 * mainRequestBO = FlightDetailsValidator.validateFlightDetails(mainRequestBO,
		 * flightDetailsVO); mainRequestBO =
		 * ForexDetailsValidator.validateForexDetails(mainRequestBO, forexDetailsVO);
		 * mainRequestBO =
		 * AccomodationDetailsValidator.validateAccomodationDetails(mainRequestBO,
		 * accomoDetailsVO);
		 */

		/* generate Random request Id */
		Integer generatedRequestId = generateRandomRequestId();

		mainRequestBO.setRequestId(generatedRequestId);

		if (null != newRequest.getRequestedBy()) {
			mainRequestBO.setRequestedBy(newRequest.getRequestedBy());
		}
		if (null != newRequest.getRequestedFor() && !newRequest.	getRequestedFor().isEmpty()) {
			mainRequestBO.setRequestedFor(newRequest.getRequestedFor());
		}

		if (null != newRequest.getApproverId()) {
			mainRequestBO.setApproverId(newRequest.getApproverId());
		}

		if (null != newRequest.getRemark() && !newRequest.getRemark().isEmpty()) {
			mainRequestBO.setRemark(newRequest.getRemark());
		}
		if (null != newRequest.getBillable()) {
			mainRequestBO.setBillable(newRequest.getBillable());
		}
		if (null != newRequest.getRequestType()) {
			mainRequestBO.setRequestType(newRequest.getRequestType());
		}
		if (null != newRequest.getProjectCode()) {
			mainRequestBO.setProjectCode(newRequest.getProjectCode());
		}
		if (null != newRequest.getProjectName()) {
			mainRequestBO.setProjectName(newRequest.getProjectName());
		}
		if (null != newRequest.getCurrentStatus()) {
			mainRequestBO.setCurrentStatus(newRequest.getCurrentStatus());
		}
		if (null != newRequest.getTourType()) {
			mainRequestBO.setTourType(newRequest.getTourType());
		}
		if (null != newRequest.getActivityCode()) {
			mainRequestBO.setActivityCode(newRequest.getActivityCode());
		}
		if (null != newRequest.getTypeOfProject()) {
			mainRequestBO.setTypeOfProject(newRequest.getTypeOfProject());
		}
		if (null != newRequest.getPurposeOfTravel()) {
			mainRequestBO.setPurposeOfTravel(newRequest.getPurposeOfTravel());
		}
		if (null != newRequest.getIsos()) {
			mainRequestBO.setIsos(newRequest.getIsos());
		}
		if (null != newRequest.getInsurance()) {
			mainRequestBO.setInsurance(newRequest.getInsurance());
		}
		if (null != newRequest.getEmployeeLocation()) {
			mainRequestBO.setEmployeeLocation(newRequest.getEmployeeLocation());
		}
		if (null != newRequest.getMobileNumber()) {
			mainRequestBO.setMobileNumber(newRequest.getMobileNumber());
		}
		

		mainRequestBO.setCreatedOn(new Date());

		if (accomoDetailsVO != null) {
			accomoDetailsVO.setIsActive(true);
			accomoDetailsVO.setCreatedOn(new Date());
			AccomodationDetailsBO accomodationDetailsBO = TravelBeanUtils
					.AccomodationDetailsVO2AccomodationDetailsBO(accomoDetailsVO);
			mainRequestBO.setAccomoDetailsBO(accomodationDetailsBO);
		}

		if (flightDetailsVO != null) {
			flightDetailsVO.setIsActive(true);
			flightDetailsVO.setCreatedOn(new Date());
			FlightDetailsBO flightDetailsBO = TravelBeanUtils.FlightDetailsVO2FlightDetailsBO(flightDetailsVO);
			mainRequestBO.setFlightDetailsBO(flightDetailsBO);
		}

		if (cabDetailsVO != null) {
			cabDetailsVO.setIsActive(true);
			cabDetailsVO.setCreatedOn(new Date());
			CabDetailsBO cabDetailsBO = TravelBeanUtils.CabDetailsVO2CabDetailsBO(cabDetailsVO);
			mainRequestBO.setCabDetailsBO(cabDetailsBO);
		}

		if (forexDetailsVO != null) {
			forexDetailsVO.setIsActive(true);
			forexDetailsVO.setCreatedOn(new Date());
			ForexDetailsBO forexDetailsBO = TravelBeanUtils.ForexDetailsVO2ForexDetailsBO(forexDetailsVO);
			mainRequestBO.setForexDetailsBO(forexDetailsBO);
		}

		if (visaRequestVO != null) {
			visaRequestVO.setIsActive(true);
			visaRequestVO.setCreatedOn(new Date());
			VisaRequestBO visaRequestBO = TravelBeanUtils.VisaRequestVO2VisaRequestBO(visaRequestVO);
			mainRequestBO.setVisaRequestBO(visaRequestBO);
		}

		/*
		 * accomodationDetailsBO.setMainRequestBO(mainRequestBO);
		 * cabDetailsBO.setMainRequestBO(mainRequestBO);
		 * forexDetailsBO.setMainRequestBO(mainRequestBO);
		 */

		// mainRequestBO.setEmployeeDetailsBO(employeeDetailsBO);
		logger.info("MainRequestServiceImpl : createMainRequestBO: Ended");
		return mainRequestBO;
	}

	/**
	 * this method will generate random request Id for creating travel request
	 * 
	 * @return Integer
	 */
	private Integer generateRandomRequestId() {
		//Integer requestId = (int) (Math.random() * 100000);
		//Integer requestId=Integer.parseInt(UUID.randomUUID().toString());
		UUID myuuid = UUID.randomUUID();
		long highbits = myuuid.getMostSignificantBits();
		String uid=String.valueOf(highbits);
		Integer requestId=Integer.parseInt(uid.substring(0,6).replace("-", "1"));
		return requestId;
	}

	/**
	 * this method will add entry in request_history table
	 * 
	 * @methodName insertRequestHistoryLog
	 * @param RequestHistoryLogBO
	 * @return Integer
	 * @throws TravelServiceException
	 */

	@Override
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws TravelServiceException {
		logger.info("MainRequestServiceImpl : insertRequestHistoryLog: Started");
		Integer i = 0;
		try {
			i = mainRequestDAO.insertRequestHistoryLog(requestHistoryLogBO);
			logger.info("MainRequestServiceImpl : insertRequestHistoryLog: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : insertRequestHistoryLog: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return i;
	}

/*	*//**
	 * @author e5544354 this method will get all entries in request_history table
	 * @methodName getAllRequestHistoryLogs
	 * @return List<RequestHistoryLogVO>
	 * @throws TravelServiceException
	 *//*
	@Override
	public List<RequestHistoryLogVO> getAllRequestHistoryLogs() throws TravelServiceException {
		logger.info("MainRequestServiceImpl : getAllRequestHistoryLogs: Started");
		List<RequestHistoryLogVO> listVO = null;
		try {
			List<RequestHistoryLogBO> listBO = mainRequestDAO.getAllRequestHistoryLogs();
			if (listBO != null && listBO.size() > 0) {
				listVO = new ArrayList<RequestHistoryLogVO>();
				for (RequestHistoryLogBO requestHistoryLogBO : listBO) {
					RequestHistoryLogVO requestHistoryLogVO = new RequestHistoryLogVO();
					BeanUtils.copyProperties(requestHistoryLogBO, requestHistoryLogVO);
					listVO.add(requestHistoryLogVO);
				}
			}
			logger.info("MainRequestServiceImpl : getAllRequestHistoryLogs: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getAllRequestHistoryLogs: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}

	*//**
	 * this method will get all entries in request_history table by request_id
	 * 
	 * @author e5544354
	 * @methodName getRequestHistoryLogsByRequestId
	 * @param int
	 * @return List<RequestHistoryLogVO>
	 * @throws TravelServiceException
	 *//*
	@Override
	public List<RequestHistoryLogVO> getRequestHistoryLogsByRequestId(int requestId) throws TravelServiceException {
		logger.info("MainRequestServiceImpl : getRequestHistoryLogsByRequestId: Started");
		List<RequestHistoryLogVO> listVO = null;
		try {
			List<RequestHistoryLogBO> listBO = mainRequestDAO.getRequestHistoryLogsByRequestId(requestId);
			if (listBO != null && listBO.size() > 0) {
				listVO = new ArrayList<RequestHistoryLogVO>();
				for (RequestHistoryLogBO requestHistoryLogBO : listBO) {
					RequestHistoryLogVO requestHistoryLogVO = TravelBeanUtils
							.requestHistoryLogBO2RequestHistoryLogVO(requestHistoryLogBO);
					listVO.add(requestHistoryLogVO);
				}
			}
			logger.info("MainRequestServiceImpl : getRequestHistoryLogsByRequestId: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getRequestHistoryLogsByRequestId: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}

	*//**
	 * this method will get all entries in request_history table by updated_by
	 * 
	 * @author e5544354
	 * @methodName getRequestHistoryLogsByUpdatedBy
	 * @param int
	 * @return List<RequestHistoryLogVO>
	 * @throws TravelServiceException
	 *//*
	@Override
	public List<RequestHistoryLogVO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws TravelServiceException {
		logger.info("MainRequestServiceImpl : getRequestHistoryLogsByUpdatedBy: Started");
		List<RequestHistoryLogVO> listVO = null;
		try {
			List<RequestHistoryLogBO> listBO = mainRequestDAO.getRequestHistoryLogsByUpdatedBy(updatedBy);
			if (listBO != null && listBO.size() > 0) {
				listVO = new ArrayList<RequestHistoryLogVO>();
				for (RequestHistoryLogBO requestHistoryLogBO : listBO) {
					RequestHistoryLogVO requestHistoryLogVO = TravelBeanUtils
							.requestHistoryLogBO2RequestHistoryLogVO(requestHistoryLogBO);
					listVO.add(requestHistoryLogVO);
				}
			}
			logger.info("MainRequestServiceImpl : getRequestHistoryLogsByUpdatedBy: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getRequestHistoryLogsByUpdatedBy: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}*/

	/**
	 * this method will get create RequestHistoryLogBO object for new travel request
	 * 
	 * @author e5544354
	 * @methodName createRequestHistoryLogNew
	 * @param MainRequestBO
	 * @return RequestHistoryLogBO
	 */
	private RequestHistoryLogBO createRequestHistoryLogNew(MainRequestBO mainRequestBO) {
		logger.info("MainRequestServiceImpl : createRequestHistoryLogNew: Started");
		RequestHistoryLogBO requestHistoryLogBO = new RequestHistoryLogBO();
		requestHistoryLogBO.setCreatedOn(new Date());
		requestHistoryLogBO.setRequestId(mainRequestBO.getRequestId());
		requestHistoryLogBO.setStatus(mainRequestBO.getCurrentStatus());
		requestHistoryLogBO.setUpdatedBy(mainRequestBO.getRequestedBy());
		requestHistoryLogBO.setRemarks(mainRequestBO.getRemark());
		logger.info("MainRequestServiceImpl : createRequestHistoryLogNew: Ended");
		return requestHistoryLogBO;
	}


	/**
	 * this method will get user requests by user id(eg. Requested by)
	 * 
	 * @author e5545730
	 * @methodName getRequestDetailsByRequestedId
	 * @param MainRequestVO
	 * @return list
	 */

	@Override
	public List<RequestListVO> getAllRequestByUserId(String requestedBy) throws TravelServiceException {
		logger.info("MainRequestServiceImpl : getRequestDetailsByRequestedId: Started");
		List<RequestListVO> listVO = new ArrayList<RequestListVO>();
		MainRequestVO mainRequestVo = null;
		try {
			listVO = mainRequestDAO.getRequestDetailsByUserId(Integer.parseInt(requestedBy));
			//if (allRequestList != null && allRequestList.size() > 0) {
				/*
				for (MainRequestBO requestDetailsBO : allRequestList) {
					mainRequestVo = new MainRequestVO();
					mainRequestVo = TravelBeanUtils.mainRequestBO2MainRequestVO(requestDetailsBO);
					if (requestDetailsBO.getFlightDetailsBO() != null) {
						mainRequestVo.setFlightRequest(
								TravelBeanUtils.FlightDetailsBO2FlightDetailsVO(requestDetailsBO.getFlightDetailsBO()));
					}
					if (requestDetailsBO.getCabDetailsBO() != null) {
						mainRequestVo.setCabRequest(
								TravelBeanUtils.CabDetailsBO2CabDetailsVO(requestDetailsBO.getCabDetailsBO()));
					}
					if (requestDetailsBO.getAccomoDetailsBO() != null) {
						mainRequestVo.setAccomodationRequest(TravelBeanUtils
								.AccomodationDetailsBO2AccomodationDetailsVO(requestDetailsBO.getAccomoDetailsBO()));
					}
					if (requestDetailsBO.getForexDetailsBO() != null) {
						mainRequestVo.setForexRequest(
								TravelBeanUtils.ForexDetailsBO2ForexDetailsVO(requestDetailsBO.getForexDetailsBO()));
					}
					if (requestDetailsBO.getVisaRequestBO() != null) {
						mainRequestVo.setVisaRequestVO(
								TravelBeanUtils.VisaRequestBO2VisaRequestVO(requestDetailsBO.getVisaRequestBO()));
					}
					
					// to get employee details
					if (requestDetailsBO.getRequestedBy() != null) {
						mainRequestVo.getEmpData().add(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(
								empDao.getEmployeeDetails(requestDetailsBO.getRequestedBy())));
					}
					// to get approver details
					if (requestDetailsBO.getApproverId() != null) {
						mainRequestVo.getEmpData().add(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(
								empDao.getEmployeeDetails(requestDetailsBO.getApproverId())));
					}

					// setting the comment list
					List<RequestHistoryLogVO> commentList = getRequestHistoryList(requestDetailsBO.getRequestId());
					mainRequestVo.setCommentList(commentList);
					listVO.add(mainRequestVo);
				}
*/
			//}
			logger.info("MainRequestServiceImpl : getRequestHistoryLogsByRequestId: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getRequestHistoryLogsByRequestId: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}
	
	/**
	 * this method will get user requests by Travel user id
	 * 
	 * @author 12219
	 * @methodName getAllRequestByTravelStatus
	 * @param MainRequestVO
	 * @return list
	 */

	@Override
	public TravelRequestWrapper getAllRequestByTravelStatus() throws TravelServiceException {
		
		//List<MainRequestBO> mainRequestBO = null;
		TravelRequestWrapper travelRequestWrapper = null;
		try {
			logger.info("MainRequestServiceImpl : getAllRequestByTravelStatus: Started");
			travelRequestWrapper = mainRequestDAO.getRequestDetailsByTravelStatus();
			logger.info("MainRequestServiceImpl : getRequestByTravelStatus: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getRequestByTravelStatus: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return travelRequestWrapper;
	}
	/**
	 * this method will get user requests by HC user id
	 * 
	 * @author 12219
	 * @methodName getAllRequestByHcStatus
	 * @param HcViewBO
	 * @return list
	 */
	@Override
	public List<HcViewBO> getAllRequestByHcStatus() throws TravelServiceException {
		
		List<HcViewBO> hcRequestBO = null;
		try {
			logger.info("MainRequestServiceImpl : getRequestDetailsByHcStatus: Started");
			hcRequestBO = mainRequestDAO.getRequestDetailsByHcStatus();
			logger.info("MainRequestServiceImpl : getRequestDetailsByHcStatus: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getRequestDetailsByHcStatus: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return hcRequestBO;
	}
	/**
	 * this method will get user requests by Finance user id
	 * 
	 * @author 12219
	 * @methodName getAllRequestOfFiance
	 * @param FinViewBO
	 * @return list
	 */
	@Override
	public List<FinViewBO> getAllRequestOfFinance() throws TravelServiceException {
		
		List<FinViewBO> FinRequestBO = null;
		try {
			logger.info("MainRequestServiceImpl : getAllRequestOfFinance: Started");
			FinRequestBO = mainRequestDAO.getRequestDetailsByFinance();
			logger.info("MainRequestServiceImpl : getAllRequestOfFinance: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getAllRequestOfFinance: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return FinRequestBO;
	}


	/**
	 * this method will validate all the travel request objects
	 * 
	 * @author e5545565
	 * @methodName validateTravelRequestObject
	 * @param MainRequestVO
	 * @return ResponseEntity
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity validateTravelRequestObject(MainRequestVO newRequest) {
		logger.info("MainRequestServiceImpl : validateTravelRequestObject: Started");
		AccomodationDetailsVO accomoDetailsVO = newRequest.getAccomodationRequest();
		FlightDetailsVO flightDetailsVO = newRequest.getFlightRequest();
		CabDetailsVO cabDetailsVO = newRequest.getCabRequest();
		ForexDetailsVO forexDetailsVO = newRequest.getForexRequest();
		VisaRequestVO visaRequestVO = newRequest.getVisaRequestVO();

		ForexDetailsValidator frxValidator = new ForexDetailsValidator();
		CabDetailsValidator cabValidator = new CabDetailsValidator();
		FlightDetailsValidator flightValidator = new FlightDetailsValidator();
		AccomodationDetailsValidator accomodationValidator = new AccomodationDetailsValidator();
		VisaDetailsValidator visaValidator = new VisaDetailsValidator();
		allErrors = new ArrayList();

		if (forexDetailsVO != null || cabDetailsVO != null || accomoDetailsVO != null || flightDetailsVO != null
				|| visaRequestVO != null) {
			logger.info("MainRequestServiceImpl : validateTravelRequestObject: Started Validations");
			// Validating ForexDetailsVO
			validateVO(forexDetailsVO, frxValidator);
			// Validating CabDetailsVO
			validateVO(cabDetailsVO, cabValidator);
			// Validating FlightDetailsVO
			validateVO(flightDetailsVO, flightValidator);
			// Validating AccomodationDetailsVO
			validateVO(accomoDetailsVO, accomodationValidator);
			// validating VisaDetailsVO
			validateVO(visaRequestVO, visaValidator);
		}
		if (!allErrors.isEmpty()) {
			return new ResponseEntity(allErrors, HttpStatus.NOT_ACCEPTABLE);
		} else {
			logger.info("MainRequestServiceImpl : validateTravelRequestObject: Ended");
			return null;
		}
	}

	/**
	 * This method validates the fields of Model Objects(e.g. ForexDetailsVO,
	 * CabDetailsVO)
	 * 
	 * @param voObject
	 * @param validator
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void validateVO(Object voObject, Validator validator) {
		logger.info("MainRequestServiceImpl : validateVO: Started");
		if (voObject != null) {
			DataBinder dataBinder = new DataBinder(voObject);
			dataBinder.setValidator(validator);
			dataBinder.validate();
			BindingResult result = dataBinder.getBindingResult();
			List<FieldError> errors = new ArrayList();
			if (result.hasErrors() == true) {
				// error exists
				errors = result.getFieldErrors();
				for (FieldError error : errors) {
					System.out.println(error.getObjectName() + " - " + error.getCode());
				}
				logger.info("MainRequestServiceImpl : validateVO: Ended");
				allErrors.addAll(errors);
			}
		}
	}

	/**
	 * this method will get create request entry for update travel request
	 * 
	 * @author e5544354
	 * @methodName createApproverRequestHistoryLog
	 * @param MainRequestBO
	 * @return RequestHistoryLogBO
	 */
	public RequestHistoryLogBO createApproverRequestHistoryLog(MainRequestBO oldRequest, MainRequestBaseVO newRequest,
			int oldId) {
		logger.info("MainRequestServiceImpl : createApproverRequestHistoryLog: Started");
		RequestHistoryLogBO requestHistoryLogBO = new RequestHistoryLogBO();
		requestHistoryLogBO.setRequestId(oldRequest.getRequestId());
		requestHistoryLogBO.setCreatedOn(new Date());
		// TODO: updatedBy to be taken from session (currently logged in user)
		requestHistoryLogBO.setUpdatedBy(oldId);
		requestHistoryLogBO.setStatus(newRequest.getCurrentStatus());
		requestHistoryLogBO.setRemarks(newRequest.getRemark());
		logger.info("MainRequestServiceImpl : createApproverRequestHistoryLog: Started");
		return requestHistoryLogBO;
	}

	public boolean tokenValidation(int tokenId, String capcoUserId) throws TravelServiceException {

		boolean employeeTokenValidated = empDao.tokenValidation(tokenId, capcoUserId);

		return employeeTokenValidated;
	}

	@Override
	public List<MainRequestVO> getLatestApprovedRequest(Integer requestedBy) throws TravelServiceException {
		logger.info("MainRequestServiceImpl : getLatestApprovedRequest: Started");
		List<MainRequestVO> listVO = new ArrayList<MainRequestVO>();
		MainRequestVO mainRequestVo = null;
		try {
			List<MainRequestBO> allRequestList = mainRequestDAO.getLatestApprovedRequest(requestedBy);
			if (allRequestList != null && allRequestList.size() > 0) {
				for (MainRequestBO requestDetailsBO : allRequestList) {
					mainRequestVo = new MainRequestVO();
					mainRequestVo = TravelBeanUtils.mainRequestBO2MainRequestVO(requestDetailsBO);
					if (requestDetailsBO.getFlightDetailsBO() != null) {
						mainRequestVo.setFlightRequest(
								TravelBeanUtils.FlightDetailsBO2FlightDetailsVO(requestDetailsBO.getFlightDetailsBO()));
					}
					if (requestDetailsBO.getCabDetailsBO() != null) {
						mainRequestVo.setCabRequest(
								TravelBeanUtils.CabDetailsBO2CabDetailsVO(requestDetailsBO.getCabDetailsBO()));
					}
					if (requestDetailsBO.getAccomoDetailsBO() != null) {
						mainRequestVo.setAccomodationRequest(TravelBeanUtils
								.AccomodationDetailsBO2AccomodationDetailsVO(requestDetailsBO.getAccomoDetailsBO()));
					}
					if (requestDetailsBO.getForexDetailsBO() != null) {
						mainRequestVo.setForexRequest(
								TravelBeanUtils.ForexDetailsBO2ForexDetailsVO(requestDetailsBO.getForexDetailsBO()));
					}
					if (requestDetailsBO.getVisaRequestBO() != null) {
						mainRequestVo.setVisaRequestVO(
								TravelBeanUtils.VisaRequestBO2VisaRequestVO(requestDetailsBO.getVisaRequestBO()));
					}
					
					// to get employee details
					if (requestDetailsBO.getRequestedBy() != null) {
						mainRequestVo.getEmpData().add(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(
								empDao.getEmployeeDetails(requestDetailsBO.getRequestedBy())));
					}
					// to get approver details
					if (requestDetailsBO.getApproverId() != null) {
						mainRequestVo.getEmpData().add(TravelBeanUtils.EmployeeDetailsBO2EmployeeDetailsVO(
								empDao.getEmployeeDetails(requestDetailsBO.getApproverId())));
					}

					// setting the comment list
					List<RequestHistoryLogVO> commentList = getRequestHistoryList(requestDetailsBO.getRequestId());
					mainRequestVo.setCommentList(commentList);

					listVO.add(mainRequestVo);
				}

			}
			logger.info("MainRequestServiceImpl : getLatestApprovedRequest: Ended");
		} catch (DAOException ex) {
			logger.error("MainRequestServiceImpl : getLatestApprovedRequest: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}

	/**
	 * this method will trigger email notification in different thread
	 * 
	 * @author e5544354
	 * @methodName sendEmailNotification
	 * @param int
	 * @return void
	 */
	public void sendEmailNotification(int requestId, String changeDone) {
		logger.info("MainRequestServiceImpl : sendEmailNotification: started");
		ExecutorService emailExecutor = Executors.newFixedThreadPool(10);
		
		Runnable sendMail = () -> {
			try {
				emailNotificationService.sendEmailNotification(requestId, changeDone);
			} catch (TravelServiceException e) {
				logger.info("MainRequestServiceImpl : sendEmailNotification: Exception caught: " + e);
			}
		};
		
		emailExecutor.execute(sendMail);
		emailExecutor.shutdown();
		logger.info("MainRequestServiceImpl : sendEmailNotification: ended");
	}
	
	/**
	 * this method will create the comment list from request history logs
	 * 
	 * @author e5544354
	 * @methodName getRequestHistoryList
	 * @param int
	 * @return List<RequestHistoryLogVO>
	 */
	private List<RequestHistoryLogVO> getRequestHistoryList(int requestId){
		logger.info("MainRequestServiceImpl : getRequestHistoryList: Started");
		List<RequestHistoryLogVO> commentList = new ArrayList<>();
		
		List<Object[]> list;
		try {
			list = mainRequestDAO.getRequestHistoryCommentsByRequestId(requestId);
			for(Object[] row: list) {
				RequestHistoryLogVO requestHistoryLogVO = new RequestHistoryLogVO();
				if(row[0]!=null) {
					requestHistoryLogVO.setRequestId(Integer.parseInt(row[0].toString()));
				}
				
				if(row[1]!=null) {
					requestHistoryLogVO.setCreatedOn((Date) row[1]);
				}
				
				if(row[2]!=null) {
					requestHistoryLogVO.setStatus(row[2].toString());
				}
				
				if(row[3]!=null) {
					requestHistoryLogVO.setRemarks(row[3].toString());
				}
				
				if(row[4]!=null) {
					requestHistoryLogVO .setUpdatedBy(Integer.parseInt(row[4].toString()));
				}
				
				if(row[5]!=null) {
					requestHistoryLogVO.setUpdaterName(row[5].toString());
				}
				
				commentList.add(requestHistoryLogVO);
				
			}
		} catch (DAOException e) {
			logger.error("MainRequestServiceImpl : getRequestHistoryList: Exception caught "+e);
		}
		
		logger.info("MainRequestServiceImpl : getRequestHistoryList: Ended");
		return commentList;
	}
	
	
	

	/*
	 * private MainRequestBO createTestMainRequest() { java.util.Date dNow = new
	 * java.util.Date(); String dateInString = new SimpleDateFormat
	 * ("yyyy-MM-dd HH:MM:ss").format(dNow); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date parsedDate = new Date(); try {
	 * parsedDate = formatter.parse(dateInString); } catch (ParseException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } MainRequestBO
	 * mainRequestBO = new MainRequestBO();
	 * 
	 * mainRequestBO.setRequestId(generateRandomRequestId());
	 * //mainRequestBO.setRequestedBy("15977");
	 * mainRequestBO.setRequestedFor("15977"); mainRequestBO.setApproverId(16294);
	 * mainRequestBO.setCurrentStatus(TravelConstants.SUBMITTED);
	 * mainRequestBO.setCreatedOn(new java.sql.Date(parsedDate.getTime()));
	 * mainRequestBO.setModifiedOn(new java.sql.Date(parsedDate.getTime()));
	 * EmployeeDetailsBO empDetailsBO = new EmployeeDetailsBO();
	 * empDetailsBO.setEmployeeId(15977);
	 * empDetailsBO.setEmailId("saraswati.khade@capco.com");
	 * empDetailsBO.setEmployeeLocation("Pune");
	 * empDetailsBO.setEmployeeMobileNumber(new Long("9970174436"));
	 * //empDetailsBO.setEmployeeName("Saraswati Khade");
	 * empDetailsBO.setIsApprover(false); empDetailsBO.setProjectCode("8888");
	 * AccomodationDetailsBO accomoDetailsBO = new AccomodationDetailsBO();
	 * FlightDetailsBO flightDetailsBO = new FlightDetailsBO(); CabDetailsBO
	 * cabDetailsBO = new CabDetailsBO(); ForexDetailsBO forexDetailsBO = new
	 * ForexDetailsBO(); flightDetailsBO.setBusinessPurpose("traininf");
	 * flightDetailsBO.setCreatedOn(new java.sql.Date(parsedDate.getTime()));
	 * flightDetailsBO.setDateOfBirth(new java.sql.Date(parsedDate.getTime()));
	 * flightDetailsBO.setDepartureDate(new java.sql.Date(parsedDate.getTime()));
	 * flightDetailsBO.setDepartureLocation("Pune");
	 * flightDetailsBO.setDestinationLocation("Bangalore");
	 * flightDetailsBO.setGivenName("Saraswati");
	 * flightDetailsBO.setIssuingAuthority("Passport Officer");
	 * flightDetailsBO.setIsValidVisa(1); flightDetailsBO.setMealPref("veg");
	 * flightDetailsBO.setModifiedOn(new java.sql.Date(parsedDate.getTime()));
	 * flightDetailsBO.setNationality("Indian");
	 * flightDetailsBO.setPassportExpiryDate(new
	 * java.sql.Date(parsedDate.getTime()));
	 * flightDetailsBO.setPassportIssuePlace("Pune");
	 * flightDetailsBO.setPassportNumber("H2855908");
	 * flightDetailsBO.setPrefClass("Business");
	 * //flightDetailsBO.setRequestId(mainRequestBO.getRequestId());
	 * //flightDetailsBO.setReturnDateTime(new Timestamp(parsedDate.getTime()));
	 * flightDetailsBO.setReturnDate(new java.sql.Date(parsedDate.getTime()));
	 * flightDetailsBO.setReturnTime("10:00");
	 * flightDetailsBO.setSeatPref("Window"); flightDetailsBO.setSurname("Khade");
	 * flightDetailsBO.setTourType(1); flightDetailsBO.setTravelType(1);
	 * flightDetailsBO.setDepartureTime("10:00");
	 * flightDetailsBO.setRemarks("test");
	 * 
	 * accomoDetailsBO.setAccomodationType("International");
	 * accomoDetailsBO.setBudget("2000"); accomoDetailsBO.setCategory("Hotel");
	 * accomoDetailsBO.setCheckIn(parsedDate);
	 * accomoDetailsBO.setCheckOut(parsedDate);
	 * accomoDetailsBO.setCity("Bangalore"); accomoDetailsBO.setCountry("India");
	 * accomoDetailsBO.setCreatedOn(new Timestamp(parsedDate.getTime()));
	 * accomoDetailsBO.setCurrency("INR");
	 * //accomoDetailsBO.setCurrentStatus(TravelConstants.SUBMITTED);
	 * accomoDetailsBO.setModifiedOn(new Timestamp(parsedDate.getTime()));
	 * accomoDetailsBO.setRemarks("new request");
	 * //accomoDetailsBO.setRequestId(mainRequestBO.getRequestId());
	 * accomoDetailsBO.setRoomType("Single");
	 * 
	 * cabDetailsBO.setCarType("Sedan"); cabDetailsBO.setCity("Bangalore");
	 * cabDetailsBO.setCountry("India"); cabDetailsBO.setDropLocation("CAPCO");
	 * cabDetailsBO.setFromDate(parsedDate); cabDetailsBO.setFullDay(true);
	 * cabDetailsBO.setNumberOfPersons(2); cabDetailsBO.setPickUpLocation("Pune");
	 * cabDetailsBO.setPickUpTime("10.00"); cabDetailsBO.setRemarks("New cab");
	 * //cabDetailsBO.setRequestId(mainRequestBO.getRequestId());
	 * cabDetailsBO.setTaxiTravelType("local"); cabDetailsBO.setToDate(parsedDate);
	 * 
	 * forexDetailsBO.setForexAmount(2000.00);
	 * forexDetailsBO.setForexBankDesk("Pune");
	 * forexDetailsBO.setForexCollectionCenter("Pune");
	 * forexDetailsBO.setForexCountry("India");
	 * forexDetailsBO.setForexCurrency("INR"); forexDetailsBO.setForexFromDate(new
	 * java.sql.Date(parsedDate.getTime())); forexDetailsBO.setForexNoOfDays(4);
	 * forexDetailsBO.setForexRemarks("forex request");
	 * //forexDetailsBO.setForexRequestId(mainRequestBO.getRequestId());
	 * forexDetailsBO.setForexToDate(new java.sql.Date(parsedDate.getTime()));
	 * 
	 * mainRequestBO.setCabDetailsBO(cabDetailsBO);
	 * mainRequestBO.setAccomoDetailsBO(accomoDetailsBO);
	 * mainRequestBO.setFlightDetailsBO(flightDetailsBO);
	 * //mainRequestBO.setEmployeeDetailsBO(empDetailsBO);
	 * mainRequestBO.setForexDetailsBO(forexDetailsBO); return mainRequestBO;
	 * 
	 * }
	 */
}
