package com.atomic.pafyplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.atomic.pafyplatform.exceptions.InvalidCredentials;
import com.atomic.pafyplatform.models.Gender;
import com.atomic.pafyplatform.models.User;
import com.atomic.pafyplatform.request.UpdateUser;
import com.atomic.pafyplatform.request.UpdateUserEmail;
import com.atomic.pafyplatform.request.UpdateUserPassword;
import com.atomic.pafyplatform.responses.ResponseMessage;
import com.atomic.pafyplatform.responses.UserResponse;
import com.atomic.pafyplatform.services.IUserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        try{
            User theUser = this.userService.getUser(email);
            return ResponseEntity.ok(theUser);
        }catch (UsernameNotFoundException e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND, false));
        }catch (Exception e){
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Erro ao buscar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false));
        }
    }
    
    @GetMapping("/get-by-id/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable UUID userId){
        try{
            User theUser = this.userService.getUserById(userId);
            return ResponseEntity.ok(theUser);
        }catch (UsernameNotFoundException e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND, false));
        }catch (Exception e){
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Erro ao buscar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false));
        }
    }
    
    @PutMapping("/update/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #userId == principal.id)")
	public ResponseEntity<?> updateUser(
			@PathVariable UUID userId, 
			@RequestBody UpdateUser data) {
    	try {
    		UserResponse updatedUser = new UserResponse(data.getFirstName(), data.getLastName(), data.getUsername(), data.getGender());
    		User user = userService.updateUser(userId, updatedUser);
    		return ResponseEntity.ok().body(user);
    	} catch (IllegalArgumentException ex) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage(), HttpStatus.NOT_FOUND, false));
    	} catch (Exception ex) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, false));
    	}
    }
    
    @PutMapping("/update-email/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #userId == principal.id)")
	public ResponseEntity<?> updateUserEmail(
			@PathVariable UUID userId, 
			@RequestBody UpdateUserEmail data) {
    	try {
    		User user = userService.updateUserEmail(userId, data);
    		return ResponseEntity.ok().body(user);
    	} catch (IllegalArgumentException ex) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage(), HttpStatus.NOT_FOUND, false));
    	} catch (UsernameNotFoundException e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND, false));
    	} catch (Exception ex) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, false));
    	}
    }
    
    @PutMapping("/update-password/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #userId == principal.id)")
	public ResponseEntity<?> updateUserPassword(
			@PathVariable UUID userId, 
			@RequestBody UpdateUserPassword data) {
    	try {
    		userService.updateUserPassword(userId, data);
    		return ResponseEntity.ok().body(new ResponseMessage("Senha atualizada com sucesso", HttpStatus.OK, true));
    	} catch (IllegalArgumentException ex) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, false));
    	} catch (InvalidCredentials ex) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, false));
    	} catch (Exception ex) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, false));
    	}
    }
    
    @DeleteMapping("/delete/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #email == principal.user.getEmail())")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email){
        try{
            this.userService.deleteUser(email);
            return ResponseEntity.ok().body(new ResponseMessage("Usuário deletado com sucesso", HttpStatus.OK, true));
        }catch (UsernameNotFoundException e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND, false));
        }catch (Exception e){
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Erro ao deletar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false));
        }
    }
}