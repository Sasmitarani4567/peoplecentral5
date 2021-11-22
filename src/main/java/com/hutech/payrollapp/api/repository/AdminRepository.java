package com.hutech.payrollapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hutech.payrollapp.api.model.AdminLogin;
@Repository
public interface AdminRepository extends JpaRepository<AdminLogin, Long> {
	
	AdminLogin findByUserName(String username);
}
