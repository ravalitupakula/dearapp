package com.google.dearapp.entity;

import java.util.List;

import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserRole;
import com.google.dearapp.util.UserStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Long phone;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserGender gender = UserGender.FEMALE;
	private Integer age;
	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER;
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ACTIVE;
	@ElementCollection
	private List<String> interests;
	
}


/* Plan
 * Develop
 * Design
 * Documentation
 * 
 * 
 * 
 * Method Type			EndPoint							Requested Data			Responses			Comments 
 * 
 * POST					/users								{}										
 * 
 * GET					/users
 * 
 * GET					{/users/status/active
 * 
 * GET					/users/status/inactive
 * 
 * GET					/users/status/deleted
 * 
 * GET					/users/status/blocked
 * 
 * GET					/users/status/terminated}
 * 						replace the above 5 end points
 * 						only one way that is 
 * 						/users/status/{status}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
 