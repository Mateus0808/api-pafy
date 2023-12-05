package com.atomic.pafyplatform.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atomic.pafyplatform.exceptions.InvalidCredentials;
import com.atomic.pafyplatform.exceptions.UserAlreadyExistsException;
import com.atomic.pafyplatform.models.Role;
import com.atomic.pafyplatform.models.User;
import com.atomic.pafyplatform.repositories.RoleRepository;
import com.atomic.pafyplatform.repositories.UserRepository;
import com.atomic.pafyplatform.request.UpdateUserEmail;
import com.atomic.pafyplatform.request.UpdateUserPassword;
import com.atomic.pafyplatform.responses.UserResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    
    @Override
    public User registerUser(User user) {
    	boolean myUser = userRepository.findByEmail(user.getEmail()).isPresent();
    	
    	if (myUser) 
    		throw new UserAlreadyExistsException("Email já existe");
        
        if (userRepository.findByUsername(user.getUsername()).isPresent())
        	throw new UserAlreadyExistsException("Nome de usuário já existe");
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

	@Override
	public User getUserById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}

	@Override
	public User updateUser(UUID userId, UserResponse updatedUser) {
		User user = this.getUserById(userId);
		
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setUsername(updatedUser.getUsername());
		user.setGender(updatedUser.getGender());
		
		return this.userRepository.save(user);
	}

	@Override
	public User updateUserEmail(UUID userId, UpdateUserEmail data) {
		User user = this.getUserById(userId);
		
		this.getUser(data.getCurrentEmail());
		boolean newUser = userRepository.findByEmail(data.getNewEmail()).isPresent();
		if(newUser) 
			new UsernameNotFoundException("O email já existe");
		
		user.setEmail(data.getNewEmail());
		
		return this.userRepository.save(user);
	}
	
    @Override
    public User updateUserPassword(UUID userId, UpdateUserPassword userPassword) throws InvalidCredentials {
    	User user = this.getUserById(userId);
    	
    	boolean pass = this.passwordEncoder.matches(userPassword.getCurrentPassword(), user.getPassword());
    	if(!pass) throw new InvalidCredentials("Senha atual não confere");
    	
    	if(!userPassword.getNewPassword().trim().equals(userPassword.getConfirmPassword().trim())) {
    		throw new InvalidCredentials("As senhas não conferem");
    	}
        user.setPassword(passwordEncoder.encode(userPassword.getNewPassword()));
        return userRepository.save(user);
    }
}
