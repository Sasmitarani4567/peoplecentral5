package com.hutech.payrollapp.api.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hutech.payrollapp.api.model.AdminLogin;
import com.hutech.payrollapp.api.repository.AdminRepository;

@Service
public class AdminLoginService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		AdminLogin adminLogin = adminRepository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(adminLogin.getUserName(), adminLogin.getPassword(),
				new ArrayList<>());
	}
	
	
}

