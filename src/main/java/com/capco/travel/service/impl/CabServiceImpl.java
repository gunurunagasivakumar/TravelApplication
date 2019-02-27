package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.CabDAO;
import com.capco.travel.model.CabDetailsBO;
import com.capco.travel.service.CabService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.CabDetailsVO;

/**
 * @author e5542274
 *
 */
@Transactional
@Service
public class CabServiceImpl implements CabService {
	private static final Logger logger = Logger.getLogger(CabServiceImpl.class);

	@Autowired
	private CabDAO cabDAO;

	/**
	 * This method to add Cab Details into database*
	 * @methodName insertCabDetails
	 * @param com.capco.travel.model.CabDetailsVO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void insertCabDetails(CabDetailsVO cabDetailsVO) throws TravelServiceException {
		logger.info("CabServiceImpl : insertCabDetails: Started");
		try {
			CabDetailsBO cabDetailsBo = TravelBeanUtils.CabDetailsVO2CabDetailsBO(cabDetailsVO);
			cabDAO.insertCabDetails(cabDetailsBo);
			logger.info("CabServiceImpl : insertCabDetails: Ended");
		} catch (DAOException ex) {
			logger.error("CabServiceImpl : insertCabDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}

	}

	/**
	 * This method to get Cab Details from database*
	 * @methodName getCabDetails
	 * @param long
	 * @return CabDetailsVO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public CabDetailsVO getCabDetails(int requestId) throws TravelServiceException {
		logger.info("CabServiceImpl : getCabDetails: Started");
		CabDetailsVO cabDetailsVO = null;
		try {
			CabDetailsBO cabDetailsBO = cabDAO.getCabDetails(requestId);
			if (cabDetailsBO != null) {
				cabDetailsVO = TravelBeanUtils.CabDetailsBO2CabDetailsVO(cabDetailsBO);
			}
			else {
				throw new DAOException(TravelConstants.RESULT_NULL_OBJECT+requestId);
			}
			logger.info("CabServiceImpl : getCabDetails: Ended");
		} catch (DAOException ex) {
			logger.error("CabServiceImpl : getCabDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return cabDetailsVO;
	}

	/**
	 * This method to update Cab Details in database*
	 * @methodName updateCabDetails
	 * @param com.capco.travel.model.CabDetailsVO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void updateCabDetails(CabDetailsVO cabDetailsVO) throws TravelServiceException {
		logger.info("CabServiceImpl : updateCabDetails: Started");
		try {
			CabDetailsBO cabDetailsBo = TravelBeanUtils.CabDetailsVO2CabDetailsBO(cabDetailsVO);
			cabDAO.updateCabDetails(cabDetailsBo);
		} catch (DAOException ex) {
			logger.error("CabServiceImpl : updateCabDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	/**
	 * This method to delete Cab Details from database*
	 * @methodName deleteCabDetails
	 * @param int
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void deleteCabDetails(int requestId) throws TravelServiceException {
		logger.info("CabServiceImpl : deleteCabDetails: Started");
		try {
			cabDAO.deleteCabDetails(requestId);
			logger.info("CabServiceImpl : deleteCabDetails: Ended");
		} catch (DAOException ex) {
			logger.error("CabServiceImpl : deleteCabDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	/**
	 * This method to get all Cab Details from database*
	 * @methodName getAllCabDetails
	 * @return List<CabDetailsVO>
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public List<CabDetailsVO> getAllCabDetails() throws TravelServiceException {
		logger.info("CabServiceImpl : deleteCabDetails: Started");
		List<CabDetailsVO> listVO = null;
		try {
			List<CabDetailsBO> listBO = cabDAO.getAllCabDetails();
			if (listBO != null && !listBO.isEmpty()) {
				listVO = new ArrayList<>();
				for (CabDetailsBO cabDetailsBO : listBO) {
					CabDetailsVO cabDetailsVO = new CabDetailsVO();
					BeanUtils.copyProperties(cabDetailsBO, cabDetailsVO);
					listVO.add(cabDetailsVO);
				}
			}
			logger.info("CabServiceImpl : deleteCabDetails: Ended");
		} catch (DAOException ex) {
			logger.error("CabServiceImpl : deleteCabDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}
}
