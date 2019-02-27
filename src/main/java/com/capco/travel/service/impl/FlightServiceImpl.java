package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.FlightDAO;
import com.capco.travel.model.FlightDetailsBO;
import com.capco.travel.service.FlightService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.FlightDetailsVO;

@Transactional
@Service
public class FlightServiceImpl implements FlightService {
	private static final Logger logger = Logger.getLogger(FlightServiceImpl.class);

	@Autowired
	FlightDAO flightDAO;

	/**
	 * This method to add flight Details into database*
	 * @methodName insertFlightDetails
	 * @param com.capco.travel.model.FlightDetailsVO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void insertFlightDetails(FlightDetailsVO flightDetails) throws TravelServiceException {
		logger.info("FlightServiceImpl : insertFlightDetails: Started");
		try {
			FlightDetailsBO flightDetailsBO = TravelBeanUtils.FlightDetailsVO2FlightDetailsBO(flightDetails);
			flightDAO.insertFlightDetails(flightDetailsBO);
			logger.info("FlightServiceImpl : insertFlightDetails: Ended");
		} catch (DAOException ex) {
			logger.error("FlightServiceImpl : insertFlightDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}

	}

	/**
	 * This method to get all flight Details from database*
	 * @methodName getAllFlightsDetails
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public List<FlightDetailsVO> getAllFlightsDetails() throws TravelServiceException {
		logger.info("FlightServiceImpl : getAllFlightsDetails: Started");
		List<FlightDetailsVO> listVO = null;
		try {
			List<FlightDetailsBO> listBO = flightDAO.getAllFlightsDetails();
			if (listBO != null && !listBO.isEmpty()) {
				listVO = new ArrayList<>();
				for (FlightDetailsBO flightDetailsBO : listBO) {
					FlightDetailsVO flightDetailsVO = TravelBeanUtils.FlightDetailsBO2FlightDetailsVO(flightDetailsBO);
					
					listVO.add(flightDetailsVO);
				}
			}
			logger.info("FlightServiceImpl : getAllFlightsDetails: Ended");
		} catch (DAOException ex) {
			logger.error("FlightServiceImpl : getAllFlightsDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;

	}

	@Override
	public FlightDetailsVO getEmployeeFlightsDetails(int requestId) throws TravelServiceException {

		logger.info("FlightServiceImpl : getAllFlightsDetails: Started");
		FlightDetailsVO flightDetailsVO = null;
		try {
			FlightDetailsBO flightDetailsBO = flightDAO.getEmployeeFlightsDetails(requestId);
			if (flightDetailsBO != null) {
				flightDetailsVO = TravelBeanUtils.FlightDetailsBO2FlightDetailsVO(flightDetailsBO);
			}
			logger.info("FlightServiceImpl : getAllFlightsDetails: Ended");
		} catch (DAOException ex) {
			logger.error("FlightServiceImpl : getAllFlightsDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return flightDetailsVO;

	}
	

	/**
	 * This method to update flight Details *
	 * @methodName updateFlightDetails
	 * @param com.capco.travel.model.FlightDetailsVO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */

	@Override
	public void updateFlightDetails(FlightDetailsVO updateflightDetails) throws TravelServiceException {
		logger.info("FlightServiceImpl : updateFlightDetails: Started");
		try {
			FlightDetailsBO flightDetailsBO = TravelBeanUtils.FlightDetailsVO2FlightDetailsBO(updateflightDetails);
			flightDAO.updateFlightDetails(flightDetailsBO);
			logger.info("FlightServiceImpl : updateFlightDetails: Ended");
		} catch (DAOException ex) {
			logger.error("FlightServiceImpl : updateFlightDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}

	}

	/**
	 * This method to delete flight Details *
	 * @methodName deletFlightDetails
	 * @param int requestId
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void deletFlightDetails(int requestId) throws TravelServiceException {
		logger.info("FlightServiceImpl : deletFlightDetails: Started");
		try {
			flightDAO.deletFlightDetails(requestId);
			logger.info("FlightServiceImpl : deletFlightDetails: Ended");
		} catch (DAOException ex) {
			logger.error("FlightServiceImpl : deletFlightDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}

	}
}
