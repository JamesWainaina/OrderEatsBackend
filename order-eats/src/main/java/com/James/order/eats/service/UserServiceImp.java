package com.James.order.eats.service;

import com.James.order.eats.config.JwtProvider;
import com.James.order.eats.model.User;
import com.James.order.eats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findByEmail(email);
        return user;
    }

    @Override
    public User findByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
