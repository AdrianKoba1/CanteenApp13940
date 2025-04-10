package com.example.universityCanteenManagement.component;

import com.example.universityCanteenManagement.domain.User;
import com.example.universityCanteenManagement.enums.Role;
import com.example.universityCanteenManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception{

        if (initMode.equals("always")) {

            User owner = new User();
            owner.setUsername("Abdurakhmon");
            owner.setPassword(passwordEncoder.encode("123456"));
            owner.setEmail("abdurakhmonkomilov480@gmail.com");
            owner.setRole(Role.OWNER);
            userRepository.save(owner);

            User admin = new User();
            admin.setUsername("Ravshan");
            admin.setPassword(passwordEncoder.encode("1234567"));
            admin.setEmail("ravshantuymurodov39@gmail.com");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

        }

    }
}
