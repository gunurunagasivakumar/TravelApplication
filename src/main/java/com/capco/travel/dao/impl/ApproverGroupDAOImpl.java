package com.capco.travel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.ApproverGroupDAO;
import com.capco.travel.vo.ApproverGroupVO;

/**
 * This class is the DAO implementation for ApproverGroup
 * @author e5544344
 *
 */

@Repository
public class ApproverGroupDAOImpl implements ApproverGroupDAO{

	private static final Logger logger = Logger.getLogger(ApproverGroupDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method will get all approver details using level
	 * 	  
	 * @author e5544344
	 * @methodName getAllApproversDetails
	 * @param int level
	 * @return List<ApproverGroupBO>
	 */	
	@Override
	public List<ApproverGroupVO> getAllApproversDetails(int level) throws DAOException {
		logger.info("ApproverGroupDAOImpl : getAllApproversDetails: Started");
		List<Object[]> approverGroupList = new ArrayList<>();
		List<ApproverGroupVO> approverGroupVOList=new ArrayList<>();
		try {
			Session session = getSessionFactory().getCurrentSession();			
			String sql ="select e.employeeName, a.approverId, a.level from ApproverGroupBO a, EmployeeDetailsBO e where a.approverId = e.employeeId and a.level =:level";
			Query  query = session.createQuery(sql);  
			query.setParameter("level", level);  		
			approverGroupList  = query.list();	
			for(Object[] row : approverGroupList){
				ApproverGroupVO approverGroupVO=new ApproverGroupVO();
				if(row[0]!=null){
					approverGroupVO.setApproverName(row[0].toString());
				}
				if(row[1]!=null){
					approverGroupVO.setApproverId(Integer.parseInt(row[1].toString()));
				}
				if(row[2]!=null){
					approverGroupVO.setLevel(Integer.parseInt(row[2].toString()));
				}
				approverGroupVOList.add(approverGroupVO);
				logger.info("ApproverActionsDaoImpl : getAllRequestByApproverId: Ended");
			}
			logger.info("ApproverGroupDAOImpl : getAllApproversDetails: Ended");
		} catch (Exception e) {
			logger.error("ApproverGroupDAOImpl : getAllApproversDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return approverGroupVOList ;
	}

}
