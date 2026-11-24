
package com.smart_parking.smart_parking.config;

import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.enums.UserRole;
import com.smart_parking.smart_parking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminAccountInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        boolean adminExists =
                userRepository.existsByRole(UserRole.ADMIN);

        if (adminExists) {
            return;
        }

        User admin = new User();
        admin.setName("Parking Admin");
        admin.setEmail(adminEmail);
        admin.setPassword(
                passwordEncoder.encode(adminPassword));
        admin.setMobileNumber("9876543210");
        admin.setRole(UserRole.ADMIN);

        userRepository.save(admin);
    }
}
