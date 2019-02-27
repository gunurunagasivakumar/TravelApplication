package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.PerDeimDAO;
import com.capco.travel.model.PerDeimBO;
import com.capco.travel.service.PerDeimService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.PerDeimVO;


@Transactional
@Service
public class PerDeimServiceImpl implements PerDeimService{
	
	@Autowired
	PerDeimDAO perDeimDao;
	
	private final Logger logger = Logger.getLogger(ApproverServiceImpl.class);
	
	@Override
	public List<PerDeimVO> getPerDeimList() throws TravelServiceException {
		logger.info("PerDeimServiceImpl : getPerDeimDetails: Started");
		List<PerDeimVO> listVO=null;
		try{
			List<PerDeimBO> listBO=perDeimDao.getPerDeimList();
			if (listBO != null) {
			listVO = new ArrayList<>();
				for (PerDeimBO getPerDeimBOList : listBO) {
					PerDeimVO PerDeimVO = TravelBeanUtils.perDeimBO2PerDeimVO(getPerDeimBOList);
					listVO.add(PerDeimVO);
				}
			}
			logger.info("PerDeimServiceImpl : getPerDeimList: Ended");
		}catch (DAOException ex ) {
			logger.error("PerDeimServiceImpl : getPerDeimList: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	
	}

}
