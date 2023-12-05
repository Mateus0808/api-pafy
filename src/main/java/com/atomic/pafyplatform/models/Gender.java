package com.atomic.pafyplatform.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
	FEMALE("Female"), MALE("Male"), OTHER("Other");
	
	private String gender;
}
