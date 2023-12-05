package com.atomic.pafyplatform.responses;

import java.math.BigDecimal;
import java.util.Date;

import com.atomic.pafyplatform.models.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
	private Long id;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthDate;
	
	private BigDecimal annualIncome;
	
	private String email;
	
	private Gender gender;
	
	public UserResponse(String firstName, String lastName, String username, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.username = username;
	}
}
