package org.dataengineering.datatierx.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dataset_metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatasetMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fileName;

    private String fileType;

    private Long fileSizeBytes;

    private Long rowCount;

    @Enumerated(EnumType.STRING)
    private StorageTier storageTier;

    private Long accessCount;

    private LocalDateTime createdAt;

    private LocalDateTime lastAccessedAt;

    private String storageLocation;
}