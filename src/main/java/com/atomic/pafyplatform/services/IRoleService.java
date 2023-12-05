package com.atomic.pafyplatform.services;

import java.util.List;
import java.util.UUID;

import com.atomic.pafyplatform.models.Role;
import com.atomic.pafyplatform.models.User;

public interface IRoleService {
	List<Role> getRoles();
	
    Role createRole(Role theRole);

    void deleteRole(UUID id);
    
    Role findByName(String name);

    User removeUserFromRole(UUID userId, UUID roleId);
    
    User assignRoleToUser(UUID userId, UUID roleId);
    
    Role removeAllUsersFromRole(UUID roleId);
}
