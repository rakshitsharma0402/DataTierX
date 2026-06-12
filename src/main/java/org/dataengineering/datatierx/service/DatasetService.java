package org.dataengineering.datatierx.service;

import org.dataengineering.datatierx.dto.CreateDatasetRequest;
import org.dataengineering.datatierx.entity.DatasetMetadata;
import org.dataengineering.datatierx.repository.DatasetMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatasetService {

    private final DatasetMetadataRepository repository;

    public DatasetMetadata createDataset(CreateDatasetRequest request) {

        DatasetMetadata dataset = DatasetMetadata.builder()
                .fileName(request.getFileName())
                .fileType(request.getFileType())
                .fileSizeBytes(request.getFileSizeBytes())
                .rowCount(request.getRowCount())
                .storageTier("COLD")
                .accessCount(0L)
                .createdAt(LocalDateTime.now())
                .storageLocation("PENDING")
                .build();

        return repository.save(dataset);
    }

    public List<DatasetMetadata> getAllDatasets() {
        return repository.findAll();
    }

    public DatasetMetadata getDataset(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dataset not found"));
    }
}