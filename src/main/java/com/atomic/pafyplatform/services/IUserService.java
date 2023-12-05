package com.atomic.pafyplatform.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import com.atomic.pafyplatform.exceptions.InvalidCredentials;
import com.atomic.pafyplatform.models.User;
import com.atomic.pafyplatform.request.UpdateUserEmail;
import com.atomic.pafyplatform.request.UpdateUserPassword;
import com.atomic.pafyplatform.responses.UserResponse;

public interface IUserService {
	User registerUser(User user);
	
    List<User> getUsers();
    
    void deleteUser(String email);
    
    User getUser(String email);
    
    User getUserById(UUID id);

	User updateUser(UUID userId, UserResponse updatedUser);

	User updateUserEmail(UUID userId, UpdateUserEmail data);

	User updateUserPassword(UUID userId, UpdateUserPassword user) throws InvalidCredentials;
}
