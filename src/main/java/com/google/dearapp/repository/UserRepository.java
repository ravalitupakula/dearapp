package com.google.dearapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.google.dearapp.entity.User;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserRole;
import com.google.dearapp.util.UserStatus;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(Long phone);

//	@Query("select u from User u where u.gender = 'MALE'")
//	List<User> findAllMaleUsers();
	List<User> findByGender(UserGender male);

//	@Query("select u from User u where u.gender = 'FEMALE'")
//	List<User> findAllFemaleUSers();
	
	@Query("select u from User u where u.email = ?1or u.name = ?1")
	List<User> searchUsersByEmailOrName(String query);

	List<User> findByStatus(UserStatus active);

	List<User> findByRole(UserRole user);

	List<User> findByGenderAndStatus(UserGender male, UserStatus active);

	//List<User> findByUserGender(UserGender gender);



	

	
	
	 

	 

	



		

}
