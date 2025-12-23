package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private static final Map<String, DemoUser> USERS = new HashMap<>();

    static {
        USERS.put("admin@city.com",
                new DemoUser(1L, "Admin", "admin@city.com", "ADMIN", "admin123"));
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        DemoUser user = USERS.get(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(user.getEmail())
                .password("{noop}" + user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (USERS.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }

        DemoUser user = new DemoUser(
                (long) (USERS.size() + 1),
                name,
                email,
                "USER",
                password
        );
        USERS.put(email, user);
        return user;
    }

    public DemoUser getByEmail(String email) {
        return USERS.get(email);
    }

    // Inner class
    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String role;
        private String password;

        public DemoUser(Long id, String name, String email, String role, String password) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.password = password;
        }

        public Long getId() { return id; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getPassword() { return password; }
    }
}
