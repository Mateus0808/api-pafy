package com.atomic.pafyplatform.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserPassword {
	@NotBlank
	private String currentPassword;
	
	@NotBlank
	private String newPassword;
	
	@NotBlank
	private String confirmPassword;
}
