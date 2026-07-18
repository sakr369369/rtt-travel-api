package com.roottronics.travelcore.document.entity;

import com.roottronics.travelcore.common.entity.BaseEntity;
import com.roottronics.travelcore.common.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "document_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_document_type_code",
                        columnNames = "document_code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentType extends BaseEntity {

    @Column(name = "document_code", nullable = false, length = 50)
    private String documentCode;

    @Column(name = "document_name", nullable = false, length = 150)
    private String documentName;

    @Column(name = "mandatory", nullable = false)
    @Builder.Default
    private Boolean mandatory = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.ACTIVE;

}