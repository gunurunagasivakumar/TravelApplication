package com.capco.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.dao.PocDAO;
import com.capco.travel.model.EmployeeDetailsBO;

@RestController
public class POCController {

	@Autowired
	private PocDAO PocDAO;

	public PocDAO getPocDAO() {
		return PocDAO;
	}

	public void setPocDAO(PocDAO PocDAO) {
		this.PocDAO = PocDAO;
	}

	 @RequestMapping(value = "/addEmployee", method = RequestMethod.POST, headers = "Accept=application/json")
	 public EmployeeDetailsBO addEmployee(@RequestBody EmployeeDetailsBO empDetails) { 
		 getPocDAO().insertEmployee(empDetails);	 
		 System.out.println("inserted data");
		 return empDetails;
	 } 
	 
	 @RequestMapping(value = "/getEmployees", method = RequestMethod.GET, headers = "Accept=application/json")
	 public EmployeeDetailsBO getEmployees() { 	 	 
		 System.out.println("data initiated for printing");
		 EmployeeDetailsBO listOfEmployees = getPocDAO().getEmployee();
		 System.out.println(listOfEmployees);
		 return listOfEmployees;

	 } 

	 @RequestMapping(value = "/getData")
	 public String getData() { 	 	 
		 System.out.println("got data");
		 return "Hello";
	 } 
	 
}