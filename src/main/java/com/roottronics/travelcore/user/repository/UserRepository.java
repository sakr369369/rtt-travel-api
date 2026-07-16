package com.roottronics.travelcore.user.repository;

import com.roottronics.travelcore.user.entity.AppUser;
import com.roottronics.travelcore.user.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByUserType(UserType userType);

}
