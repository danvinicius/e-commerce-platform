package com.danvinicius.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.clients.EmailServiceClient;
import com.danvinicius.ecommerce.config.email.content.RegisterUserEmailContent;
import com.danvinicius.ecommerce.dto.email.EmailRequestDTO;
import com.danvinicius.ecommerce.dto.user.UserRequestDTO;
import com.danvinicius.ecommerce.entities.cart.Cart;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.entities.user.UserRole;
import com.danvinicius.ecommerce.exceptions.ForbiddenException;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceClient emailServiceClient;

    private User user() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Boolean userHasPermission(User user) {
        return this.user().getRole().equals(UserRole.ADMIN.getRole()) || user.equals(this.user());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public User getMe() {
        String id = this.user().getId().toString();
        return this.getUserById(id);
    }

    public User getUserById(String id) throws ResourceNotFoundException {
        User user = userRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
        if (!userHasPermission(user)) {
            throw new ForbiddenException();
        }
        ;
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserRequestDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.phone(), data.email(), encryptedPassword, UserRole.USER.getRole());
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        userRepository.save(user);
        // this.sendRegistrationEmail(user);
        return user;
    }

    private void sendRegistrationEmail(User user) {
        String firstName = user.getName().split(" ")[0];
        emailServiceClient.sendEmail(
                new EmailRequestDTO(
                        user.getEmail(),
                        RegisterUserEmailContent.getSubject(),
                        RegisterUserEmailContent.getBody(firstName)));
    }

    public User createAdminUser(UserRequestDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.phone(), data.email(), encryptedPassword, UserRole.ADMIN.getRole());
        return userRepository.save(user);
    }

    public User updateUser(String id, UserRequestDTO data) {
        User user = getUserById(id);
        if (!userHasPermission(user)) {
            throw new ForbiddenException();
        }
        ;
        if (data.name() != null && !data.name().isEmpty()) {
            user.setName(data.name());
        }
        if (data.phone() != null && !data.phone().isEmpty()) {
            user.setPhone(data.phone());
        }
        if (data.password() != null && !data.password().isEmpty()) {
            user.setPassword(data.password());
        }
        userRepository.save(user);
        return user;
    }

    public void deleteUser(String id) {
        User user = getUserById(id);
        if (!userHasPermission(user)) {
            throw new ForbiddenException();
        }
        ;
        if (!user.getRole().equals(UserRole.ADMIN.getRole())) {
            userRepository.delete(user);
        }
    }

    public void deleteAdminUser(String id) {
        User user = getUserById(id);
        if (user.getRole().equals(UserRole.ADMIN.getRole())) {
            userRepository.delete(user);
        }
    }
}
