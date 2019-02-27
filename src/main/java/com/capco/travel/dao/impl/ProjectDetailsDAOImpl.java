package com.capco.travel.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.ProjectDetailsDAO;
import com.capco.travel.model.ProjectDetailsBO;

/**
 * This class is the DAO implementation for the ProjectDetails
 * 
 * @author e5544354
 *
 */

@Repository
public class ProjectDetailsDAOImpl implements ProjectDetailsDAO {
	private static final Logger logger = Logger.getLogger(ProjectDetailsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<ProjectDetailsBO> getAllProjectDetails() throws DAOException {
		logger.info("ProjectDetailsDAOImpl : getAllProjectDetails: Started");
		List<ProjectDetailsBO> projectDetailsList = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			projectDetailsList = session.createQuery("from ProjectDetailsBO").list();
			logger.info("ProjectDetailsDAOImpl : getAllProjectDetails: Ended");
		} catch (Exception e) {
			logger.error("ProjectDetailsDAOImpl : getAllProjectDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return projectDetailsList;
	}

	@Override
	public ProjectDetailsBO getProjectDetails(String projectId) throws DAOException {
		logger.info("ProjectDetailsDAOImpl : getProjectDetails: Started");
		ProjectDetailsBO projectDetails;
		try {
			Session session = getSessionFactory().getCurrentSession();
			String sql = "from ProjectDetailsBO where projectCode =:projectId";
			Query query = session.createQuery(sql);
			query.setParameter("projectId", projectId);
			projectDetails = (ProjectDetailsBO) query.uniqueResult();
		} catch (Exception e) {
			logger.error("ProjectDetailsDAOImpl : getProjectDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return projectDetails;
	}

}
