package com.hutech.payrollapp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin_info", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class AdminLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userName")
	@Email
	private String userName;

	@Column(name = "password")
	private String password;

	private Boolean isAdmin;
	private Boolean isManager;

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
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

	public AdminLogin(@NotEmpty @Email String userName, Boolean isAdmin, Boolean isManager,
			@NotEmpty @Size(min = 8, message = "password should have at least 8 character") String password) {
		super();
		this.userName = userName;
		this.isAdmin = isAdmin;
		this.isManager = isManager;
		this.password = password;
	}

	public AdminLogin() {

	}

}
