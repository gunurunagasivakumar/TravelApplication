package com.capco.travel.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.ProjectDetailsBO;

public interface ProjectDetailsDAO {
	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	public  List<ProjectDetailsBO> getAllProjectDetails() throws DAOException;
	public ProjectDetailsBO getProjectDetails(String projectId)throws DAOException;
}
