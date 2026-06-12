package org.dataengineering.datatierx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDatasetRequest {

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileType;

    @NotNull
    private Long fileSizeBytes;

    @NotNull
    private Long rowCount;
}