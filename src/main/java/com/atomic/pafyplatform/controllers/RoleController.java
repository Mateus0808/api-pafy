package com.atomic.pafyplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atomic.pafyplatform.exceptions.RoleAlreadyExistException;
import com.atomic.pafyplatform.models.Role;
import com.atomic.pafyplatform.models.User;
import com.atomic.pafyplatform.services.IRoleService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @GetMapping("/all-roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getRoles(), FOUND);
    }

    @PostMapping("/create-new-role")
    public ResponseEntity<String> createRole(@RequestBody Role theRole){
        try{
            roleService.createRole(theRole);
            return ResponseEntity.ok("New role created successfully!");
        }catch(RoleAlreadyExistException re){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{roleId}")
    public void deleteRole(@PathVariable("roleId") UUID roleId){
        roleService.deleteRole(roleId);
    }
    
    @PostMapping("/remove-all-users-from-role/{roleId}")
    public Role removeAllUsersFromRole(@PathVariable("roleId") UUID roleId){
        return roleService.removeAllUsersFromRole(roleId);
    }

    @PostMapping("/remove-user-from-role")
    public User removeUserFromRole(
            @RequestParam("userId") UUID userId,
            @RequestParam("roleId") UUID roleId){
        return roleService.removeUserFromRole(userId, roleId);
    }
    @PostMapping("/assign-user-to-role")
    public User assignUserToRole(
            @RequestParam("userId") UUID userId,
            @RequestParam("roleId") UUID roleId){
        return roleService.assignRoleToUser(userId, roleId);
    }
}
