package com.atomic.pafyplatform.responses;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
	private String message;
	
	private HttpStatus status;
	
	private boolean success;
}
