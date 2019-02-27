package com.capco.travel.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.ForexDetailsBO;

public interface ForexDAO {

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	public void insertForexDetails(ForexDetailsBO empDetails) throws DAOException;

	public ForexDetailsBO getForexDetails(int i) throws DAOException;

	public void updateForexDetails(ForexDetailsBO empDetails) throws DAOException;

	public void deleteForexDetails(int id) throws DAOException;

	public List<ForexDetailsBO> getAllForexDetails() throws DAOException;

}
