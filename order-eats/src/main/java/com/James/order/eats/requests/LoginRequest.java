package com.James.order.eats.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private  String email;

    private String password;
}
