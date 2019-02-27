package com.capco.travel.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.PerDeimBO;

public interface PerDeimDAO {
	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	public  List<PerDeimBO> getPerDeimList() throws DAOException;

}
