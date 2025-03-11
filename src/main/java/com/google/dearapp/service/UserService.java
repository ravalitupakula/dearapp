package com.google.dearapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.dearapp.dao.UserDao;
import com.google.dearapp.dto.MatchingUser;
import com.google.dearapp.entity.User;
import com.google.dearapp.exceptionclasses.DuplicateEmailIdEsception;
import com.google.dearapp.exceptionclasses.DuplicatePhoneException;
import com.google.dearapp.exceptionclasses.InvalidUserIdException;
import com.google.dearapp.responsestructure.ResponseStructure;
//import com.google.dearapp.util.SortByAgeDifference;
import com.google.dearapp.util.SortByAgeDifferenceAsc;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserRole;
import com.google.dearapp.util.UserStatus;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	//**********************Save User***************************
	
	public ResponseStructure<User> saveUser(User u) {
		
		Optional<User> optional = userDao.findByEmail(u.getEmail());
		if(optional.isPresent()) {
			throw new DuplicateEmailIdEsception("Account Already Exit with Email : "+u.getEmail()+" please try to login otherwise use different email for registration");
			
		}
		
		Optional< User> optional2 = userDao.findByPhone(u.getPhone());
		if(optional2.isPresent()) {
			
			throw new DuplicatePhoneException("Account already exit this phone Number: "+u.getPhone()+" please try to login, otherwise use different Phone Number for Registration");
		}
	
		u = userDao.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Save The User");
		structure.setBody(u);
		return structure;
	}

	//********************************Fetch All Users *******************************************
	
	public ResponseStructure<List<User>> fetchAllUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.fetchAllUsers();
		if(users.isEmpty()) {
//			throw new UserEmptyException();
		}
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Fetch All Users");
		structure.setBody(users);
		return structure;
	}
	
	//*************************************Find User By Id ***************************************

	public ResponseStructure<User> findUserById(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
			throw new InvalidUserIdException("Invalid User Id : "+id+" Unable To Find User");
		}
		User user = optional.get();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Find User Details By Id : " +id);
		structure.setBody(user);
		return structure;
	}


	//*****************************************Update User By Id *****************************************
	
	public ResponseStructure<User> updateUserById(User u, Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPhone(u.getPhone());
		user.setGender(u.getGender());
		user.setPassword(u.getPassword());
		user.setAge(u.getAge());
		User updateUser = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Update All User Details By Id : " +id);
		structure.setBody(updateUser);
		return structure;
	}

	

	//************************************Find All Male USer By Gender***************************

//	public ResponseStructure<List<User>> findAllMaleUSers(UserGender gender) {
//		ResponseStructure<List<User>> structure = new ResponseStructure<>();
//		 List<User> users = userDao.findAllMaleUSers();
//		 if(users.isEmpty()) {
////			 throw new NoUserGenderFoundException();
//		 }
//		 structure.setStatus(HttpStatus.OK.value());
//		 structure.setMessage("Fetch All Male User Details");
//		 structure.setBody(users);
//		 return structure;
//	}
	
	public ResponseStructure<List<User>> fetchAllMaleUSers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users  = userDao.fetchAllMaleUsers(UserGender.MALE);
//		if(users.isEmpty()) {
//			
//		}
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Fetch All Male Users");
		structure.setBody(users);
		return structure;
	}


	//************************************Find All Female USer By Gender***************************
	
//	public ResponseStructure<List<User>> findAllFemaleUSers(UserGender gender) {
//		ResponseStructure<List<User>> structure = new ResponseStructure<>();
//		 List<User> users = userDao.findAllFemaleUSers();
//		 if(users.isEmpty()) {
////			 throw new NoUserGenderFoundException();
//		 }
//		 structure.setStatus(HttpStatus.OK.value());
//		 structure.setMessage("Fetch All Female User Details");
//		 structure.setBody(users);
//		 return structure;
//	}
	

	public ResponseStructure<List<User>> fetchAllFemaleUSers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users  = userDao.fetchAllFealeUsers(UserGender.FEMALE);
//		if(users.isEmpty()) {
//			
//		}
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Fetch All Male Users");
		structure.setBody(users);
		return structure;
	}


	
	//************************************Find All Active USer By Status***************************
	
	public ResponseStructure<List<User>> findAllActiveUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findAllActiveUsers(UserStatus.ACTIVE);
		if(users.isEmpty()) {
//			 throw new NoUserStausFoundException();
		 }
		 structure.setStatus(HttpStatus.OK.value());
		 structure.setMessage("Fetch All Active User Details");
		 structure.setBody(users);
		 return structure;
	}
	
	//*****************Fetch All Inactive Users*****************************

	public ResponseStructure<List<User>> findAllInctiveUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findAllInctiveUsers(UserStatus.IN_ACTIVE);
		if(users.isEmpty()) {
//			 throw new NoUserStausFoundException();
		 }
		 structure.setStatus(HttpStatus.OK.value());
		 structure.setMessage("Fetch All In_Active User Details");
		 structure.setBody(users);
		 return structure;
	}
	
	//********************************Find All Blocked USers ***********************

	public ResponseStructure<List<User>> findAllBlockedUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findAllBlockedUsers(UserStatus.BLOCKED);
		if(users.isEmpty()) {
//			 throw new NoUserStausFoundException();
		 }
		 structure.setStatus(HttpStatus.OK.value());
		 structure.setMessage("Fetch All Blocked User Details");
		 structure.setBody(users);
		 return structure;
	}
	
	//*****************************************Find All Deleted USers ******************

	public ResponseStructure<List<User>> findAllDeletedUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findAllDeletedUsers(UserStatus.DELETED);
		if(users.isEmpty()) {
//			 throw new NoUserStausFoundException();
		 }
		 structure.setStatus(HttpStatus.OK.value());
		 structure.setMessage("Fetch All Deleted User Details");
		 structure.setBody(users);
		 return structure;
	}
	
	//******************Find All Terminated Users ****************************************

	public ResponseStructure<List<User>> findAllTerminatedUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findAllTerminatedUsers(UserStatus.TERMINATED);
		if(users.isEmpty()) {
//			 throw new NoUserStausFoundException();
		 }
		 structure.setStatus(HttpStatus.OK.value());
		 structure.setMessage("Fetch All Terminated User Details");
		 structure.setBody(users);
		 return structure;
	}

	//**********************************Search Users By Email Or Name ************
	
	public ResponseStructure<List<User>> searchUsersByEmailOrName(String query) {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List< User> users = userDao.searchUsersByEmailOrName(query);
		if(users.isEmpty()) {
//			throw new NoUsersFoundException();
		}
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All The MAtching User Details Are Fetched" );
		structure.setBody(users);
		return structure;
	}
	
	//*******************************Update USer Status To Active ****************************

	public ResponseStructure<User> updateUserStatusToActive(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setStatus(UserStatus.ACTIVE);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Status To Active");
		structure.setBody(user);
		return structure;
	}
	
	//*****************************Update User Status To In_Active *****************************
	
	public ResponseStructure<User> updateUserStatusToInctive(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setStatus(UserStatus.IN_ACTIVE);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Status To In_Active");
		structure.setBody(user);
		return structure;
	}
	
	//**********************************Update User Status To Blocked ********************************

	public ResponseStructure<User> updateUserStatusToBlocked(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setStatus(UserStatus.BLOCKED);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Status To Blocked");
		structure.setBody(user);
		return structure;
	}
	
	//********************************************Update User Status To Deleted *******************

	public ResponseStructure<User> updateUserStatusToDeleted(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setStatus(UserStatus.DELETED);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Status To Deleted");
		structure.setBody(user);
		return structure;
	}
	
	//*************************************Update User Status To Terminate ****************************

	public ResponseStructure<User> updateUserStatusToTerminated(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setStatus(UserStatus.TERMINATED);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Status To Terminated");
		structure.setBody(user);
		return structure;
	}
	
	//************************************Fetch All USer By Role ****************************

	public ResponseStructure<List<User>> fetchAllRoleUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.fetchAllRoleUsers(UserRole.USER);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Fetch User Details");
		structure.setBody(users);
		return structure;
	}
	
	//*****************************Fetch All Admins By Role ******************************

	public ResponseStructure<List<User>> fetchAllRoleAdmins() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.fetchAllRoleAdmin(UserRole.ADMIN);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Fetch User Details");
		structure.setBody(users);
		return structure;
	}
	
	//****************************Update User Role To User *****************************

	public ResponseStructure<User> updateUserRoleToUser(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setRole(UserRole.USER);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Role To User");
		structure.setBody(user);
		return structure;
	}
	
	//******************************Update User Role To Admin***************************

	public ResponseStructure<User> updateUserRoleToAdmin(Long id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
//			throw new UnableToUpdateUserException();
		}
		User user = optional.get();
		user.setRole(UserRole.ADMIN);
		user = userDao.saveUser(user);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Successfully Updated User Role To admin");
		structure.setBody(user);
		return structure;
	}
	
	//*****************Fetch All Active Male Users***********************************

	public ResponseStructure<List<User>> fetchAllActiveMaleUsers() {
		//ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findByUserGenderAndStatus(UserGender.MALE, UserStatus.ACTIVE);
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("Fetch All Active Males");
//		structure.setBody(null);
		return new ResponseStructure<List<User>>(HttpStatus.OK.value(), "Fetch The All Active Male Users Successfully", users);
	}

	public ResponseStructure<List<User>> fetchAllActiveFemaleUsers() {
		List<User> users = userDao.findByUserGenderAndStatus(UserGender.FEMALE, UserStatus.ACTIVE);
		return new ResponseStructure<List<User>>(HttpStatus.OK.value(), "Fetch The All Active Female Users Successfully", users);
	}

	public ResponseStructure<List<MatchingUser>> findAllMatches(Long id, Integer top) {
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) {
			throw new InvalidUserIdException("Invalid User Id : "+ id +" Unable to find Top Matches ");
		}
		User user = optional.get();
		UserGender gender = user.getGender();
		//System.out.println(gender.equals(UserGender.MALE) ? UserGender.FEMALE : UserGender.MALE);
		
		List<User> users = new ArrayList<>();
		if(gender.equals(UserGender.MALE)) {
			users = userDao.findByGender(UserGender.FEMALE);
		}
		else {
			users = userDao.findByGender(UserGender.MALE);
		}
		
		//printCollection(users);
		
		List<MatchingUser> matchingUsers = new ArrayList<>();
		for(User u : users) {
			MatchingUser mu =  new MatchingUser();
			mu.setName(u.getName());
			mu.setAge(u.getAge());
			mu.setAgeDifference(Math.abs(user.getAge() - u.getAge()));
			mu.setMatchingInterestCount(countInterest(user.getInterests(), u.getInterests()));
			mu.setGender(u.getGender());
			mu.setInterests(u.getInterests());
			matchingUsers.add(mu);
		}
		Collections.sort(matchingUsers, new SortByAgeDifferenceAsc());
		
		matchingUsers = matchingUsers.stream().limit(top).collect(Collectors.toList());
		ptintCollection(matchingUsers);
		
		return new ResponseStructure<List<MatchingUser>>(HttpStatus.OK.value(), "Successfully Fetch All Matching Users", matchingUsers);
	}

	private void ptintCollection(Collection c) {
		for(Object o : c) {
			System.out.println(o);
		}
	}

	private int countInterest(List<String> list1, List<String> list2) {
		int c = 0;
		for(String s : list1) {
			if(list2.contains(s))
			c++;
		}
		return c;
	}
	
}
