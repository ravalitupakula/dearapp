package com.google.dearapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.dearapp.entity.User;
import com.google.dearapp.repository.UserRepository;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserRole;
import com.google.dearapp.util.UserStatus;

@Component
public class UserDao {
	@Autowired
	private UserRepository userRepo;

	public User saveUser(User u) {
		return userRepo.save(u);
	}

	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public Optional<User> findByPhone(Long phone) {
		return userRepo.findByPhone(phone);
	}

	public List<User> fetchAllUsers() {
		return userRepo.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return userRepo.findById(id);
	}

//	public List<User> findAllMaleUSers() {
//		return userRepo.findAllMaleUsers();
//	}
	
	public List<User> fetchAllMaleUsers(UserGender male) {
		return userRepo.findByGender(male);
	}
//
//	public List<User> findAllFemaleUSers() {
//		return userRepo.findAllFemaleUSers();
//	}

	public List<User> fetchAllFealeUsers(UserGender female) {
		return userRepo.findByGender(female);
	}


	public List<User> findAllActiveUsers(UserStatus active) {
		return userRepo.findByStatus(active);
	}
	public List<User> findAllInctiveUsers(UserStatus inactive) {
		return userRepo.findByStatus(inactive);
	}

	public List<User> findAllBlockedUsers(UserStatus blocked) {
		return userRepo.findByStatus(blocked);
	}

	public List<User> findAllDeletedUsers(UserStatus deleted) {
		return userRepo.findByStatus(deleted);
	}

	public List<User> findAllTerminatedUsers(UserStatus terminated) {
		return userRepo.findByStatus(terminated);
	}

	public List<User> searchUsersByEmailOrName(String query) {
		return userRepo.searchUsersByEmailOrName(query);
	}

	public List<User> fetchAllRoleUsers(UserRole user) {
		return userRepo.findByRole(user);
	}

	public List<User> fetchAllRoleAdmin(UserRole admin) {
		return userRepo.findByRole(admin);
	}

	public List<User> findByUserGenderAndStatus(UserGender male, UserStatus active) {
		return userRepo.findByGenderAndStatus(male, active);
	}

	public List<User> findByGender(UserGender gender) {
		return userRepo.findByGender(gender);
	}

	

	
	


	
}
