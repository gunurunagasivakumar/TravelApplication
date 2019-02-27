package com.capco.travel.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.ApproverGroupBO;
import com.capco.travel.vo.ApproverGroupVO;

public interface ApproverGroupDAO {
	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	public  List<ApproverGroupVO> getAllApproversDetails(int level) throws DAOException;

}
