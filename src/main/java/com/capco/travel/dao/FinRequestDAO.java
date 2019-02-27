package com.capco.travel.dao;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.FinViewBO;

public interface FinRequestDAO {
	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	public  void getAllFinPendingDetails(FinViewBO finViewBO) throws DAOException;
	public void updateFinStatus(FinViewBO finViewBO) throws DAOException;

}
