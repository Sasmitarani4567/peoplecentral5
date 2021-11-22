package com.hutech.payrollapp.api.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hutech.payrollapp.api.exceptionhandler.IncorrectEmailException;
import com.hutech.payrollapp.api.jwtUtill.JwtUtil;
import com.hutech.payrollapp.api.model.AdminLogin;
import com.hutech.payrollapp.api.model.AdminRequest;
import com.hutech.payrollapp.api.model.AdminResponse;
import com.hutech.payrollapp.api.model.Employee;
import com.hutech.payrollapp.api.repository.AdminRepository;
import com.hutech.payrollapp.api.repository.EmployeeRepository;
import com.hutech.payrollapp.api.serviceImpl.AdminLoginService;
import com.hutech.payrollapp.api.serviceImpl.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminLoginController {

	@GetMapping("/adminlogin")
	public String login() {
		return "adminLogin";
	}

	@GetMapping("/success")
	public String home() {
		return "index";
	}

	private static final @NotEmpty @Size(min = 8, message = "password should have at least 8 character") String String = null;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminLoginService loginService;
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping("/logintoken")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AdminRequest adminrequest) throws UsernameNotFoundException{

		AdminLogin adminLog = adminRepository.findByUserName(adminrequest.getUserName());
		Employee emp=employeeRepository.findByEmpEmail(adminrequest.getEmpEmail());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


		if (adminLog!=null && adminLog.getPassword().equals(adminrequest.getPassword())) {
			final UserDetails userDetails = loginService.loadUserByUsername(adminrequest.getUserName());
			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AdminResponse(token, adminrequest.getUserName(), adminrequest.getIsAdmin(),
					adminrequest.getIsManager(),adminrequest.getIsEmployee()));
		}
		
		else if(emp.getEmpEmail().equals(adminrequest.getEmpEmail()) &&passwordEncoder.matches(adminrequest.getPassword(), emp.getPassword())) {
			final UserDetails userDetails = employeeServiceImpl.loadUserByUsername(adminrequest.getEmpEmail());
			final String token = jwtTokenUtil.generateToken(userDetails);
			adminrequest.setIsAdmin(false);
			return ResponseEntity.ok(new AdminRequest(token, adminrequest.getEmpEmail(),adminrequest.getPassword(), adminrequest.getIsAdmin(),
					adminrequest.getIsManager(),adminrequest.getIsEmployee()));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("incorrect username or password");
	}

	@PostMapping("/addAdmin")
	public String addAdmin(@RequestBody AdminLogin adminLogin) {
		adminRepository.save(adminLogin);
		return "Admin Added";
	}
}
