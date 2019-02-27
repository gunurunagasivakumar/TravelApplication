package com.capco.travel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.capco.travel.model.AccomodationDetailsBO;
import com.capco.travel.model.ApproverGroupBO;
import com.capco.travel.model.CabDetailsBO;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.model.FlightDetailsBO;
import com.capco.travel.model.ForexDetailsBO;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.PerDeimBO;
import com.capco.travel.model.ProjectDetailsBO;
import com.capco.travel.model.RequestCountBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.model.VisaRequestBO;
import com.capco.travel.vo.AccomodationDetailsVO;
import com.capco.travel.vo.ApproverGroupVO;
import com.capco.travel.vo.CabDetailsVO;
import com.capco.travel.vo.EmployeeDetailsVO;
import com.capco.travel.vo.FinActionDetailsVO;
import com.capco.travel.vo.FlightDetailsVO;
import com.capco.travel.vo.ForexDetailsVO;
import com.capco.travel.vo.HcActionDetailsVO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.PerDeimVO;
import com.capco.travel.vo.ProjectDetailsVO;
import com.capco.travel.vo.RequestCountVO;
import com.capco.travel.vo.RequestHistoryLogVO;
import com.capco.travel.vo.VisaRequestVO;

public class TravelBeanUtils {
	public static final Logger logger = Logger.getLogger(TravelBeanUtils.class);

	public static AccomodationDetailsVO AccomodationDetailsBO2AccomodationDetailsVO(
			AccomodationDetailsBO accomodationDetailsBO) {
		AccomodationDetailsVO accomodationDetailsVO = new AccomodationDetailsVO();
		BeanUtils.copyProperties(accomodationDetailsBO, accomodationDetailsVO);
		accomodationDetailsVO.setRequestId(accomodationDetailsBO.getMainRequestBO().getRequestId());
	
		if(accomodationDetailsBO.getCheckIn()!=null){			
				String[] splited = accomodationDetailsBO.getCheckIn().toString().split("\\s+");
				accomodationDetailsVO.setCheckInDate(DateFormat(splited[0]));
				accomodationDetailsVO.setCheckInTime(splited[1]);			
		}
		if(accomodationDetailsBO.getCheckOut()!=null){		
				String[] splited = accomodationDetailsBO.getCheckOut().toString().split("\\s+");
				accomodationDetailsVO.setCheckOutDate(DateFormat(splited[0]));
				accomodationDetailsVO.setCheckOutTime(splited[1]);			
		}
		return accomodationDetailsVO;
	}

	public static AccomodationDetailsBO AccomodationDetailsVO2AccomodationDetailsBO(
			AccomodationDetailsVO accomodationDetailsVO) {
		AccomodationDetailsBO accomodationDetailsBO = new AccomodationDetailsBO();
		BeanUtils.copyProperties(accomodationDetailsVO, accomodationDetailsBO);
		return accomodationDetailsBO;
	}

	public static CabDetailsVO CabDetailsBO2CabDetailsVO(CabDetailsBO cabDetailsBO) {
		CabDetailsVO cabDetailsVO = new CabDetailsVO();
		BeanUtils.copyProperties(cabDetailsBO, cabDetailsVO);
		cabDetailsVO.setRequestId(cabDetailsBO.getMainRequestBO().getRequestId());
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		if(cabDetailsBO.getFromDateAndTime()!=null){
			try {
				String[] splited = cabDetailsBO.getFromDateAndTime().toString().split("\\s+");
				cabDetailsVO.setFromDate(date.parse(splited[0]));
				cabDetailsVO.setFromTime(splited[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return cabDetailsVO;
	}

	public static MainRequestVO mainRequestBO2MainRequestVO(MainRequestBO mainRequestBO) {
		MainRequestVO mainRequestVO = new MainRequestVO();
		BeanUtils.copyProperties(mainRequestBO, mainRequestVO);
		return mainRequestVO;
	}

	public static MainRequestBO mainRequestVO2MainRequestBO(MainRequestVO mainRequestVO) {
		MainRequestBO mainRequestBO = new MainRequestBO();
		BeanUtils.copyProperties(mainRequestVO, mainRequestBO);
		return mainRequestBO;
	}
	public static CabDetailsBO CabDetailsVO2CabDetailsBO(CabDetailsVO cabDetailsVO) {
		CabDetailsBO cabDetailsBO = new CabDetailsBO();
		BeanUtils.copyProperties(cabDetailsVO, cabDetailsBO);
		return cabDetailsBO;
	}

	public static EmployeeDetailsVO EmployeeDetailsBO2EmployeeDetailsVO(EmployeeDetailsBO employeeDetailsBO) {
		EmployeeDetailsVO employeeDetailsVO = new EmployeeDetailsVO();
		BeanUtils.copyProperties(employeeDetailsBO, employeeDetailsVO);
		return employeeDetailsVO;
	}

	public static EmployeeDetailsBO EmployeeDetailsVO2EmployeeDetailsBO(EmployeeDetailsVO employeeDetailsVO) {
		EmployeeDetailsBO employeeDetailsBO = new EmployeeDetailsBO();
		BeanUtils.copyProperties(employeeDetailsVO, employeeDetailsBO);
		return employeeDetailsBO;
	}

	public static FlightDetailsVO FlightDetailsBO2FlightDetailsVO(FlightDetailsBO flightDetailsBO) {
		FlightDetailsVO flightDetailsVO = new FlightDetailsVO();
		BeanUtils.copyProperties(flightDetailsBO, flightDetailsVO);
		flightDetailsVO.setRequestId(flightDetailsBO.getMainRequestBO().getRequestId());
		return flightDetailsVO;
	}

	public static FlightDetailsBO FlightDetailsVO2FlightDetailsBO(FlightDetailsVO flightDetailsVO) {
		FlightDetailsBO flightDetailsBO = new FlightDetailsBO();
		BeanUtils.copyProperties(flightDetailsVO, flightDetailsBO);
		return flightDetailsBO;
	}

	public static ProjectDetailsVO ProjectDetailsBO2ProjectDetailsVO(ProjectDetailsBO projectDetailsBO) {
		ProjectDetailsVO projectDetailsVO = new ProjectDetailsVO();
		BeanUtils.copyProperties(projectDetailsBO, projectDetailsVO);
		return projectDetailsVO;
	}

	public static ForexDetailsBO ForexDetailsVO2ForexDetailsBO(ForexDetailsVO frxDetailsVO) {
		ForexDetailsBO forexDetailsBO = new ForexDetailsBO();
		BeanUtils.copyProperties(frxDetailsVO, forexDetailsBO);
		return forexDetailsBO;
	}

	public static ForexDetailsVO ForexDetailsBO2ForexDetailsVO(ForexDetailsBO forexDetailsBO) {
		ForexDetailsVO forexDetailsVO = new ForexDetailsVO();
		BeanUtils.copyProperties(forexDetailsBO, forexDetailsVO);
		forexDetailsVO.setForexRequestId(forexDetailsBO.getMainRequestBO().getRequestId());
		return forexDetailsVO;
	}

	public static RequestHistoryLogVO requestHistoryLogBO2RequestHistoryLogVO(RequestHistoryLogBO requestHistoryLogBO) {
		RequestHistoryLogVO requestHistoryLogVO = new RequestHistoryLogVO();
		BeanUtils.copyProperties(requestHistoryLogBO, requestHistoryLogVO);
		return requestHistoryLogVO;
	}
	public static MainRequestBaseVO mainRequestBO2MainRequestBaseVO(MainRequestBO  mainRequestBO){
		MainRequestBaseVO mainRequestBaseVO=new MainRequestBaseVO();
		mainRequestBaseVO.setRequestId(mainRequestBO.getRequestId());
		mainRequestBaseVO.setCurrentStatus(mainRequestBO.getCurrentStatus());
		mainRequestBaseVO.setRemark(mainRequestBO.getRemark());
		mainRequestBaseVO.setRequestedBy(mainRequestBO.getRequestedBy());
		mainRequestBaseVO.setRequestedFor(mainRequestBO.getRequestedFor());
		mainRequestBaseVO.setCreatedOn(mainRequestBO.getCreatedOn());
		mainRequestBaseVO.setModifiedOn(mainRequestBO.getModifiedOn());
		mainRequestBaseVO.setApproverId(mainRequestBO.getApproverId());
		mainRequestBaseVO.setBillable(mainRequestBO.getBillable());
		return mainRequestBaseVO;
	}
	public static ApproverGroupVO approverGroupBO2approverGroupVO(ApproverGroupBO approverGroupBO){
		ApproverGroupVO approverGroupVO=new ApproverGroupVO();
		BeanUtils.copyProperties(approverGroupBO, approverGroupVO);
		return approverGroupVO;
	}

	public static VisaRequestBO VisaRequestVO2VisaRequestBO(VisaRequestVO visaRequestVO) {
		VisaRequestBO visaRequestBO = new VisaRequestBO();
		BeanUtils.copyProperties(visaRequestVO, visaRequestBO);
		return visaRequestBO;
	}

	public static VisaRequestVO VisaRequestBO2VisaRequestVO(VisaRequestBO visaRequestBO) {
		VisaRequestVO visaRequestVO = new VisaRequestVO();
		BeanUtils.copyProperties(visaRequestBO, visaRequestVO);
		visaRequestVO.setRequestId(visaRequestBO.getMainRequestBO().getRequestId());
		return visaRequestVO;
	}
	public static RequestCountVO requestCountBO2requestCountVO(RequestCountBO requestCountBO){
		RequestCountVO requestCountVO=new RequestCountVO();
		BeanUtils.copyProperties(requestCountBO, requestCountVO);
		return requestCountVO;
	}
	public static HcActionDetailsVO HcViewBO2HcActionDetailsVO(HcViewBO hcViewBO) {
		HcActionDetailsVO hcActionDetailsVO = new HcActionDetailsVO();
		BeanUtils.copyProperties(hcViewBO, hcActionDetailsVO);
		return hcActionDetailsVO;
	}

	public static HcViewBO HcActionDetailsVO2HcViewBO(HcActionDetailsVO hcActionDetailsVO) {
		HcViewBO hcViewBO = new HcViewBO();
		BeanUtils.copyProperties(hcActionDetailsVO, hcViewBO);
		return hcViewBO;
	}
	public static FinActionDetailsVO FinViewBO2FinActionDetailsVO(FinViewBO finViewBO) {
		FinActionDetailsVO finActionDetailsVO = new FinActionDetailsVO();
		BeanUtils.copyProperties(finViewBO, finActionDetailsVO);
		return finActionDetailsVO;
	}

	public static FinViewBO FinActionDetailsVO2FinViewBO(FinActionDetailsVO finActionDetailsVO) {
		FinViewBO finViewBO = new FinViewBO();
		BeanUtils.copyProperties(finActionDetailsVO, finViewBO);
		return finViewBO;
	}
	
	public static Date DateFormat(String dateStr){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date= dateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.info("TravelBeanUtils: DateFormat: Exception cought "+e);
		}
		return date;
	}
	
	public static PerDeimVO perDeimBO2PerDeimVO(PerDeimBO perDeimListBO) {
		PerDeimVO perDeimListVO = new PerDeimVO();
		BeanUtils.copyProperties(perDeimListBO, perDeimListVO);
		return perDeimListVO;
	}
}

