package com.hutech.payrollapp.api.model;

public class AdminRequest {

	private String userName;
	private String password;
	private String empEmail;
	private Boolean isAdmin;
	private Boolean isManager;
	private Boolean isEmployee;

	private  String token;
	
	
	public Boolean getIsEmployee() {
		return true;
	}
	public void setIsEmployee(Boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public String getToken() {
		return token;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return false;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = true;
	}

	public Boolean getIsManager() {
		return false;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = false;
	}

	
	  public AdminRequest(String userName,String password, 
	  Boolean isAdmin) { 
		 //this.token = token;
	  this.userName = userName;
	  this.password = password; 
	 // this.isAdmin = isAdmin;
	  this.isAdmin = isManager; 
	  }
	 
	  public AdminRequest(String token,String empEmail,String password, Boolean isAdmin,
			  Boolean isManager,Boolean isEmployee) { 
				  this.token = token;
			  this.empEmail = empEmail;
			  this.password = password; 
			  this.isAdmin = isAdmin;
			  this.isManager = isManager; 
			  this.isEmployee=isEmployee;
			  }
			 
				/*
				 * public AdminRequest(String empEmail,String password, Boolean isAdmin, Boolean
				 * isManager) { // this.token = token; this.empEmail = empEmail; this.password =
				 * password; this.isAdmin = isAdmin; this.isManager = isManager; }
				 */

	

	public AdminRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*
	 * public AdminRequest(String token,String userName, String password, String
	 * empEmail, Boolean isAdmin, Boolean isManager) { super(); this.token = token;
	 * //this.userName = userName; this.password = password; this.empEmail =
	 * empEmail; this.isAdmin = isAdmin; this.isManager = isManager; }
	 */

	
	/*
	 * public AdminRequest(String token, String password, String empEmail, Boolean
	 * isAdmin, Boolean isManager) { super(); this.token = token; //this.userName =
	 * userName; this.password = password; this.empEmail = empEmail; this.isAdmin =
	 * isAdmin; this.isManager = isManager; }
	 */
	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	

	

}
