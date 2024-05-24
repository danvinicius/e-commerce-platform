package com.danvinicius.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.user.UserRequestDTO;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.entities.user.UserRole;
import com.danvinicius.ecommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public User createUser(UserRequestDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.email(), encryptedPassword, UserRole.USER.getRole());
        return userRepository.save(user);
    }

    public User createAdminUser(UserRequestDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.email(), encryptedPassword, UserRole.ADMIN.getRole());
        System.out.println(user);
        return userRepository.save(user);
    }
}
