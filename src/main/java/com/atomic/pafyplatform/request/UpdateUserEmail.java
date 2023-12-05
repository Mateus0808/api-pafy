package com.atomic.pafyplatform.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserEmail {
	@NotBlank
	private String currentEmail;
	
	@NotBlank
	private String newEmail;
}
