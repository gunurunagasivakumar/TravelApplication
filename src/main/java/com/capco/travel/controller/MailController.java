package com.capco.travel.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.beans.EmailRequestWrapper;
import com.capco.travel.beans.EmployeeRegistrationTravel;
import com.capco.travel.util.email.EmailService;
import com.capco.travel.util.email.EmailTemplate;


@RestController
public class MailController {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	EmailService emailService;

	/*@RequestMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("demo@gmail.com");
			helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
			EmailTemplate template = new EmailTemplate("hello-world-plain.txt");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("user", "Ambarish");
			replacements.put("today", String.valueOf(new Date()));
			helper.setText("This message is from rest endpoint" + "\n\n"+template.getTemplate(replacements));
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}*/

	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMail/newrequest")
	@ResponseBody
	public EmailRequestWrapper registerEmployee(@RequestBody EmailRequestWrapper emailRequestWrapper) {
	//EmployeeRegistrationTravel registerEmployee(@RequestBody EmployeeRegistration employeeregd, @RequestBody Approver approver) {
		EmployeeRegistrationTravel empregtravel = new EmployeeRegistrationTravel();
		empregtravel.setName(emailRequestWrapper.getEmployeeregd().getName());
		empregtravel.setEmployeeNumber(emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
		empregtravel.setProjectName(emailRequestWrapper.getEmployeeregd().getProjectName());
		//empregtravel.setEmployeeTravelDestination(employeeregd.getTravelDestination());
		empregtravel.setEmployeeisosInfo(emailRequestWrapper.getEmployeeregd().isIsosInfo());
	
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		if(!empregtravel.isEmployeeisosInfo())
		{
			try
			{

				helper.setFrom("CapcoTravelAssist-Team@capco.com");
				helper.setTo("traveldesk@capco.com");
				//helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
				EmailTemplate template = new EmailTemplate("travel-mail-template-ISOS-Info-trigger.txt");
				Map<String, String> replacements = new HashMap<String, String>();
				replacements.put("employeeName", emailRequestWrapper.getEmployeeregd().getName());
				replacements.put("employeeNumber", emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
				replacements.put("projectName", emailRequestWrapper.getEmployeeregd().getProjectName());
				replacements.put("today", String.valueOf(new Date()));
				//replacements.put("destination", employeeregd.getTravelDestination());
				helper.setText(template.getTemplate(replacements));
				helper.setSubject("Travel Request for "+emailRequestWrapper.getEmployeeregd().getName()+ "-- ISOS not generated");
				
			
			}
		
			catch (MessagingException me)
			{
				me.printStackTrace();
			}
		}

		else 
		{
		try {
			helper.setFrom("CapcoTravelAssist-Team@capco.com");
			helper.setTo(emailRequestWrapper.getApprover().getApproverEmailID());
			//helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
			EmailTemplate template = new EmailTemplate("travel-mail-template.txt");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("approverName", emailRequestWrapper.getApprover().getApproverName());
			replacements.put("employeeName", emailRequestWrapper.getEmployeeregd().getName());
			replacements.put("employeeNumber", emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
			replacements.put("projectName", emailRequestWrapper.getEmployeeregd().getProjectName());
			replacements.put("today", String.valueOf(new Date()));
			//replacements.put("destination", employeeregd.getTravelDestination());
			helper.setText(template.getTemplate(replacements));
			helper.setSubject("New Travel Request for "+emailRequestWrapper.getEmployeeregd().getName());
			
		} catch (MessagingException me1) {
			me1.printStackTrace();
			
		}
		
		}
		sender.send(message);
		return emailRequestWrapper;
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMail/approverequest")
	@ResponseBody
	public EmailRequestWrapper approveRequestEmail(@RequestBody EmailRequestWrapper emailRequestWrapper) {
	//EmployeeRegistrationTravel registerEmployee(@RequestBody EmployeeRegistration employeeregd, @RequestBody Approver approver) {
		EmployeeRegistrationTravel empregtravel = new EmployeeRegistrationTravel();
		empregtravel.setName(emailRequestWrapper.getEmployeeregd().getName());
		empregtravel.setEmployeeNumber(emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
		empregtravel.setProjectName(emailRequestWrapper.getEmployeeregd().getProjectName());
		//empregtravel.setEmployeeTravelDestination(employeeregd.getTravelDestination());
		empregtravel.setEmployeeisosInfo(emailRequestWrapper.getEmployeeregd().isIsosInfo());
	
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setFrom("CapcoTravelAssist-Team@capco.com");
			helper.setTo(emailRequestWrapper.getPartner().getPartnerEmailID());
			//helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
			EmailTemplate template = new EmailTemplate("approver-mail-template.txt");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("partnerName", emailRequestWrapper.getPartner().getPartnerName());
			replacements.put("approverName", emailRequestWrapper.getApprover().getApproverName());
			replacements.put("employeeName", emailRequestWrapper.getEmployeeregd().getName());
			replacements.put("employeeNumber", emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
			replacements.put("projectName", emailRequestWrapper.getEmployeeregd().getProjectName());
			replacements.put("today", String.valueOf(new Date()));
			//replacements.put("destination", employeeregd.getTravelDestination());
			helper.setText(template.getTemplate(replacements));
			helper.setSubject("New Travel Request for "+emailRequestWrapper.getEmployeeregd().getName());
			
		} catch (MessagingException me1) {
			me1.printStackTrace();
			
		}
		
		
		sender.send(message);
		return emailRequestWrapper;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMail/rejectrequest")
	@ResponseBody
	public EmailRequestWrapper approverrejectRequestEmail(@RequestBody EmailRequestWrapper emailRequestWrapper) {
	//EmployeeRegistrationTravel registerEmployee(@RequestBody EmployeeRegistration employeeregd, @RequestBody Approver approver) {
		EmployeeRegistrationTravel empregtravel = new EmployeeRegistrationTravel();
		empregtravel.setName(emailRequestWrapper.getEmployeeregd().getName());
		empregtravel.setEmployeeNumber(emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
		empregtravel.setProjectName(emailRequestWrapper.getEmployeeregd().getProjectName());
		empregtravel.setEmployeeEmailID(emailRequestWrapper.getEmployeeregd().getEmployeeEmailID());
		//empregtravel.setEmployeeTravelDestination(employeeregd.getTravelDestination());
		//empregtravel.setEmployeeisosInfo(emailRequestWrapper.getEmployeeregd().isIsosInfo());
	
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setFrom("CapcoTravelAssist-Team@capco.com");
			helper.setTo(empregtravel.getEmployeeEmailID());
			//helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
			EmailTemplate template = new EmailTemplate("approver-reject-mail-template.txt");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("employeeName", emailRequestWrapper.getEmployeeregd().getName());
			replacements.put("employeeNumber", emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
			replacements.put("projectName", emailRequestWrapper.getEmployeeregd().getProjectName());
			replacements.put("approverName", emailRequestWrapper.getApprover().getApproverName());
			//replacements.put("destination", employeeregd.getTravelDestination());
			helper.setText(template.getTemplate(replacements));
			helper.setSubject("New Travel Request for "+emailRequestWrapper.getEmployeeregd().getName()+ "--Status: Rejected by Approver");
			
		} catch (MessagingException me1) {
			me1.printStackTrace();
			
		}
		
		
		sender.send(message);
		return emailRequestWrapper;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMail/partnerApproval")
	@ResponseBody
	public EmailRequestWrapper partnerapprovalEmail(@RequestBody EmailRequestWrapper emailRequestWrapper) {
	//EmployeeRegistrationTravel registerEmployee(@RequestBody EmployeeRegistration employeeregd, @RequestBody Approver approver) {
		EmployeeRegistrationTravel empregtravel = new EmployeeRegistrationTravel();
		empregtravel.setName(emailRequestWrapper.getEmployeeregd().getName());
		empregtravel.setEmployeeNumber(emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
		empregtravel.setProjectName(emailRequestWrapper.getEmployeeregd().getProjectName());		
		//empregtravel.setEmployeeTravelDestination(employeeregd.getTravelDestination());
		//empregtravel.setEmployeeisosInfo(emailRequestWrapper.getEmployeeregd().isIsosInfo());
	
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String to[] = {"traveldesk@capco.com","finance-team@capco.com","HC-Ops@capco.com",emailRequestWrapper.getApprover().getApproverEmailID()};
		
		try {
			helper.setFrom("CapcoTravelAssist-Team@capco.com");
			helper.setTo(to);
			//helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
			EmailTemplate template = new EmailTemplate("partner-approval-mail-template.txt");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("employeeName", emailRequestWrapper.getEmployeeregd().getName());
			replacements.put("employeeNumber", emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
			replacements.put("projectName", emailRequestWrapper.getEmployeeregd().getProjectName());
			replacements.put("approverName", emailRequestWrapper.getApprover().getApproverName());
			replacements.put("partnerName", emailRequestWrapper.getPartner().getPartnerName());
			//replacements.put("destination", employeeregd.getTravelDestination());
			helper.setText(template.getTemplate(replacements));
			helper.setSubject("New Travel Request for "+emailRequestWrapper.getEmployeeregd().getName()+ "--Status: Confirmed");
			
		} catch (MessagingException me1) {
			me1.printStackTrace();
			
		}
		
		
		sender.send(message);
		return emailRequestWrapper;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMail/hcBP")
	@ResponseBody
	public EmailRequestWrapper hcBPEmail(@RequestBody EmailRequestWrapper emailRequestWrapper) {
	//EmployeeRegistrationTravel registerEmployee(@RequestBody EmployeeRegistration employeeregd, @RequestBody Approver approver) {
		EmployeeRegistrationTravel empregtravel = new EmployeeRegistrationTravel();
		empregtravel.setName(emailRequestWrapper.getEmployeeregd().getName());
		empregtravel.setEmployeeNumber(emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
		empregtravel.setProjectName(emailRequestWrapper.getEmployeeregd().getProjectName());		
		//empregtravel.setEmployeeTravelDestination(employeeregd.getTravelDestination());
		//empregtravel.setEmployeeisosInfo(emailRequestWrapper.getEmployeeregd().isIsosInfo());
		
	
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String to[] = {emailRequestWrapper.getHcbp().getHcBPEmailID()};
		
		try {
			helper.setFrom("CapcoTravelAssist-Team@capco.com");
			helper.setTo(to);
			//helper.setText("Greetings :) This message is from rest endpoint" + "\n\n");
			EmailTemplate template = new EmailTemplate("hcbp-mail-template.txt");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("employeeName", emailRequestWrapper.getEmployeeregd().getName());
			replacements.put("employeeNumber", emailRequestWrapper.getEmployeeregd().getEmployeeNumber());
			replacements.put("projectName", emailRequestWrapper.getEmployeeregd().getProjectName());
			replacements.put("hcbpName", emailRequestWrapper.getHcbp().getHcBPName());
			helper.setText(template.getTemplate(replacements));
			helper.setSubject("New Travel Request for "+emailRequestWrapper.getEmployeeregd().getName()+ "--Status: Confirmed");
		} catch (MessagingException me1) {
			me1.printStackTrace();
			
		}
		
		
		sender.send(message);
		return emailRequestWrapper;
	}
	/*@RequestMapping("/")
	public String home()
	{
		return "redirect:/angular-test.html";
	}*/
	/*@RequestMapping("/sendMailAtt")
	public String sendMailAttachment() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		try {
			helper.setTo("demo@gmail.com");
			helper.setText("Greetings :)\n Please find the attached docuemnt for your reference.");
			helper.setSubject("Mail From Spring Boot");
			ClassPathResource file = new ClassPathResource("document.PNG");
			helper.addAttachment("document.PNG", file);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}*/


}
