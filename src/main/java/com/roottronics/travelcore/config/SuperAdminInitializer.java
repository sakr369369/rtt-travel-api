package com.roottronics.travelcore.config;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.user.entity.AppUser;
import com.roottronics.travelcore.user.enums.UserType;
import com.roottronics.travelcore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SuperAdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final Environment environment;

    @Value("${security.bootstrap.enabled:false}")
    private boolean bootstrapEnabled;

    @Value("${security.bootstrap.email}")
    private String adminEmail;

    @Value("${security.bootstrap.password}")
    private String adminPassword;

    @Override
    public void run(ApplicationArguments args) {

        String activeProfiles =
                String.join(",", environment.getActiveProfiles());

        log.info("Checking SUPER_ADMIN bootstrap | profile={}", activeProfiles);

        if (environment.acceptsProfiles(Profiles.of("prod"))) {

            log.info("Bootstrap skipped for Production");

            return;

        }

        if (!bootstrapEnabled) {

            log.info("Bootstrap disabled.");

            return;

        }

        if (userRepository.existsByUserType(UserType.SUPER_ADMIN)) {

            log.info("SUPER_ADMIN already exists.");

            return;

        }

        AppUser admin = new AppUser();


        //admin.setCompanyId(1L);

        admin.setEmployeeCode("ADMIN001");

        admin.setFirstName("System");

        admin.setLastName("Administrator");

        admin.setEmail(adminEmail);

        admin.setMobile("9999999999");

        admin.setPassword(passwordEncoder.encode(adminPassword));

        admin.setUserType(UserType.SUPER_ADMIN);

        admin.setEnabled(true);

        admin.setAccountNonExpired(true);

        admin.setAccountNonLocked(true);

        admin.setCredentialsNonExpired(true);

        admin.setStatus(Status.ACTIVE);

        userRepository.save(admin);

        log.info("SUPER_ADMIN created successfully.");

    }

}