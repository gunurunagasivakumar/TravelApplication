package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.ForexDAO;
import com.capco.travel.model.ForexDetailsBO;
import com.capco.travel.service.ForexService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.ForexDetailsVO;

@Transactional
@Service
public class ForexServiceImpl implements ForexService {
	private static final Logger logger = Logger.getLogger(ForexServiceImpl.class);
	@Autowired
	ForexDAO frxDao;

	@Override
	public void insertForexDetails(ForexDetailsVO frxDetails) throws TravelServiceException {
		logger.info("ForexServiceImpl : insertForexDetails: Started");
		try {
			ForexDetailsBO forexDetailsBO = TravelBeanUtils.ForexDetailsVO2ForexDetailsBO(frxDetails);
			frxDao.insertForexDetails(forexDetailsBO);
			logger.info("ForexServiceImpl : insertForexDetails: Ended");
		} catch (DAOException ex) {
			logger.error("ForexServiceImpl : insertForexDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	@Override
	public ForexDetailsVO getForexDetails(int id) throws TravelServiceException {
		logger.info("ForexServiceImpl : getForexDetails: Started");
		ForexDetailsVO forexDetailsVO = null;
		try {
			ForexDetailsBO forexDetailsBO = frxDao.getForexDetails(id);
			if (forexDetailsBO != null) {
				forexDetailsVO = TravelBeanUtils.ForexDetailsBO2ForexDetailsVO(forexDetailsBO);
			}
			logger.info("ForexServiceImpl : getForexDetails: Ended");
		} catch (DAOException ex) {
			logger.error("ForexServiceImpl : getForexDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return forexDetailsVO;
	}

	@Override
	public void updateForexDetails(ForexDetailsVO forexDetailsVO) throws TravelServiceException {
		logger.info("ForexServiceImpl : updateForexDetails: Started");
		try {
			ForexDetailsBO forexDetailsBO = TravelBeanUtils.ForexDetailsVO2ForexDetailsBO(forexDetailsVO);
			frxDao.updateForexDetails(forexDetailsBO);
			logger.info("ForexServiceImpl : updateForexDetails: Ended");
		} catch (DAOException ex) {
			logger.error("ForexServiceImpl : updateForexDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	@Override
	public void deleteForexDetails(int id) throws TravelServiceException {
		logger.info("ForexServiceImpl : deleteForexDetails: Started");
		try {
			frxDao.deleteForexDetails(id);
			logger.info("ForexServiceImpl : deleteForexDetails: Ended");
		} catch (DAOException ex) {
			logger.error("ForexServiceImpl : deleteForexDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
	}

	@Override
	public List<ForexDetailsVO> getAllForexDetails() throws TravelServiceException {
		logger.info("ForexServiceImpl : getAllForexDetails: Started");
		List<ForexDetailsVO> forexDetailsVOList = null;
		try {
			List<ForexDetailsBO> forexDetailsBOList = frxDao.getAllForexDetails();
			if (forexDetailsBOList != null && !forexDetailsBOList.isEmpty()) {
				forexDetailsVOList = new ArrayList<>();
				for (ForexDetailsBO forexDetailsBO : forexDetailsBOList) {
					ForexDetailsVO forexDetailsVO = TravelBeanUtils.ForexDetailsBO2ForexDetailsVO(forexDetailsBO);
					forexDetailsVOList.add(forexDetailsVO);
				}
			}
			logger.info("ForexServiceImpl : getAllForexDetails: Ended");
		} catch (DAOException ex) {
			logger.error("ForexServiceImpl : getAllForexDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return forexDetailsVOList;
	}

}
