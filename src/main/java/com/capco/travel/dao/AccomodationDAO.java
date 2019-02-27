package com.capco.travel.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.AccomodationDetailsBO;

public interface AccomodationDAO {

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	public void insertAccomodationDetails(AccomodationDetailsBO accomodationDetailsBO) throws DAOException;

	public AccomodationDetailsBO getAccomodationDetails(int requestId) throws DAOException;

	public void updateAccomodationDetails(AccomodationDetailsBO accomodationDetailsBO) throws DAOException;

	public void deleteAccomodationDetails(int requestId) throws DAOException;

	public List<AccomodationDetailsBO> getAllAccomodationDetails() throws DAOException;
}
