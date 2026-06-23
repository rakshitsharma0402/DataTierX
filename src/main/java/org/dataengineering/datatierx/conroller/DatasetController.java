package org.dataengineering.datatierx.conroller;

import org.dataengineering.datatierx.dto.CreateDatasetRequest;
import org.dataengineering.datatierx.entity.DatasetMetadata;
import org.dataengineering.datatierx.service.DatasetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/datasets")
@RequiredArgsConstructor
public class DatasetController {

    private final DatasetService datasetService;

    @PostMapping
    public DatasetMetadata createDataset(
            @Valid @RequestBody CreateDatasetRequest request) {

        return datasetService.createDataset(request);
    }

    @GetMapping
    public List<DatasetMetadata> getAllDatasets() {

        return datasetService.getAllDatasets();
    }

    @GetMapping("/{id}")
    public DatasetMetadata getDataset(@PathVariable UUID id) {

        return datasetService.getDataset(id);
    }

    @PostMapping("/upload")
    public DatasetMetadata uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        return datasetService.uploadFile(file);
    }
}