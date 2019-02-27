package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.AccomodationDAO;
import com.capco.travel.model.AccomodationDetailsBO;
import com.capco.travel.service.AccomodationService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.AccomodationDetailsVO;

/**
 * This class is the implementation for AccomodationService
 * @author e5542274
 *
 */
@Transactional
@Service
public class AccomodationServiceImpl implements AccomodationService {
	private static final Logger logger = Logger.getLogger(AccomodationServiceImpl.class);
	@Autowired
	AccomodationDAO accomodationDAO;

	@Override
	public void insertAccomodationDetails(AccomodationDetailsVO accomodationDetailsVO) throws TravelServiceException {
		logger.info("AccomodationServiceImpl : insertAccomodationDetails: Started");
		try {
			AccomodationDetailsBO accomodationDetailsBO = TravelBeanUtils
					.AccomodationDetailsVO2AccomodationDetailsBO(accomodationDetailsVO);
			accomodationDAO.insertAccomodationDetails(accomodationDetailsBO);
			logger.info("AccomodationServiceImpl : insertAccomodationDetails: Ended");
		} catch (DAOException ex) {
			logger.error("AccomodationServiceImpl : insertAccomodationDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	@Override
	public void updateAccomodationDetails(AccomodationDetailsVO accomodationDetailsVO) throws TravelServiceException {
		logger.info("AccomodationServiceImpl : updateAccomodationDetails: Started");
		try {
			AccomodationDetailsBO accomodationDetailsBO = TravelBeanUtils
					.AccomodationDetailsVO2AccomodationDetailsBO(accomodationDetailsVO);
			accomodationDAO.updateAccomodationDetails(accomodationDetailsBO);
			logger.info("AccomodationServiceImpl : updateAccomodationDetails: Ended");
		} catch (DAOException ex) {
			logger.error("AccomodationServiceImpl : updateAccomodationDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	@Override
	public void deleteAccomodationDetails(int requestId) throws TravelServiceException {

		logger.info("AccomodationServiceImpl : deleteAccomodationDetails: Started");
		try {
			accomodationDAO.deleteAccomodationDetails(requestId);
			logger.info("AccomodationServiceImpl : deleteAccomodationDetails: Ended");
		} catch (DAOException ex) {
			logger.error("AccomodationServiceImpl : deleteAccomodationDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}

	}

	@Override
	public AccomodationDetailsVO getAccomodationDetails(int requestId) throws TravelServiceException {
		logger.info("AccomodationServiceImpl : getAccomodationDetails: Started");
		AccomodationDetailsVO accomodationDetailsVO = null;
		try {
			AccomodationDetailsBO accomodationDetailsBO = accomodationDAO.getAccomodationDetails(requestId);
			if (accomodationDetailsBO != null) {
				accomodationDetailsVO = TravelBeanUtils
						.AccomodationDetailsBO2AccomodationDetailsVO(accomodationDetailsBO);
			}
			logger.info("AccomodationServiceImpl : getAccomodationDetails: Ended");
		} catch (DAOException ex) {
			logger.error("AccomodationServiceImpl : getAccomodationDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return accomodationDetailsVO;
	}

	@Override
	public List<AccomodationDetailsVO> getAllAccomodationDetails() throws TravelServiceException {

		logger.info("AccomodationServiceImpl : getAllAccomodationDetails: Started");
		List<AccomodationDetailsVO> listVO = null;
		try {
			List<AccomodationDetailsBO> listBO = accomodationDAO.getAllAccomodationDetails();
			if (listBO != null && !listBO.isEmpty()) {
				listVO = new ArrayList<>();
				for (AccomodationDetailsBO accomodationDetailsBO : listBO) {
					AccomodationDetailsVO accomodationDetailsVO = TravelBeanUtils
							.AccomodationDetailsBO2AccomodationDetailsVO(accomodationDetailsBO);
					listVO.add(accomodationDetailsVO);
				}
			}
			logger.info("AccomodationServiceImpl : getAllAccomodationDetails: Ended");
		} catch (DAOException ex) {
			logger.error("AccomodationServiceImpl : getAllAccomodationDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;

	}

}
