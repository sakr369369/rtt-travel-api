package com.roottronics.travelcore.company.entity;

import com.roottronics.travelcore.common.entity.BaseEntity;
import com.roottronics.travelcore.common.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "companies")
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity {

    @Column(nullable = false)
    private Long tenantId;

    @Column(nullable = false, unique = true, length = 20)
    private String companyCode;

    @Column(nullable = false, length = 150)
    private String companyName;

    @Column(nullable = false, length = 100)
    private String contactPerson;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String mobile;

    @Column(length = 100)
    private String country;

    @Column(length = 100)
    private String state;

    @Column(length = 100)
    private String city;

    @Column(length = 500)
    private String address;

    @Column(nullable = false)
    private Status status = Status.ACTIVE;

}