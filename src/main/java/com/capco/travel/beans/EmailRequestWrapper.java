package com.capco.travel.beans;

public class EmailRequestWrapper {
	
	Approver approver;
	
	EmployeeRegistration employeeregd;
	
	Partner partner;
	
	HCBP hcbp;

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Approver getApprover() {
		return approver;
	}

	public void setApprover(Approver approver) {
		this.approver = approver;
	}

	public EmployeeRegistration getEmployeeregd() {
		return employeeregd;
	}

	public void setEmployeeregd(EmployeeRegistration employeeregd) {
		this.employeeregd = employeeregd;
	}

	public HCBP getHcbp() {
		return hcbp;
	}

	public void setHcbp(HCBP hcbp) {
		this.hcbp = hcbp;
	}
	
}
