package org.dataengineering.datatierx.service;

import org.dataengineering.datatierx.dto.CreateDatasetRequest;
import org.dataengineering.datatierx.entity.DatasetMetadata;
import org.dataengineering.datatierx.repository.DatasetMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.dataengineering.datatierx.entity.StorageTier;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;
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

    public DatasetMetadata uploadFile(MultipartFile file) throws IOException {

        Path uploadDir = Paths.get("uploads");

        Files.createDirectories(uploadDir);

        Path targetPath =
                uploadDir.resolve(file.getOriginalFilename());

        Files.copy(
                file.getInputStream(),
                targetPath,
                StandardCopyOption.REPLACE_EXISTING
        );

        long rowCount = 0;

        try (Stream<String> lines = Files.lines(targetPath)) {

            rowCount = Math.max(0, lines.count() - 1);
        }

        DatasetMetadata dataset =
                DatasetMetadata.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType("CSV")
                        .fileSizeBytes(file.getSize())
                        .rowCount(rowCount)
                        .storageTier(StorageTier.COLD)
                        .accessCount(0L)
                        .createdAt(LocalDateTime.now())
                        .storageLocation(targetPath.toString())
                        .build();

        return repository.save(dataset);
    }
}