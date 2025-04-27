package com.hydrolink.api.report.model.dto;

public record ReportRequest(
        Long sensorId,
        String type
) {
}
