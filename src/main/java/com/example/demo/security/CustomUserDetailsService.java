package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, DemoUser> users = new HashMap<>();

    public CustomUserDetailsService() {
        users.put("admin@city.com",
                new DemoUser("Admin", "admin@city.com", "admin123", "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        DemoUser user = users.get(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(user.getEmail())
                .password("{noop}" + user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }
        DemoUser user = new DemoUser(name, email, password, "USER");
        users.put(email, user);
        return user;
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    public static class DemoUser {
        private final String name;
        private final String email;
        private final String password;
        private final String role;

        public DemoUser(String name, String email, String password, String role) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }
}
