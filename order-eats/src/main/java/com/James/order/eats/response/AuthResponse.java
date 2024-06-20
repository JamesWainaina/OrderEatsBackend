package com.James.order.eats.response;

import com.James.order.eats.model.User_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private User_ROLE role;
}
