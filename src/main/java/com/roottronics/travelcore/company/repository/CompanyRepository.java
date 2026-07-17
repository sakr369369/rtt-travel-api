package com.roottronics.travelcore.company.repository;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Find Company
     */
    Optional<Company> findByIdAndTenantId(Long id, Long tenantId);

    Optional<Company> findByCompanyCodeAndTenantId(String companyCode, Long tenantId);

    /**
     * Duplicate Validations
     */
    boolean existsByCompanyCodeAndTenantId(String companyCode, Long tenantId);

    boolean existsByCompanyNameAndTenantId(String companyName, Long tenantId);

    boolean existsByEmailAndTenantId(String email, Long tenantId);

    boolean existsByMobileAndTenantId(String mobile, Long tenantId);

    /**
     * Future APIs
     */
    List<Company> findAllByTenantId(Long tenantId);

    List<Company> findAllByTenantIdAndStatus(Long tenantId, Status status);

}