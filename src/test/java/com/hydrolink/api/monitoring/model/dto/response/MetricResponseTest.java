package com.hydrolink.api.monitoring.model.dto.response;

import com.hydrolink.api.monitoring.model.entities.Metric;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MetricResponseTest {

    @Test
    void shouldConvertMetricEntityToResponseCorrectly() {
        // Arrange
        Metric metric = Metric.builder()
                .value(10.0)
                .unit("kg")
                .sensor(null)
                .timestamp(java.time.LocalDateTime.parse("2023-10-01T12:00"))
                .build();

        // Act
        MetricResponse response = MetricResponse.fromEntity(metric);

        // Assert
        assertEquals("10.0 kg", response.metric());
        assertEquals("2023-10-01T12:00", response.timestamp());
    }

    @Test
    void shouldConvertMetricEntityListToResponseList() {
        // Arrange
        Metric metric1 = Metric.builder()
                .value(10.0)
                .unit("kg")
                .timestamp(LocalDateTime.parse("2023-10-01T12:00:00"))
                .sensor(null)
                .build();

        Metric metric2 = Metric.builder()
                .value(5.5)
                .unit("g")
                .timestamp(LocalDateTime.parse("2023-10-01T13:00:00"))
                .sensor(null)
                .build();

        List<Metric> metrics = List.of(metric1, metric2);

        // Act
        List<MetricResponse> responses = MetricResponse.fromEntityList(metrics);

        // Assert
        assertEquals(2, responses.size());
        assertEquals("10.0 kg", responses.get(0).metric());
        assertEquals("2023-10-01T12:00", responses.get(0).timestamp());
        assertEquals("5.5 g", responses.get(1).metric());
        assertEquals("2023-10-01T13:00", responses.get(1).timestamp());
    }

}
