package org.abhishek.many_to_many.config;

import org.abhishek.many_to_many.model.AppUser;
import org.abhishek.many_to_many.model.Role;
import org.abhishek.many_to_many.repository.RoleRepository;
import org.abhishek.many_to_many.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Role admin = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_ADMIN").build()));
        Role user = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").build()));

        if (!userRepository.existsByUsername("admin")) {
            AppUser adminUser = AppUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .enabled(true)
                    .roles(new HashSet<>())
                    .build();
            adminUser.getRoles().add(admin);
            adminUser.getRoles().add(user);
            userRepository.save(adminUser);
            log.info("Created default admin user");
        }

        if (!userRepository.existsByUsername("user")) {
            AppUser normal = AppUser.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .enabled(true)
                    .roles(new HashSet<>())
                    .build();
            normal.getRoles().add(user);
            userRepository.save(normal);
            log.info("Created default user");
        }
    }
}


