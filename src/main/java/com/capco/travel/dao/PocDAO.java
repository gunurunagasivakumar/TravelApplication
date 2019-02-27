package com.capco.travel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.model.EmployeeDetailsBO;

@Repository                 
@Transactional
public class PocDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	Transaction tx = null;
	
	
	public void insertEmployee(EmployeeDetailsBO empDetails)
	{
		//EmployeeDetailsBO emp = new EmployeeDetailsBO();
		
		
		System.out.println("employee details initiated..");
		Session session = getSessionFactory().getCurrentSession();		
		System.out.println(empDetails);
		session.save(empDetails);
		/*tx = session.beginTransaction();                          //working transaction
	    session.save(emp);
	    tx.commit();*/
	    
		//session.saveOrUpdate(emp);
		System.out.println("employee details inserted");
	}

	public EmployeeDetailsBO getEmployee() {
		// TODO Auto-generated method stub
		System.out.println("employee details initiated..");
		EmployeeDetailsBO empDetails;
		Session session = getSessionFactory().openSession();
		empDetails=(EmployeeDetailsBO) session.get(EmployeeDetailsBO.class,1);
		System.out.println("employee details populated as json");
		System.out.println(empDetails);
		return empDetails;
	}	

}

