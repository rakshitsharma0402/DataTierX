package org.dataengineering.datatierx.service;

import org.dataengineering.datatierx.entity.StorageTier;
import org.springframework.stereotype.Service;

@Service
public class ClassificationService {

    public StorageTier classify(long fileSizeBytes) {

        long sizeInMb = fileSizeBytes / (1024 * 1024);

        if (sizeInMb < 10) {
            return StorageTier.HOT;
        }

        if (sizeInMb < 100) {
            return StorageTier.WARM;
        }

        return StorageTier.COLD;
    }
}