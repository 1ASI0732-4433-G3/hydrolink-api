package com.hydrolink.api.monitoring.model.dto.request;

import com.hydrolink.api.monitoring.model.entities.SensorConfig;
import com.hydrolink.api.monitoring.model.enums.SensorType;

public record SensorConfigRequest(
        Double min,
        Double max,
        Double threshold,
        SensorType type
) {
    public SensorConfig toEntity() {
        return SensorConfig.builder()
                .min(min)
                .max(max)
                .threshold(threshold)
                .build();
    }
}
