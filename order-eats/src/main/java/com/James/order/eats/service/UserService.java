package com.James.order.eats.service;

import com.James.order.eats.model.User;

public interface UserService {
    public User findByJwtToken(String jwt) throws Exception;

    public User findByEmail(String email) throws Exception;
}
