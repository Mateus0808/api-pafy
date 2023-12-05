package com.atomic.pafyplatform.responses;

import java.util.List;
import java.util.UUID;

import com.atomic.pafyplatform.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
//    private UUID id;
//    private String email;
    private User user;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse(User user, String token, List<String> roles) {
        this.user = user;
        this.token = token;
        this.roles = roles;
    }
}
