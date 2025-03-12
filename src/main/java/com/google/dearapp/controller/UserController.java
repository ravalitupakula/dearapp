package com.google.dearapp.controller;

import java.awt.Taskbar.State;import java.lang.Character.UnicodeScript;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.dearapp.dto.MatchingUser;
import com.google.dearapp.entity.User;
import com.google.dearapp.responsestructure.ResponseStructure;
import com.google.dearapp.service.UserService;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserStatus;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	// 1************************************Saving the User ***********************************

	@PostMapping
	public ResponseStructure<User> saveUser(@RequestBody User u) {
		return userService.saveUser(u);
	}

	//2 ******************************Fetch All Users ******************************************

	@GetMapping
	public ResponseStructure<List<User>> fetchAllUsers() {
		return userService.fetchAllUsers();
	}

	// 3*************************************Find User By Id ***********************************

	@GetMapping("/{id}")
	public ResponseStructure<User> findUserById(@PathVariable(name = "id") Long id) { // (@PathVariable(name = "id") Long id)
		return userService.findUserById(id);
	}

	//4 *************************************Update The User By Id *******************************

	@PatchMapping("/id/{id}")
	public ResponseStructure<User> updateUserById(@RequestBody User u, @PathVariable Long id) {
		return userService.updateUserById(u, id);
	}

	//5 ****************************************Fetch All Male User By Gender********************************
//
//	@GetMapping("/gender/male/{gender}")
//	public ResponseStructure<List<User>> findAllMaleUsers(@PathVariable UserGender gender) {
//		return userService.findAllMaleUSers(gender);
//	}
	
	@GetMapping("/gender/male")
	public ResponseStructure<List<User>> fetchAllMaleUsers(){
		return userService.fetchAllMaleUSers();
	}

	//6 ****************************************Fetch All Female Users By Gender********************************

//	@GetMapping("/gender/female/{gender}")
//	public ResponseStructure<List<User>> findAllFemaleUsers(@PathVariable UserGender gender) {
//		return userService.findAllFemaleUSers(gender);
//	}
//	
	

	@GetMapping("/gender/female")
	public ResponseStructure<List<User>> fetchAllFemaleUsers(){
		return userService.fetchAllFemaleUSers();
	}
	

	// 7****************************************Fetch All Active Users By status********************************

	@GetMapping("/status/active")
	public ResponseStructure<List<User>> findAllActiveUsers() {
		return userService.findAllActiveUsers();
	}
	

	// 8****************************************Fetch All In_Active Users By status********************************

	@GetMapping("/status/inactive")
	public ResponseStructure<List<User>> findAllInctiveUsers() {
		return userService.findAllInctiveUsers();
	}

	

	// 9****************************************Fetch All blocked Users By status********************************

	@GetMapping("/status/blocked")
	public ResponseStructure<List<User>> findAllBlockedUsers() {
		return userService.findAllBlockedUsers();
	}
	

	//10 ****************************************Fetch All Deleted Users By status********************************

	@GetMapping("/status/deleted")
	public ResponseStructure<List<User>> findAllDeletedUsers() {
		return userService.findAllDeletedUsers();
	}
	
	//11 ****************************************Fetch All Terminated Users By status********************************

		@GetMapping("/status/terminated")
		public ResponseStructure<List<User>> findAllTerminatedUsers() {
			return userService.findAllTerminatedUsers();
		}
		
	//12************************************Search Users By Email or Name*********************
		
	@GetMapping("/search/query/{query}")
	public ResponseStructure<List<User>> searchUsersByEmailOrName(@PathVariable String query){
		return userService.searchUsersByEmailOrName(query);
	}
		
		
	//13*******************************Update User Status To Active By id****************
	
	@PatchMapping("/status/active/{id}")
	public ResponseStructure<User> updateUserStatusToActive(@PathVariable(name = "id") Long id){
		return userService.updateUserStatusToActive(id);
	}
	
	//14*******************************Update User Status To In_Active By id****************
	
	@PatchMapping("/status/inactive/{id}")
	public ResponseStructure<User> updateUserStatusToInctive(@PathVariable(name = "id") Long id){
		return userService.updateUserStatusToInctive(id);
	}
	
	//15*******************************Update User Status To Blocked By id****************
	
	@PatchMapping("/status/blocked/{id}")
	public ResponseStructure<User> updateUserStatusToBlocked(@PathVariable(name = "id") Long id){
		return userService.updateUserStatusToBlocked(id);
	}
	
	//16*******************************Update User Status To Deleted By id****************
	
		
	@PatchMapping("/status/deleted/{id}")
	public ResponseStructure<User> updateUserStatusToDeleted(@PathVariable(name = "id") Long id){
		return userService.updateUserStatusToDeleted(id);
	}
	
	//17*******************************Update User Status To Terminated By id****************
	
	
	@PatchMapping("/status/terminated/{id}")
	public ResponseStructure<User> updateUserStatusToTerminated(@PathVariable(name = "id") Long id){
		return userService.updateUserStatusToTerminated(id);
	}
	
	//18*******************************Fetch all USer Details ************************
	
	@GetMapping("/role/user")
	public ResponseStructure<List<User>> fetchAllRoleUsers() {
		return userService.fetchAllRoleUsers();
	}
	
	//19*****************************Fetch All Admin Details **************************
	
	@GetMapping("/role/admin")
	public ResponseStructure<List<User>> fetchAllRoleAdmins() {
		return userService.fetchAllRoleAdmins();
	}
	
	//20*************************************Update USer to User by id*************************
	
	@PatchMapping("/role/user/{id}")
	public ResponseStructure<User> updateUserRoleToUser(@PathVariable(name = "id") Long id){
		return userService.updateUserRoleToUser(id);
	}
	
	//21*************************************Update USer to Admin by id*************************
	
	@PatchMapping("/role/admin/{id}")
	public ResponseStructure<User> updateUserRoleToAdmin(@PathVariable(name = "id") Long id){
		return userService.updateUserRoleToAdmin(id);
	}

	
	//22**************************Fetch All Active Males ************************************
	
	@GetMapping("/active/male")
	public ResponseStructure<List<User>> fetchAllActiveMaleUsers(){
		return userService.fetchAllActiveMaleUsers();
	}
	
	//23******************************Fetch All Active Female ********************************
	

	@GetMapping("/active/female")
	public ResponseStructure<List<User>> fetchAllActiveFemaleUsers(){
		return userService.fetchAllActiveFemaleUsers();
	}
	
	//24******************************Fetch the Matched Users ********************************
	
	@GetMapping("/matches/{id}/{top}") //   /users/matches/2/3
	public ResponseStructure<List<MatchingUser>> findAllMatches(@PathVariable(name = "id") Long id, @PathVariable(name = "top") Integer top){
		return userService.findAllMatches(id, top);
	}
	
	//****************************Send OTP*************************
	
	@PatchMapping("/verifyotp/{id}/{otp}")
	public ResponseStructure<User> verifyOTP(@PathVariable(name = "id") Long id, @PathVariable(name = "otp") int otp){
		return userService.verifyOTP(id, otp);
	}

		
		
		
}
