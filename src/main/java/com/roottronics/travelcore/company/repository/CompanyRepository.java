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

    /**
     * Duplicate Validations
     */
    boolean existsByCompanyCode(String companyCode);

    boolean existsByCompanyName(String companyName);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    /**
     * Future APIs
     */


    List<Company> findAllByStatus( Status status);

}
