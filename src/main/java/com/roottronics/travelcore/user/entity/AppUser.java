package com.roottronics.travelcore.user.entity;

import com.roottronics.travelcore.common.entity.BaseEntity;
import com.roottronics.travelcore.user.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class AppUser extends BaseEntity {

    private Long tenantId;

    private Long companyId;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Boolean enabled = true;

    private Boolean accountNonExpired = true;

    private Boolean accountNonLocked = true;

    private Boolean credentialsNonExpired = true;

}
