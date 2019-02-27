package com.capco.travel.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.vo.HcActionDetailsVO;

public interface HcRequestDAO {
	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	public  void getAllHcPendingDetails(HcViewBO hcViewBO) throws DAOException;
	public void updateHcStatus(HcViewBO hcViewBO) throws DAOException;

}
