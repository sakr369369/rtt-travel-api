package com.roottronics.travelcore.tenant.repository;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.tenant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    //Optional<Tenant> findByTenantCode(String tenantCode);

    Optional<Tenant> findByEmployeeCode(String employeeCode);

    Optional<Tenant> findByTenantName(String tenantName);

    Optional<Tenant> findByEmail(String email);

    Optional<Tenant> findByMobile(String mobile);

    //boolean existsByTenantCode(String tenantCode);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByTenantName(String tenantName);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    List<Tenant> findByStatus(Status status);

}