package com.example.demo.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
	@Query("SELECT u from User u where u.email= :email and u.password= :password")
	User verifyUserEmail(@Param("email")String email, @Param("password")String password);
	User findByEmail(String email);
	
	
	
}
