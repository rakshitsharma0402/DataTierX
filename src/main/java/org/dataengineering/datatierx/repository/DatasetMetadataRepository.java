package org.dataengineering.datatierx.repository;

import org.dataengineering.datatierx.entity.DatasetMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DatasetMetadataRepository
        extends JpaRepository<DatasetMetadata, UUID> {
}